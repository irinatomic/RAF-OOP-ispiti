package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Termin{

	private String termin;
	private Map<String, Integer> ucionice;
	
	public Termin(int redniBroj, boolean samoRacunari) {
		this.termin = "termin" + redniBroj;
		ucionice = new HashMap<String, Integer>();
		
		for(Ucionica u : Baza.getUcionice()) {
			if(samoRacunari && !u.getTipUcionice().equals(TipUcionice.R))
				continue;
			ucionice.put(u.toString(), Baza.getBrojPoUcionici());
		}
	}

	public String getTermin() {
		return termin;
	}

	public List<String> getUcioniceString() {
		List<String> listaUcionica = new ArrayList<String>();
		
		for(Map.Entry<String, Integer> unos : ucionice.entrySet()) {
			String linija = unos.getKey().toString() + " - " + unos.getValue();
			listaUcionica.add(linija);
		}
		return listaUcionica;
	}

	public Map<String, Integer> getUcionice() {
		return ucionice;
	}
		
}
