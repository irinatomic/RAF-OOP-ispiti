package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Udzbenik> udzbenici;
	private static List<Udzbenik> udzbeniciPrikaz;
	private static List<Izdavac> izdavaci;
	private static List<String> razredi;

	private Baza() {
		udzbenici = new ArrayList<Udzbenik>();
		udzbeniciPrikaz = new ArrayList<Udzbenik>();
		izdavaci = new ArrayList<Izdavac>();
		razredi = new ArrayList<String>();
		razredi.add("Svi razredi");
		FileUtil.ucitajPodatke("udzbenici-prvitermin.txt");
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static void dodajRazred(int r) {
		String razred = r + "";
		if(!razredi.contains(razred))
			razredi.add(razred);
	}

	public static List<Udzbenik> getUdzbenici() {
		return udzbenici;
	}

	public static List<Udzbenik> getUdzbeniciPrikaz() {
		return udzbeniciPrikaz;
	}

	public static List<Izdavac> getIzdavaci() {
		return izdavaci;
	}

	public static List<String> getRazredi() {
		return razredi;
	}
}
