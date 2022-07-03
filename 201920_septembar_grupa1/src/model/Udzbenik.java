package model;

import java.util.List;

public class Udzbenik {

	private int idBroj;
	private String naslov;
	private int razred;
	private Izdavac izdavac;
	private String predmet;
	private List<String> autori;
	private double cena;
	private int brPrimeraka;
	
	public Udzbenik(int idBroj, String naslov, int razred, String predmet, List<String> autori, double cena, int brPrimeraka) {
		this.idBroj = idBroj;
		this.naslov = naslov;
		this.razred = razred;
		this.predmet = predmet;
		this.autori = autori;
		this.cena = cena;
		this.brPrimeraka = brPrimeraka;
		//IZDAVACA DODAJEMO NAKNADNO
	}
	
	public String getIspisZaFajl() {
		StringBuilder sb = new StringBuilder();
		sb.append(idBroj + "," + naslov + "," + izdavac.getNaziv() + "," + predmet + ",");
		for(String autor : autori)
			sb.append(autor + ",");
		sb.append(cena + "," + brPrimeraka);
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return naslov + " (razred: " + razred + "), " + predmet + ", " + autori + ", cena: " + cena  + " (brPrimeraka: " + brPrimeraka + ")";
	}

	public int getIdBroj() {
		return idBroj;
	}

	public String getNaslov() {
		return naslov;
	}

	public int getRazred() {
		return razred;
	}

	public String getIzdavac() {
		return izdavac.getNaziv();
	}

	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
	}

	public String getPredmet() {
		return predmet;
	}

	public List<String> getAutori() {
		return autori;
	}

	public double getCena() {
		return Math.round(cena * 100.0) / 100.0;
	}

	public int getBrPrimeraka() {
		return brPrimeraka;
	}

	public void setBrPrimeraka(int brPrimeraka) {
		this.brPrimeraka = brPrimeraka;
	}
}
