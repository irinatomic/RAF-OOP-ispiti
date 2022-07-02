package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PozicijaStatistika implements Comparable<PozicijaStatistika>{

	private String pozicija;
	private List<Zaposleni> zaposleni;
	private int ukZarada;
	private int brZaposlenih;
	
	private static Comparator<PozicijaStatistika> cmp = Comparator.comparing(PozicijaStatistika :: getUkZarada).thenComparing(PozicijaStatistika :: getBrZaposlenih);
	
	public PozicijaStatistika(String pozicija) {
		this.pozicija = pozicija;
		zaposleni = new ArrayList<Zaposleni>();
		ukZarada = 0;
		brZaposlenih = 0;
	}
	
	public void dodajZaposlenog(Zaposleni z) {
		zaposleni.add(z);
		ukZarada += z.getPlata();
		brZaposlenih++;
	}
	
	@Override
	public String toString() {
		return pozicija + " (" + brZaposlenih + ") - " + ukZarada;
	}
	
	public String getPozicija() {
		return pozicija;
	}

	public int getUkZarada() {
		return ukZarada;
	}

	public int getBrZaposlenih() {
		return brZaposlenih;
	}
	
	public List<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	@Override
	public int compareTo(PozicijaStatistika o) {
		return cmp.compare(o, this);
	}
}
