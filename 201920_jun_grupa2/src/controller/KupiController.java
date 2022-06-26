package controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import model.Artikal;
import model.ArtikalKorpa;
import model.Baza;
import utils.FileUtil;

public class KupiController implements EventHandler<ActionEvent> {

	private ListView<Artikal> lvKorpa;

	public KupiController(ListView<Artikal> lvKorpa) {
		this.lvKorpa = lvKorpa;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Artikal> korpa = lvKorpa.getItems();
		
		for(Artikal a : korpa) {
			ArtikalKorpa ak = new ArtikalKorpa(a.getIzvodjac());
			if(!Baza.getKupljeniArtikli().contains(ak)) {
				ak.dodajArtikal(a);
				Baza.getKupljeniArtikli().add(ak);
				continue;
			}
			
			for(ArtikalKorpa akBaza : Baza.getKupljeniArtikli()) {
				if(akBaza.equals(ak))
					akBaza.dodajArtikal(a);
			}
		}
		
		FileUtil.ispisiKolekciju();
	}
 
}
