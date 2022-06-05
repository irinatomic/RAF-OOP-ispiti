package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Akcija;
import model.Polaznik;

public class PregledController implements EventHandler<ActionEvent> {

	private ListView<Polaznik> lvPolaznici;
	private TableView<Akcija> tvAkcije;
	
	public PregledController(ListView<Polaznik> lvPolaznici, TableView<Akcija> tvAkcije) {
		this.lvPolaznici = lvPolaznici;
		this.tvAkcije = tvAkcije;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Akcija> zaPrikaz = new ArrayList<Akcija>();	
		for(Polaznik p : lvPolaznici.getSelectionModel().getSelectedItems()) {
			zaPrikaz.addAll(p.getAkcije());
		}		
		tvAkcije.getItems().clear();
		tvAkcije.getItems().addAll(zaPrikaz);
	}
	
}
