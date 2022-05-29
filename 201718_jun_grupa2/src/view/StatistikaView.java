package view;

import controller.ZatvoriController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StatistikaView extends GridPane {

	private TextField tfUtakmicaMaxGolova;
	private TextField tfRepMaxGolova;
	private ListView<String> lvRepTriPobede;
	private Button btnZatvori;
	
	public StatistikaView() {
		init();
		addElements();
	}
	
	private void init() {
		tfUtakmicaMaxGolova = new TextField();
		tfRepMaxGolova = new TextField();
		lvRepTriPobede = new ListView<String>();
		lvRepTriPobede.setMaxHeight(150);
		btnZatvori = new Button("Zatvori");
		
		//AKCIJE
		btnZatvori.setOnAction(new ZatvoriController());
	}
	
	private void addElements() {
		add(new Label("Utakmica sa najvise golova"), 0, 0);
		add(new Label("Reprezentacija sa najvise datih golova"), 0, 1);
		add(new Label("Reprezentacije sa 3 pobede"), 0, 2);
		add(btnZatvori, 0, 3);
		
		add(tfUtakmicaMaxGolova, 1, 0);
		add(tfRepMaxGolova, 1, 1);
		add(lvRepTriPobede, 1, 2);
		
		setAlignment(Pos.CENTER);
		setHgap(5);
		setVgap(5);
		setPadding(new Insets(20));
	}

	public TextField getTfUtakmicaMaxGolova() {
		return tfUtakmicaMaxGolova;
	}

	public TextField getTfRepMaxGolova() {
		return tfRepMaxGolova;
	}

	public ListView<String> getLvRepTriPobede() {
		return lvRepTriPobede;
	}
}
