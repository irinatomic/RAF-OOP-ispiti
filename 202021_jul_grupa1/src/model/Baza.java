package model;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class Baza {

	private static Baza instance;
	private static List<Akcija> akcije;
	private static List<String> kategorije;

	private Baza() {
		kategorije = new ArrayList<String>();
		akcije = FileUtil.ucitajPlacanja("placanja.txt");
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<Akcija> getAkcije() {
		return akcije;
	}

	public static List<String> getKategorije() {
		return kategorije;
	}
}
