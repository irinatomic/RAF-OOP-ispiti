package model;

import java.util.Comparator;

public class Student implements Comparable<Student>{

	private String ime;
	private String prezime;
	private int brojUpisa;
	private int godUpisa;
	private String smer;
	
	private String ucionica = "";
	private String termin = "";
	
	private static Comparator<Student> cmp = Comparator.comparing(Student :: getTermin).thenComparing(Student :: getUcionica);
	
	public Student(String ime, String prezime, int brojUpisa, int godUpisa, String smer) {
		this.ime = ime;
		this.prezime = prezime;
		this.brojUpisa = brojUpisa;
		this.godUpisa = godUpisa;
		this.smer = smer;
	}

	@Override
	public String toString() {
		return ime + " " + prezime + " " + brojUpisa + "/" + smer + "-" + godUpisa;
	}
	
	@Override
	public int compareTo(Student o) {
		return cmp.compare(this, o);
	}
	
	public String getStudent() {
		return this.toString();
	}
	
	public String getUcionica() {
		return ucionica;
	}

	public String getTermin() {
		return termin;
	}

	public void setUcionica(String ucionica) {
		this.ucionica = ucionica;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

}
