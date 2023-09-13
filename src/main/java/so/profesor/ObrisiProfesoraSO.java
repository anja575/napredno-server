package so.profesor;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Profesor;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za brisanje odredjenog profesora iz baze podataka.
 * 
 * @author Anja
 *
 */
public class ObrisiProfesoraSO extends OpstaSistemskaOperacija{
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase Profesor.
	 * .
	 * @throws Exception ukoliko objekat nije instanca klase Profesor.
	 */
	@Override
    protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Profesor)) {
            throw new Exception("Dati objekat nije instanca klase Profesor.");
        }
    }
	/**
	 * Vrsi brisanje prosledjenog profesora u bazi podataka, tako sto
	 * poziva DBBrokera da izvrsi DELETE upit.
	 */
    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().delete(odo);
    }

}
