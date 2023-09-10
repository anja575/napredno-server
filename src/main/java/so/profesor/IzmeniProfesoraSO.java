package so.profesor;

import java.util.ArrayList;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Profesor;
import so.OpstaSistemskaOperacija;

public class IzmeniProfesoraSO extends OpstaSistemskaOperacija{
	
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Profesor)) {
			throw new Exception("Dati objekat nije instanca klase Profesor.");
		}
		
		Profesor p = (Profesor) odo;
		
		ArrayList<Profesor> profesori = (ArrayList<Profesor>) (ArrayList<?>) DBBroker.getInstance().select(new Profesor());

		for (Profesor profesor : profesori) {
			if (profesor.getEmail().equals(p.getEmail())) {
				throw new Exception("Vec postoji profesor sa datim email-om.");
			}
		}
		
	}

	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().update(odo);
	}

}
