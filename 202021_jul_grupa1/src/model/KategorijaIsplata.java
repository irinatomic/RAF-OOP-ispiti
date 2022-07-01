package model;

import java.util.ArrayList;
import java.util.List;

//ZA PREGLED ISPLATA VIEW
public class KategorijaIsplata {

	private String kategorija;
	private List<Isplata> isplate;
	private int kolicina;
	private int iznos;
	
	public KategorijaIsplata(String kategorija) {
		this.kategorija = kategorija;
		isplate = new ArrayList<Isplata>();
		kolicina = 0;
		iznos = 0;
	}
	
	public void dodajIsplatu(Isplata u) {
		isplate.add(u);
		iznos += u.getIznos();
		kolicina++;
	}

	public String getKategorija() {
		return kategorija;
	}

	public int getKolicina() {
		return kolicina;
	}

	public int getIznos() {
		return iznos;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof KategorijaIsplata)) return false;
		
		KategorijaIsplata ki = (KategorijaIsplata)obj;
		if(kategorija.equals(ki.kategorija)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return kategorija + "(" + kolicina + " placanja): " + iznos + " RSD";
	}
}

