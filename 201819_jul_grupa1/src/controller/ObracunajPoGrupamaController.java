package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.Baza;
import model.Selekcija;
import model.Termin;

public class ObracunajPoGrupamaController implements EventHandler<ActionEvent> {

	private TableView<Termin> tvTermini;
	private TableView<Selekcija> tvSelekcija;
		
	public ObracunajPoGrupamaController(TableView<Termin> tvTermini, TableView<Selekcija> tvSelekcija) {
		this.tvTermini = tvTermini;
		this.tvSelekcija = tvSelekcija;
	}

	@Override
	public void handle(ActionEvent event) {
		ObservableList<Termin> selektTermini = tvTermini.getSelectionModel().getSelectedItems();
		
		for(Termin t : selektTermini) {
			String[] grupe = t.getGrupa().split(" ");
			for(String grupa : grupe) {
				if(Baza.getInstance().getGrupeUSelekciji().contains(grupa)) {
					for(Selekcija s : Baza.getInstance().getSelekcija()) {
						if(s.getGrupa().equals(grupa)) {
							s.dodajDan(t.getDan());
							s.dodajNastavnika(t.getNastavnik());
							s.dodajTermin(t);
						}
					}
				} 
				else {
					Selekcija s = new Selekcija(grupa);
					s.dodajDan(t.getDan());
					s.dodajNastavnika(t.getNastavnik());
					s.dodajTermin(t);
					Baza.getInstance().getSelekcija().add(s);
					Baza.getInstance().getGrupeUSelekciji().add(grupa);
				}
			}
		}
		tvSelekcija.setItems(FXCollections.observableArrayList(Baza.getInstance().getSelekcija()));
		tvSelekcija.refresh();
	}

}
