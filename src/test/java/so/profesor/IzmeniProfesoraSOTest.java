package so.profesor;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import db.DBBroker;
import domen.Profesor;
import domen.Skola;
import domen.Smer;

class IzmeniProfesoraSOTest {
	
	IzmeniProfesoraSO so;
	
	@BeforeEach
	void setUp() throws Exception {
		so = new IzmeniProfesoraSO();
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
	public void testUspesnaIzmenaProfesora() {
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
			new DodajProfesoraSO().izvrsavanje(profesor);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		ArrayList<Profesor> profesori = ucitajProfesore();
		
		for(Profesor p : profesori) {
			if(p.getEmail().equals(profesor.getEmail())) {
				profesor.setProfesorID(p.getProfesorID());
			}
		}
		
		profesor.setIme("Marko");
		profesor.setEmail("marko55@gmail.com");

		
		try {
			so.izvrsavanje(profesor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		profesori = ucitajProfesore();
		for(Profesor p : profesori) {
			if(p.getProfesorID() == profesor.getProfesorID()) {
				assertTrue(p.getEmail().equals(profesor.getEmail()));
				assertTrue(p.getIme().equals(profesor.getIme()));
				break;
			}
		}
		
		obrisiProfesora(profesor);
		
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
			DBBroker.getInstance().getConnection().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testNeuspesnaValidacijaPostojiEmail() {
		Profesor profesor = new Profesor();
		Skola skola = new Skola();
		
		skola.setSkolaID(1L);
		skola.setNaziv("Ekonomska skola");
		skola.setAdresa("Bulevar Kralja Aleksandra 192");
		
		profesor.setIme("Filip");
		profesor.setPrezime("Filipovic");
		profesor.setEmail("filip@gmail.com");
		profesor.setSkola(skola);
		
		String emailPrvi = profesor.getEmail();
		
		Profesor profesor2 = new Profesor();
		profesor2.setIme("Milan");
		profesor2.setPrezime("Milanovic");
		profesor2.setEmail("milan@gmail.com");
		profesor2.setSkola(skola);
		
		
		try {
			new DodajProfesoraSO().izvrsavanje(profesor);
			new DodajProfesoraSO().izvrsavanje(profesor2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Profesor> profesori = ucitajProfesore();
		
		for (Profesor p : profesori) {
			if(profesor.getEmail().equals(p.getEmail())) {
				profesor.setProfesorID(p.getProfesorID());
			}
			if(profesor2.getEmail().equals(p.getEmail())) {
			profesor2.setProfesorID(p.getProfesorID());
			}
		}
		
		profesor.setEmail(profesor2.getEmail());


		assertThrows(Exception.class, () -> so.izvrsavanje(profesor));

		profesor.setEmail(emailPrvi);

		obrisiProfesora(profesor);
		obrisiProfesora(profesor2);
		
	}
	
}
