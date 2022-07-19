package model;

import java.util.Comparator;

public class Drzava implements Comparable<Drzava>{

	private String naziv;
	private int brPronadjenih;
	
	private static Comparator<Drzava> cmp = Comparator.comparing(Drzava :: getBrPronadjenih);
	
	public Drzava(String naziv) {
		this.naziv = naziv;
	}
	
	@Override
	public String toString() {
		return naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public int getBrPronadjenih() {
		return brPronadjenih;
	}

	public void setBrPronadjenih(int brPronadjenih) {
		this.brPronadjenih = brPronadjenih;
	}

	@Override
	public int compareTo(Drzava o) {
		return cmp.compare(this, o);
	}
}
