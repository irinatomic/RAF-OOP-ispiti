package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//ZA LIST VIEW
public class AkcijaStatistika implements Comparable<AkcijaStatistika>{

	private String tipAkcije;
	private List<Akcija> akcije; 						//sve akcije
	private List<Akcija> akcijeFiltrirano;
	
	private static Comparator<AkcijaStatistika> cmp = Comparator.comparing(AkcijaStatistika :: getBrFiltrirano);
	
	public AkcijaStatistika(String tipAkcije) {
		this.tipAkcije = tipAkcije;
		akcije = new ArrayList<Akcija>();
		akcijeFiltrirano = new ArrayList<Akcija>();
	}
	
	public void dodajAkciju(Akcija a) {
		akcije.add(a);
		akcijeFiltrirano.add(a);
	}

	public void resetuj() {
		akcijeFiltrirano.clear();
		akcijeFiltrirano.addAll(akcije);
	}
	
	public void filtriraj(String filter, Date datumFilter) {
		akcijeFiltrirano.clear();
		akcijeFiltrirano.addAll(akcije);
		switch(filter) {
			case "pre" : akcijeFiltrirano.removeIf(akcija -> !(akcija.getDatum().compareTo(datumFilter) < 0)); break;
			case "posle" : akcijeFiltrirano.removeIf(akcija -> !(akcija.getDatum().compareTo(datumFilter) > 0)); break;
		}
	}
	
	//ZA COMPARATOR (SORTIRANJE)
	private int getBrFiltrirano() {
		return akcijeFiltrirano.size();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof AkcijaStatistika)) return false;
		
		AkcijaStatistika as = (AkcijaStatistika)obj;
		if(as.tipAkcije.equals(tipAkcije)) return true;
		return false;
	}
	
	@Override
	public int compareTo(AkcijaStatistika o) {
		return cmp.compare(o, this);
	}
	
	@Override
	public String toString() {
		return tipAkcije + ": " + akcijeFiltrirano.size();
	}

	public List<Akcija> getAkcijeFiltrirano() {
		return akcijeFiltrirano;
	}
}
