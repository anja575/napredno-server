package so.profesor;

import java.util.ArrayList;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Profesor;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za ucitavanje svih profesora iz baze podataka.
 * 
 * @author Anja
 *
 */
public class UcitajProfesoreSO extends OpstaSistemskaOperacija{
	/**
	 * Lista sa svim profesorima.
	 */
	private ArrayList<Profesor> lista;
	/**
	 * Proverava da li je poslati objekat klase Profesor.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Profesor.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Profesor)) {
			throw new Exception("Dati objekat nije instanca klase Profesor.");
		}
	}
	/**
	 * Poziva DBBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, 
	 * gde se kao rezultat dobija lista svih profesora.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> profesori = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Profesor>) (ArrayList<?>) profesori;
	}
	/**
	 * Vraca listu sa svim profesorima.
	 * 
	 * @return lista svih profesora.
	 */
	public ArrayList<Profesor> getLista() {
		return lista;
	}

}
