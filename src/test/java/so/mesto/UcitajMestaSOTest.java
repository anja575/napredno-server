package so.mesto;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Mesto;
import domen.Smer;

class UcitajMestaSOTest {

	UcitajMestaSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajMestaSO();
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
			so.izvrsavanje(new Mesto());
			List<Mesto> mesta = so.getLista();
			assertNotNull(mesta);
			assertFalse(mesta.isEmpty());
			assertEquals(3, mesta.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
