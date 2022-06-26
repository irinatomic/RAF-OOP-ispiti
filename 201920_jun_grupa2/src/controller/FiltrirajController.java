package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import model.Artikal;
import model.Baza;
import model.TipNosaca;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbIzvodjac;
	private ToggleGroup tgTipNosaca;
	private ComboBox<String> cmbVeceManje;
	private TextField tfCena;
	private TableView<Artikal> tvArtikli;
	
	public FiltrirajController(ComboBox<String> cmbIzvodjac, ToggleGroup tgTipNosaca, ComboBox<String> cmbVeceManje,
			TextField tfCena, TableView<Artikal> tvArtikli) {
		this.cmbIzvodjac = cmbIzvodjac;
		this.tgTipNosaca = tgTipNosaca;
		this.cmbVeceManje = cmbVeceManje;
		this.tfCena = tfCena;
		this.tvArtikli = tvArtikli;
	}

	@Override
	public void handle(ActionEvent event) {
		String izvodjac = cmbIzvodjac.getSelectionModel().getSelectedItem();
		String poredjenje = cmbVeceManje.getSelectionModel().getSelectedItem();
		int cena = Integer.parseInt(tfCena.getText());

		//AKO KORISNIK NIJE SELEKTOVAO SVE POTREBNO
		if(izvodjac == "" || tgTipNosaca.getSelectedToggle() == null || poredjenje == "" || cena == 0) {
			new Alert(AlertType.ERROR, "Morate odabrati sve selektovano").show();
			return;
		}

		String tipNosaca = ((RadioButton)tgTipNosaca.getSelectedToggle()).getText();
		List<Artikal> filtrArtikli = new ArrayList<Artikal>();
		
		//FILTRIRAMO ZA IZVODJACA
		for(Artikal a : Baza.getArtikli()) {
			if(a.getIzvodjac().equals(izvodjac))
				filtrArtikli.add(a);
		}

		//FILTRIRAMO ZA TIP NOSACA
		Iterator<Artikal> it = filtrArtikli.iterator();
		while(it.hasNext()) {
			Artikal a = it.next();
			if(tipNosaca.equals("Vinyl i CD"))
				continue;
			
			if(tipNosaca.equals("Vinyl") && a.getTipNosaca().equals(TipNosaca.CD) ||
				tipNosaca.equals("CD") && a.getTipNosaca().equals(TipNosaca.Vinyl))
				it.remove();
		}
		
		//FILTRIRAMO ZA CENU
		it = filtrArtikli.iterator();
		while(it.hasNext()) {
			Artikal a = it.next();
			if(poredjenje.equals(">") && a.getCena() > cena || poredjenje.equals("<") && a.getCena() < cena ||
				poredjenje.equals("=") && a.getCena() == cena)
				continue;
			else
				it.remove();
			
		}
		
		tvArtikli.getItems().clear();
		tvArtikli.getItems().addAll(filtrArtikli);
	}

}
