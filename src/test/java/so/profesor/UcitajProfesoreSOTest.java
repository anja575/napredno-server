package so.profesor;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Profesor;
import domen.Smer;

class UcitajProfesoreSOTest {
	
	UcitajProfesoreSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajProfesoreSO();
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
			so.izvrsavanje(new Profesor());
			List<Profesor> profesori = so.getLista();
			assertNotNull(profesori);
			assertFalse(profesori.isEmpty());
			assertEquals(6, profesori.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
