package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.*;
import view.MainView;

public class PrikaziController implements EventHandler<ActionEvent> {

	private ListView<AkcijaStatistika> lvStatistika;
	private TableView<Polaznik> tvPolaznici;

	public PrikaziController(ListView<AkcijaStatistika> lvStatistika, TableView<Polaznik> tvPolaznici) {
		this.lvStatistika = lvStatistika;
		this.tvPolaznici = tvPolaznici;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Polaznik> polazniciZaPrikaz = new ArrayList<Polaznik>();
		
		for(Akcija a : lvStatistika.getSelectionModel().getSelectedItem().getAkcijeFiltrirano()) {
			a.getPolaznik().getAkcijeFiltrirano().clear();
		}
		
		for(Akcija a : lvStatistika.getSelectionModel().getSelectedItem().getAkcijeFiltrirano()) {
			Polaznik p = a.getPolaznik();
			p.dodajFiltriranuAkciju(a);
			if(!polazniciZaPrikaz.contains(p))
				polazniciZaPrikaz.add(p);
		}
		
		MainView.getOlPolaznici().clear();
		MainView.getOlPolaznici().addAll(polazniciZaPrikaz);
		tvPolaznici.refresh();
	}
}
