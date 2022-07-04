package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.*;
import view.RacunView;

public class RacunController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		int ukCena = 0;
		StringBuilder sb = new StringBuilder();
		for(UdzbenikKorpa uk : Baza.getUdzbeniciKorpa()) {
			sb.append(uk.getCenaUkupno() + "-" + uk.getNaslov() + ", " + uk.getIzdavac() + "\n");
			ukCena += uk.getCenaUkupno();
		}
		sb.append("Ukupna cena: " + ukCena);
		RacunView.getInstance();
		RacunView.setRacun(sb.toString());
	}
}
