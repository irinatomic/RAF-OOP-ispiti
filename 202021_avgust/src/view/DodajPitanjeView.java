package view;

import controller.DodajPitanjeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DodajPitanjeView extends Stage {

	private static DodajPitanjeView instance;
	private GridPane gpView;
	private TextField tfPitanje;
	private TextField tfOdgovor;
	private TextField tfPredmet;
	private Button btnOdustani;
	private Button btnDodaj;

	private DodajPitanjeView() {
		init();
		actions();
		addElements();
	}
	
	public static DodajPitanjeView getInstance() {
		instance = new DodajPitanjeView();
		return instance;
	}
	
	private void init() {
		tfPitanje = new TextField();
		tfOdgovor = new TextField();
		tfPredmet = new TextField();
		btnOdustani = new Button("Odustani");
		btnDodaj = new Button("Dodaj");
		
		gpView = new GridPane();
		gpView.addRow(0, new Label(""), new Label("Pitanje"), tfPitanje);
		gpView.addRow(1, new Label(""), new Label("Odgovor"), tfOdgovor);
		gpView.addRow(2, new Label(""), new Label("Predmet"), tfPredmet);
		gpView.addRow(5, btnOdustani, new Label(""), new Label(""), btnDodaj);
		gpView.setAlignment(Pos.CENTER);
		gpView.setPadding(new Insets(25));
		gpView.setHgap(10);
		gpView.setVgap(10);
	}
	
	private void actions() {
		btnOdustani.setOnAction(event -> this.close());
		btnDodaj.setOnAction(new DodajPitanjeController(tfPitanje, tfOdgovor, tfPredmet));
	}
	
	private void addElements() {
		Scene scene = new Scene(gpView);
		setScene(scene);
		show();
	}
}
