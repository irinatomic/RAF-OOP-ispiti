package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Student;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbSmer;
	private ComboBox<String> cmbGodina;
	private ListView<Student> lvSviStudenti;
	
	public FiltrirajController(ComboBox<String> cmbSmer, ComboBox<String> cmbGodina, ListView<Student> lvSviStudenti) {
		this.cmbSmer = cmbSmer;
		this.cmbGodina = cmbGodina;
		this.lvSviStudenti = lvSviStudenti;
	}

	@Override
	public void handle(ActionEvent event) {
		String smer = cmbSmer.getValue();
		String godina = cmbGodina.getValue();
		
		if(smer.equals("") && godina.equals("")) {
			new Alert(AlertType.ERROR, "Izaberite neki kriterijum!").show();
			lvSviStudenti.getItems().clear();
			lvSviStudenti.getItems().addAll(Baza.getSviStudenti());
			return;
		}
		
		List<Student> filtrStudenti = new ArrayList<Student>();
		for(Student s : Baza.getSviStudenti()) {
			if(s.getSmer().equals(smer) && s.getGodUpisa().equals(godina) || smer.equals("") && s.getGodUpisa().equals(godina)
				|| s.getSmer().equals(smer) && godina.equals(""))
				filtrStudenti.add(s);
		}
		
		lvSviStudenti.getItems().clear();
		lvSviStudenti.getItems().addAll(filtrStudenti);
	}
	
	
}
