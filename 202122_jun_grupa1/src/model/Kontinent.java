package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kontinent implements Comparable<Kontinent>{

	private String naziv;
	private List<Drzava> drzave;
	private int brPronadjenih;
	
	private static Comparator<Kontinent> cmp = Comparator.comparing(Kontinent :: getNaziv);
	
	public Kontinent(String naziv) {
		this.naziv = naziv;
		drzave = new ArrayList<Drzava>();
	}
	
	public void izracunajBrPronadjenih() {
		for(Drzava d : drzave)
			brPronadjenih += d.getBrPronadjenih();
	}
	
	@Override
	public String toString() {
		return naziv;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Kontinent)) return false;
		
		Kontinent k = (Kontinent)obj;
		if(k.naziv.equals(naziv)) return true;
		return false;
	}

	public String getNaziv() {
		return naziv;
	}

	public List<Drzava> getDrzave() {
		return drzave;
	}

	public int getBrPronadjenih() {
		return brPronadjenih;
	}

	@Override
	public int compareTo(Kontinent o) {
		return cmp.compare(this, o);
	}
}
