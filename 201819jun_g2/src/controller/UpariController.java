package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import model.Baza;
import model.NeupareniEmail;
import model.Student;
import view.MainView;

public class UpariController implements EventHandler<ActionEvent> {

	private ListView<Student> lvSviStudenti;
	private ListView<NeupareniEmail> lvNeupareniEmailovi;
	
	public UpariController(ListView<Student> lvSviStudenti, ListView<NeupareniEmail> lvNeupareniEmailovi) {
		this.lvSviStudenti = lvSviStudenti;
		this.lvNeupareniEmailovi = lvNeupareniEmailovi;
	}

	@Override
	public void handle(ActionEvent event) {
		NeupareniEmail ne = lvNeupareniEmailovi.getSelectionModel().getSelectedItem();
		Student s = lvSviStudenti.getSelectionModel().getSelectedItem();
		
		MainView.getOlNeupareniEmailovi().remove(ne);
		
		if(Baza.getUpareniStudenti().contains(s))
			return;
		
		s.setEmail(ne.getEmail());
		s.setPoeni(ne.getPoeni());
		MainView.getOlUpareniStudenti().add(s);
	}
	
	
}
