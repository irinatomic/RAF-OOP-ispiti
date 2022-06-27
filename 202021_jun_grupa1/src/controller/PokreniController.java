package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.Baza;
import model.TipUcionice;
import model.Ucionica;
import view.MainView;

public class PokreniController implements EventHandler<ActionEvent> {

	private TextField tfBrTermina;
	private TextField tfBrStudenata;
	private CheckBox cbRacunari;
	private Label lblPoruka;
	
	public PokreniController(TextField tfBrTermina, TextField tfBrStudenata, CheckBox cbRacunari, Label lblPoruka) {
		this.tfBrTermina = tfBrTermina;
		this.tfBrStudenata = tfBrStudenata;
		this.cbRacunari = cbRacunari;
		this.lblPoruka = lblPoruka;
	}

	@Override
	public void handle(ActionEvent event) {
		int zeljeniBrMesta;
		int zeljeniBrTermina;
		try {
			zeljeniBrMesta = Integer.parseInt(tfBrStudenata.getText());
			zeljeniBrTermina = Integer.parseInt(tfBrTermina.getText());
		} catch(NumberFormatException | NullPointerException e) {
			lblPoruka.setText("Pogresan format broja");
			return;
		}
		
		int brStudenata = Baza.getNerasporedjeni().size();
		int brMesta = 0;
		boolean samoRacunari = cbRacunari.isSelected();
		Baza.setSamoRacunari(samoRacunari);
		
		for(Ucionica u : Baza.getUcionice()) {
			if(samoRacunari && !u.getTipUcionice().equals(TipUcionice.R))
				continue;
			
			if(u.getTipUcionice().equals(TipUcionice.R) || u.getTipUcionice().equals(TipUcionice.NULL))
				brMesta += zeljeniBrMesta;
			else
				brMesta += 2*zeljeniBrMesta;
		}
		brMesta *= zeljeniBrTermina;
		
		if(brMesta < brStudenata) {
			lblPoruka.setText("Nije moguca raspodela");
			return;
		}
		
		Baza.setBrojPoUcionici(zeljeniBrMesta);
		Baza.kreirajTermine(zeljeniBrTermina);
		
		Main.getWindow().setScene(new Scene(new MainView()));
		Main.getWindow().show();
	}
	
	
}
