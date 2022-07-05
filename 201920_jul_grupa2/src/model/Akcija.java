package model;

import java.util.Date;

public class Akcija {

	private Polaznik polaznik;
	private Date datum;
	private String tipAkcije;
	private double iznos;

	public Akcija(Polaznik polaznik, Date datum, String tipAkcije, double iznos) {
		this.polaznik = polaznik;
		this.datum = datum;
		this.tipAkcije = tipAkcije;
		this.iznos = iznos;
	}
	
	public Polaznik getPolaznik() {
		return polaznik;
	}
	
	public Date getDatum() {
		return datum;
	}
	
	public String getTipAkcije() {
		return tipAkcije;
	}
	
	public double getIznos() {
		return iznos;
	}
	
	
}
