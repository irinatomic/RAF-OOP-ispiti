package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<String> pozicije;
	private static List<Zaposleni> zaposleni;
	private static ObservableList<Zaposleni> olZaposleni;
	private static Map<String, Double> platniKoeficijenti;
	private static List<PozicijaStatistika> pozicijeStatistika;

	private Baza() {
		pozicije = new ArrayList<String>();
		pozicije.add("Sve pozicije");
		zaposleni = FileUtil.ucitajZaposlene("zaposleni.txt");
		olZaposleni = FXCollections.observableArrayList(zaposleni);
		platniKoeficijenti = FileUtil.ucitajPlatneKoef("zanimanja.txt");
		pozicijeStatistika = new ArrayList<PozicijaStatistika>();
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}
	
	public static void odradiStatistiku() {
		for(String poz : pozicije) {
			if(poz.equals("Sve pozicije")) continue;
			
			PozicijaStatistika ps = new PozicijaStatistika(poz);
			for(Zaposleni z : zaposleni) {
				if(z.getPozicija().equals(poz))
					ps.dodajZaposlenog(z);
			}
			pozicijeStatistika.add(ps);
		}
	}

	public static List<String> getPozicije() {
		return pozicije;
	}

	public static List<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public static Map<String, Double> getPlatniKoeficijenti() {
		return platniKoeficijenti;
	}

	public static ObservableList<Zaposleni> getOlZaposleni() {
		return olZaposleni;
	}

	public static List<PozicijaStatistika> getPozicijeStatistika() {
		return pozicijeStatistika;
	}
}
