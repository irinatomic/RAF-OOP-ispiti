package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.MainView;

public class IzaberiController implements EventHandler<ActionEvent> {

	private TableView<Predmet> tvPredmeti;

	public IzaberiController(TableView<Predmet> tvPredmeti) {
		this.tvPredmeti = tvPredmeti;
	}

	@Override
	public void handle(ActionEvent event) {
		if(tvPredmeti.getSelectionModel().getSelectedItem() == null) {
			new Alert(AlertType.ERROR, "Izaberi predmet za koji zelete da vidite udzbenike").showAndWait();
			return;
		}
		
		Predmet odabranPredmet = tvPredmeti.getSelectionModel().getSelectedItem();
		MainView.getOlIzabraniUdzbenici().clear();
		MainView.getOlIzabraniUdzbenici().addAll(odabranPredmet.getUdzbenici());
	}
}
