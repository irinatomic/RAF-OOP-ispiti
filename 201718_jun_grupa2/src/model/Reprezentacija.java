package model;

public class Reprezentacija implements Comparable<Reprezentacija>{

	private String naziv;
	private int brojPoena;
	private int brDatihGolova;
	private int brPrimljenihGolova;
	private String grupa;
	
	private int brPobeda = 0;
	private int brPoraza = 0;
	private int brNeresenih = 0;

	public Reprezentacija(String naziv, int brojPoena, int brDatihGolova, int brPrimljenihGolova, String grupa) {
		this.naziv = naziv;
		this.brojPoena = brojPoena;
		this.brDatihGolova = brDatihGolova;
		this.brPrimljenihGolova = brPrimljenihGolova;
		this.grupa = grupa;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Reprezentacija)) return false;
		
		return ((Reprezentacija)obj).naziv.equals(naziv);
	}
	
	public String getGolRazlikaString() {
		return brDatihGolova + ":" + brPrimljenihGolova;
	}

	public int getGolRazlika() {
		return brDatihGolova - brPrimljenihGolova;
	}

	public String getNaziv() {
		return naziv;
	}

	public int getBrojPoena() {
		return brojPoena;
	}

	public int getBrDatihGolova() {
		return brDatihGolova;
	}

	public int getBrPrimljenihGolova() {
		return brPrimljenihGolova;
	}

	public void setBrPrimljenihGolova(int brPrimljenihGolova) {
		this.brPrimljenihGolova = brPrimljenihGolova;
	}

	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}

	public void setBrDatihGolova(int brDatihGolova) {
		this.brDatihGolova = brDatihGolova;
	}

	public String getGrupa() {
		return grupa;
	}
		
	public int getBrPobeda() {
		return brPobeda;
	}

	public void setBrPobeda(int brPobeda) {
		this.brPobeda = brPobeda;
	}

	public int getBrPoraza() {
		return brPoraza;
	}

	public void setBrPoraza(int brPoraza) {
		this.brPoraza = brPoraza;
	}

	public int getBrNeresenih() {
		return brNeresenih;
	}

	public void setBrNeresenih(int brNeresenih) {
		this.brNeresenih = brNeresenih;
	}

	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int compareTo(Reprezentacija o) {
		return naziv.compareTo(o.naziv);
	}
}
