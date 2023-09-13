package so.korisnik;

import java.util.ArrayList;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import db.DBBroker;

/**
 * 
 * Odnosi se na sistemsku operaciju za prijavu korisnika(pedagoga) na sistem.
 * 
 * @author Anja
 *
 */
public class PrijavaKorisnikaSO extends OpstaSistemskaOperacija{
	/**
	 * Instanca klase Korisnik koja se odnosi na ulogovanog korisnika.
	 */
	private Korisnik korisnikUlogovani;
	/**
	 * Proverava da li je poslati objekat klase Korisnik.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Korisnik.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Korisnik)) {
			throw new Exception("Dati objekat nije instanca klase Korisnik.");
		}
	}
	/**
	 * Poziva DBBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, a zatim prolazi
	 * kroz listu svih korisnika dobijenih iz baze i proverava da li postoji korisnik sa unetim
	 * kredencijalima za logovanje na sistem. 
	 * 
	 * @throws Exception ako ne postoji korisnik sa unetim kredencijalima.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {

		Korisnik k = (Korisnik) odo;

		ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(odo);

		for (Korisnik korisnik : korisnici) {
			if (korisnik.getUsername().equals(k.getUsername()) && korisnik.getPassword().equals(k.getPassword())) {
				korisnikUlogovani = korisnik;
				return;
			}
		}

		throw new Exception("Ne postoji korisnik sa datim kredencijalima.");

	}
	/**
	 * Vraca ulogovanog korisnika na sistem.
	 * 
	 * @return ulogovani korisnik kao Korisnik.
	 */
	public Korisnik getKorisnik() {
		return korisnikUlogovani;
	}

	
	
	

}
