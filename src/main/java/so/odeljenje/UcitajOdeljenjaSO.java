package so.odeljenje;

import java.util.ArrayList;

import db.DBBroker;
import domen.Odeljenje;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

public class UcitajOdeljenjaSO extends OpstaSistemskaOperacija{
	
	private ArrayList<Odeljenje> lista;
	
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Odeljenje)) {
			throw new Exception("Dati objekat nije instanca klase Odeljenje.");
		}
	}

	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> odeljenja = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Odeljenje>) (ArrayList<?>) odeljenja;
	}
	
	public ArrayList<Odeljenje> getLista() {
		return lista;
	}

}
