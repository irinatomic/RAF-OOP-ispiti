package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;

public class FilterController implements EventHandler<ActionEvent> {
	
	private TextField tfFilter;
	private ComboBox<String> cmbPozicije;
	
	public FilterController(TextField tfFilter, ComboBox<String> cmbPozicije) {
		this.tfFilter = tfFilter;
		this.cmbPozicije = cmbPozicije;
	}

	@Override
	public void handle(ActionEvent event) {
		if(tfFilter.getText().isEmpty()) {
			Baza.getOlZaposleni().addAll(Baza.getZaposleni());
			return;
		}

		String filter = tfFilter.getText();		
		if(cmbPozicije.getValue().equals("Sve pozicije"))
			Baza.getOlZaposleni().removeIf(zaposleni -> !zaposleni.getIme().contains(filter) && !zaposleni.getPrezime().contains(filter));
		else
			Baza.getOlZaposleni().removeIf(zaposleni -> !zaposleni.getIme().contains(filter) && !zaposleni.getPrezime().contains(filter) 
							|| !zaposleni.getPozicija().equals(cmbPozicije.getSelectionModel().getSelectedItem()));
	}	
}
