package view;

import controller.PovratakNaMainStageController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import model.Baza;

public class UkrstenoStage extends VBox {

	private ListView<String> lvUkrsteno;
	private Button btnZatvori;
	
	public UkrstenoStage() {
		init();
		addElements();
	}
	
	private void init() {
		lvUkrsteno = new ListView<String>();
		lvUkrsteno.getItems().addAll(Baza.getUkrsteneUtakmice());
		btnZatvori = new Button("Zatvori"); 
		
		//AKCIJE
		btnZatvori.setOnAction(new PovratakNaMainStageController());
	}
	
	private void addElements() {
		getChildren().addAll(new Label("Utakmice osmine finala"), lvUkrsteno, btnZatvori);
		setPadding(new Insets(20));
		setSpacing(20);
	}
}
