package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Utakmica;
import view.MainView;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private MainView view;
		
	public FiltrirajController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		if(view.getCmbFilter().getValue().equals("") || view.getTfFilter().getText().equals("")) {
			new Alert(AlertType.ERROR, "Morate ispuniti oba kriterijuma").show();
			return;
		}
		
		int brGolovaFiltr;
		try {
			brGolovaFiltr = Integer.parseInt(view.getTfFilter().getText());
		} catch(NumberFormatException e) {
			new Alert(AlertType.ERROR, "Morate uneti ceo broj!").show();
			return;
		}
		
		String poredjenje = view.getCmbFilter().getValue();
		List<Utakmica> filtrUtakmice = new ArrayList<Utakmica>();
		
		//TRAZIMO UTAKMICE KOJE ISPUNJAVAJU KRITERIJUM
		for(Utakmica u : Baza.getSpisakUtakmica()) {
			int brGolova = u.getGoloviTim1() + u.getGoloviTim2();
			
			if(poredjenje.equals(">") && brGolova > brGolovaFiltr)
				filtrUtakmice.add(u);
			else if(poredjenje.equals("<") && brGolova < brGolovaFiltr)
				filtrUtakmice.add(u);
			else if(poredjenje.equals("=") && brGolova == brGolovaFiltr)
				filtrUtakmice.add(u);
		}
			
		view.getTvUtakmice().getItems().clear();
		view.getTvUtakmice().getItems().addAll(filtrUtakmice);
	}

}
