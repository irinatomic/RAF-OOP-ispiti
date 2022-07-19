package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static List<Kontinent> kontinenti;
	private static List<Drzava> drzave;
	private static List<Vanzemaljac> vanzemaljci;
	
	static {
		kontinenti = new ArrayList<Kontinent>();
		kontinenti.add(new Kontinent("Svi kontinenti"));
		drzave = new ArrayList<Drzava>();
		vanzemaljci = new ArrayList<Vanzemaljac>();
	}
	
	public static void ucitajFajl(String fileName) {
		FileUtil.ucitajFajl(fileName);
		Collections.sort(vanzemaljci);
	}
	
	public static void obracunajPronadjene() {
		for(Vanzemaljac v : vanzemaljci) {
			if(!v.isIzgubljen()) {
				Drzava d = v.getDrzavaKlasu();
				d.setBrPronadjenih(d.getBrPronadjenih() + 1);
			}
		}
		
		for(Kontinent k : kontinenti)
			k.izracunajBrPronadjenih();
	}

	public static List<Kontinent> getKontinenti() {
		return kontinenti;
	}

	public static List<Drzava> getDrzave() {
		return drzave;
	}

	public static List<Vanzemaljac> getVanzemaljci() {
		return vanzemaljci;
	}
}
