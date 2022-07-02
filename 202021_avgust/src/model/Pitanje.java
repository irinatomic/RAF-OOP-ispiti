package model;

public class Pitanje {

	private String pitanje;
	private String odgovor;
	private String predmet;
	
	public Pitanje(String pitanje, String odgovor, String predmet) {
		this.pitanje = pitanje;
		this.odgovor = odgovor;
		this.predmet = predmet;
	}
	
	public String getIspisZaFajl() {
		return pitanje + ";" + odgovor + ";" + predmet;
	}

	public String getPitanje() {
		return pitanje;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public String getPredmet() {
		return predmet;
	}
	
	@Override
	public String toString() {
		return pitanje;
	}
}
