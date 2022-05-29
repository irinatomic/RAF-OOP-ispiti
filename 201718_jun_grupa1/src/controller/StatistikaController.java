package controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Utakmica;
import view.MainStage;

public class StatistikaController implements EventHandler<ActionEvent> {

	private MainStage view;

	public StatistikaController(MainStage view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		List<Utakmica> utakmice = view.getTvUtakmice().getItems();
		
		int ukGolova = 0;
		String timNajGolova = "";
		int najGolova = 0;
		Utakmica utakmcNajGolRazlika = null;
		String timNajGolRazlika = "";
		int najGolRazlika = 0;
		int brNeresenih = 0;
		
		for(Utakmica u : utakmice) {
			ukGolova += u.getGoloviTim1() + u.getGoloviTim2();
			
			if(u.getGoloviTim1() > najGolova) {
				timNajGolova = u.getTim1();
				najGolova = u.getGoloviTim1();
			} else if(u.getGoloviTim2() > najGolova) {
				timNajGolova = u.getTim2();
				najGolova = u.getGoloviTim2();
			}
			
			if(Math.abs(u.getGoloviTim1() - u.getGoloviTim2()) > najGolRazlika) {
				utakmcNajGolRazlika = u;
				najGolRazlika = Math.abs(u.getGoloviTim1() - u.getGoloviTim2());
				if(u.getGoloviTim1() > u.getGoloviTim2())
					timNajGolRazlika = u.getTim1();
				else
					timNajGolRazlika = u.getTim2();
			}
			
			if(u.getGoloviTim1() == u.getGoloviTim2())
				brNeresenih++;
		}
		
		view.getTfUkGolova().setText(ukGolova + "");
		view.getTfTimNajGolova().setText(timNajGolova + " - " + najGolova);
		view.getTfTimNajGolRazlika().setText(timNajGolRazlika + " - " + utakmcNajGolRazlika.getGoloviTim1() + ":" + utakmcNajGolRazlika.getGoloviTim2()); 
		view.getTfBrNeresenih().setText(brNeresenih + "");
 	}

}
