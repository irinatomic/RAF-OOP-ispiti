package model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Zanr> zanrovi;
	private static List<Artikal> artikli;
	private static List<ArtikalKorpa> korpa;
	private static ObservableList<ArtikalKorpa> olKorpa; 
	
	private Baza() {
		zanrovi = new ArrayList<Zanr>();
		artikli = new ArrayList<Artikal>();
		FileUtil.ucitajKatalog("katalog.txt");
		korpa = new ArrayList<ArtikalKorpa>();
		olKorpa = FXCollections.observableList(korpa); 
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static void dodajUKorpu(ArtikalKorpa a) {
		//AKO JE POLOVAN ARTIKAL	
		if(a.getTip().equals(Tip.polovna)) {
			if(!olKorpa.contains(a))			
				olKorpa.add(a);
			return;
		}
		
		//ARTIKAL NIJE POLOVAN I NEMAMO GA U KORPI
		if(!olKorpa.contains(a)) {
			olKorpa.add(a);
			return;
		}
		
		//ARTIKAL NIJE POLOVAN I IMAMO GA U KORPI -> TRAZIMO GA
		for(ArtikalKorpa aKorpa : olKorpa) {
			if(a.equals(aKorpa)) {
				olKorpa.remove(olKorpa.indexOf(aKorpa));
				aKorpa.dodajBrojKomada(a.getBrKomada());
				olKorpa.add(aKorpa);
				break;
			}
		}
	}

	public static List<Zanr> getZanrovi() {
		return zanrovi;
	}

	public static List<Artikal> getArtikli() {
		return artikli;
	}

	public static List<ArtikalKorpa> getKorpa() {
		return korpa;
	}

	public static ObservableList<ArtikalKorpa> getOlKorpa() {
		return olKorpa;
	}
}
