package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.Artikal;
import model.Baza;

public class PrikaziSveController implements EventHandler<ActionEvent> {

	private TableView<Artikal> tvArtikli;

	public PrikaziSveController(TableView<Artikal> tvArtikli) {
		this.tvArtikli = tvArtikli;
	}

	@Override
	public void handle(ActionEvent event) {
		tvArtikli.getItems().clear();
		tvArtikli.getItems().addAll(Baza.getArtikli());
	}

}
