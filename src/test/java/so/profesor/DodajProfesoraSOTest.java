package so.profesor;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Profesor;
import domen.Skola;
import domen.Smer;

class DodajProfesoraSOTest {
	
	DodajProfesoraSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new DodajProfesoraSO();
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
	public void testUspesnoDodavanjeProfesora() {
		ArrayList<Profesor> profesori = ucitajProfesore();
		int brojProfesoraPreDodavanja = profesori.size();
		
		Profesor profesor = new Profesor();
		Skola skola = new Skola();
		
		skola.setSkolaID(1L);
		skola.setNaziv("Ekonomska skola");
		skola.setAdresa("Bulevar Kralja Aleksandra 192");
		
		profesor.setIme("Filip");
		profesor.setPrezime("Filipovic");
		profesor.setEmail("filip@gmail.com");
		profesor.setSkola(skola);
		
		try {
			so.izvrsavanje(profesor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		profesori = ucitajProfesore();
		
		boolean postoji=false;
		for(Profesor p : profesori) {
			if(p.getEmail().equals(profesor.getEmail())) {
				postoji = true;
				break;
			}
		}
		
		assertEquals((brojProfesoraPreDodavanja + 1), profesori.size());
		assertEquals(true, postoji);
		
		for(int i = 0;i < profesori.size();i++) {
			if(profesori.get(i).getEmail().equals(profesor.getEmail())) {
				obrisiProfesora(profesori.get(i));
			}
		}
		
	}

	private ArrayList<Profesor> ucitajProfesore() {
		try {
			UcitajProfesoreSO upso = new UcitajProfesoreSO();
			upso.izvrsavanje(new Profesor());
			return upso.getLista();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

	private void obrisiProfesora(Profesor profesor) {
		ObrisiProfesoraSO opso = new ObrisiProfesoraSO();
		try {
			opso.izvrsavanje(profesor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Test
	public void testNeuspesnaValidacijaPostojiEmail() {
		Profesor profesor = new Profesor();
		Skola skola = new Skola();
		
		skola.setSkolaID(1L);
		skola.setNaziv("Ekonomska skola");
		skola.setAdresa("Bulevar Kralja Aleksandra 192");
		
		profesor.setIme("Filip");
		profesor.setPrezime("Filipovic");
		profesor.setEmail("filip@gmail.com");
		profesor.setSkola(skola);

		String prvoPrezime = profesor.getPrezime();

		try {
			so.izvrsavanje(profesor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		profesor.setPrezime("Petrovic");

		assertThrows(Exception.class, () -> so.izvrsavanje(profesor));

		profesor.setPrezime(prvoPrezime);
		
        ArrayList<Profesor> profesori = ucitajProfesore();
		
		for(int i = 0;i < profesori.size();i++) {
			if(profesori.get(i).getEmail().equals(profesor.getEmail())) {
				obrisiProfesora(profesori.get(i));
			}
		}
	}
	

}
