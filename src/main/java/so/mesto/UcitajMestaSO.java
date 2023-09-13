package so.mesto;

import java.util.ArrayList;
import db.DBBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za ucitavanje svih mesta iz baze podataka.
 * 
 * @author Anja
 *
 */
public class UcitajMestaSO extends OpstaSistemskaOperacija {
	/**
	 * Lista sa svim mestima.
	 */
	private ArrayList<Mesto> lista;
	/**
	 * Proverava da li je poslati objekat klase Mesto.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Mesto.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Mesto)) {
			throw new Exception("Dati objekat nije instanca klase Mesto.");
		}
	}
	/**
	 * Poziva DBBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, 
	 * gde se kao rezultat dobija lista svih mesta.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> mesta = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Mesto>) (ArrayList<?>) mesta;
	}
	/**
	 * Vraca listu sa svim mestima.
	 * 
	 * @return lista svih mesta.
	 */
	public ArrayList<Mesto> getLista() {
		return lista;
	}

}
