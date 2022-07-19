package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import model.*;

public class UpisiContoller implements EventHandler<ActionEvent> {

	private TableView<Vanzemaljac> tvVanzemaljci;
	private ComboBox<Drzava> cmbDrzave;
	private Label lblPoruka;
	
	public UpisiContoller(TableView<Vanzemaljac> tvVanzemaljci, ComboBox<Drzava> cmbDrzave, Label lblPoruka) {
		this.tvVanzemaljci = tvVanzemaljci;
		this.cmbDrzave = cmbDrzave;
		this.lblPoruka = lblPoruka;
	}

	@Override
	public void handle(ActionEvent event) {
		Vanzemaljac v = tvVanzemaljci.getSelectionModel().getSelectedItem();
		
		if(!v.isIzgubljen()) {
			lblPoruka.setText("Vanzemaljac sa ID-jem " + v.getId() + " je vec pronadjen");
			return;
		}
		
		Kontinent kIzgubljenog = v.getKontinentKlasu();
		Drzava dPronadjen = cmbDrzave.getSelectionModel().getSelectedItem();
		Kontinent kPronadjen = null;
		for(Kontinent kBaza : Baza.getKontinenti()) {
			if(kBaza.getDrzave().contains(dPronadjen)) {
				kPronadjen = kBaza;
				break;
			}
		}
		
		if(!kIzgubljenog.equals(kPronadjen))
			lblPoruka.setText("Drzava " + dPronadjen + " ne pripada kontinentu " + kIzgubljenog + " vec kontinentu " + kPronadjen);
		else {
			v.setIzgubljen(false);
			v.setDrzava(dPronadjen);
			tvVanzemaljci.refresh();
			lblPoruka.setText("Uspesno izvrsavanje");
		}
	}	
}
