package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Ucionica> ucionice;
	private static List<Termin> termini;
	private static List<Student> studenti;
	private static List<Integer> satnice;
	private static Termin izabranTermin;

	private Baza() {
		ucionice = FileUtil.ucitajUcionice("ucionice2.txt");
		termini = new ArrayList<Termin>();
		studenti = new ArrayList<Student>();
		satnice = new ArrayList<Integer>();
		FileUtil.ucitajStudente("raspored2.txt");
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static void dodajSatnicu(Integer sat) {
		if(!satnice.contains(sat))
			satnice.add(sat);
	}

	public static List<Ucionica> getUcionice() {
		return ucionice;
	}

	public static List<Termin> getTermini() {
		return termini;
	}

	public static List<Student> getStudenti() {
		return studenti;
	}

	public static List<Integer> getSatnice() {
		return satnice;
	}

	public static Termin getIzabranTermin() {
		return izabranTermin;
	}

	public static void setIzabranTermin(Termin izabranTermin) {
		Baza.izabranTermin = izabranTermin;
	}  
}
