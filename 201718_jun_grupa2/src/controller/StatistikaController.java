package controller;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import model.Baza;
import model.Reprezentacija;
import model.Utakmica;
import view.StatistikaView;

public class StatistikaController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		StatistikaView view = new StatistikaView();
		
		String utakmicaMaxGolova = "";
		int maxUtakmGolova = 0;
		for(Utakmica u : Baza.getSpisakUtakmica()) {
			int brGolova = u.getGoloviTim1() + u.getGoloviTim2();
			if(brGolova > maxUtakmGolova) {
				maxUtakmGolova = brGolova;
				utakmicaMaxGolova = u.getTim1() + " - " + u.getTim2() + " - " + maxUtakmGolova;
			}
		}
		view.getTfUtakmicaMaxGolova().setText(utakmicaMaxGolova);
		
		String repMaxDatihGolova = "";
		int maxRepDatihGolova = 0;
		List<String> repSaTriPobede = new ArrayList<String>();
		for(Reprezentacija r : Baza.getSpisakReprezentacija()) {
			if(r.getBrDatihGolova() > maxRepDatihGolova) {
				repMaxDatihGolova = r.getNaziv();
				maxRepDatihGolova = r.getBrDatihGolova();
			}
			
			if(r.getBrPobeda() == 3)
				repSaTriPobede.add(r.getNaziv());
		}
		view.getTfRepMaxGolova().setText(repMaxDatihGolova + " - " + maxRepDatihGolova);
		view.getLvRepTriPobede().getItems().addAll(repSaTriPobede);
		
		Scene scene = new Scene(view);
		Main.getWindow().setScene(scene);
	}
	
}
