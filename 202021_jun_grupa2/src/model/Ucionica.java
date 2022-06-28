package model;

public class Ucionica implements Comparable<Ucionica>{

	private String naziv;
	private int max;
	
	public Ucionica(String naziv, int max) {
		this.naziv = naziv;
		this.max = max;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Ucionica)) return false;
		
		if(((Ucionica)obj).naziv.equals(naziv)) return true;
		return false;
	}
	
	public String getLVIspis() {
		return naziv + " broj mesta: " + max;
	}

	@Override
	public String toString() {
		return naziv;
	}
	
	public String getNaziv() {
		return naziv;
	}

	public int getMax() {
		return max;
	}

	@Override
	public int compareTo(Ucionica o) {
		return naziv.compareTo(o.naziv);
	}
}
