package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.MainView;

public class KupiController implements EventHandler<ActionEvent> {

	private ListView<Udzbenik> lvIzabraniUdzbenici;
	private TableView<UdzbenikKorpa> tvKorpa;
	private TextField tfBrPrimeraka;

	public KupiController(ListView<Udzbenik> lvIzabraniUdzbenici, TableView<UdzbenikKorpa> tvKorpa, TextField tfBrPrimeraka) {
		super();
		this.lvIzabraniUdzbenici = lvIzabraniUdzbenici;
		this.tvKorpa = tvKorpa;
		this.tfBrPrimeraka = tfBrPrimeraka;
	}

	@Override
	public void handle(ActionEvent event) {
		if(lvIzabraniUdzbenici.getSelectionModel().getSelectedItems() == null) {
			new Alert(AlertType.ERROR, "Selektujte udzbenike iz liste").showAndWait();
			return;
		}
		
		if(tfBrPrimeraka.getText().isEmpty()) {
			new Alert(AlertType.ERROR, "Unesite zlejeni broj udzbenika").show();
			return;
		}
		
		int brUdzbenika = 0;
		try {
			brUdzbenika = Integer.parseInt(tfBrPrimeraka.getText());
		} catch (NumberFormatException e) {
			new Alert(AlertType.ERROR, "Unesite broj!").show();
			return;
		}
		
		for(Udzbenik u : lvIzabraniUdzbenici.getSelectionModel().getSelectedItems()) {
			//AKO ZELIMO DA KUPIMO VISE OD BROJA UDZBENIKA NA STANJU NISTA SE NE DESAVA
			if(brUdzbenika > u.getBrPrimeraka())
				continue;
			
			//STAVLJAMO UDZBENIKE U KORPU
			UdzbenikKorpa uk = new UdzbenikKorpa(u.getNaslov(), u.getIzdavac(), brUdzbenika, u.getCenaPrimerka());
			int ukIndex = MainView.getOlKorpa().indexOf(uk);
			if(ukIndex == -1)
				MainView.getOlKorpa().add(uk);
			else {
				uk = MainView.getOlKorpa().get(ukIndex);
				uk.dodajPrimerke(brUdzbenika);
			}
			
			//UDZBENIKU ODUZIMAMO POTREBAN BROJ PRIMERAKA
			u.setBrPrimeraka(u.getBrPrimeraka() - brUdzbenika);
		}
		
		//REFRESHUJEMO LISTU I KORPU
		tvKorpa.refresh();
		lvIzabraniUdzbenici.refresh();
	}	
}
