package utils;

import java.util.Comparator;

import model.Baza;
import model.Reprezentacija;
import model.Utakmica;

public class PlasirajReprezentacije implements Comparator<Reprezentacija> {

	@Override
	public int compare(Reprezentacija o1, Reprezentacija o2) {
		if(o1.getBrojPoena() > o2.getBrojPoena())
			return -1;
		else if(o1.getBrojPoena() < o2.getBrojPoena())
			return 1;
			
		if(o1.getGolRazlika() > o2.getGolRazlika())
			return -1;
		else if(o1.getGolRazlika() < o2.getGolRazlika())
			return 1;
				
		if(o1.getBrDatihGolova() > o2.getBrDatihGolova())
			return -1;
		else if(o1.getBrDatihGolova() < o2.getBrDatihGolova())
			return 1;
				
		for(Utakmica u : Baza.getSpisakUtakmica()) {
			if(u.getTim1().equals(o1.getNaziv()) && u.getTim2().equals(o2.getNaziv()) ||
			   u.getTim1().equals(o2.getNaziv()) && u.getTim2().equals(o1.getNaziv())) {
				
				if(u.getPobednik().equals(o1.getNaziv()))
					return -1;
				else if(u.getPobednik().equals(o2.getNaziv()))
					return 1;
				else 
					return 0;
			}
		}
		
		//NIKAD NECEMO OVDE STICI
		return 0;
	}

}
