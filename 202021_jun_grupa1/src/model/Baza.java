package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Student> nerasporedjeni;
	private static List<Student> rasporedjeni;
	private static List<Ucionica> ucionice;
	private static List<Termin> termini;
	private static int brojPoUcionici;
	private static boolean samoRacunari;

	private Baza() {
		nerasporedjeni = FileUtil.ucitajStudente("studenti1.txt");
		ucionice = FileUtil.ucitajUcionice("ucionice1.txt");
		rasporedjeni = new ArrayList<Student>();
		termini = new ArrayList<Termin>();
		brojPoUcionici = 0;
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static void kreirajTermine(int brojTermina) {
		for(int i = 1; i <= brojTermina; i++) {
			Termin t = new Termin(i, samoRacunari);
			termini.add(t);
		}
	}

	public static List<Student> getNerasporedjeni() {
		return nerasporedjeni;
	}

	public static List<Student> getRasporedjeni() {
		return rasporedjeni;
	}

	public static List<Ucionica> getUcionice() {
		return ucionice;
	}
	
	public static int getBrojPoUcionici() {
		return brojPoUcionici;
	}

	public static void setBrojPoUcionici(int brojPoUcionici) {
		Baza.brojPoUcionici = brojPoUcionici;
	}

	public static List<Termin> getTermini() {
		return termini;
	}

	public static boolean isSamoRacunari() {
		return samoRacunari;
	}

	public static void setSamoRacunari(boolean samoRacunari) {
		Baza.samoRacunari = samoRacunari;
	}
}
