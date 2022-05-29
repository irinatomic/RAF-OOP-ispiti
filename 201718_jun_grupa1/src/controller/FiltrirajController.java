package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Baza;
import model.Utakmica;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbGrupa;
	private TableView<Utakmica> tvUtakmice;
	
	public FiltrirajController(ComboBox<String> cmbGrupa, TableView<Utakmica> tvUtakmice) {
		this.cmbGrupa = cmbGrupa;
		this.tvUtakmice = tvUtakmice;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Utakmica> filtr = new ArrayList<Utakmica>();
		String grupa = cmbGrupa.getValue();
		
		for(Utakmica u : Baza.getSpisakUtakmica()) {
			if(u.getGrupa().equals(grupa))
				filtr.add(u);
		}
		
		tvUtakmice.getItems().clear();
		tvUtakmice.getItems().addAll(filtr);
	}
	
	
	
	
	
}
