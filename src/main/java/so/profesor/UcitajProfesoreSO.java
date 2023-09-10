package so.profesor;

import java.util.ArrayList;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Profesor;
import so.OpstaSistemskaOperacija;

public class UcitajProfesoreSO extends OpstaSistemskaOperacija{
	
	private ArrayList<Profesor> lista;
	
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Profesor)) {
			throw new Exception("Dati objekat nije instanca klase Profesor.");
		}
	}

	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> profesori = DBBroker.getInstance().select(odo);
		lista = (ArrayList<Profesor>) (ArrayList<?>) profesori;
	}
	
	public ArrayList<Profesor> getLista() {
		return lista;
	}

}
