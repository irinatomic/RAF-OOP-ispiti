package view;

import java.util.List;

import controller.FiltrirajController;
import controller.ObracunajPoGrupamaController;
import controller.PrikaziSveController;
import controller.SacuvajPoGrupamaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Baza;
import model.Sat;
import model.Selekcija;
import model.Termin;

public class MainStage extends Stage {

	private static MainStage instance;	
	private VBox vbox = new VBox(20);
	
	private HBox hbFilter = new HBox(5);
	private ComboBox<String> cmbUcionice = new ComboBox<String>();
	private TextField tfOd = new TextField();
	private TextField tfDo = new TextField();
	private Button btnFiltriraj = new Button("Filtriraj");
	private Button btnPrikaziSve = new Button("Prikazi sve");
	
	private TableView<Termin> tvTermini = new TableView<Termin>();
	private TableColumn<Termin, String> tcPredmet = new TableColumn<Termin, String>("Predmet");
	private TableColumn<Termin, String> tcNastavnik = new TableColumn<Termin, String>("Nastavnik");
	private TableColumn<Termin, String> tcVrsta = new TableColumn<Termin, String>("Vrsta");
	private TableColumn<Termin, String> tcDan = new TableColumn<Termin, String>("Dan");
	private TableColumn<Termin, String> tcUcionica = new TableColumn<Termin, String>("Ucionica");
	private TableColumn<Termin, String> tcGrupe = new TableColumn<Termin, String>("Grupe");
	private TableColumn<Termin, Sat> tcPocetak = new TableColumn<Termin, Sat>("Pocetak");
	private TableColumn<Termin, Integer> tcBrojCasova = new TableColumn<Termin, Integer>("Broj casova");
	
	private Button btnObracunajPoGrupama = new Button("Obracunaj po grupama");
	private Button btnSacuvajPoGrupama = new Button("Sacuvaj po grupama");
	
	private TableView<Selekcija> tvSelekcija = new TableView<Selekcija>();
	private TableColumn<Selekcija, String> tcSGrupa = new TableColumn<Selekcija, String>("Grupa");
	private TableColumn<Selekcija, List<String>> tcSDani = new TableColumn<Selekcija, List<String>>("Dani");
	private TableColumn<Selekcija, Integer> tcSBrCasova = new TableColumn<Selekcija, Integer>("Broj casova");
	private TableColumn<Selekcija, Integer> tcSBrNastavnika = new TableColumn<Selekcija, Integer>("Broj nastavnika");
	
	private MainStage() {
		init();
		addElements();
	}
	
	public static MainStage getInstance() {
		if(instance == null)
			instance = new MainStage();
		return instance;
	}
	
	private void init() {
		ObservableList<String> olUcionice = FXCollections.observableArrayList(Baza.getInstance().getUcionice());
		cmbUcionice.setItems(olUcionice);
		
		//PRVA TABELA
		tcPredmet.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		tcNastavnik.setCellValueFactory(new PropertyValueFactory<>("nastavnik"));
		tcVrsta.setCellValueFactory(new PropertyValueFactory<>("vrstaNastave"));
		tcDan.setCellValueFactory(new PropertyValueFactory<>("dan"));
		tcUcionica.setCellValueFactory(new PropertyValueFactory<>("ucionica"));
		tcGrupe.setCellValueFactory(new PropertyValueFactory<>("grupa"));
		tcPocetak.setCellValueFactory(new PropertyValueFactory<>("pocetak"));
		tcBrojCasova.setCellValueFactory(new PropertyValueFactory<>("brCasova"));
		tvTermini.getColumns().addAll(tcPredmet, tcNastavnik, tcVrsta, tcDan, tcUcionica, tcGrupe, tcPocetak, tcBrojCasova);
		ObservableList<Termin> olTermini = FXCollections.observableArrayList(Baza.getInstance().getSviTermini());
		tvTermini.setItems(olTermini);
		tvTermini.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//DRUGA TABELA
		tcSGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
		tcSDani.setCellValueFactory(new PropertyValueFactory<>("dani"));
		tcSBrCasova.setCellValueFactory(new PropertyValueFactory<>("brCasova"));
		tcSBrCasova.setMaxWidth(Double.MAX_VALUE);
		tcSBrNastavnika.setCellValueFactory(new PropertyValueFactory<>("brNastavnika"));
		tcSBrNastavnika.setMaxWidth(Double.MAX_VALUE);
		tvSelekcija.getColumns().addAll(tcSGrupa, tcSDani, tcSBrCasova, tcSBrNastavnika);
		ObservableList<Selekcija> olSelekcija = FXCollections.observableArrayList(Baza.getInstance().getSelekcija());
		tvSelekcija.setItems(olSelekcija);
		
		//AKCIJE
		btnFiltriraj.setOnAction(new FiltrirajController(cmbUcionice, tfOd, tfDo, tvTermini));
		btnPrikaziSve.setOnAction(new PrikaziSveController(tvTermini, cmbUcionice, tfOd, tfDo));
		btnObracunajPoGrupama.setOnAction(new ObracunajPoGrupamaController(tvTermini, tvSelekcija));
		btnSacuvajPoGrupama.setOnAction(new SacuvajPoGrupamaController());
	}
	
	private void addElements() {
		hbFilter.getChildren().addAll(new Label("Ucionica:"), cmbUcionice, new Label("Od"), tfOd, new Label("Do"), tfDo);
		hbFilter.getChildren().addAll(btnFiltriraj, btnPrikaziSve);
		hbFilter.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(hbFilter, tvTermini, btnObracunajPoGrupama, tvSelekcija, btnSacuvajPoGrupama);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		
		Scene scene = new Scene(vbox, 850, 600);
		setScene(scene);
		show();
	}
}
