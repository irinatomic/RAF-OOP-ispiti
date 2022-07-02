package view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class IgrajView extends Stage {

	private static IgrajView instance;
	private VBox vbView;
	private Label lblPitanje;
	private TextField tfOdgovor;
	private Button btnDalje;
	private HBox hbDalje;
	
	private int indexPitanja;
	private List<Pitanje> pitanjaZaIgru;
	private Rezultat rezultat;

	private IgrajView(String predmet) {
		rezultat = new Rezultat(predmet);
		indexPitanja = 0;
		pitanjaZaIgru = new ArrayList<Pitanje>();
		pitanjaZaIgru.addAll(Baza.getPitanja());
		if(!predmet.equals("Svi predmeti"))
			pitanjaZaIgru.removeIf(pitanje -> !pitanje.getPredmet().equals(predmet));
		
		init();
		actions();
		addElements();
	}
	
	public static IgrajView getInstance(String predmet) {
		instance = new IgrajView(predmet);
		return instance;
	}
	
	private void init() {
		lblPitanje = new Label(pitanjaZaIgru.get(indexPitanja).getPitanje());
		tfOdgovor = new TextField();
		btnDalje = new Button("Dalje");
		hbDalje = new HBox(btnDalje);
		hbDalje.setAlignment(Pos.CENTER);
		vbView = new VBox(10);
		vbView.getChildren().addAll(new Label("Pitanje"), lblPitanje, new Label("Odgovor"), new Label(""), tfOdgovor, new Label(""), hbDalje);
		vbView.setPadding(new Insets(20));
		vbView.setAlignment(Pos.CENTER_LEFT);
	}
	
	private void actions() {
		btnDalje.setOnAction(event -> {
			if(pitanjaZaIgru.get(indexPitanja).getOdgovor().equalsIgnoreCase(tfOdgovor.getText()))
				rezultat.dodajPoen();
			
			//AKO SMO PROSLI KROZ SVA PITANJA
			indexPitanja++;
			if(indexPitanja == pitanjaZaIgru.size()) {
				MainView.getOlRezultati().add(rezultat);
				this.close();
				return;
			}
			
			//NISMO PROSLI KROZ SVA PITANJA -> SETUJEMO IZGLED ZA NAREDNO PITANJE
			tfOdgovor.setText("");
			lblPitanje.setText(pitanjaZaIgru.get(indexPitanja).getPitanje());
		});
	}
	
	private void addElements() {
		Scene scene = new Scene(vbView);
		setScene(scene);
		show();
	}
}
