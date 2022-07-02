package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import model.Baza;
import view.MainView;

public class FilterController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbPredmeti;

	public FilterController(ComboBox<String> cmbPredmeti) {
		this.cmbPredmeti = cmbPredmeti;
	}

	@Override
	public void handle(ActionEvent event) {
		String zeljeniPredmet = cmbPredmeti.getSelectionModel().getSelectedItem();
		//UVEK ZELIMO DA IZDAVAJMO IZ SVIH PITANJA IZ BAZE
		MainView.getOlPitanja().clear();
		MainView.getOlPitanja().addAll(Baza.getPitanja());
		
		if(zeljeniPredmet.equals("Svi predmeti"))
			return;
		MainView.getOlPitanja().removeIf(pitanje -> !pitanje.getPredmet().equals(zeljeniPredmet));
	}
}
