package model;

import java.util.ArrayList;
import java.util.List;

public class ArtikalKorpa {

	private String izvodjac;
	private List<Artikal> artikli;
	private List<String> albumi;
	private int brVinyl;
	private int brCD;
	private int ukKomada;
	private int brNovih;
	private int brPolovnih;
	private int vredost;
	
	public ArtikalKorpa(String izvodjac) {
		this.izvodjac = izvodjac;
		artikli = new ArrayList<Artikal>();
		albumi = new ArrayList<String>();
		brVinyl = 0;
		brCD = 0;
		brNovih = 0;
		brPolovnih = 0;
		vredost = 0;
	}
	
	public void dodajArtikal(Artikal a) {
		if(artikli.contains(a))
			return;
		artikli.add(a);
		vredost += a.getCena();
		ukKomada += a.getBrNosaca();
		albumi.add(a.getNaslov() + " - " + a.getTipNosaca() + " (" + a.getGodIzdanja() + ")");
		
		if(a.getTipNosaca().equals(TipNosaca.Vinyl))
			brVinyl++;
		else
			brCD++;
		
		if(a.getKategorija().equals(Kategorija.polovna))
			brPolovnih += a.getBrNosaca();
		else
			brNovih += a.getBrNosaca();
	}

	public String getIzvodjac() {
		return izvodjac;
	}

	public List<Artikal> getArtikli() {
		return artikli;
	}

	public int getBrVinyl() {
		return brVinyl;
	}

	public int getBrCD() {
		return brCD;
	}

	public int getBrNovih() {
		return brNovih;
	}

	public int getBrPolovnih() {
		return brPolovnih;
	}

	public int getVrednost() {
		return vredost;
	}

	public List<String> getAlbumi() {
		return albumi;
	}
	
	public int getUkKomada() {
		return ukKomada;
	}

	public int getVredost() {
		return vredost;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof ArtikalKorpa)) return false;
		
		ArtikalKorpa ak = (ArtikalKorpa)obj;
		if(izvodjac.equals(ak.izvodjac)) return true;
		return false;
	}
}
