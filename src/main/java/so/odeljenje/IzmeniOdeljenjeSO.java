package so.odeljenje;

import db.DBBroker;
import domen.Odeljenje;
import domen.OpstiDomenskiObjekat;
import domen.Ucenik;
import so.OpstaSistemskaOperacija;

public class IzmeniOdeljenjeSO extends OpstaSistemskaOperacija{
	
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Odeljenje)) {
			throw new Exception("Dati objekat nije instanca klase Odeljenje.");
		}
		
		Odeljenje odeljenje = (Odeljenje) odo;
		
		if(odeljenje.getUcenici().size()<5 || odeljenje.getUcenici().size()>30) {
			throw new Exception("Broj ucenika u odeljenju mora biti od 5 do 30.");
		}	
	}

	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().update(odo);

		Odeljenje odeljenje = (Odeljenje) odo;

		DBBroker.getInstance().delete(odeljenje.getUcenici().get(0));

		for (Ucenik ucenik : odeljenje.getUcenici()) {
			DBBroker.getInstance().insert(ucenik);
		}
	}


}
