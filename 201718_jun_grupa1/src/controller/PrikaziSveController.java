package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Baza;
import model.Utakmica;

public class PrikaziSveController implements EventHandler<ActionEvent> {

	private TableView<Utakmica> tvUtakmice;
	private ComboBox<String> cmbGrupa;

	public PrikaziSveController(TableView<Utakmica> tvUtakmice, ComboBox<String> cmbGrupa) {
		this.tvUtakmice = tvUtakmice;
		this.cmbGrupa = cmbGrupa;
	}
	@Override
	public void handle(ActionEvent event) {
		cmbGrupa.setValue("");
		tvUtakmice.getItems().clear();
		tvUtakmice.getItems().addAll(Baza.getSpisakUtakmica());
	}
	
	
}
