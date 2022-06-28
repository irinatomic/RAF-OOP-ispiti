package controller;

import java.util.List;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;
import view.TerminiView;

public class PrebaciController implements EventHandler<ActionEvent> {

	private ComboBox<Integer> cmbSatnice;
	private ComboBox<Ucionica> cmbUcionice;
	private ListView<Student> lvStudenti;
	private Label lblPoruka;
 
	public PrebaciController(ComboBox<Integer> cmbSatnice, ComboBox<Ucionica> cmbUcionice, ListView<Student> lvStudenti, Label lblPoruka) {
		this.cmbSatnice = cmbSatnice;
		this.cmbUcionice = cmbUcionice;
		this.lvStudenti = lvStudenti;
		this.lblPoruka = lblPoruka;
	}

	@Override
	public void handle(ActionEvent event) {
		//AKO NISTA NIJE SELEKTOVANO
		if(cmbSatnice.getSelectionModel().getSelectedItem() == null || cmbUcionice.getSelectionModel().getSelectedItem() == null
			|| lvStudenti.getSelectionModel().getSelectedItems() == null) {
			lblPoruka.setText("Selektujte sve potrebne kriterijume");
			return;
		}
		
		List<Student> studenti = lvStudenti.getSelectionModel().getSelectedItems();
		Termin termin = new Termin(cmbUcionice.getSelectionModel().getSelectedItem(), cmbSatnice.getSelectionModel().getSelectedItem());
		int indexTermina = Baza.getTermini().indexOf(termin);
		
		//AKO NE POSTOJI UCIONICA ZA ODABRANI TERMIN
		if(indexTermina == -1) {
			lblPoruka.setText("Odabrana ucionica nije predvidjena za dati termin");
			return;
		}
		
		termin = Baza.getTermini().get(indexTermina);
		
		//AKO U ODABRANOM TERMINU IMA MANJE SLOBODNIH MESTA OD BROJA ODABRANIH STUDENATA	
		if(termin.getSlobodno() < studenti.size()) {
			lblPoruka.setText("U odabranom terminu nema dovoljno mesta");
			return;
		}
		
		//SVE JE U REDU -> ODABRANIM STUDENTIMA MENJAMO TERMIN
		for(Student s : studenti)
			s.setTermin(termin);
		
		termin.getStudenti().addAll(studenti); 	
		Baza.getIzabranTermin().getStudenti().removeAll(studenti); 			//iz izabranom termina uklanjanjmo premestene studente
		
		//ZAVRSENO PREBACIVANJE -> POVRATAK NA PRETHODNU FORMU
		Scene scene = new Scene(new TerminiView(), 500, 550);
		Main.getWindow().setScene(scene);
		Main.getWindow().show();
	}
}
