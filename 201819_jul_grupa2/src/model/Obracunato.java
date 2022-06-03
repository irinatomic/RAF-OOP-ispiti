package model;

import java.util.ArrayList;
import java.util.List;

public class Obracunato {

	private String ucionica;
	private List<String> satnica;
	private List<String> grupe;
	
	public Obracunato(String ucionica, Sat pocetak, Sat kraj, String grupe) {
		this.ucionica = ucionica;
		this.grupe = new ArrayList<String>();
		
		String[] args = grupe.split(" ");
		for(String s : args)
			this.grupe.add(s);
		
		satnica = new ArrayList<String>();
		satnica.add(pocetak + "-" + kraj);
	}
	
	public void dodajGrupe(String grupe) {
		String[] args = grupe.split(" ");
		for(String s : args) {
			if(!this.grupe.contains(s))
				this.grupe.add(s);
		}
	}

	public void dodajSatnicu(Sat pocetak, Sat kraj) {
		int noviSatPocetka = pocetak.getSat();
		int noviSatKraja = kraj.getSat();
		boolean nadovezano = false;
		
		for(String s : satnica) {	
			String next = s.toString();
			String[] args = next.split("[:-]");
			int stariSatPocetka = Integer.parseInt(args[0]);
			int stariSatKraja = Integer.parseInt(args[2]);
						
			//AKO NOVI POCINJE U TOKU STAROG
			if(noviSatPocetka >= stariSatPocetka && noviSatPocetka <= stariSatKraja) {
				if(noviSatKraja > stariSatKraja) {
					String novi = args[0] + ":" + args[1] + "-" + kraj;
					satnica.set(satnica.indexOf(s), novi);
				}
				nadovezano = true;
				break;
			}
				
			//AKO SE NOVI ZAVRSAVA U TOKU STAROG
			if(noviSatKraja >= stariSatPocetka && noviSatKraja <= stariSatKraja) {
				if(noviSatPocetka < stariSatPocetka) {
					String novi = pocetak + "-" + args[2] + ":" + args[3];
					satnica.set(satnica.indexOf(s), novi);
				}
				nadovezano = true;
				break;
			}
		}
		
		if(!nadovezano)
			satnica.add(pocetak + "-" + kraj);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Obracunato)) return false;
		
		Obracunato o = (Obracunato)obj;
		if(ucionica.equals(o.ucionica)) return true;
		return false;
	}

	public String getUcionica() {
		return ucionica;
	}

	public List<String> getSatnica() {
		return satnica;
	}

	public List<String> getGrupe() {
		return grupe;
	}
}
