package view;

import java.util.Collections;

import controller.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class MainView extends Stage {

	private static MainView instance;
	private BorderPane bpView;
	private VBox vbPolaznici;
	private ListView<Polaznik> lvPolaznici;
	
	private GridPane gpFilter;
	private TextField tfTextFilter;
	private ComboBox<String> cmbPoredjenje;
	private TextField tfPoredjenjeFilter;
	private ComboBox<TipAkcije> cmbTipAkcije;
	private Button btnResetuj;
	private Button btnFiltriraj;
	private Button btnPregled;
	private Button btnObracun;
	private TableView<Akcija> tvAkcije;
	
	public static MainView getInstance() {
		if(instance == null)
			instance = new MainView();
		return instance;
	}
	
	private MainView() {
		init();
		addElements();
	}
	
	private void gridPaneFilter() {
		gpFilter = new GridPane();
		tfTextFilter = new TextField();
		cmbPoredjenje = new ComboBox<String>();
		cmbPoredjenje.getItems().addAll(">", "<", "=");
		cmbPoredjenje.setValue(">");
		tfPoredjenjeFilter = new TextField();
		cmbTipAkcije = new ComboBox<TipAkcije>();
		cmbTipAkcije.getItems().addAll(TipAkcije.values());
		cmbTipAkcije.setValue(TipAkcije.ČAS_TEORIJE);
		btnResetuj = new Button("Resetuj");
		btnFiltriraj = new Button("Filtriraj");
		btnPregled = new Button("Pregled");
		btnObracun = new Button("Obracun");
		
		gpFilter.add(new Label("Filter polaznika"), 0, 0, 2, 1);
		gpFilter.addRow(1, new Label("Deo imena"), tfTextFilter);
		gpFilter.add(new Label("Ubaci samo one koji imaju"), 0, 2, 2, 1);
		gpFilter.addRow(3, cmbPoredjenje, tfPoredjenjeFilter, cmbTipAkcije);
		gpFilter.addRow(4, btnResetuj, btnFiltriraj, btnPregled);
		gpFilter.addRow(5, btnObracun);
		
		gpFilter.setHgap(10);
		gpFilter.setVgap(5);
		gpFilter.setPadding(new Insets(20));
	}
	
	private void tabelaAkcija() {
		tvAkcije = new TableView<Akcija>();
		
		TableColumn<Akcija, String> tcDatum = new TableColumn<Akcija, String>("Datum"); 
		TableColumn<Akcija, String> tcIme = new TableColumn<Akcija, String>("Ime"); 
		TableColumn<Akcija, String> tcPrezime = new TableColumn<Akcija, String>("Prezime"); 
		TableColumn<Akcija, String> tcAkcija = new TableColumn<Akcija, String>("Akcija"); 
		TableColumn<Akcija, String> tcIznos = new TableColumn<Akcija, String>("Iznos"); 
		
		tcDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
		tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
		tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		tcAkcija.setCellValueFactory(new PropertyValueFactory<>("tipAkcijeTabela"));
		tcIznos.setCellValueFactory(new PropertyValueFactory<>("iznos"));
		tvAkcije.getColumns().addAll(tcDatum, tcIme, tcPrezime, tcAkcija, tcIznos);
		tvAkcije.setMaxHeight(250);
	}
	
	private void init() {
		vbPolaznici = new VBox(10);
		lvPolaznici = new ListView<Polaznik>();
		Collections.sort(Baza.getPolaznici());
		lvPolaznici.getItems().addAll(Baza.getPolaznici());
		lvPolaznici.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvPolaznici.setMaxHeight(250);
		vbPolaznici.getChildren().addAll(new Label("Polaznici"), lvPolaznici, new Label("Akcije"));
		vbPolaznici.setPadding(new Insets(5));
		
		gridPaneFilter();
		tabelaAkcija();
		
		//AKCIJE
		btnResetuj.setOnAction(new ResetujController(lvPolaznici));
		btnFiltriraj.setOnAction(new FiltrirajController(lvPolaznici, tfTextFilter, cmbPoredjenje, tfPoredjenjeFilter, cmbTipAkcije));
		btnPregled.setOnAction(new PregledController(lvPolaznici, tvAkcije));
		btnObracun.setOnAction(e -> {ObracunView.getInstance();});
	}
	
	private void addElements() {
		Region rBpRight = new Region();
		rBpRight.setMinSize(150, lvPolaznici.getHeight());
		bpView = new BorderPane(gpFilter, null, rBpRight, tvAkcije, vbPolaznici);
		bpView.setPadding(new Insets(10));
		
		setTitle("Ispit iz OOP");
		Scene scene = new Scene(bpView);
		setScene(scene);
		show();
	}
}
