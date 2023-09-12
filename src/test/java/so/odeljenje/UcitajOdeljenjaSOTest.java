package so.odeljenje;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Odeljenje;
import domen.Smer;

class UcitajOdeljenjaSOTest {
	
	UcitajOdeljenjaSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajOdeljenjaSO();
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
	public void testUspesnoUcitaniProfesori() {
		try {
			so.izvrsavanje(new Odeljenje());
			List<Odeljenje> odeljenja = so.getLista();
			assertNotNull(odeljenja);
			assertFalse(odeljenja.isEmpty());
			assertEquals(3, odeljenja.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
