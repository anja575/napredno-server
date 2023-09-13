package so.odeljenje;

import java.util.ArrayList;
import db.DBBroker;
import domen.Odeljenje;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za ucitavanje svih odeljenja iz baze podataka.
 * 
 * @author Anja
 *
 */
public class UcitajOdeljenjaSO extends OpstaSistemskaOperacija{
	/**
	 * Lista sa svim odeljenjima.
	 */
	private ArrayList<Odeljenje> lista;
	/**
	 * Proverava da li je poslati objekat klase Odeljenje.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Odeljenje.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Odeljenje)) {
			throw new Exception("Dati objekat nije instanca klase Odeljenje.");
		}
	}
	/**
	 * Poziva DBBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, 
	 * gde se kao rezultat dobija lista svih odeljenja.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> odeljenja = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Odeljenje>) (ArrayList<?>) odeljenja;
	}
	/**
	 * Vraca listu sa svim odeljenjima.
	 * 
	 * @return lista svih odeljenja.
	 */
	public ArrayList<Odeljenje> getLista() {
		return lista;
	}

}
