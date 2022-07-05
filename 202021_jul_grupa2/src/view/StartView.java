package view;

import controller.FilterController;
import controller.ObracunajController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.*;
import utils.SnimiUFajl;

public class StartView extends Stage {

	private static StartView instance;
	private VBox vbView;
	private HBox hbObracunaj;
	private TextField tfObracunaj;
	private Button btnObracunaj;
	private TableView<Zaposleni> tvZaposleni;
	
	private HBox hbFilter;
	private TextField tfFilter;
	private ComboBox<String> cmbPozicije;
	private Button btnFiltriraj;
	
	private HBox hbDugmad;
	private Button btnDodajZaposlenog;
	private Button btnSnimi;
	private Button btnStatistika;

	private StartView() {
		init();
		actions();
		addElements();
	}
	
	public static StartView getInstance() {
		if(instance == null)
			instance = new StartView();
		return instance;
	}
	
	private void tabelaZaposlenih() {
		tvZaposleni = new TableView<Zaposleni>();
		TableColumn<Zaposleni, String> tcIme = new TableColumn<Zaposleni, String>("Ime");
		TableColumn<Zaposleni, String> tcPrezime = new TableColumn<Zaposleni, String>("Prezime");
		TableColumn<Zaposleni, String> tcJmbg = new TableColumn<Zaposleni, String>("JMBG");
		TableColumn<Zaposleni, String> tcPozicija = new TableColumn<Zaposleni, String>("Pozicija");
		TableColumn<Zaposleni, Double> tcPlata = new TableColumn<Zaposleni, Double>("Plata");
		
		tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
		tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		tcJmbg.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
		tcPozicija.setCellValueFactory(new PropertyValueFactory<>("pozicija"));
		tcPlata.setCellValueFactory(new PropertyValueFactory<>("plata"));
		tvZaposleni.getColumns().addAll(tcIme, tcPrezime, tcJmbg, tcPozicija, tcPlata);
		tvZaposleni.setItems(Baza.getOlZaposleni());
		tvZaposleni.setPrefSize(400, 150);
	}
	
	private void init() {
		vbView = new VBox(10);
		tfObracunaj = new TextField();
		btnObracunaj = new Button("Obracunaj");
		hbObracunaj = new HBox(10, new Label("Cena rada:"), tfObracunaj, btnObracunaj);
		hbObracunaj.setAlignment(Pos.CENTER);
		tabelaZaposlenih();
		
		tfFilter = new TextField();
		cmbPozicije = new ComboBox<String>(FXCollections.observableList(Baza.getPozicije()));
		cmbPozicije.setValue("Sve pozicije");
		btnFiltriraj = new Button("Filtriraj");
		hbFilter = new HBox(10, tfFilter, cmbPozicije, btnFiltriraj);
		hbFilter.setAlignment(Pos.CENTER);
		
		btnDodajZaposlenog = new Button("Dodaj zaposlenog");
		btnSnimi = new Button("Snimi");
		btnStatistika = new Button("Statistika");
		hbDugmad = new HBox(10, btnDodajZaposlenog, btnSnimi, btnStatistika);
		hbDugmad.setAlignment(Pos.CENTER);
	}
	
	private void actions() {
		btnObracunaj.setOnAction(new ObracunajController(tvZaposleni, tfObracunaj));
		btnFiltriraj.setOnAction(new FilterController(tfFilter, cmbPozicije));
		btnDodajZaposlenog.setOnAction(event -> DodajZaposlenogView.getInstance());
		btnStatistika.setOnAction(event -> StatistikaView.getInstance());
		btnSnimi.setOnAction(event -> SnimiUFajl.snimiUFajl());
	}
	
	private void addElements() {
		vbView.getChildren().addAll(hbObracunaj,tvZaposleni, hbFilter, hbDugmad);
		vbView.setPadding(new Insets(10, 0, 10, 0));
		vbView.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbView);
		setScene(scene);
		show();
	}
}
