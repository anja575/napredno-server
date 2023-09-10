package so.ucenik;

import java.util.ArrayList;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Ucenik;
import so.OpstaSistemskaOperacija;

public class UcitajUcenikeSO extends OpstaSistemskaOperacija {
	
private ArrayList<Ucenik> lista;
	
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Ucenik)) {
			throw new Exception("Dati objekat nije instanca klase Ucenik.");
		}
	}

	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> ucenici = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Ucenik>) (ArrayList<?>) ucenici;
	}
	
	public ArrayList<Ucenik> getLista() {
		return lista;
	}

}
