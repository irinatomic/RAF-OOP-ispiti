package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Termin implements Comparable<Termin>{

	private Ucionica ucionica;
	private int satnica;
	private List<Student> studenti;
	
	private static Comparator<Termin> cmp = Comparator.comparing(Termin :: getSatnica).thenComparing(Termin :: getUcionica);
	
	public Termin(Ucionica ucionica, int satnica) {
		this.ucionica = ucionica;
		this.satnica = satnica;
		studenti = new ArrayList<Student>();
	}

	public Ucionica getUcionica() {
		return ucionica;
	}

	public int getSatnica() {
		return satnica;
	}
	
	public int getBrStudenata() {
		return studenti.size();
	}

	public List<Student> getStudenti() {
		return studenti;
	}
	
	public int getOgranicenje() {
		return ucionica.getMax();
	}
	
	public int getPrekoracenje() {
		if(studenti.size() <= ucionica.getMax())
			return 0;
		return studenti.size() - ucionica.getMax();
	}
	
	public int getSlobodno() {
		if(studenti.size() > ucionica.getMax())
			return 0;
		return ucionica.getMax() - studenti.size();
	}
	
	public String getInfo() {
		return "Ucionica: " + ucionica.getNaziv() + ", termin: " + satnica + ", prekoraceno: " + getPrekoracenje();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Termin)) return false;
		
		Termin t = (Termin)obj;
		if(t.ucionica.equals(ucionica) && t.satnica == satnica) return true;
		return false;
	}

	@Override
	public int compareTo(Termin o) {
		return cmp.compare(this, o);
	}
}
