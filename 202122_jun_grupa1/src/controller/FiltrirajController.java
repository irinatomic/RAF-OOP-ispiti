package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import model.*;
import view.MainView;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<Kontinent> cmbKontinenti;
	private CheckBox cbPronadjen;
	private CheckBox cbIzgubljen;

	public FiltrirajController(ComboBox<Kontinent> cmbKontinenti, CheckBox cbPronadjen, CheckBox cbIzgubljen) {
		this.cmbKontinenti = cmbKontinenti;
		this.cbPronadjen = cbPronadjen;
		this.cbIzgubljen = cbIzgubljen;
	}

	@Override
	public void handle(ActionEvent event) {
		boolean pronadjeni = cbPronadjen.isSelected();
		boolean izgubljeni = cbIzgubljen.isSelected();
		Kontinent selektovano = cmbKontinenti.getSelectionModel().getSelectedItem();
		
		if(pronadjeni && izgubljeni)
			return;
		
		//UVEK FILTRIRAMO NAD LISTOM SVIH VANZEMALJACA
		MainView.getOlVanzemaljci().clear();
		MainView.getOlVanzemaljci().addAll(Baza.getVanzemaljci());
		
		if(selektovano.getNaziv().equals("Svi kontinenti")) {
			if(pronadjeni && !izgubljeni)
				MainView.getOlVanzemaljci().removeIf(v -> v.isIzgubljen());
			 else if(!pronadjeni && izgubljeni)
				 MainView.getOlVanzemaljci().removeIf(v -> !v.isIzgubljen());
			return;
		}
		
		//AKO JE SELEKTOVAN SAMO KONTINENT
		if(!pronadjeni && !izgubljeni)
			MainView.getOlVanzemaljci().removeIf(v -> !v.getKontinent().equals(selektovano.getNaziv()));
		else if(pronadjeni && !izgubljeni)
			MainView.getOlVanzemaljci().removeIf(v -> (!v.getKontinent().equals(selektovano.getNaziv()) || v.isIzgubljen()));
		else 
			MainView.getOlVanzemaljci().removeIf(v -> (!v.getKontinent().equals(selektovano.getNaziv()) || !v.isIzgubljen()));
	}
}
