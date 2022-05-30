package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Baza;
import model.Student;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private TextField tfFilter;
	private ListView<Student> lvSviStudenti;
		
	public FiltrirajController(TextField tfFilter, ListView<Student> lvSviStudenti) {
		this.tfFilter = tfFilter;
		this.lvSviStudenti = lvSviStudenti;
	}

	@Override
	public void handle(ActionEvent event) {
		String filter = tfFilter.getText().toLowerCase();
		lvSviStudenti.getItems().clear();
		
		if(filter.equals("")) {
			lvSviStudenti.getItems().addAll(Baza.getSviStudenti());
			return;
		}
		
		List<Student> filtrstudenti = new ArrayList<Student>();
		for(Student s : Baza.getSviStudenti()) {
			if(s.toString().toLowerCase().contains(filter))
				filtrstudenti.add(s);
		}
		lvSviStudenti.getItems().addAll(filtrstudenti);
	}

}
