package model;

import java.util.Date;

public class Isplata extends Akcija {

	private String primalac;
	private String kategorija;
	
	public Isplata(int iznos, Date datum, String primalac, String kategorija) {
		super(iznos, datum);
		this.primalac = primalac;
		this.kategorija = kategorija;
	}

	public String getUplatilacPrimalac() {
		return primalac;
	}

	public String getKategorija() {
		return kategorija;
	}
	
	public String getTip() {
		return "ISPLATA";
	}

	@Override
	public String ispisUFajl() {
		return super.getIznos() + ";" + primalac + ";" + super.getDatum() + ";" + kategorija;
	}
}
