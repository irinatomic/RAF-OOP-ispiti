package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.NeupareniEmail;
import model.Student;

public class UpariController implements EventHandler<ActionEvent> {

	private ListView<Student> lvSviStudenti;
	private ListView<NeupareniEmail> lvNeupareniEmail;
	
	public UpariController(ListView<Student> lvSviStudenti, ListView<NeupareniEmail> lvNeupareniEmail) {
		this.lvSviStudenti = lvSviStudenti;
		this.lvNeupareniEmail = lvNeupareniEmail;
	}

	@Override
	public void handle(ActionEvent event) {
		Student s = lvSviStudenti.getSelectionModel().getSelectedItem();
		NeupareniEmail ne = lvNeupareniEmail.getSelectionModel().getSelectedItem();
		
		if(Baza.getOlUpareniStudenti().contains(s)) {
			Baza.getOlNeupareniEmailovi().remove(ne);
			new Alert(AlertType.INFORMATION, "Student vec postoji u tabeli").show();
			return;
		}
		
		s.setEmail(ne.getEmail());
		s.setPitanje1(ne.getPitanje1());
		s.setPitanje2(ne.getPitanje2());
		s.setPitanje3(ne.getPitanje3());
		
		Baza.getOlNeupareniEmailovi().remove(ne);		
		Baza.getOlUpareniStudenti().add(s);
	}

}
