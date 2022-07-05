package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static List<Akcija> akcije;
	private static List<Polaznik> polaznici;
	private static List<AkcijaStatistika> statistika;
	private static final double cenaTeorija;
	private static final double cenaVoznja;
	private static final double cenaPolaganje;
	
	static {
		akcije = new ArrayList<Akcija>();
		polaznici = new ArrayList<Polaznik>();
		statistika = new ArrayList<AkcijaStatistika>();
		cenaTeorija = 630;
		cenaVoznja = 890;
		cenaPolaganje = 2750;
	}
	
	public static void ucitajFajl(String fileName) {
		FileUtil.ucitajFajl(fileName);
	}

	public static double izracunajKredit() {
		double ukUplaceno = 0;
		double ukCenaAkcija = 0;
		for(Akcija a : akcije) {
			String tipAkcije = a.getTipAkcije();
			switch(tipAkcije) {
				case "UPLATA" : ukUplaceno += a.getIznos(); break;
				case "ČAS VOŽNJE" : ukCenaAkcija += cenaVoznja; break;
				case "ČAS TEORIJE" : ukCenaAkcija += cenaTeorija; break;
				case "POLAGANJE" : ukCenaAkcija += cenaPolaganje; break;
			}
		}
		return Math.round(Math.abs(ukCenaAkcija - ukUplaceno) * 100) / 100;
	}
	
	public static double izracunajUkupanDug() {
		double ukDug = 0;
		for(Polaznik p : polaznici)
			ukDug += p.getDug();
		return ukDug;
	}
	
	public static int getBrDuznika() {
		int brojac = 0;
		for(Polaznik p : polaznici)
			brojac = p.isDuznik()? brojac + 1 : brojac;
		return brojac;
	}
	
	public static List<Akcija> getAkcije() {
		return akcije;
	}

	public static List<Polaznik> getPolaznici() {
		return polaznici;
	}

	public static List<AkcijaStatistika> getStatistika() {
		return statistika;
	}

	public static double getCenaTeorija() {
		return cenaTeorija;
	}

	public static double getCenaVoznja() {
		return cenaVoznja;
	}

	public static double getCenaPolaganje() {
		return cenaPolaganje;
	}
}
