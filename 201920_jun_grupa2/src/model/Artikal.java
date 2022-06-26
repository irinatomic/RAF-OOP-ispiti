package model;

public class Artikal {

	private String izvodjac;
	private String naslov;
	private int godIzdanja;
	private String zanr;
	private TipNosaca tipNosaca;
	private int brNosaca;
	private int cena;
	private Kategorija kategorija;
	
	public Artikal(String izvodjac, String naslov, int godIzdanja, String nosacIBroj, int cena, Kategorija kategorija, String zanr) {
		this.izvodjac = izvodjac;
		this.naslov = naslov;
		this.godIzdanja = godIzdanja;
		this.cena = cena;
		this.kategorija = kategorija;
		this.zanr = zanr;
		
		String[] args = nosacIBroj.split("-");
		this.tipNosaca = TipNosaca.valueOf(args[0]);
		this.brNosaca = Integer.parseInt(args[1]);
	}

	@Override
	public String toString() {
		return izvodjac + " \"" + naslov + "\" " + cena + "din";
	}
	
	public String getIzvodjac() {
		return izvodjac;
	}
	
	public String getNaslov() {
		return naslov;
	}
	
	public int getGodIzdanja() {
		return godIzdanja;
	}
	
	public String getZanr() {
		return zanr;
	}

	public TipNosaca getTipNosaca() {
		return tipNosaca;
	}

	public int getBrNosaca() {
		return brNosaca;
	}

	public int getCena() {
		return cena;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

}
