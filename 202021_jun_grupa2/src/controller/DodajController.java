package controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.TerminiView;

public class DodajController implements EventHandler<ActionEvent> {

	private TextField tfDodajTermin;
	private ListView<Ucionica> lvUcionice;
	
	public DodajController(TextField tfDodajTermin, ListView<Ucionica> lvUcionice) {
		this.tfDodajTermin = tfDodajTermin;
		this.lvUcionice = lvUcionice;
	}

	@Override
	public void handle(ActionEvent event){
		List<Ucionica> ucionice = lvUcionice.getSelectionModel().getSelectedItems();
		int satnica = 0;
		
		try {
			satnica = Integer.parseInt(tfDodajTermin.getText());
			if(satnica < 9 || satnica > 19)
				throw new Exception();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Niste uneli dobar broj").show();
			return;
		}
		
		Baza.dodajSatnicu(satnica);
		for(Ucionica u : ucionice) {
			Termin t = new Termin(u, satnica);
			if(!Baza.getTermini().contains(t)) 				//ne zelimo da dupliramo termine
				TerminiView.getOlTermini().add(t);
		}
	}
}
