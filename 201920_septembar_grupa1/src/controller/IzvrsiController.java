package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Izdavac;
import model.Udzbenik;
import view.MainView;

public class IzvrsiController implements EventHandler<ActionEvent> {

	private TableView<Izdavac> tvIzdavaci;
	private TableView<Udzbenik> tvUdzbenici;
	private ComboBox<String> cmbIzmeni;
	private TextField tfIzmeni;

	public IzvrsiController(TableView<Izdavac> tvIzdavaci, TableView<Udzbenik> tvUdzbenici, ComboBox<String> cmbIzmeni, TextField tfIzmeni) {
		super();
		this.tvIzdavaci = tvIzdavaci;
		this.tvUdzbenici = tvUdzbenici;
		this.cmbIzmeni = cmbIzmeni;
		this.tfIzmeni = tfIzmeni;
	}

	@Override
	public void handle(ActionEvent event) {
		int brPrimeraka = 0;
		try {
			brPrimeraka = Integer.parseInt(tfIzmeni.getText());
		} catch (NumberFormatException e){
			new Alert(AlertType.ERROR, "Unesite broj u text field").show();
			return;
		}
		
		if(tvUdzbenici.getSelectionModel().getSelectedItems() == null) {
			new Alert(AlertType.ERROR, "Selektujte udzbenike u tabeli").show();
			return;
		}
		
		String izbor = cmbIzmeni.getSelectionModel().getSelectedItem();
		for(Udzbenik u : tvUdzbenici.getSelectionModel().getSelectedItems()) {
			switch(izbor) {
				case "Dodaj primerke": u.setBrPrimeraka(u.getBrPrimeraka() + brPrimeraka); break;
				case "Smanji broj primeraka": u.setBrPrimeraka(u.getBrPrimeraka() - brPrimeraka); break;
			}
			
			if(u.getBrPrimeraka() <= 0)
				MainView.getOlUdzbeniciPrikaz().remove(u);
		}
		
		tvIzdavaci.refresh();
		tvUdzbenici.refresh();
	}
}
