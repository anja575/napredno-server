package so.odeljenje;

import db.DBBroker;
import domen.Odeljenje;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

public class ObrisiOdeljenjeSO extends OpstaSistemskaOperacija {
	
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Odeljenje)) {
			throw new Exception("Dati objekat nije instanca klase Odeljenje.");
		}
	}

	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().delete(odo);
	}

}
