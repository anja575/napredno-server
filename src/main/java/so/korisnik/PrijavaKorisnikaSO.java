package so.korisnik;

import java.util.ArrayList;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import db.DBBroker;

public class PrijavaKorisnikaSO extends OpstaSistemskaOperacija{

	private Korisnik korisnikUlogovani;

	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Korisnik)) {
			throw new Exception("Dati objekat nije instanca klase Korisnik.");
		}
	}

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

	public Korisnik getKorisnik() {
		return korisnikUlogovani;
	}

	
	
	

}
