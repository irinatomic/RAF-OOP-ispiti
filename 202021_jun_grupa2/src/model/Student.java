package model;

public class Student {

	private String ime;
	private String prezime;
	private String brUpisa;
	private String godUpisa;
	private String smer;
	private Termin termin;
	
	public Student(String ime, String prezime, String brUpisa, String godUpisa, String smer) {
		this.ime = ime;
		this.prezime = prezime;
		this.brUpisa = brUpisa;
		this.godUpisa = godUpisa;
		this.smer = smer;
	}
	
	@Override
	public String toString() {
		return ime + " " + prezime + brUpisa + "/" + smer + "-" + godUpisa;
	}
	
	public String ispisiUFajl() {
		return smer + "," + brUpisa + "," + godUpisa + prezime + "," + ime + "," + termin.getSatnica() + "," + termin.getUcionica();
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getBrUpisa() {
		return brUpisa;
	}

	public String getGodUpisa() {
		return godUpisa;
	}

	public String getSmer() {
		return smer;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}
}
