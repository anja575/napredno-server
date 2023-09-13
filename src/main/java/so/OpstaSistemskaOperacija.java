package so;

import java.sql.SQLException;
import db.DBBroker;
import domen.OpstiDomenskiObjekat;

/**
 * 
 * OpstaSistemskaOperacija je apstraktna klasa koju nasledjuju sve klase koje se odnose
 * na sistemske operacije. Obuhvata apstraktne metode za validaciju i izvrsavanje operacije,
 * kao i metode za definisanje redosleda izvrsavanja, za potvrdu i za ponistavanje transakcije.
 * 
 * @author Anja
 *
 */
public abstract class OpstaSistemskaOperacija {
	/**
	 * Vrsi validaciju za unete podatke koji se koriste prilikom izvrsavanja odredjene
	 * sistemske operacije specificne klase.
	 * 
	 * @param odo kao OpstiDomenskiObjekat nad cijim podacima se vrsi validacija.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom validacije podataka.
	 */
	protected abstract void validacija(OpstiDomenskiObjekat odo) throws Exception;
	/**
	 * Pokrece izvrsavanje specificne sistemske operacije, u okviru koje se poziva DBBroker kako 
	 * bi se izvrsio zeljeni upit nad bazom podataka.
	 * 
	 * @param odo kao OpstiDomenskiObjekat koji sadrzi podatke neophodne za izvrsavanje sistemske operacije.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja sistemske operacije.
	 */
	protected abstract void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception;
	/**
	 * Definise redosled metoda prilikom izvrsavanja sistemske operacije, tj. prvo
	 * ide validacija podataka, zatim izvrsavanje zahtevane operacije, a onda
	 * potvrdjivanje ili ponistavanje transakcije.
	 * 
	 * @param odo kao OpstiDomenskiObjekat koji sadrzi podatke neophodne za izvrsavanje metoda.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja metoda.
	 */
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
	/**
	 * Vrsi potvrdjivanje transakcije, koje je nastala kao rezultat izvrsenja sistemske operacije.
	 * 
	 * @throws SQLException ukoliko dodje do greske prilikom potvrdjivanja transakcije.
	 */
	public void commit() throws SQLException {
		DBBroker.getInstance().getConnection().commit();
	}
	/**
	 * Vrsi ponistavanje transakcije, koja je nastala kao rezultat izvrsenja sistemske operacije.
	 * 
	 * @throws SQLException ukoliko dodje do greske prilikom ponistavanja transakcije.
	 */
	public void rollback() throws SQLException {
		DBBroker.getInstance().getConnection().rollback();
	}
	
	
	
	
	
	
}
