package model;

public class Artikal {

	private String izvodjac;
	private String naslov;
	private int godina;
	private String nosacZvuka;
	private int brNosaca;
	private String zanr;
	private double cena;
	private Tip tip;
	
	public Artikal(String izvodjac, String naslov, int godina, String nosac, String zanr, int cena, Tip tip) {
		this.izvodjac = izvodjac;
		this.naslov = naslov;
		this.godina = godina;
		this.zanr = zanr;
		this.cena = 0 + cena;
		this.tip = tip;
		
		String[] args = nosac.split("-");
		nosacZvuka = args[0];
		if(args.length == 1)
			brNosaca = 1;
		else
			brNosaca = Integer.parseInt(args[1]);
	}
	
	public String ispisZaRacun() {
		StringBuilder sb = new StringBuilder();
		sb.append(izvodjac + ", " + naslov + " (" + godina + ") " + nosacZvuka);
		if(brNosaca > 1)
			sb.append("-" + brNosaca + " ");
		sb.append(tip.toString());
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(izvodjac + " \"" + naslov + "\" " + godina + " (" + tip.toString() + ") " + cena + " din ");
		if(brNosaca > 1)
			sb.append(brNosaca + " ");
		sb.append(nosacZvuka);
		return sb.toString();
		
	}

	public String getIzvodjac() {
		return izvodjac;
	}

	public String getNaslov() {
		return naslov;
	}

	public int getGodina() {
		return godina;
	}

	public String getNosacZvuka() {
		return nosacZvuka;
	}

	public int getBrNosaca() {
		return brNosaca;
	}

	public String getZanr() {
		return zanr;
	}

	public double getCena() {
		return cena;
	}

	public Tip getTip() {
		return tip;
	}
}
