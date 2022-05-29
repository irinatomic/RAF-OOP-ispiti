package view;

import java.util.List;

import controller.FiltrirajController;
import controller.PrikaziSveController;
import controller.PrikaziTabeluController;
import controller.StatistikaController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Baza;
import model.Reprezentacija;
import model.Utakmica;

public class MainView extends VBox {

	private HBox hbFilter;
	private ComboBox<String> cmbFilter;
	private TextField tfFilter;
	private Button btnFiltriraj;
	private Button btnPrikaziSve;
	private Button btnStatistika;
	
	private HBox hbFilterGrupa;
	private TableView<Utakmica> tvUtakmice;
	private ComboBox<String> cmbGrupa;
	private Button btnPrikaziTabelu;
	private TableView<Reprezentacija> tvReprezentacije;
	
	public MainView() {
		init();
		addElements();
	}
	
	private void init() {
		hbFilter = new HBox(3);
		cmbFilter = new ComboBox<String>();
		cmbFilter.getItems().addAll(List.of("", ">", "<", "="));
		cmbFilter.setValue("");
		tfFilter = new TextField();
		tfFilter.setPromptText("Broj golova");
		btnFiltriraj = new Button("Filtriraj");
		btnPrikaziSve = new Button("Prikazi sve");
		btnStatistika = new Button("Statistika");
		
		//TABELA ZA UTAKMICE
		tvUtakmice = new TableView<Utakmica>();
		tvUtakmice.setItems(FXCollections.observableArrayList(Baza.getSpisakUtakmica())); 		//array -> ne zelimo direktnu vezu s listom, samo elemente
		
		TableColumn<Utakmica, String> tcTim1 = new TableColumn<Utakmica, String>("Tim1");
		TableColumn<Utakmica, String> tcTim2 = new TableColumn<Utakmica, String>("Tim2");
		TableColumn<Utakmica, Integer> tcGoloviTim1 = new TableColumn<Utakmica, Integer>("Golovi tim1");
		TableColumn<Utakmica, Integer> tcGoloviTim2 = new TableColumn<Utakmica, Integer>("Golovi tim2");
		TableColumn<Utakmica, String> tcGrupa = new TableColumn<Utakmica, String>("Grupa");
		
		tcTim1.setCellValueFactory(new PropertyValueFactory<>("tim1"));
		tcTim2.setCellValueFactory(new PropertyValueFactory<>("tim2"));
		tcGoloviTim1.setCellValueFactory(new PropertyValueFactory<>("goloviTim1"));
		tcGoloviTim2.setCellValueFactory(new PropertyValueFactory<>("goloviTim2"));
		tcGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
		tvUtakmice.getColumns().addAll(tcTim1, tcTim2, tcGoloviTim1, tcGoloviTim2, tcGrupa);
		tvUtakmice.setMaxHeight(250);
		
		hbFilterGrupa = new HBox(5);
		cmbGrupa = new ComboBox<String>();
		cmbGrupa.getItems().addAll(Baza.getGrupe());
		cmbGrupa.setValue("");
		btnPrikaziTabelu = new Button("Prikazi sve");
		
		//TABELA ZA REPREZENTACIJE NEKE GRUPE
		tvReprezentacije = new TableView<Reprezentacija>();
		
		TableColumn<Reprezentacija, String> tcTim = new TableColumn<Reprezentacija, String>("Tim");
		TableColumn<Reprezentacija, Integer> tcPobeda = new TableColumn<Reprezentacija, Integer>("Pobeda");
		TableColumn<Reprezentacija, Integer> tcNeresenih = new TableColumn<Reprezentacija, Integer>("Neresenih");
		TableColumn<Reprezentacija, Integer> tcPoraza = new TableColumn<Reprezentacija, Integer>("Poraza");
		TableColumn<Reprezentacija, String> tcGolovi = new TableColumn<Reprezentacija, String>("Golovi");
		TableColumn<Reprezentacija, Integer> tcPoeni = new TableColumn<Reprezentacija, Integer>("Poeni");
		
		tcTim.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		tcPobeda.setCellValueFactory(new PropertyValueFactory<>("brPobeda"));
		tcNeresenih.setCellValueFactory(new PropertyValueFactory<>("brNeresenih"));
		tcPoraza.setCellValueFactory(new PropertyValueFactory<>("brPoraza"));
		tcGolovi.setCellValueFactory(new PropertyValueFactory<>("golRazlikaString"));
		tcPoeni.setCellValueFactory(new PropertyValueFactory<>("brojPoena"));
		tvReprezentacije.getColumns().addAll(tcTim, tcPobeda, tcNeresenih, tcPoraza, tcGolovi, tcPoeni);
		tvReprezentacije.setMaxHeight(150);
		
		//AKCIJE
		btnFiltriraj.setOnAction(new FiltrirajController(this));
		btnPrikaziSve.setOnAction(new PrikaziSveController(this));
		btnPrikaziTabelu.setOnAction(new PrikaziTabeluController(this));
		btnStatistika.setOnAction(new StatistikaController());
	}
	
	private void addElements() {
		hbFilter.getChildren().addAll(new Label("Broj golova"), cmbFilter, tfFilter, btnFiltriraj, btnPrikaziSve, new Label("\t"), btnStatistika);
		btnStatistika.setAlignment(Pos.CENTER_RIGHT);
		hbFilter.setAlignment(Pos.CENTER);
		
		hbFilterGrupa.getChildren().addAll(new Label("Grupa"), cmbGrupa, btnPrikaziTabelu);
		hbFilterGrupa.setAlignment(Pos.CENTER);
		
		getChildren().addAll(hbFilter, tvUtakmice, hbFilterGrupa, tvReprezentacije);
		setSpacing(10);
		setPadding(new Insets(10, 0, 0, 0));
	}

	public ComboBox<String> getCmbFilter() {
		return cmbFilter;
	}

	public TextField getTfFilter() {
		return tfFilter;
	}

	public TableView<Utakmica> getTvUtakmice() {
		return tvUtakmice;
	}

	public ComboBox<String> getCmbGrupa() {
		return cmbGrupa;
	}

	public TableView<Reprezentacija> getTvReprezentacije() {
		return tvReprezentacije;
	}
		
}
