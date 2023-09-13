package so.odeljenje;

import db.DBBroker;
import domen.Odeljenje;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za brisanje odredjenog odeljenja iz baze podataka.
 * 
 * @author Anja
 *
 */
public class ObrisiOdeljenjeSO extends OpstaSistemskaOperacija {
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase Odeljenje.
	 * .
	 * @throws Exception ukoliko objekat nije instanca klase Odeljenje.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Odeljenje)) {
			throw new Exception("Dati objekat nije instanca klase Odeljenje.");
		}
	}
	/**
	 * Vrsi brisanje prosledjenog odeljenja u bazi podataka, tako sto
	 * poziva DBBrokera da izvrsi DELETE upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().delete(odo);
	}

}
