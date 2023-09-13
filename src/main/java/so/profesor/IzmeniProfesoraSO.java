package so.profesor;

import java.util.ArrayList;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Profesor;
import so.OpstaSistemskaOperacija;

/**
 * Sistemska operacija za izmenu podataka postojeceg profesora u bazi podataka.
 * 
 * @author Anja
 *
 */
public class IzmeniProfesoraSO extends OpstaSistemskaOperacija{
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase Profesor
	 * i da li postoji profesor sa prosledjenim mejlom vec u bazi.
	 * 
	 * @throws Exception ukoliko objekat nije instanca klase Profesor ili ukoliko vec postoji profesor sa prosledjenim mejlom.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Profesor)) {
			throw new Exception("Dati objekat nije instanca klase Profesor.");
		}
		
		Profesor p = (Profesor) odo;
		
		ArrayList<Profesor> profesori = (ArrayList<Profesor>) (ArrayList<?>) DBBroker.getInstance().select(new Profesor());

		for (Profesor profesor : profesori) {
			if (profesor.getEmail().equals(p.getEmail())) {
				throw new Exception("Vec postoji profesor sa datim email-om.");
			}
		}
		
	}
	/**
	 * Vrsi izmenu podataka o profesoru, tako sto poziva DBBrokera 
	 * da izvrsi UPDATE upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().update(odo);
	}

}
