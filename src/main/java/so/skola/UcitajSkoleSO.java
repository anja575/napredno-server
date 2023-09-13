package so.skola;

import java.util.ArrayList;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Skola;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za ucitavanje svih skola iz baze podataka.
 * 
 * @author Anja
 *
 */
public class UcitajSkoleSO extends OpstaSistemskaOperacija{
	/**
	 * Lista sa svim skolama.
	 */
	private ArrayList<Skola> lista;
	/**
	 * Proverava da li je poslati objekat klase Skola.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Skola.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Skola)) {
			throw new Exception("Dati objekat nije instanca klase Skola.");
		}
	}
	/**
	 * Poziva DBBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, 
	 * gde se kao rezultat dobija lista svih skola.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> skole = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Skola>) (ArrayList<?>) skole;
	}
	/**
	 * Vraca listu sa svim skolama.
	 * 
	 * @return lista svih skolama.
	 */
	public ArrayList<Skola> getLista() {
		return lista;
	}

}
