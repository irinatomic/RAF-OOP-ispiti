package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Reprezentacija;
import utils.ReprezentacijaComparator;
import view.MainView;

public class PrikaziTabeluController implements EventHandler<ActionEvent> {

	private MainView view;

	public PrikaziTabeluController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		String trazenaGrupa = view.getCmbGrupa().getValue();
		
		if(trazenaGrupa.equals("")) {
			view.getTvReprezentacije().getItems().clear();
			new Alert(AlertType.INFORMATION, "Morate odabrati grupu").show();
			return;
		}
		
		List<Reprezentacija> filtrReprezentacije = new ArrayList<Reprezentacija>();
		for(Reprezentacija r : Baza.getSpisakReprezentacija()) {
			if(r.getGrupa().equals(trazenaGrupa))
				filtrReprezentacije.add(r);
		}
		
		filtrReprezentacije.sort(new ReprezentacijaComparator());
		view.getTvReprezentacije().getItems().clear();
		view.getTvReprezentacije().getItems().addAll(filtrReprezentacije);
	}
	
}
