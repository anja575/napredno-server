package so.smer;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Skola;
import domen.Smer;

class UcitajSmeroveSOTest {
	
	UcitajSmeroveSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajSmeroveSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	public void testNeuspesnaValidacijaRazlicitaKlasa() {
		assertThrows(Exception.class, () -> so.validacija(new Skola()));
	}
	
	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> so.validacija(null));
	}
	
	@Test
	public void testUspesnoUcitaniSmerovi() {
		try {
			so.izvrsavanje(new Smer());
			List<Smer> smerovi = so.getLista();
			assertNotNull(smerovi);
			assertFalse(smerovi.isEmpty());
			assertEquals(9, smerovi.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
