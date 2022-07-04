package model;

public class UdzbenikKorpa {

	private String naslov;
	private String izdavac;
	private int brPrimeraka;
	private double cenaPrimerka;
	private double cenaUkupno;
	
	public UdzbenikKorpa(String naslov, String izdavac, int brPrimeraka, double cenaPrimerka) {
		this.naslov = naslov;
		this.izdavac = izdavac;
		this.brPrimeraka = brPrimeraka;
		this.cenaPrimerka = cenaPrimerka;
		cenaUkupno = brPrimeraka * cenaPrimerka;
	}

	public String getNaslov() {
		return naslov;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public int getBrPrimeraka() {
		return brPrimeraka;
	}
	
	public void dodajPrimerke(int brPrimeraka) {
		this.brPrimeraka += brPrimeraka;
		this.cenaUkupno += brPrimeraka * this.cenaPrimerka;
	}

	public double getCenaUkupno() {
		return cenaUkupno;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof UdzbenikKorpa)) return false;
		
		UdzbenikKorpa uk = (UdzbenikKorpa)obj;
		if(uk.naslov.equals(naslov) && uk.izdavac.equals(izdavac) && uk.cenaPrimerka == cenaPrimerka) return true;
		return false;
	}
}
