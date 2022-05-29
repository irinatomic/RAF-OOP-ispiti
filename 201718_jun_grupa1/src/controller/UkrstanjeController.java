package controller;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import model.Baza;
import model.Reprezentacija;
import utils.PlasirajReprezentacije;
import view.UkrstenoStage;

public class UkrstanjeController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		List<Reprezentacija> grupa1 = new ArrayList<Reprezentacija>();
		List<Reprezentacija> grupa2 = new ArrayList<Reprezentacija>();
		
		for(int i = 1; i < Baza.getGrupe().size() - 1; i++) {
			System.out.println(i);
			grupa1.clear();
			grupa2.clear();
			for(Reprezentacija r : Baza.getSpisakReprezentacija()) {
				if(r.getGrupa().equals(Baza.getGrupe().get(i)) && !grupa1.contains(r))
					grupa1.add(r);
				else if(r.getGrupa().equals(Baza.getGrupe().get(i + 1)) && !grupa2.contains(r))
					grupa2.add(r);
			}
			grupa1.sort(new PlasirajReprezentacije());
			grupa2.sort(new PlasirajReprezentacije());
		
			String utakmica1 = grupa1.get(0) + " - " + grupa2.get(1);
			String utakmica2 = grupa2.get(0) + " - " + grupa1.get(1);
			
			Baza.getUkrsteneUtakmice().add(utakmica1);
			Baza.getUkrsteneUtakmice().add(utakmica2);
		}
		Main.getWindow().setScene(new Scene(new UkrstenoStage(), 600, 600));
	}

}
