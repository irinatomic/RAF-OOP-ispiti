package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.MainView;

public class IzaberiController implements EventHandler<ActionEvent> {

	private TableView<Izdavac> tvIzdavaci;
	private ComboBox<String> cmbRazredi;
	
	public IzaberiController(TableView<Izdavac> tvIzdavaci, ComboBox<String> cmbRazredi) {
		this.tvIzdavaci = tvIzdavaci;
		this.cmbRazredi = cmbRazredi;
	}

	@Override
	public void handle(ActionEvent event) {
		if(tvIzdavaci.getSelectionModel().getSelectedItem() == null) {
			new Alert(AlertType.ERROR, "Selektuje zeljenog izdavaca").show();
			return;
		}
		
		Izdavac selektIzdavac =  tvIzdavaci.getSelectionModel().getSelectedItem();
		String selektRazred = cmbRazredi.getSelectionModel().getSelectedItem();
		MainView.getOlUdzbeniciPrikaz().clear();
		
		if(selektRazred.equals("Svi razredi")) {
			MainView.getOlUdzbeniciPrikaz().addAll(selektIzdavac.getUdzbenici());
			return;
		}
		
		for(Udzbenik u : selektIzdavac.getUdzbenici()) {
			if(selektRazred.equals(u.getRazred() + ""))
				MainView.getOlUdzbeniciPrikaz().add(u);
		}
	}	
}
