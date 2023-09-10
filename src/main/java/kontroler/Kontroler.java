package kontroler;

import java.util.ArrayList;

import domen.Korisnik;
import domen.Mesto;
import domen.Odeljenje;
import domen.Profesor;
import domen.Skola;
import domen.Smer;
import domen.Ucenik;
import so.korisnik.PrijavaKorisnikaSO;
import so.mesto.UcitajMestaSO;
import so.odeljenje.DodajOdeljenjeSO;
import so.odeljenje.IzmeniOdeljenjeSO;
import so.odeljenje.ObrisiOdeljenjeSO;
import so.odeljenje.UcitajOdeljenjaSO;
import so.profesor.DodajProfesoraSO;
import so.profesor.IzmeniProfesoraSO;
import so.profesor.ObrisiProfesoraSO;
import so.profesor.UcitajProfesoreSO;
import so.skola.UcitajSkoleSO;
import so.smer.UcitajSmeroveSO;
import so.ucenik.UcitajUcenikeSO;

public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Korisnik login(Korisnik korisnik) throws Exception {
        PrijavaKorisnikaSO pkso = new PrijavaKorisnikaSO();
        pkso.izvrsavanje(korisnik);
        return pkso.getKorisnik();
    }

    public void dodajOdeljenje(Odeljenje odeljenje) throws Exception {
        (new DodajOdeljenjeSO()).izvrsavanje(odeljenje);
    }

    public void dodajProfesora(Profesor profesor) throws Exception {
        (new DodajProfesoraSO()).izvrsavanje(profesor);
    }

    public void obrisiOdeljenje(Odeljenje odeljenje) throws Exception {
        (new ObrisiOdeljenjeSO()).izvrsavanje(odeljenje);
    }

    public void obrisiProfesora(Profesor profesor) throws Exception {
        (new ObrisiProfesoraSO()).izvrsavanje(profesor);
    }
 
    public void izmeniOdeljenje(Odeljenje odeljenje) throws Exception {
        (new IzmeniOdeljenjeSO()).izvrsavanje(odeljenje);
    }

    public void izmeniProfesora(Profesor profesor) throws Exception {
        (new IzmeniProfesoraSO()).izvrsavanje(profesor);
    }

    public ArrayList<Odeljenje> ucitajOdeljenja() throws Exception {
        UcitajOdeljenjaSO uoso = new UcitajOdeljenjaSO();
        uoso.izvrsavanje(new Odeljenje());
        return uoso.getLista();
    }
    
    public ArrayList<Profesor> ucitajProfesore() throws Exception {
        UcitajProfesoreSO upso = new UcitajProfesoreSO();
        upso.izvrsavanje(new Profesor());
        return upso.getLista();
    }
    
    public ArrayList<Smer> ucitajSmerove() throws Exception {
        UcitajSmeroveSO usso = new UcitajSmeroveSO();
        usso.izvrsavanje(new Smer());
        return usso.getLista();
    }
    
    public ArrayList<Skola> ucitajSkole() throws Exception {
        UcitajSkoleSO usso = new UcitajSkoleSO();
        usso.izvrsavanje(new Skola());
        return usso.getLista();
    }
    
    public ArrayList<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO umso = new UcitajMestaSO();
        umso.izvrsavanje(new Mesto());
        return umso.getLista();
    }
    
    public ArrayList<Ucenik> ucitajUcenike(Odeljenje odeljenje) throws Exception {
        UcitajUcenikeSO uuso = new UcitajUcenikeSO();

        Ucenik u = new Ucenik();
        u.setOdeljenje(odeljenje);

        uuso.izvrsavanje(u);
        return uuso.getLista();
    }
}

