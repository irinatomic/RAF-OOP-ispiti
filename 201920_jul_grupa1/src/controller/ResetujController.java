package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import model.Baza;
import model.Polaznik;

public class ResetujController implements EventHandler<ActionEvent> {

	private ListView<Polaznik> lvPolaznici;

	public ResetujController(ListView<Polaznik> lvPolaznici) {
		this.lvPolaznici = lvPolaznici;
	}

	@Override
	public void handle(ActionEvent event) {
		lvPolaznici.getItems().clear();
		lvPolaznici.getItems().addAll(Baza.getPolaznici());
	}
}
