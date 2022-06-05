package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Polaznik implements Comparable<Polaznik> {

	private String ime;
	private String prezime;
	private List<Akcija> akcije;
	private int brCasovaTeorije = 0;
	private int brCasovaVoznje = 0;
	private int brUplata = 0;
	private int brPolaganja = 0;
	private double uplata = 0;
	
	private static Comparator<Polaznik> cmp = Comparator.comparing(Polaznik :: getPrezime).thenComparing(Polaznik::getIme);
	
	public Polaznik(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
		akcije = new ArrayList<Akcija>();
	}
	
	public void dodajAkciju(Akcija a) {
		akcije.add(a);
		if(a.getTipAkcije().equals(TipAkcije.POLAGANJE)) brPolaganja++;
		if(a.getTipAkcije().equals(TipAkcije.UPLATA)) brUplata++;
		if(a.getTipAkcije().equals(TipAkcije.ČAS_TEORIJE)) brCasovaTeorije++;
		if(a.getTipAkcije().equals(TipAkcije.ČAS_VOŽNJE)) brCasovaVoznje++;
		if(a.getTipAkcije().equals(TipAkcije.UPLATA)) uplata += a.getIznosBroj();
	}
	public int getBrCasovaTeorije() {
		return brCasovaTeorije;
	}

	public int getBrCasovaVoznje() {
		return brCasovaVoznje;
	}

	public int getBrUplata() {
		return brUplata;
	}

	public int getBrPolaganja() {
		return brPolaganja;
	}

	public int getBrAkcije(TipAkcije ta) {
		if(ta.equals(TipAkcije.POLAGANJE))
			return getBrPolaganja();
		else if(ta.equals(TipAkcije.UPLATA))
			return getBrUplata();
		else if(ta.equals(TipAkcije.ČAS_TEORIJE))
			return getBrCasovaTeorije();
		else
			return getBrCasovaVoznje();
	}
	
	public double getDug() {
		double cenaCasova = brCasovaTeorije * Baza.getCenaCasTeorija() + brCasovaVoznje + Baza.getCenaCasVoznje();
		double cenaPolaganja = brPolaganja * Baza.getCenaPolaganja();
		if(cenaCasova <= uplata)
			return 0;
		return cenaCasova + cenaPolaganja - uplata;
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
	
	@Override
	public String toString() {
		return ime + " " + prezime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Polaznik)) return false;
		
		Polaznik p = (Polaznik)obj;
		if(ime.equals(p.ime) && prezime.equals(p.prezime)) return true;
		return false;
	}

	@Override
	public int compareTo(Polaznik o) {
		return cmp.compare(this, o);
	}
}
