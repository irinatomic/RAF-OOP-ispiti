package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

public class DodajZaposlenogView extends Stage {

	private static DodajZaposlenogView instance;
	private GridPane gpView;
	private TextField tfIme;
	private TextField tfPrezime;
	private TextField tfJmbg;
	private ComboBox<String> cmbPozicije;
	private TextField tfGodZaposlenja;
	private Button btnDodaj;
	
	private DodajZaposlenogView() {
		init();
		actions();
		addElements();
	}
	
	public static DodajZaposlenogView getInstance() {
		if(instance == null)
			instance = new DodajZaposlenogView();
		return instance;
	}
	
	private void init() {
		gpView = new GridPane();
		tfIme = new TextField();
		tfPrezime = new TextField();
		tfJmbg = new TextField();
		cmbPozicije = new ComboBox<String>(FXCollections.observableArrayList(Baza.getPozicije()));
		tfGodZaposlenja = new TextField();
		btnDodaj = new Button("Dodaj");
	}
	
	private void actions() {
		btnDodaj.setOnAction(event -> {
			if(tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty() || tfJmbg.getText().isEmpty() || tfGodZaposlenja.getText().isEmpty() ||cmbPozicije.getSelectionModel().getSelectedItem() == null) {
				new Alert(AlertType.ERROR, "Unesite sve podatke").show();
				return;
			}
			
			int godZaposlenja = Integer.parseInt(tfGodZaposlenja.getText());
			String pozicija =  cmbPozicije.getSelectionModel().getSelectedItem();
			Zaposleni z =  new Zaposleni(tfIme.getText(), tfPrezime.getText(), tfJmbg.getText(), godZaposlenja, pozicija);
			Baza.getOlZaposleni().add(z);
			this.close();
		});
	}
	
	private void addElements() {
		gpView.addRow(0, new Label("Ime"), tfIme);
		gpView.addRow(1, new Label("Prezime"), tfPrezime);
		gpView.addRow(2, new Label("JMBG"), tfJmbg);
		gpView.addRow(3, new Label("Pozicija"), cmbPozicije);
		gpView.addRow(4, new Label("Godina zaposlenja"), tfGodZaposlenja);
		gpView.addRow(5, btnDodaj);
		
		gpView.setHgap(10);
		gpView.setVgap(10);
		gpView.setAlignment(Pos.CENTER);
		gpView.setPadding(new Insets(10));
		
		Scene scene = new Scene(gpView);
		setScene(scene);
		show();
	}
}
