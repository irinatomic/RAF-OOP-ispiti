package model;

import java.util.ArrayList;
import java.util.List;

public class Izdavac {

	private String naziv;
	private List<Integer> razredi;
	private List<Udzbenik> udzbenici;
	private int brojPrimeraka;
	private double ukupnaCena;
	private double prosecnaCena;
	
	public Izdavac(String naziv) {
		this.naziv = naziv;
		udzbenici = new ArrayList<Udzbenik>();
		razredi = new ArrayList<Integer>();
		ukupnaCena = 0;
	}
	
	public void dodajUdzbenik(Udzbenik u) {
		udzbenici.add(u);
		dodajRazred(u.getRazred());
		brojPrimeraka += u.getBrPrimeraka();
		ukupnaCena += u.getCena();
		prosecnaCena = ukupnaCena / udzbenici.size();
	}
	
	private void dodajRazred(int razred) {
		if(!razredi.contains(razred))
			razredi.add(razred);
		Baza.dodajRazred(razred);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Izdavac)) return false;
		
		Izdavac i = (Izdavac)obj;
		if(i.naziv.equals(naziv)) return true;
		return false;
	}

	public String getNaziv() {
		return naziv;
	}

	public List<Integer> getRazredi() {
		return razredi;
	}

	public int getBrojPrimeraka() {
		brojPrimeraka = 0;
		for(Udzbenik u : udzbenici)
			brojPrimeraka += u.getBrPrimeraka();
		return brojPrimeraka;
	}

	public double getProsecnaCena() {
		return Math.round(prosecnaCena * 100.0) / 100.0;
	}

	public List<Udzbenik> getUdzbenici() {
		return udzbenici;
	}
}
