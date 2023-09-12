package so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Korisnik;
import domen.Smer;

class PrijavaKorisnikaSOTest {
	
	PrijavaKorisnikaSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new PrijavaKorisnikaSO();
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
	public void testNePostojiKorisnik() {
		Korisnik korisnik = new Korisnik();
		korisnik.setUsername("marija");
		korisnik.setPassword("marija12345");
	
		assertThrows(Exception.class, () -> so.izvrsavanje(korisnik));
	}
	
	@Test
	public void testUspesnaPrijava() {
		Korisnik korisnik = new Korisnik();
		korisnik.setUsername("anja");
		korisnik.setPassword("anja12345");
		
		try {
			so.izvrsavanje(korisnik);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(korisnik.getUsername(), so.getKorisnik().getUsername());
		assertEquals(korisnik.getPassword(), so.getKorisnik().getPassword());
	}

}
