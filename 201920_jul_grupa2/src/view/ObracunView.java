package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Baza;

public class ObracunView extends Stage {

	private static ObracunView instance;
	private VBox vbView;
	private double ukKredit;
	private double prosecanDug;
	private double brDuznika;
	
	private ObracunView() {
		obracunaj();
		init();
		addElements();
	}
	
	public static ObracunView getInstance() {
		instance = new ObracunView();
		return instance;
	}
	
	private void obracunaj() {
		ukKredit = Baza.izracunajKredit();
		prosecanDug = Math.round((Baza.izracunajUkupanDug() / Baza.getPolaznici().size()) * 100) / 100;
		brDuznika = Baza.getBrDuznika();
	}

	private void init() {
		vbView = new VBox(10);
		vbView.getChildren().add(new Label("Ukupan kredit: " + ukKredit));
		vbView.getChildren().add(new Label("Prosecan dug za casove: " + prosecanDug));
		vbView.getChildren().add(new Label("Broj duznika: " + brDuznika));
		vbView.setPadding(new Insets(15));
	}
	
	private void addElements() {
		Scene scene = new Scene(vbView, 250, 100);
		setScene(scene);
		show();
	}
}
