package controller;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Student;
import view.MainView;

public class DodeliController implements EventHandler<ActionEvent> {

	private ListView<Student> lvNerasporedjeni;
	private ComboBox<String> cmbTermin;
	private ListView<String> lvUcionice;

	public DodeliController(ListView<Student> lvNerasporedjeni, ComboBox<String> cmbTermin, ListView<String> lvUcionice) {
		this.lvNerasporedjeni = lvNerasporedjeni;
		this.cmbTermin = cmbTermin;
		this.lvUcionice = lvUcionice;
	}

	@Override
	public void handle(ActionEvent event) {
		//AKO NISU OBA LIST VIEW SELEKTOVANA
		if(lvNerasporedjeni.getSelectionModel().getSelectedItem() == null || lvUcionice.getSelectionModel().getSelectedItem() == null) {
			new Alert(AlertType.ERROR, "Selektujte").show();
			return;
		}
		
		String selektovano = cmbTermin.getSelectionModel().getSelectedItem();
		int brTermina = Integer.parseInt(selektovano.charAt(selektovano.length() - 1) + "");
		String[] args = lvUcionice.getSelectionModel().getSelectedItem().split(" ");
		String trazenaUcionica = args[0]; 
		List<Student> selektStudenti = lvNerasporedjeni.getSelectionModel().getSelectedItems();
		
		for(Map.Entry<String, Integer> entry : Baza.getTermini().get(brTermina - 1).getUcionice().entrySet()) {
			if(entry.getKey().equals(trazenaUcionica)) {
				
				//AKO ZELIMO DA STAVIMO VISE STUDENATA OD PRESOTALIH MESTA U UCIONICI
				if(selektStudenti.size() >  entry.getValue()) {
					new Alert(AlertType.ERROR, "U ucionici nije ostalo dovoljno mesta").show();
					return;
				}
				
				for(Student s : selektStudenti) {
					s.setTermin(selektovano);
					s.setUcionica(entry.getKey());
				}
				
				entry.setValue(entry.getValue() - selektStudenti.size());
				MainView.getOlRasporedjeni().addAll(selektStudenti);
				MainView.getOlNerasporedjeni().removeAll(selektStudenti);
				MainView.getOlUcionice().clear();
				MainView.getOlUcionice().setAll(Baza.getTermini().get(brTermina - 1).getUcioniceString());
				
			}
				
		}
	}

}
