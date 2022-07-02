package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import model.*;
import view.MainView;

public class DodajPitanjeController implements EventHandler<ActionEvent> {

	private TextField tfPitanje;
	private TextField tfOdgovor;
	private TextField tfPredmet;
	
	public DodajPitanjeController(TextField tfPitanje, TextField tfOdgovor, TextField tfPredmet) {
		this.tfPitanje = tfPitanje;
		this.tfOdgovor = tfOdgovor;
		this.tfPredmet = tfPredmet;
	}

	@Override
	public void handle(ActionEvent event) {
		if(tfPitanje.getText().isEmpty() || tfOdgovor.getText().isEmpty() || tfPredmet.getText().isEmpty()) {
			new Alert(AlertType.ERROR, "Popunite sva polja").show();
			return;
		}
		
		Pitanje p = new Pitanje(tfPitanje.getText(), tfOdgovor.getText(), tfPredmet.getText());
		Baza.getPitanja().add(p);
		MainView.getOlPitanja().clear();
		MainView.getOlPitanja().addAll(Baza.getPitanja());
		MainView.getCmbPredmeti().setValue("Svi predmeti");
		
		tfPitanje.clear();
		tfOdgovor.clear();
		tfPredmet.clear();
	}
}
