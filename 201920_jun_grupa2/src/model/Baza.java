package model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<String> izvodjaci;
	private static List<Artikal> artikli;
	private static List<Artikal> korpa;
	private static ObservableList<Artikal> olKorpa;
	private static List<ArtikalKorpa> kupljeniArtikli;
	
	private Baza() {
		izvodjaci = new ArrayList<String>();
		izvodjaci.add("");
		artikli = FileUtil.ucitajKatalog("katalog.txt");
		korpa = new ArrayList<Artikal>();
		olKorpa = FXCollections.observableList(korpa);
		kupljeniArtikli = new ArrayList<ArtikalKorpa>();
	}

	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<String> getIzvodjaci() {
		return izvodjaci;
	}

	public static List<Artikal> getArtikli() {
		return artikli;
	}

	public static List<Artikal> getKorpa() {
		return korpa;
	}

	public static ObservableList<Artikal> getOlKorpa() {
		return olKorpa;
	}

	public static List<ArtikalKorpa> getKupljeniArtikli() {
		return kupljeniArtikli;
	}
	
	public static int getUkVrednostKupljenihArtikala() {
		int vrednost = 0;
		for(ArtikalKorpa ak : kupljeniArtikli)
			vrednost += ak.getVrednost();
		return vrednost;
	}
}
