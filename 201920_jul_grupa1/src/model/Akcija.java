package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Akcija {

	private Polaznik polaznik;
	private Date datum;
	private TipAkcije tipAkcije;
	private double iznos;
	
	public Akcija(Polaznik polaznik, Date datum, String tipAkcije) {
		this.polaznik = polaznik;
		this.datum = datum;
		
		tipAkcije = tipAkcije.replace(" ", "_");
		String[] args = tipAkcije.split("-");
		this.tipAkcije = TipAkcije.valueOf(args[0]);
		if(args.length > 1)
			iznos = Double.parseDouble(args[1]);
	}

	public String getIme() {
		return polaznik.getIme();
	}
	
	public String getPrezime() {
		return polaznik.getPrezime();
	}

	public String getDatum() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy.");
		return sdf.format(datum);
	}

	public String getTipAkcijeTabela() {
		return tipAkcije.toString().replace("_", " ");
	}

	public TipAkcije getTipAkcije() {
		return tipAkcije;
	}

	public String getIznos() {
		if(tipAkcije.equals(TipAkcije.UPLATA))
			return iznos + "";
		return "";
	}

	public double getIznosBroj() {
		return iznos;
	}
}
