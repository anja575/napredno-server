package so.skola;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Skola;
import domen.Smer;

class UcitajSkoleSOTest {
	
	UcitajSkoleSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajSkoleSO();
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
	public void testUspesnoUcitanaMesta() {
		try {
			so.izvrsavanje(new Skola());
			List<Skola> skole = so.getLista();
			assertNotNull(skole);
			assertFalse(skole.isEmpty());
			assertEquals(3, skole.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
