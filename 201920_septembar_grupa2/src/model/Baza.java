package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Udzbenik> udzbenici;
	private static List<Predmet> predmeti;
	private static List<UdzbenikKorpa> udzbeniciKorpa;

	private Baza() {
		udzbenici = new ArrayList<Udzbenik>();
		udzbeniciKorpa = new ArrayList<UdzbenikKorpa>();
		predmeti = new ArrayList<Predmet>();
		FileUtil.ucitajFajl("udzbenici-drugitermin.txt");
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<Udzbenik> getUdzbenici() {
		return udzbenici;
	}

	public static List<Predmet> getPredmeti() {
		return predmeti;
	}

	public static List<UdzbenikKorpa> getUdzbeniciKorpa() {
		return udzbeniciKorpa;
	}
}
