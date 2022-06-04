package view;

import controller.DodajUKorpuController;
import controller.PrikaziController;
import controller.StampajRacunController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Artikal;
import model.ArtikalKorpa;
import model.Baza;
import model.Zanr;

public class MainView extends VBox {

	private HBox hbPonuda;
	private TableView<Zanr> tvZanrovi;
	
	private VBox vbIzbor;
	private HBox hbRadioButtons;
	private ToggleGroup tgTip;
	private RadioButton rbNove;
	private RadioButton rbPolovne;
	private RadioButton rbSve;
	private Button btnPrikazi;
	
	private VBox vbDodajUKorpu;
	private HBox hbDodajUKorpu;
	private ListView<Artikal> lvArtikli;
	private ComboBox<Integer> cmbKolicina;
	private Button btnDodajUKorpu;
	
	private TableView<ArtikalKorpa> tvKorpa;
	private HBox hbStampajRacun;
	private Label lblUkCena;
	private Button btnStampajRacun;
	
	public MainView() {
		init();
		addElements();
	}
	
	private void tabelaZanrovi() {
		tvZanrovi = new TableView<Zanr>();
		tvZanrovi.getItems().addAll(Baza.getZanrovi());
		
		TableColumn<Zanr, String> tcZanr = new TableColumn<Zanr, String>("Zanr");
		TableColumn<Zanr, Integer> tcNove = new TableColumn<Zanr, Integer>("Nove");
		TableColumn<Zanr, Integer> tcPolovne = new TableColumn<Zanr, Integer>("Polovne");
		
		tcZanr.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		tcNove.setCellValueFactory(new PropertyValueFactory<>("brNovih"));
		tcPolovne.setCellValueFactory(new PropertyValueFactory<>("brPolovnih"));
		tvZanrovi.getColumns().addAll(tcZanr, tcNove, tcPolovne);
		tvZanrovi.setMaxWidth(tcZanr.getWidth() * 3);
	}
	
	private void tgTip() {
		tgTip = new ToggleGroup();
		rbNove = new RadioButton("nova");
		rbPolovne = new RadioButton("polovna");
		rbSve = new RadioButton("sve");
		
		rbNove.setToggleGroup(tgTip);
		rbPolovne.setToggleGroup(tgTip);
		rbSve.setToggleGroup(tgTip);
	}
	
	private void tabelaKorpa() {
		tvKorpa = new TableView<ArtikalKorpa>();
		tvKorpa.setItems(Baza.getOlKorpa());
		
		TableColumn<ArtikalKorpa, String> tcArtikal = new TableColumn<ArtikalKorpa, String>("Artikal");
		TableColumn<ArtikalKorpa, Integer> tcBrojKomada = new TableColumn<ArtikalKorpa, Integer>("Broj komada");
		TableColumn<ArtikalKorpa, Double> tcCena = new TableColumn<ArtikalKorpa, Double>("Cena");
		
		tcArtikal.setCellValueFactory(new PropertyValueFactory<>("opis"));
		tcBrojKomada.setCellValueFactory(new PropertyValueFactory<>("brKomada"));
		tcCena.setCellValueFactory(new PropertyValueFactory<>("ukCena"));
		tvKorpa.getColumns().addAll(tcArtikal, tcBrojKomada, tcCena);
	}
	
	private void init() {
		hbPonuda = new HBox(10);
		tabelaZanrovi();
		
		tgTip();
		btnPrikazi = new Button("Prikazi");
		hbRadioButtons = new HBox(5);
		hbRadioButtons.getChildren().addAll(rbNove, rbPolovne, rbSve);
		hbRadioButtons.setAlignment(Pos.CENTER);
		vbIzbor = new VBox();
		vbIzbor.getChildren().addAll(hbRadioButtons, btnPrikazi);
		vbIzbor.setAlignment(Pos.CENTER);
		vbIzbor.setSpacing(10);
		
		lvArtikli = new ListView<Artikal>();
		lvArtikli.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cmbKolicina = new ComboBox<Integer>();
		cmbKolicina.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cmbKolicina.setValue(1);
		btnDodajUKorpu = new Button("Dodaj u korpu");
		hbDodajUKorpu = new HBox(5);
		hbDodajUKorpu.getChildren().addAll(new Label("Kolicina: "), cmbKolicina, btnDodajUKorpu);
		hbDodajUKorpu.setAlignment(Pos.CENTER_LEFT);
		vbDodajUKorpu = new VBox(5);
		vbDodajUKorpu.getChildren().addAll(lvArtikli, hbDodajUKorpu);
		vbDodajUKorpu.setAlignment(Pos.CENTER);
		
		hbPonuda.getChildren().addAll(tvZanrovi, vbIzbor, vbDodajUKorpu);
		HBox.setHgrow(tvZanrovi, Priority.ALWAYS);
		HBox.setHgrow(vbDodajUKorpu, Priority.ALWAYS);
		
		tabelaKorpa();
		lblUkCena = new Label(" ");
		btnStampajRacun = new Button("Stampaj racun");
		hbStampajRacun = new HBox(10);
		hbStampajRacun.getChildren().addAll(new Label("Ukupno:"), lblUkCena, btnStampajRacun);
		hbStampajRacun.setAlignment(Pos.CENTER);
		hbStampajRacun.setSpacing(5);
		
		//AKCIJE
		btnPrikazi.setOnAction(new PrikaziController(tvZanrovi, tgTip, lvArtikli));
		btnDodajUKorpu.setOnAction(new DodajUKorpuController(lvArtikli, cmbKolicina, lblUkCena));
		btnStampajRacun.setOnAction(new StampajRacunController());
	}
	
	private void addElements() {
		getChildren().addAll(hbPonuda, new Label("Sadrzaj korpe"), tvKorpa, hbStampajRacun);
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPadding(new Insets(20));
	}

	public TableView<ArtikalKorpa> getTvKorpa() {
		return tvKorpa;
	}

	public void setTvKorpa(TableView<ArtikalKorpa> tvKorpa) {
		this.tvKorpa = tvKorpa;
	}
}
