package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Baza {

	private static Baza instance;
	private static List<String> grupe;
	private static List<Utakmica> spisakUtakmica;
	private static Set<Reprezentacija> spisakReprezentacija;
	private static List<String> ukrsteneUtakmice;
	
	private Baza() {
		spisakUtakmica = new ArrayList<Utakmica>();
		spisakReprezentacija = new TreeSet<Reprezentacija>();
		ukrsteneUtakmice = new ArrayList<String>();
		grupe = new ArrayList<String>();
		grupe.add("");
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<Utakmica> getSpisakUtakmica() {
		return spisakUtakmica;
	}

	public static Set<Reprezentacija> getSpisakReprezentacija() {
		return spisakReprezentacija;
	}

	public static List<String> getGrupe() {
		return grupe;
	}

	public static List<String> getUkrsteneUtakmice() {
		return ukrsteneUtakmice;
	}
}
