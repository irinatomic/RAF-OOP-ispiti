package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RacunView extends Stage {

	private static RacunView instance;
	private static VBox vbView;
	private static TextArea taRacun;
	private static Button btnZatvori;
	private static String racun;
	
	public static RacunView getInstance() {
		instance = new RacunView();
		return instance;
	}
	
	private RacunView() {
		init();
		actions();
		addElements();
	}
	
	private void init() {
		taRacun = new TextArea(racun);
		taRacun.setEditable(false);
		btnZatvori = new Button("Zatvori");
		vbView = new VBox(taRacun, btnZatvori);
	}
	
	private void actions() {
		btnZatvori.setOnAction(event -> this.close());
	}
	
	private void addElements() {
		Scene scene = new Scene(vbView);
		setScene(scene);
		show();
	}

	public static void setRacun(String racun) {
		RacunView.racun = racun;
		taRacun.setText(racun);
	}
}
