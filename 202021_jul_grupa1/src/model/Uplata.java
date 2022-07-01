package model;

import java.util.Date;

public class Uplata extends Akcija {

	private String uplatilac;

	public Uplata(int iznos, Date datum, String uplatilac) {
		super(iznos, datum);
		this.uplatilac = uplatilac;
	}

	public String getUplatilacPrimalac() {
		return uplatilac;
	}
	
	public String getTip() {
		return "UPLATA";
	}

	@Override
	public String ispisUFajl() {
		return super.getIznos() + ";" + uplatilac + ";" + super.getDatum();
	}
}
