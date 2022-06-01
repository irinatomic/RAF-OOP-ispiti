package model;

import java.util.ArrayList;
import java.util.List;

public class Baza {

	private static Baza instance;
	private List<Termin> sviTermini;
	private List<String> ucionice;
	private List<Selekcija> selekcija;
	private List<String> grupeUSelekciji;
	
	private Baza() {
		sviTermini = new ArrayList<Termin>();
		ucionice = new ArrayList<String>();
		selekcija = new ArrayList<Selekcija>();
		grupeUSelekciji = new ArrayList<String>();
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public List<Termin> getSviTermini() {
		return sviTermini;
	}

	public List<String> getUcionice() {
		return ucionice;
	}

	public List<Selekcija> getSelekcija() {
		return selekcija;
	}

	public List<String> getGrupeUSelekciji() {
		return grupeUSelekciji;
	}
}
