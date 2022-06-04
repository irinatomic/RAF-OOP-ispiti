package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Artikal;
import model.ArtikalKorpa;
import model.Baza;

public class DodajUKorpuController implements EventHandler<ActionEvent> {

	private ListView<Artikal> lvArtikli;
	private ComboBox<Integer> cmbKolicina;
	private Label lblUkCena;
	
	public DodajUKorpuController(ListView<Artikal> lvArtikli, ComboBox<Integer> cmbKolicina, Label lblUkCena) {
		this.lvArtikli = lvArtikli;
		this.cmbKolicina = cmbKolicina;
		this.lblUkCena = lblUkCena;
	}

	@Override
	public void handle(ActionEvent event) {
		int kolicina = cmbKolicina.getValue();
		double ukCena = 0;
		
		for(Artikal a : lvArtikli.getSelectionModel().getSelectedItems()) {
			ArtikalKorpa ak = new ArtikalKorpa(a, a.toString(), kolicina, a.getCena(), a.getTip());
			Baza.dodajUKorpu(ak);
		}
		
		for(ArtikalKorpa ak : Baza.getKorpa())
			ukCena += ak.getUkCena();
		
		lblUkCena.setText(ukCena + "");
	}

}
