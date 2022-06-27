package view;

import controller.PokreniController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StartView extends GridPane {
	
	private Label lblNaslov;
	private TextField tfBrTermina;
	private TextField tfBrStudenata;
	private CheckBox cbRacunari;
	private Button btnPokreni;
	private Label lblPoruka;
	
	public StartView() {
		init();
		addElements();
	}
	
	private void init() {
		lblNaslov = new Label("Unesite podatke za raspodelu");
		tfBrTermina = new TextField();
		tfBrStudenata = new TextField();
		cbRacunari = new CheckBox("Samo ucionice sa racunarima");
		btnPokreni = new Button("Pokreni");
		lblPoruka = new Label("");
		
		//AKCIJE
		btnPokreni.setOnAction(new PokreniController(tfBrTermina, tfBrStudenata, cbRacunari, lblPoruka));
	}
	
	private void addElements() {
		add(lblNaslov, 0, 0, 2, 1);
		addRow(1, new Label("Broj termina"), tfBrTermina);
		addRow(2, new Label("Broj studenata u ucionici"), tfBrStudenata);
		add(cbRacunari, 0, 3, 2, 1);
		add(btnPokreni, 0, 4, 2, 1);
		add(lblPoruka, 0, 5, 2, 1);
		
		setHalignment(lblNaslov, HPos.CENTER);
		setHalignment(cbRacunari, HPos.CENTER);
		setHalignment(btnPokreni, HPos.CENTER);
		setHalignment(lblPoruka, HPos.CENTER);
		
		setAlignment(Pos.CENTER);
		setHgap(5);
		setVgap(5);
		setPadding(new Insets(40));
	}
}
