package model;

public class Termin {

	private String naziv;
	private String vrstaNastave;
	private String nastavnik;
	private String grupa;
	private String dan;
	private Sat pocetak;
	private Sat kraj;
	private String ucionica;
	private int brCasova;
	
	public Termin(String naziv, String vrstaNastave, String nastavnik, String grupa, String dan, String satnica,
			String ucionica) {
		this.naziv = naziv;
		this.vrstaNastave = vrstaNastave;
		this.nastavnik = nastavnik;
		this.grupa = grupa;
		this.dan = dan;
		
		String args[] = satnica.split("-");
		this.pocetak = new Sat(args[0]);
		this.kraj = new Sat(args[1]);
		
		this.ucionica = ucionica;
		this.brCasova = Sat.izracunajBrCasova(pocetak, kraj);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Termin)) return false;
		
		Termin t = (Termin)obj;
		if(naziv.equals(t.naziv) && nastavnik.equals(t.nastavnik) && grupa.equals(t.grupa))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return naziv + " (" + vrstaNastave.toLowerCase() + "), " + nastavnik + ": " + ucionica + ", " + dan.toLowerCase() + ", " + 
				pocetak + "-" + kraj;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getVrstaNastave() {
		return vrstaNastave;
	}

	public String getNastavnik() {
		return nastavnik;
	}

	public String getGrupa() {
		return grupa;
	}

	public String getDan() {
		return dan;
	}

	public String getUcionica() {
		return ucionica;
	}

	public int getBrCasova() {
		return brCasova;
	}

	public Sat getPocetak() {
		return pocetak;
	}

	public Sat getKraj() {
		return kraj;
	}

}
