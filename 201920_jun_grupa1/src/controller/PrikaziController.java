package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import model.Artikal;
import model.Tip;
import model.Zanr;

public class PrikaziController implements EventHandler<ActionEvent> {

	private TableView<Zanr> tvZanr;
	private ToggleGroup tgTip;
	private ListView<Artikal> lvArtikli;
	
	public PrikaziController(TableView<Zanr> tvZanr, ToggleGroup tgTip, ListView<Artikal> lvArtikli) {
		this.tvZanr = tvZanr;
		this.tgTip = tgTip;
		this.lvArtikli = lvArtikli;
	}

	@Override
	public void handle(ActionEvent event) {
		//AKO KORISNIK NIJE SELEKTOVAO ZELJENO
		if(tgTip.getSelectedToggle() == null || tvZanr.getSelectionModel().getSelectedItem() == null) {
			new Alert(AlertType.ERROR, "Selektuje sta zelite").show();
			return;
		}
		
		List<Artikal> filtrArtikli = new ArrayList<Artikal>();
		String tip = ((RadioButton)tgTip.getSelectedToggle()).getText();;
		Zanr zeljeniZanr = tvZanr.getSelectionModel().getSelectedItem();
		
		//AKO JE KORISNIK IZABRAO "SVE"
		if(tip.equals("sve")) {
			lvArtikli.getItems().clear();
			lvArtikli.getItems().addAll(zeljeniZanr.getArtikliUZanru());
			return;
		}
		
		for(Artikal a : zeljeniZanr.getArtikliUZanru()) {
			if(a.getTip().equals(Tip.valueOf(tip)))
				filtrArtikli.add(a);
		}
		
		lvArtikli.getItems().clear();
		lvArtikli.getItems().addAll(filtrArtikli);
	}

}
