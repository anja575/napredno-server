package so.profesor;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Profesor;
import so.OpstaSistemskaOperacija;

public class ObrisiProfesoraSO extends OpstaSistemskaOperacija{
	
	@Override
    protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Profesor)) {
            throw new Exception("Dati objekat nije instanca klase Profesor.");
        }
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().delete(odo);
    }

}
