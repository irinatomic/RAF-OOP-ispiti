package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<String> predmeti;
	private static List<Pitanje> pitanja;
	private static List<Rezultat> rezultati;

	private Baza() {
		predmeti = new ArrayList<String>();
		predmeti.add("Svi predmeti");
		pitanja = FileUtil.ucitajPitanja("pitanja.txt");
		rezultati = new ArrayList<Rezultat>();
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static List<String> getPredmeti() {
		return predmeti;
	}

	public static List<Pitanje> getPitanja() {
		return pitanja;
	}

	public static List<Rezultat> getRezultati() {
		return rezultati;
	}

}
