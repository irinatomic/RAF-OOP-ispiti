package model;

public class ArtikalKorpa {

	private Artikal ogArtikal;
	private String opis;
	private int brKomada;
	private double komadCena;
	private double ukCena;
	private Tip tip;
	
	public ArtikalKorpa(Artikal ogArtikal, String opis, int brKomada, double komadCena, Tip tip) {
		this.ogArtikal = ogArtikal;
		this.opis = opis;
		this.brKomada = brKomada;
		this.komadCena = komadCena;
		this.tip = tip;
		
		if(tip.equals(Tip.polovna))
			this.brKomada = 1;
		ukCena = this.brKomada * komadCena;
	}
	
	public void dodajBrojKomada(int broj) {
		if(tip.equals(Tip.polovna))
			return;
		
		brKomada += broj;
		ukCena += broj * komadCena;
	}

	public String getOpis() {
		return opis;
	}

	public int getBrKomada() {
		return brKomada;
	}
	
	public double getUkCena() {
		return ukCena;
	}
	
	public Tip getTip() {
		return tip;
	}

	public Artikal getOgArtikal() {
		return ogArtikal;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof ArtikalKorpa)) return false;
		
		ArtikalKorpa ak = (ArtikalKorpa)obj;
		if(opis.equals(ak.opis)) return true;
		return false;
	}
}
