package model;

import java.util.ArrayList;
import java.util.List;

public class Zanr {

	private String naziv;
	private int brNovih;
	private int brPolovnih;
	private List<Artikal> artikliUZanru;
	
	public Zanr(String naziv, int brNovih, int brPolovnih) {
		this.naziv = naziv;
		this.brNovih = brNovih;
		this.brPolovnih = brPolovnih;
		artikliUZanru = new ArrayList<Artikal>();
	}
	
	public void dodajArtikal(Artikal a) {
		artikliUZanru.add(a);
	}
	
	public String getNaziv() {
		return naziv;
	}

	public int getBrNovih() {
		return brNovih;
	}

	public int getBrPolovnih() {
		return brPolovnih;
	}

	public void setBrNovih(int brNovih) {
		this.brNovih = brNovih;
	}

	public void setBrPolovnih(int brPolovnih) {
		this.brPolovnih = brPolovnih;
	}
	
	public List<Artikal> getArtikliUZanru() {
		return artikliUZanru;
	}

	@Override
	public String toString() {
		return naziv + " nove: " + brNovih + " polovne: " + brPolovnih;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Zanr)) return false;
		
		Zanr z = (Zanr)obj;
		if(naziv.equals(z.naziv)) return true;
		return false;
	}
}
