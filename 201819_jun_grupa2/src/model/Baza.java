package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.FileUtil;

public class Baza {
	
	private static Baza instance;
	private static List<Student> sviStudenti;
	private static Map<String, Student> potencijalniEmailovi;
	private static List<Student> upareniStudenti;
	private static List<NeupareniEmail> neupareniEmailovi;
	private static Map<String, NeupareniEmail> neupareniEmailoviMapa;
	
	private static List<String> smerovi;
	private static List<String> godine;
	
	private Baza() {
		sviStudenti = new ArrayList<Student>();
		potencijalniEmailovi = new HashMap<String, Student>();
		upareniStudenti = new ArrayList<Student>();
		neupareniEmailovi = new ArrayList<NeupareniEmail>();
		neupareniEmailoviMapa = new HashMap<String, NeupareniEmail>();
		
		smerovi = new ArrayList<String>();
		godine = new ArrayList<String>();
		smerovi.add("");
		godine.add("");
		
		FileUtil.ucitajStudente("studenti.txt");
		FileUtil.ucitajPoene("pitanje1.txt");
		FileUtil.ucitajPoene("pitanje2.txt");
		FileUtil.ucitajPoene("pitanje3.txt");
		
		//CISTIMO MAPE JER NAM VISE NE TREBAJU
		Baza.getNeupareniEmailoviMapa().clear();
		Baza.getPotencijalniEmailovi().clear();
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static void popuniPotencijalneEmailove() {
		for(Student s : sviStudenti) {
			potencijalniEmailovi.put(s.getPotEmail(), s);
		}
	}
	
	public static List<String> getSmerovi() {
		return smerovi;
	}

	public static List<String> getGodine() {
		return godine;
	}

	public static List<Student> getSviStudenti() {
		return sviStudenti;
	}

	public static Map<String, Student> getPotencijalniEmailovi() {
		return potencijalniEmailovi;
	}

	public static List<Student> getUpareniStudenti() {
		return upareniStudenti;
	}

	public static List<NeupareniEmail> getNeupareniEmailovi() {
		return neupareniEmailovi;
	}

	public static Map<String, NeupareniEmail> getNeupareniEmailoviMapa() {
		return neupareniEmailoviMapa;
	}
}
