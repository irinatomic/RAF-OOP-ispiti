package view;

import java.util.Collections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class StatistikaView extends Stage {

	private static StatistikaView instance;
	private VBox vbView;
	private ListView<PozicijaStatistika> lvStatistika;
	private Label lblUkMesecniIzdatak;
	private Label lblBrZaposlenih;
	private Label lblProsecnaZarada;
	
	private int ukMesecniIzdatak;
	private int brZaposlenih;
	
	private StatistikaView() {
		odradiStatistiku();
		init();
		addElements();
	}
	
	public static StatistikaView getInstance() {
		if(instance == null)
			instance = new StatistikaView();
		return instance;
	}
	
	private void odradiStatistiku() {
		for(Zaposleni z : Baza.getZaposleni())
			ukMesecniIzdatak += z.getPlata();
		brZaposlenih = Baza.getZaposleni().size();
	}
	
	private void init() {
		vbView = new VBox(10);
		lvStatistika = new ListView<PozicijaStatistika>();
		Collections.sort(Baza.getPozicijeStatistika());
		lvStatistika.getItems().addAll(Baza.getPozicijeStatistika());
		lblUkMesecniIzdatak = new Label("Ukupni mesecni izdatak: " + ukMesecniIzdatak);
		lblBrZaposlenih = new Label("Broj zaposlenih: " + brZaposlenih);
		lblProsecnaZarada = new Label("Prosecna zarada: " + (ukMesecniIzdatak / brZaposlenih));
	}
	
	private void addElements() {
		vbView.getChildren().addAll(lvStatistika, lblUkMesecniIzdatak, lblBrZaposlenih, lblProsecnaZarada);
		vbView.setAlignment(Pos.CENTER);
		vbView.setPadding(new Insets(10));
		
		Scene scene = new Scene(vbView, 400, 400);
		setScene(scene);
		show();
	}
}
