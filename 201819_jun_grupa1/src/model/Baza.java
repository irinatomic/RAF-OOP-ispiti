package model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Baza {

	private static Baza instance;
	private static List<Student> sviStudenti;
	private static List<Student> upareniStudenti;
	private static List<NeupareniEmail> neupareniEmailovi;
	private static ObservableList<Student> olUpareniStudenti;
	private static ObservableList<NeupareniEmail> olNeupareniEmailovi;
 	
	private Baza() {
		sviStudenti = new ArrayList<Student>();
		upareniStudenti = new ArrayList<Student>();
		neupareniEmailovi = new ArrayList<NeupareniEmail>();
		olUpareniStudenti = FXCollections.observableList(upareniStudenti);
		olNeupareniEmailovi = FXCollections.observableList(neupareniEmailovi);
	}
	
	public static Baza getInstance() {
		if(instance == null)
			instance = new Baza();
		return instance;
	}

	public static List<Student> getSviStudenti() {
		return sviStudenti;
	}

	public static ObservableList<Student> getOlUpareniStudenti() {
		return olUpareniStudenti;
	}

	public static ObservableList<NeupareniEmail> getOlNeupareniEmailovi() {
		return olNeupareniEmailovi;
	}

	public static List<Student> getUpareniStudenti() {
		return upareniStudenti;
	}

	public static List<NeupareniEmail> getNeupareniEmailovi() {
		return neupareniEmailovi;
	}
}
