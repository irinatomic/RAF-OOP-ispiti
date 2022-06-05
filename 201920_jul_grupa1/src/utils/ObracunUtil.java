package utils;

import model.*;

public class ObracunUtil {

	public static String getUkupanUplacenIznos() {
		double ukupno = 0;
		for(Akcija a : Baza.getAkcije()) {
			if(a.getTipAkcije().equals(TipAkcije.UPLATA))
				ukupno += Double.parseDouble(a.getIznos());
		}
		return String.format("%.2f", ukupno);
	}
	
	public static String getUkupanDugZaCasove() {
		double ukupno = 0;
		for(Polaznik p : Baza.getPolaznici())
			ukupno += p.getDug();
		return String.format("%.2f", ukupno);
	}
	
	public static String getBrojPretplacenih() {
		int brojac = 0;
		for(Polaznik p : Baza.getPolaznici())
			if(p.getDug() == 0)
				brojac ++;
		return brojac + "";
	}
}
