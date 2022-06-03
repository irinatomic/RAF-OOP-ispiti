package controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.Baza;
import model.Obracunato;
import model.Termin;

public class ObracunajController implements EventHandler<ActionEvent> {

	private TableView<Termin> tvTermini;
	private TableView<Obracunato> tvObracunato;

	public ObracunajController(TableView<Termin> tvTermini, TableView<Obracunato> tvObracunato) {
		this.tvTermini = tvTermini;
		this.tvObracunato = tvObracunato;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Termin> selektovani = tvTermini.getSelectionModel().getSelectedItems();
		
		for(Termin t : selektovani) {
			Obracunato newO = new Obracunato(t.getUcionica(), t.getPocetak(), t.getKraj(), t.getGrupa());
			//AKO NE POSTOJI U BAZI, SAMO SDODAJEMO NOVI OBJEKAT
			if(!Baza.getObracunato().contains(newO)) {
				Baza.getObracunato().add(newO);
				continue;
			}
			
			//AKO VEC POSTOJI U BAZI, DODAJEMO POTREBNE INFORMACIJE - GRUPE I TERMIN
			for(Obracunato bazaO : Baza.getObracunato()) {
				if(bazaO.equals(newO)) {
					bazaO.dodajGrupe(t.getGrupa());
					bazaO.dodajSatnicu(t.getPocetak(), t.getKraj());
				}
			}
		}
		
		tvObracunato.getItems().clear();
		tvObracunato.getItems().addAll(Baza.getObracunato());
	}
	
	
}
