package controller;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.AkcijaStatistika;
import model.Baza;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbFilter;
	private TextField tfDanFilter;
	private TextField tfMesecFilter;
	private TextField tfGodinaFilter;
	private ListView<AkcijaStatistika> lvStatistika;
	
	public FiltrirajController(ComboBox<String> cmbFilter, TextField tfDanFilter, TextField tfMesecFilter, TextField tfGodinaFilter, ListView<AkcijaStatistika> lvStatistika) {
		this.cmbFilter = cmbFilter;
		this.tfDanFilter = tfDanFilter;
		this.tfMesecFilter = tfMesecFilter;
		this.tfGodinaFilter = tfGodinaFilter;
		this.lvStatistika = lvStatistika;
	}

	@Override
	public void handle(ActionEvent event) {
		if(tfDanFilter.getText().isEmpty() || tfMesecFilter.getText().isEmpty() || tfGodinaFilter.getText().isEmpty()) {
			new Alert(AlertType.ERROR, "Popunite polja za datum").showAndWait();
			return;
		}
		
		int dan = 0;
		int mesec = 0;
		int godina = 0;
		try {
			dan = Integer.parseInt(tfDanFilter.getText());
			mesec = Integer.parseInt(tfMesecFilter.getText());
			godina = Integer.parseInt(tfGodinaFilter.getText());
		} catch (NumberFormatException e) {
			new Alert(AlertType.ERROR, "Unesite broj").showAndWait();
			return;
		}
		
		Date datumFilter = new Date(godina-1900, mesec, dan);
		for(AkcijaStatistika as : Baza.getStatistika())
			as.filtriraj(cmbFilter.getSelectionModel().getSelectedItem(), datumFilter);
		lvStatistika.refresh();
	}
}
