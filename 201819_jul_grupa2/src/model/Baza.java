package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Termin> sviTermini;
	private static List<String> dani;
	private static List<Obracunato> obracunato;
	
	private Baza() {
		dani = new ArrayList<String>();
		dani.add("");
		sviTermini = FileUtil.ucitajTermine("raspored.txt");
		obracunato = new ArrayList<Obracunato>();
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<String> getDani() {
		return dani;
	}

	public static List<Termin> getSviTermini() {
		return sviTermini;
	}

	public static List<Obracunato> getObracunato() {
		return obracunato;
	}
}
