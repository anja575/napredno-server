package so.odeljenje;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Korisnik;
import domen.Mesto;
import domen.Odeljenje;
import domen.Pol;
import domen.Skola;
import domen.Smer;
import domen.Ucenik;

class ObrisiOdeljenjeSOTest {
	
	ObrisiOdeljenjeSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new ObrisiOdeljenjeSO();
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
	void testUspesnoObrisanoOdeljenje() {
		Odeljenje odeljenje = new Odeljenje();
		
		odeljenje.setNaziv("I-2");
		odeljenje.setKorisnik(new Korisnik(1, "Anja", "Cirkovic", "anja", "anja12345"));
		odeljenje.setSkola(new Skola(1, "Ekonomska skola", "Bulevar Kralja Aleksandra 192"));
		odeljenje.setSmer(new Smer(4, "ekonomski tehnicar"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date datum = null;
		try {
			datum = sdf.parse("20.09.2000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mesto m = new Mesto(1, "Priboj", "31330");
		
		Ucenik u1 = new Ucenik(odeljenje, 1, "Anja", "Cirkovic", datum, "Radnicka", "30", m, Pol.Zenski);          
		Ucenik u2 = new Ucenik(odeljenje, 2, "Mara", "Maric", datum, "Narodnih heroja", "48", m, Pol.Zenski);
		Ucenik u3 = new Ucenik(odeljenje, 3, "Mihailo", "Mihailovic", datum, "Takovska", "50", m, Pol.Muski);
		Ucenik u4 = new Ucenik(odeljenje, 4, "Igor", "Igor", datum, "Humska", "37", m, Pol.Muski);
		Ucenik u5 = new Ucenik(odeljenje, 5, "Eva", "Vidakovic", datum, "Tosin bunar", "147", m, Pol.Zenski);
		
		ArrayList<Ucenik> ucenici = new ArrayList<>();
		ucenici.add(u1);
		ucenici.add(u2);
		ucenici.add(u3);
		ucenici.add(u4);
		ucenici.add(u5);
		
		odeljenje.setUcenici(ucenici);
		
		try {
			new DodajOdeljenjeSO().izvrsavanje(odeljenje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Odeljenje> odeljenja = ucitajOdeljenja();
		int brojOdeljenjaPreBrisanja = odeljenja.size();
		
		for (Odeljenje o : odeljenja) {
			if (o.getNaziv().equals(odeljenje.getNaziv())) {
				odeljenje.setOdeljenjeID(o.getOdeljenjeID());
			}
		} 
        
		
		try {
			so.izvrsavanje(odeljenje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		odeljenja = ucitajOdeljenja();
		

		assertEquals(brojOdeljenjaPreBrisanja - 1, odeljenja.size());
		assertFalse(odeljenja.contains(odeljenje));		
	}

	private ArrayList<Odeljenje> ucitajOdeljenja() {
		try {
			UcitajOdeljenjaSO so = new UcitajOdeljenjaSO();
			so.izvrsavanje(new Odeljenje());
			return so.getLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
