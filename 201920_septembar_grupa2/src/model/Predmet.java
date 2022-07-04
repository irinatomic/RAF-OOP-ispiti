package model;

import java.util.ArrayList;
import java.util.List;

public class Predmet {

	private int razred;
	private String naziv;
	private List<Udzbenik> udzbenici;
	
	public Predmet(int razred, String naziv) {
		this.razred = razred;
		this.naziv = naziv;
		udzbenici = new ArrayList<Udzbenik>();
	}
	
	public String getRazred() {
		return razred + ". razred";
	}

	public String getNaziv() {
		return naziv;
	}
	
	public int getBrUdzbenika() {
		return udzbenici.size();
	}

	public void dodajUdzbenik(Udzbenik u) {
		udzbenici.add(u);
	}
		
	public List<Udzbenik> getUdzbenici() {
		return udzbenici;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Predmet)) return false;
		
		Predmet p = (Predmet)obj;
		if(p.razred == razred && p.naziv.equals(naziv)) return true;
		return false;
	}
}
