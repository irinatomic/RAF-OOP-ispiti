package model;

import java.util.List;

public class Udzbenik {

	private String naslov;
	private List<String> autori;
	private int razred;
	private String izdavac;
	private Predmet predmet;
	private double cenaPrimerka;
	private int brPrimeraka;
	
	public Udzbenik(String naslov, List<String> autori, int razred, String izdavac, double cenaPrimerka, int brPrimeraka) {
		this.naslov = naslov;
		this.autori = autori;
		this.razred = razred;
		this.izdavac = izdavac;
		this.cenaPrimerka = cenaPrimerka;
		this.brPrimeraka = brPrimeraka;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append(brPrimeraka + "-" + naslov + ", ");
		sb.append(autori + ", " + izdavac + ", " + cenaPrimerka);
		return sb.toString();
	}

	public void setPredmet(Predmet predmet) {
		if(this.predmet == null)
			this.predmet = predmet;
	}

	public String getNaslov() {
		return naslov;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public double getCenaPrimerka() {
		return cenaPrimerka;
	}

	public int getBrPrimeraka() {
		return brPrimeraka;
	}

	public void setBrPrimeraka(int brPrimeraka) {
		this.brPrimeraka = brPrimeraka;
	}
}
