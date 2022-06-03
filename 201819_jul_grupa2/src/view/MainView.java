package view;

import java.util.List;

import controller.FiltrirajController;
import controller.ObracunajController;
import controller.PrikaziSveController;
import controller.SacuvajController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Baza;
import model.Obracunato;
import model.Sat;
import model.Termin;

public class MainView extends Stage {

	private static MainView instance;
	private VBox vbView;
	private HBox hbFilter;
	private ComboBox<String> cmbDani;
	private TextField tfOd;
	private TextField tfDo;
	private Button btnFiltriraj;
	private Button btnPrikaziSve;
	
	private TableView<Termin> tvTermini;
	private Button btnObracunaj;
	private TableView<Obracunato> tvObracunato;
	private Button btnSnimiObracunato;
	
	private MainView() {
		init();
		addElements();
	}
	
	public static MainView getInstance() {
		if(instance == null)
			instance = new MainView();
		return instance;
	}
	
	private void init() {
		vbView = new VBox(10);
		vbView.setPadding(new Insets(10, 0, 0, 0));
		vbView.setAlignment(Pos.CENTER);
		
		hbFilter = new HBox(5);
		hbFilter.setAlignment(Pos.CENTER);
		cmbDani = new ComboBox<String>(FXCollections.observableArrayList(Baza.getDani()));
		cmbDani.setValue("");
		tfOd = new TextField();
		tfDo = new TextField();
		btnFiltriraj = new Button("Filtriraj");
		btnPrikaziSve = new Button("Prikazi sve");
		hbFilter.getChildren().addAll(new Label("Dan:"), cmbDani, new Label("Iskljuci termine od:"), tfOd);
		hbFilter.getChildren().addAll(new Label("do:"), tfDo, btnFiltriraj, btnPrikaziSve);
		
		btnObracunaj = new Button("Obracunaj po ucionicama");
		btnSnimiObracunato = new Button("Sacuvaj po ucionicama");
		
		//TABELA TERMINA
		tabelaTermina();
		
		//DRUGA TABELA
		tabelaObracunato();
		
		//AKCIJE
		btnFiltriraj.setOnAction(new FiltrirajController(cmbDani, tfOd, tfDo, tvTermini));
		btnPrikaziSve.setOnAction(new PrikaziSveController(tvTermini));
		btnObracunaj.setOnAction(new ObracunajController(tvTermini, tvObracunato));
		btnSnimiObracunato.setOnAction(new SacuvajController());
	}
	
	private void tabelaTermina() {
		tvTermini = new TableView<Termin>();
		tvTermini.getItems().addAll(Baza.getSviTermini());
		tvTermini.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		TableColumn<Termin, String> tcPredmet = new TableColumn<Termin, String>("Predmet");
		TableColumn<Termin, String> tcNastavnik = new TableColumn<Termin, String>("Nastavnik");
		TableColumn<Termin, String> tcVrsta = new TableColumn<Termin, String>("Vrsta");
		TableColumn<Termin, String> tcDan = new TableColumn<Termin, String>("Dan");
		TableColumn<Termin, String> tcUcionica = new TableColumn<Termin, String>("Ucionica");
		TableColumn<Termin, String> tcGrupe = new TableColumn<Termin, String>("Grupe");
		TableColumn<Termin, String> tcPocetak = new TableColumn<Termin, String>("Pocetak");
		TableColumn<Termin, String> tcKraj = new TableColumn<Termin, String>("Kraj");
		tvTermini.getColumns().addAll(tcPredmet, tcNastavnik, tcVrsta, tcDan, tcUcionica, tcGrupe, tcPocetak, tcKraj);
		
		tcPredmet.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		tcNastavnik.setCellValueFactory(new PropertyValueFactory<>("nastavnik"));
		tcVrsta.setCellValueFactory(new PropertyValueFactory<>("vrstaNastave"));
		tcDan.setCellValueFactory(new PropertyValueFactory<>("dan"));
		tcUcionica.setCellValueFactory(new PropertyValueFactory<>("ucionica"));
		tcGrupe.setCellValueFactory(new PropertyValueFactory<>("grupa"));
		tcPocetak.setCellValueFactory(new PropertyValueFactory<>("pocetak"));
		tcKraj.setCellValueFactory(new PropertyValueFactory<>("kraj"));
	}
	
	private void tabelaObracunato() {
		tvObracunato = new TableView<Obracunato>();
		TableColumn<Obracunato, String> tcUcionica = new TableColumn<Obracunato, String>("Ucionica");
		TableColumn<Obracunato, List<String>> tcVremena = new TableColumn<Obracunato, List<String>>("Zauzeti termini");
		TableColumn<Obracunato, List<String>> tcOGrupe = new TableColumn<Obracunato, List<String>>("Grupe");
		tvObracunato.getColumns().addAll(tcUcionica, tcVremena, tcOGrupe);
		
		tcUcionica.setCellValueFactory(new PropertyValueFactory<>("ucionica"));
		tcVremena.setCellValueFactory(new PropertyValueFactory<>("satnica"));
		tcOGrupe.setCellValueFactory(new PropertyValueFactory<>("grupe"));
	}
	
	private void addElements() {
		vbView.getChildren().addAll(hbFilter, tvTermini, btnObracunaj, tvObracunato, btnSnimiObracunato);
		
		Scene scene = new Scene(vbView, 800, 600);
		setScene(scene);
		show();
	}
}
