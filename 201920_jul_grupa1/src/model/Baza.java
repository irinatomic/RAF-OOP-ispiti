package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Polaznik> polaznici;
	private static List<Akcija> akcije;
	private static int cenaCasTeorija = 500;
	private static int cenaCasVoznje = 750;
	private static int cenaPolaganja = 2500;
	
	private Baza() {
		polaznici = new ArrayList<Polaznik>();
		akcije = new ArrayList<Akcija>();
		FileUtil.ucitajFajl("skola.txt");
	}

	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<Polaznik> getPolaznici() {
		return polaznici;
	}

	public static List<Akcija> getAkcije() {
		return akcije;
	}

	public static int getCena(TipAkcije ta) {
		if(ta.equals(TipAkcije.ČAS_VOŽNJE))
			return cenaCasVoznje;
		else if(ta.equals(TipAkcije.ČAS_TEORIJE))
			return cenaCasTeorija;
		else
			return cenaPolaganja;
	}

	public static int getCenaCasTeorija() {
		return cenaCasTeorija;
	}

	public static int getCenaCasVoznje() {
		return cenaCasVoznje;
	}

	public static int getCenaPolaganja() {
		return cenaPolaganja;
	}
}
