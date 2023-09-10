package so;

import java.sql.SQLException;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;

public abstract class OpstaSistemskaOperacija {
	
	protected abstract void validacija(OpstiDomenskiObjekat odo) throws Exception;
	
	protected abstract void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception;
	
	public void izvrsavanje(OpstiDomenskiObjekat odo) throws Exception {
		try {
			validacija(odo);
			izvrsiOperaciju(odo);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}
	
	public void commit() throws SQLException {
		DBBroker.getInstance().getConnection().commit();
	}
	
	public void rollback() throws SQLException {
		DBBroker.getInstance().getConnection().rollback();
	}
	
	
	
	
	
	
}
