package model;

import java.util.Comparator;

public class Vanzemaljac implements Comparable<Vanzemaljac>{

	private int id;
	private Kontinent kontinent;
	private Drzava drzava;
	private boolean izgubljen;
	
	private static Comparator<Vanzemaljac> cmp = Comparator.comparing(Vanzemaljac :: getId);
	
	public Vanzemaljac(int id, Kontinent kontinent, Drzava drzava, boolean izgubljen) {
		this.id = id;
		this.kontinent = kontinent;
		this.drzava = drzava;
		this.izgubljen = izgubljen;
	}
	
	@Override
	public String toString() {
		return id + " " + kontinent + " " + drzava + " izgubljen: " + izgubljen;
	}

	public int getId() {
		return id;
	}

	public String getKontinent() {
		return kontinent.getNaziv();
	}

	public String getDrzava() {
		if(drzava == null)
			return "";
		return drzava.getNaziv();
	}
	
	public Kontinent getKontinentKlasu() {
		return kontinent;
	}

	public Drzava getDrzavaKlasu() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public boolean isIzgubljen() {
		return izgubljen;
	}

	public void setIzgubljen(boolean izgubljen) {
		this.izgubljen = izgubljen;
	}

	@Override
	public int compareTo(Vanzemaljac o) {
		return cmp.compare(this, o);
	}
}
