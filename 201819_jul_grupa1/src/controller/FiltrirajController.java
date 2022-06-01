package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Sat;
import model.Termin;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbUcionica;
	private TextField tfOd;
	private TextField tfDo;
	private TableView<Termin> tvTermini;
	
	public FiltrirajController(ComboBox<String> cmbUcionica, TextField tfOd, TextField tfDo, TableView<Termin> tvTermini) {
		this.cmbUcionica = cmbUcionica;
		this.tfOd = tfOd;
		this.tfDo = tfDo;
		this.tvTermini = tvTermini;
	}

	@Override
	public void handle(ActionEvent event) {
		if(cmbUcionica.getSelectionModel().getSelectedItem() == null || tfOd.getText().equals("") || tfDo.getText().equals("")) {
			new Alert(AlertType.ERROR, "Morate popuniti sve kirterijume").show();
			return;
		}
		
		String ucionica = cmbUcionica.getSelectionModel().getSelectedItem().toString();		
		int pocetakUkMin = Sat.getUkMin(new Sat(tfOd.getText()));
		int krajUkMin =Sat.getUkMin(new Sat(tfDo.getText()));
		List<Termin> filtrTermini = new ArrayList<Termin>();
		
		System.out.println(ucionica + " " + pocetakUkMin + " " + krajUkMin);
		
		for(Termin t : Baza.getInstance().getSviTermini()) {
			if(t.getUcionica().equals(ucionica) && pocetakUkMin < Sat.getUkMin(t.getPocetak()) && krajUkMin > Sat.getUkMin(t.getKraj()))
				filtrTermini.add(t);
		}
		
		tvTermini.getItems().clear();
		tvTermini.setItems(FXCollections.observableArrayList(filtrTermini));
	}

}
