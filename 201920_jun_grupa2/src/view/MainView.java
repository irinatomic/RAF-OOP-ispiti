package view;

import controller.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Artikal;
import model.Baza;

public class MainView extends Stage {

	private static MainView instance;
	private VBox vbView;
	
	private HBox hbIzvodjac;
	private ComboBox<String> cmbIzvodjac;
	private ToggleGroup tgTipNosaca;
	private RadioButton rbVinyl;
	private RadioButton rbCD;
	private RadioButton rbSve;
	
	private HBox hbCena;
	private ComboBox<String> cmbVeceManje;
	private TextField tfCena;
	private Button btnPrikaziSve;
	private Button btnFiltriraj;
	
	private TableView<Artikal> tvArtikli;
	private Button btnIzaberi;
	
	private HBox hbDonjiDeo;
	private ListView<Artikal> lvKorpa;
	private GridPane gpZaKorpu;
	private TextField tfUkupnoKosta;
	private Button btnKupi;
	private Button btnMojaKolekcija;

	public static MainView getInstance() {
		if(instance == null)
			instance = new MainView();
		return instance;
	}

	private MainView() {
		init();
		addElements();
	}
	
	private void toggleGroupTipNosaca() {
		tgTipNosaca = new ToggleGroup();
		rbVinyl = new RadioButton("Vinyl");
		rbCD = new RadioButton("CD");
		rbSve = new RadioButton("Vinyl i CD");
		
		rbVinyl.setToggleGroup(tgTipNosaca);
		rbCD.setToggleGroup(tgTipNosaca);
		rbSve.setToggleGroup(tgTipNosaca);
	}
	
	private void tabelaArtikala() {
		tvArtikli = new TableView<Artikal>();
		tvArtikli.getItems().addAll(Baza.getArtikli());
		tvArtikli.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		TableColumn<Artikal, String> tcIzvodjav = new TableColumn<Artikal, String>("Izvodjac");
		TableColumn<Artikal, String> tcNaslov = new TableColumn<Artikal, String>("Naslov");
		TableColumn<Artikal, Integer> tcGodina = new TableColumn<Artikal, Integer>("Godina");
		TableColumn<Artikal, String> tcZanr = new TableColumn<Artikal, String>("Zanr");
		TableColumn<Artikal, String> tcTip = new TableColumn<Artikal, String>("Tip");
		TableColumn<Artikal, Integer> tcKomad = new TableColumn<Artikal, Integer>("Komad");
		TableColumn<Artikal, Integer> tcCena = new TableColumn<Artikal, Integer>("Cena");
		TableColumn<Artikal, String> tcKategorija = new TableColumn<Artikal, String>("Kategorija");
		
		tcIzvodjav.setCellValueFactory(new PropertyValueFactory<>("izvodjac"));
		tcNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
		tcGodina.setCellValueFactory(new PropertyValueFactory<>("godIzdanja"));
		tcZanr.setCellValueFactory(new PropertyValueFactory<>("zanr"));
		tcTip.setCellValueFactory(new PropertyValueFactory<>("tipNosaca"));
		tcKomad.setCellValueFactory(new PropertyValueFactory<>("brNosaca"));
		tcCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
		tcKategorija.setCellValueFactory(new PropertyValueFactory<>("kategorija"));
		tvArtikli.getColumns().addAll(tcIzvodjav, tcNaslov, tcGodina, tcZanr, tcTip, tcKomad, tcCena, tcKategorija);
	}
	
	private void gridPaneZaKorpu() {
		gpZaKorpu = new GridPane();
		tfUkupnoKosta = new TextField();
		btnKupi = new Button("Kupi");
		btnMojaKolekcija = new Button("Moja kolekcija");
		
		gpZaKorpu.add(new Label("Ukupno kosta"), 0, 0);
		gpZaKorpu.add(tfUkupnoKosta, 1, 0);
		gpZaKorpu.add(btnKupi, 0, 1);
		gpZaKorpu.add(btnMojaKolekcija, 1, 1);
		gpZaKorpu.setHgap(5);
		gpZaKorpu.setVgap(5);
		gpZaKorpu.setAlignment(Pos.CENTER);
	}
	
	private void init() {
		vbView = new VBox(10);
		vbView.setAlignment(Pos.CENTER);
		
		hbIzvodjac = new HBox(15);
		cmbIzvodjac = new ComboBox<String>();
		cmbIzvodjac.getItems().addAll(Baza.getIzvodjaci());
		cmbIzvodjac.setValue("");
		toggleGroupTipNosaca();
		hbIzvodjac.getChildren().addAll(new Label("Izvodjac"), cmbIzvodjac, rbVinyl, rbCD, rbSve);
		hbIzvodjac.setAlignment(Pos.CENTER);
		
		hbCena = new HBox(20);
		cmbVeceManje = new ComboBox<String>();
		cmbVeceManje.getItems().addAll("", ">", "<", "=");
		cmbVeceManje.setValue("");
		tfCena = new TextField("0");
		btnPrikaziSve = new Button("Prikazi sve");
		btnFiltriraj = new Button("Filtriraj");
		hbCena.getChildren().addAll(new Label("Cena"), cmbVeceManje, tfCena, btnPrikaziSve, btnFiltriraj); 				
		hbCena.setAlignment(Pos.CENTER);
		
		tabelaArtikala();
		btnIzaberi = new Button("Izaberi");
		
		hbDonjiDeo = new HBox(25);
		lvKorpa = new ListView<Artikal>(Baza.getOlKorpa());
		gridPaneZaKorpu();
		hbDonjiDeo.getChildren().addAll(lvKorpa, gpZaKorpu);
		hbDonjiDeo.setAlignment(Pos.CENTER);
		
		//AKCIJE
		btnPrikaziSve.setOnAction(new PrikaziSveController(tvArtikli));
		btnFiltriraj.setOnAction(new FiltrirajController(cmbIzvodjac, tgTipNosaca, cmbVeceManje, tfCena, tvArtikli));
		btnIzaberi.setOnAction(new IzaberiController(tvArtikli, tfUkupnoKosta));
		btnKupi.setOnAction(new KupiController(lvKorpa));
		btnMojaKolekcija.setOnAction(e -> {MojaKolekcijaView.getInstance();});
	}
	
	private void addElements() {
		vbView.getChildren().addAll(hbIzvodjac, hbCena, tvArtikli, btnIzaberi, hbDonjiDeo);
		vbView.setAlignment(Pos.CENTER);
		vbView.setPadding(new Insets(20));
		setTitle("OOP JUN 2020");
		
		Scene scene = new Scene(vbView, 700, 600);
		setScene(scene);
		show();
	}
}
