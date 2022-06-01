package model;

import java.util.ArrayList;
import java.util.List;

public class Selekcija {

	private String grupa;
	private List<String> dani;
	private int brCasova;
	private List<String> nastavnici;
	private List<Termin> termini;	
	
	public Selekcija(String grupa) {
		this.grupa = grupa;
		brCasova = 0;
		dani = new ArrayList<String>();
		nastavnici = new ArrayList<String>();
		termini = new ArrayList<Termin>();
	}

	public void dodajDan(String s) {
		if(!dani.contains(s))
			dani.add(s);
	}

	public void dodajNastavnika(String s) {
		if(!nastavnici.contains(s))
			nastavnici.add(s);
	}
	
	public void dodajTermin(Termin t) {
		if(!termini.contains(t)) {
			termini.add(t);
			brCasova += t.getBrCasova();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Selekcija)) return false;
		
		Selekcija s = (Selekcija)obj;
		if(s.grupa.equals(grupa)) 
			return true;
		return false;
	}

	public String getGrupa() {
		return grupa;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	public List<String> getDani() {
		return dani;
	}

	public int getBrCasova() {
		return brCasova;
	}

	public List<String> getNastavnici() {
		return nastavnici;
	}

	public int getBrNastavnika() {
		return nastavnici.size();
	}

	public List<Termin> getTermini() {
		return termini;
	}	
}
