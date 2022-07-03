package view;

import java.util.List;

import controller.IzaberiController;
import controller.IzvrsiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import utils.FileUtil;

public class MainView extends Stage {

	private static MainView instance;
	private VBox vbView;
	private TableView<Izdavac> tvIzdavaci;
	private ComboBox<String> cmbRazredi;
	private Button btnIzaberi;
	private TableView<Udzbenik> tvUdzbenici;
	private HBox hbDonjiDeo;
	private ComboBox<String> cmbIzmeni;
	private TextField tfIzmeni;
	private Button btnIzvrsi;
	private Button btnSnimi;
	
	private static ObservableList<Izdavac> olIzdavaci = FXCollections.observableArrayList(Baza.getIzdavaci());
	private static ObservableList<Udzbenik> olUdzbeniciPrikaz = FXCollections.observableList(Baza.getUdzbeniciPrikaz());

	private MainView() {
		init();
		actions();
		addElements();
	}
	
	public static MainView getInstance() {
		if(instance == null)
			instance = new MainView();
		return instance;
	}
	
	private void tableViewIzdavaci() {
		tvIzdavaci = new TableView<Izdavac>();
		TableColumn<Izdavac, String> tcNaziv = new TableColumn<Izdavac, String>("Izdavac");
		TableColumn<Izdavac, List<Integer>> tcRazredi = new TableColumn<Izdavac, List<Integer>>("Razredi");
		TableColumn<Izdavac, Integer> tcBrPrimeraka = new TableColumn<Izdavac, Integer>("Broj primeraka");
		TableColumn<Izdavac, Double> tcProsCena = new TableColumn<Izdavac, Double>("Prosecna cena");
		
		tcNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		tcRazredi.setCellValueFactory(new PropertyValueFactory<>("razredi"));
		tcBrPrimeraka.setCellValueFactory(new PropertyValueFactory<>("brojPrimeraka"));
		tcProsCena.setCellValueFactory(new PropertyValueFactory<>("prosecnaCena"));
		tvIzdavaci.getColumns().addAll(List.of(tcNaziv, tcRazredi, tcBrPrimeraka, tcProsCena));
		tvIzdavaci.setItems(olIzdavaci);
	}
	
	private void tableViewUdzbenici() {
		tvUdzbenici = new TableView<Udzbenik>();
		TableColumn<Udzbenik, Integer> tcIdBroj = new TableColumn<Udzbenik, Integer>("Id");
		TableColumn<Udzbenik, Integer> tcRazred = new TableColumn<Udzbenik, Integer>("Razred");
		TableColumn<Udzbenik, String> tcIzdavac = new TableColumn<Udzbenik, String>("Izdavac");
		TableColumn<Udzbenik, String> tcPredmet = new TableColumn<Udzbenik, String>("Predmet");
		TableColumn<Udzbenik, String> tcNaslov = new TableColumn<Udzbenik, String>("Naslov");
		TableColumn<Udzbenik, List<String>> tcAutori = new TableColumn<Udzbenik, List<String>>("Autori");
		TableColumn<Udzbenik, Integer> tcBrPrimeraka = new TableColumn<Udzbenik, Integer>("Broj primeraka");
		TableColumn<Udzbenik, Double> tcCena = new TableColumn<Udzbenik, Double>("Cena");
		
		tcIdBroj.setCellValueFactory(new PropertyValueFactory<>("idBroj"));
		tcRazred.setCellValueFactory(new PropertyValueFactory<>("razred"));
		tcIzdavac.setCellValueFactory(new PropertyValueFactory<>("izdavac"));
		tcPredmet.setCellValueFactory(new PropertyValueFactory<>("predmet"));
		tcNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
		tcAutori.setCellValueFactory(new PropertyValueFactory<>("autori"));
		tcBrPrimeraka.setCellValueFactory(new PropertyValueFactory<>("brPrimeraka"));
		tcCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
		tvUdzbenici.getColumns().addAll(List.of(tcIdBroj, tcRazred, tcIzdavac, tcPredmet, tcNaslov, tcAutori, tcBrPrimeraka, tcCena));
		tvUdzbenici.setItems(olUdzbeniciPrikaz);
		tvUdzbenici.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	private void hbDonjiDeo() {
		cmbIzmeni = new ComboBox<String>();
		cmbIzmeni.getItems().addAll("Dodaj primerke", "Smanji broj primeraka");
		cmbIzmeni.getSelectionModel().select(0);
		tfIzmeni = new TextField();
		btnIzvrsi = new Button("Izvrsi");
		btnSnimi = new Button("Snimi");
		hbDonjiDeo = new HBox(5, cmbIzmeni, tfIzmeni, btnIzvrsi, btnSnimi);
		hbDonjiDeo.setAlignment(Pos.CENTER_LEFT);
	}
	
	private void init() {
		tableViewIzdavaci();
		cmbRazredi = new ComboBox<String>(FXCollections.observableArrayList(Baza.getRazredi()));
		cmbRazredi.getSelectionModel().select(0);
		btnIzaberi = new Button("Izaberi");
		tableViewUdzbenici();
		hbDonjiDeo();
		vbView = new VBox(5, tvIzdavaci, cmbRazredi, btnIzaberi, tvUdzbenici, hbDonjiDeo);
		vbView.setAlignment(Pos.CENTER);
	}
	
	private void actions() {
		btnIzaberi.setOnAction(new IzaberiController(tvIzdavaci, cmbRazredi));
		btnIzvrsi.setOnAction(new IzvrsiController(tvUdzbenici, cmbIzmeni, tfIzmeni));
		btnSnimi.setOnAction(event -> FileUtil.snimiUFajl("snimljeno.txt"));
	}
	
	private void addElements() {
		Scene scene = new Scene(vbView, 700, 600);
		setScene(scene);
		show();
	}

	public static ObservableList<Izdavac> getOlIzdavaci() {
		return olIzdavaci;
	}

	public static ObservableList<Udzbenik> getOlUdzbeniciPrikaz() {
		return olUdzbeniciPrikaz;
	}
}
