package so.profesor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Profesor;
import domen.Skola;
import domen.Smer;

class ObrisiProfesoraSOTest {
	
	ObrisiProfesoraSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new ObrisiProfesoraSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	public void testNeuspesnaValidacijaRazlicitaKlasa() {
		assertThrows(Exception.class, () -> so.validacija(new Smer()));
	}
	
	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> so.validacija(null));
	}
	
	@Test
	void testUspesnoBrisanjeProfesora() {
		Profesor profesor = new Profesor();
		Skola skola = new Skola();
		
		skola.setSkolaID(1L);
		skola.setNaziv("Ekonomska skola");
		skola.setAdresa("Bulevar Kralja Aleksandra 192");
		
		profesor.setIme("Filip");
		profesor.setPrezime("Filipovic");
		profesor.setEmail("filip@gmail.com");
		profesor.setSkola(skola);
		
		dodajProfesora(profesor);
		
		ArrayList<Profesor> profesori = ucitajSveProfesore();
		int brojProfesoraPreBrisanja = profesori.size();
		
		for(Profesor p : profesori) {
			if(p.getEmail().equals(profesor.getEmail())) {
				profesor.setProfesorID(p.getProfesorID());
			}
		}

		try {
			so.izvrsavanje(profesor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		profesori = ucitajSveProfesore();
		
		assertEquals(brojProfesoraPreBrisanja - 1, profesori.size());
		assertFalse(profesori.contains(profesor));
	}

	private ArrayList<Profesor> ucitajSveProfesore() {
		try {
			UcitajProfesoreSO upso = new UcitajProfesoreSO();
			upso.izvrsavanje(new Profesor());
			return upso.getLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void dodajProfesora(Profesor profesor) {
		try {
			new DodajProfesoraSO().izvrsavanje(profesor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
