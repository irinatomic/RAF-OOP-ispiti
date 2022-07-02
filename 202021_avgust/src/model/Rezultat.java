package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rezultat {

	private String predmet;
	private Date datum;
	private int skor;
	
	public Rezultat(String predmet) {
		this.predmet = predmet;
		datum = new Date();
		skor = 0;
	}

	public String getPredmet() {
		return predmet;
	}

	public String getDatum() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return sdf.format(datum);
	}

	public int getSkor() {
		return skor;
	}
	
	public void dodajPoen() {
		skor++;
	}
}
