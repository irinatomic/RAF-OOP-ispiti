package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Baza;
import model.Termin;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ComboBox<String> cmbDan;
	private TextField tfOd;
	private TextField tfDo;
	private TableView<Termin> tvTermini;

	public FiltrirajController(ComboBox<String> cmbDan, TextField tfOd, TextField tfDo, TableView<Termin> tvTermini) {
		this.cmbDan = cmbDan;
		this.tfOd = tfOd;
		this.tfDo = tfDo;
		this.tvTermini = tvTermini;
	}

	@Override
	public void handle(ActionEvent event) {
		if(cmbDan.getValue() == "" || tfOd.getText().isEmpty() || tfDo.getText().isEmpty()) {
			new Alert(AlertType.ERROR, "Popunite sve kriterijue").show();
			return;
		}
		
		List<Termin> filtrTermini = new ArrayList<Termin>();
		String dan = cmbDan.getValue().toString();
		
		int pocetak = 0;
		int kraj = 0;
		try {
			pocetak = Integer.parseInt(tfOd.getText());
			kraj = Integer.parseInt(tfDo.getText());
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		for(Termin t : Baza.getSviTermini()) {
			if(t.getDan().equals(dan)) {
				if(t.getPocetak().getSat() >= pocetak && t.getPocetak().getSat() < kraj || 
					t.getKraj().getSat() > pocetak && t.getKraj().getSat() <= kraj)
					continue;
				else
					filtrTermini.add(t);
			}
		}
		
		tvTermini.getItems().clear();
		tvTermini.getItems().addAll(filtrTermini);
	}

}
