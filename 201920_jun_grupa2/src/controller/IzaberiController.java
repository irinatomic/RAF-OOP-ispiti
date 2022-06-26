package controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Artikal;
import model.Baza;

public class IzaberiController implements EventHandler<ActionEvent> {

	private TableView<Artikal> tvArtikli;
	private TextField tfUkupnoKosta;

	public IzaberiController(TableView<Artikal> tvArtikli, TextField tfUkupnoKosta) {
		this.tvArtikli = tvArtikli;
		this.tfUkupnoKosta = tfUkupnoKosta;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Artikal> selektovani = tvArtikli.getSelectionModel().getSelectedItems();
		Baza.getOlKorpa().addAll(selektovani);
		
		int ukKosta = 0;
		for(Artikal a : Baza.getOlKorpa())
			ukKosta += a.getCena();
		
		tfUkupnoKosta.setText(ukKosta + "");
	}

}
