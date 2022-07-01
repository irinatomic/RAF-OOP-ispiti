package view;

import controller.PlatiController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Baza;

public class NovaIsplataView extends GridPane {

	private TextField tfIznos;
	private TextField tfPrimalac;
	private ComboBox<String> cmbKategorije;
	private Button btnPlati;
	
	public NovaIsplataView(){
		init();
		actions();
		AddElements();
	}
	
	private void init() {
		tfIznos = new TextField();
		tfPrimalac = new TextField();
		cmbKategorije = new ComboBox<String>();
		cmbKategorije.getItems().addAll(Baza.getKategorije());
		cmbKategorije.setValue("");
		btnPlati = new Button("Plati");
	}
	
	private void actions(){
		btnPlati.setOnAction(new PlatiController(tfIznos, tfPrimalac, cmbKategorije));
	}
	
	private void AddElements() {
		addRow(0, new Label("Iznos"), tfIznos);
		addRow(1, new Label("Primalac"), tfPrimalac);
		addRow(2, new Label("Kategorija"), cmbKategorije);
		addRow(3, btnPlati);
		
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(20));
	}
}
