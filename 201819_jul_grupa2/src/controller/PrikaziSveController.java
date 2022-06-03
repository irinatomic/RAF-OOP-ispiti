package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.Baza;
import model.Termin;

public class PrikaziSveController implements EventHandler<ActionEvent> {

	private TableView<Termin> tvTermini;

	public PrikaziSveController(TableView<Termin> tvTermini) {
		this.tvTermini = tvTermini;
	}

	@Override
	public void handle(ActionEvent event) {
		tvTermini.getItems().clear();
		tvTermini.getItems().addAll(Baza.getSviTermini());
	}

}
