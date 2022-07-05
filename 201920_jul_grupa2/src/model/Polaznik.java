package model;

import java.util.ArrayList;
import java.util.List;

public class Polaznik {

	private String ime;
	private String prezime;
	private List<Akcija> akcije;
	private List<Akcija> akcijeFiltrirano; 			//OVO POSLE
	private boolean duznik;
	
	public Polaznik(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
		akcije = new ArrayList<Akcija>();
		akcijeFiltrirano = new ArrayList<Akcija>();
	}
	
	public double getDug() {
		double ukUplaceno = 0;
		double ukCenaCasova = 0;
		double ukCenaPolaganja = 0;
		for(Akcija a : akcije) {
			String tipAkcije = a.getTipAkcije();
			switch(tipAkcije) {
				case "UPLATA" : ukUplaceno += a.getIznos(); break;
				case "ČAS VOŽNJE" : ukCenaCasova += Baza.getCenaVoznja(); break;
				case "ČAS TEORIJE" : ukCenaCasova += Baza.getCenaTeorija(); break;
				case "POLAGANJE" : ukCenaPolaganja += Baza.getCenaPolaganje(); break;
			}
		}
		
		double dug = ukUplaceno - ukCenaCasova;
		if(dug <= 0) {
			duznik = false;
			return 0;
		} 
		dug -= ukCenaPolaganja;
		duznik = true;
		return Math.round(Math.abs(dug) * 100) / 100;
	}
	
	public void dodajFiltriranuAkciju(Akcija a) {
		akcijeFiltrirano.add(a);
	}
	
	public int getBrPuta() {
		return akcijeFiltrirano.size();
	}
	
	public String getIznos() {
		int iznos = 0;
		for(Akcija a : akcijeFiltrirano)
			iznos += a.getIznos();
		if(iznos > 0)
			return iznos + "";
		return "";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Polaznik)) return false;
		
		Polaznik p = (Polaznik)obj;
		if(p.ime.equalsIgnoreCase(ime) && p.prezime.equals(prezime)) return true;
		return false;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public List<Akcija> getAkcije() {
		return akcije;
	}

	public List<Akcija> getAkcijeFiltrirano() {
		return akcijeFiltrirano;
	}

	public boolean isDuznik() {
		return duznik;
	}
}
