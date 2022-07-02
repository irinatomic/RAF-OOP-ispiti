package model;

import java.time.LocalDate;

public class Zaposleni {

	private String ime;
	private String prezime;
	private String jmbg;
	private int godZaposlenja;
	private String pozicija;
	private double plata;
	
	public Zaposleni(String ime, String prezime, String jmbg, int godZaposlenja, String pozicija) {
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.godZaposlenja = godZaposlenja;
		this.pozicija = pozicija;
		this.plata = 0;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getPozicija() {
		return pozicija;
	}

	public double getPlata() {
		return Math.round(plata * 100) / 100;
	}
	
	public int getGodStaza() {
		int trenutnaGod = LocalDate.now().getYear();
		return trenutnaGod - godZaposlenja;
	}
	
	public void obracunajPlatu(int cenaRada) {
		double platniKoef = Baza.getPlatniKoeficijenti().get(pozicija);
		plata = cenaRada * (platniKoef + 0.05 * getGodStaza());
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}
	
	public String ispisUFajl() {
		return ime + "," + prezime + "," + jmbg + "," + godZaposlenja;
	}
}
