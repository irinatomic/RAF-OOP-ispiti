package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.*;
import javafx.scene.control.Alert.AlertType;

public class ObracunajController implements EventHandler<ActionEvent> {

	private TableView<Zaposleni> tvZaposleni;
	private TextField tfObracunaj;

	public ObracunajController(TableView<Zaposleni> tvZaposleni, TextField tfObracunaj) {
		this.tvZaposleni = tvZaposleni;
		this.tfObracunaj = tfObracunaj;
	}

	@Override
	public void handle(ActionEvent event) {
		int cenaRada = 0;
		try {
			cenaRada = Integer.parseInt(tfObracunaj.getText());
		} catch (NumberFormatException e){
			new Alert(AlertType.ERROR, "Niste pravilno uneli cenu rada").show();
			return;
		}
		
		for(Zaposleni z : Baza.getOlZaposleni())
			z.obracunajPlatu(cenaRada);
		
		Baza.odradiStatistiku();
		tvZaposleni.refresh();
	}
}
