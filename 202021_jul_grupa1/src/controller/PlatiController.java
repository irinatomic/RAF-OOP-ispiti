package controller;

import java.util.Calendar;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.StartView;

public class PlatiController implements EventHandler<ActionEvent> {

	private TextField tfIznos;
	private TextField tfPrimalac;
	private ComboBox<String> cmbKategorije;
	
	public PlatiController(TextField tfIznos, TextField tfPrimalac, ComboBox<String> cmbKategorije) {
		this.tfIznos = tfIznos;
		this.tfPrimalac = tfPrimalac;
		this.cmbKategorije = cmbKategorije;
	}

	@Override
	public void handle(ActionEvent event) {
		if(tfIznos.getText() == "" || tfPrimalac.getText() == "" || cmbKategorije.getSelectionModel().getSelectedIndex() == -1) {
			new Alert(AlertType.ERROR, "Ispunite sve informacije").show();
			return;
		}
		
		int iznos = 0;
		try {
			iznos = Integer.parseInt(tfIznos.getText());
		} catch(NumberFormatException e) {
			new Alert(AlertType.ERROR, "Ukucajte broj za iznos").show();
			return;
		}
		
		Akcija akcija = new Isplata(iznos, Calendar.getInstance().getTime(), tfPrimalac.getText(), cmbKategorije.getSelectionModel().getSelectedItem());
		Baza.getAkcije().add(akcija);
		
		Main.getWindow().setScene(new Scene(new StartView(), 500, 320));
		Main.getWindow().show();
	}
}
