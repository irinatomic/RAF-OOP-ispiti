package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Baza;
import model.Termin;

public class PrikaziSveController implements EventHandler<ActionEvent> {

	private TableView<Termin> tvTermini;
	private ComboBox<String> cmbUcionice;
	private TextField tfOd;
	private TextField tfDo;

	public PrikaziSveController(TableView<Termin> tvTermini, ComboBox<String> cmbUcionice, TextField tfOd, TextField tfDo) {
		this.tvTermini = tvTermini;
		this.cmbUcionice = cmbUcionice;
		this.tfOd = tfOd;
		this.tfDo = tfDo;
	}

	@Override
	public void handle(ActionEvent event) {
		cmbUcionice.getSelectionModel().select(null);
		tfOd.setText("");
		tfDo.setText("");
		tvTermini.getItems().clear();
		tvTermini.setItems(FXCollections.observableArrayList(Baza.getInstance().getSviTermini()));;
	}
}
