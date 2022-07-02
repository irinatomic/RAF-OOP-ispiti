package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Baza;
import model.Zaposleni;
import javafx.scene.control.Alert.AlertType;

public class ObracunajController implements EventHandler<ActionEvent> {

	private TextField tfObracunaj;

	public ObracunajController(TextField tfObracunaj) {
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
	}
}
