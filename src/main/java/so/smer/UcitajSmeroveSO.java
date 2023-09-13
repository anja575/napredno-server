package so.smer;

import java.util.ArrayList;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smer;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za ucitavanje svih smerova iz baze podataka.
 * 
 * @author Anja
 *
 */
public class UcitajSmeroveSO extends OpstaSistemskaOperacija{
	/**
	 * Lista sa svim smerovima.
	 */
	private ArrayList<Smer> lista;
	/**
	 * Proverava da li je poslati objekat klase Smer.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Smer.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Smer)) {
			throw new Exception("Dati objekat nije instanca klase Smer.");
		}
	}
	/**
	 * Poziva DBBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, 
	 * gde se kao rezultat dobija lista svih smerova.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> smerovi = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Smer>) (ArrayList<?>) smerovi;
	}
	/**
	 * Vraca listu sa svim smerovima.
	 * 
	 * @return lista svih smerova.
	 */
	public ArrayList<Smer> getLista() {
		return lista;
	}

}
