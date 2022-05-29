package view;

import controller.FiltrirajController;
import controller.PrikaziSveController;
import controller.StatistikaController;
import controller.UkrstanjeController;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Baza;
import model.Utakmica;

public class MainStage extends VBox{

	private HBox hbFilter;
	private ComboBox<String> cmbGrupa;
	private Button btnFiltriraj;
	private Button btnPrikaziSve;
	
	private TableView<Utakmica> tvUtakmice;
	private Button btnStatistika;
	private GridPane gpStatistika;
	private TextField tfUkGolova;
	private TextField tfTimNajGolova;
	private TextField tfTimNajGolRazlika;
	private TextField tfBrNeresenih;
	private Button btnUkrstanje;
	
	public MainStage() {
		init();
		addElements();
	}
	
	private void init() {
		hbFilter = new HBox(20);
		cmbGrupa = new ComboBox<String>(FXCollections.observableArrayList(Baza.getGrupe()));
		cmbGrupa.setValue("");
		btnFiltriraj = new Button("Filtriraj");
		btnPrikaziSve = new Button("Prikazi sve");
		
		tvUtakmice = new TableView<Utakmica>();
		tvUtakmice.setItems(FXCollections.observableArrayList(Baza.getSpisakUtakmica()));
		
		TableColumn<Utakmica, String> tcTim1 = new TableColumn<Utakmica, String>("Tim1");
		TableColumn<Utakmica, String> tcTim2 = new TableColumn<Utakmica, String>("Tim2");
		TableColumn<Utakmica, Integer> tcGoloviTim1 = new TableColumn<Utakmica, Integer>("Golovi tim 1");
		TableColumn<Utakmica, Integer> tcGoloviTim2 = new TableColumn<Utakmica, Integer>("Golovi tim 2");
		TableColumn<Utakmica, String> tcGrupa = new TableColumn<Utakmica, String>("Grupa");
		
		tcTim1.setCellValueFactory(new PropertyValueFactory<>("tim1"));
		tcTim2.setCellValueFactory(new PropertyValueFactory<>("tim2"));
		tcGoloviTim1.setCellValueFactory(new PropertyValueFactory<>("goloviTim1"));
		tcGoloviTim2.setCellValueFactory(new PropertyValueFactory<>("goloviTim2"));
		tcGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
		tvUtakmice.getColumns().addAll(tcTim1, tcTim2, tcGoloviTim1, tcGoloviTim2, tcGrupa);
		
		btnStatistika = new Button("Statistika");
		gpStatistika = new GridPane();
		tfUkGolova = new TextField();
		tfTimNajGolova = new TextField();
		tfTimNajGolRazlika = new TextField();
		tfBrNeresenih = new TextField();
		btnUkrstanje = new Button("Ukrstanje");
		
		//AKCIJE
		btnFiltriraj.setOnAction(new FiltrirajController(cmbGrupa, tvUtakmice));
		btnPrikaziSve.setOnAction(new PrikaziSveController(tvUtakmice, cmbGrupa));
		btnStatistika.setOnAction(new StatistikaController(this));
		btnUkrstanje.setOnAction(new UkrstanjeController());
	}
	
	private void addElements() {
		hbFilter.getChildren().addAll(new Label("Grupa"), cmbGrupa, btnFiltriraj, btnPrikaziSve);
		hbFilter.setAlignment(Pos.CENTER);
		
		gpStatistika.add(new Label("Ukupno golova"), 0, 0);
		gpStatistika.add(new Label("Tim sa najvise datih golova"), 0, 1);
		gpStatistika.add(new Label("Tim sa najboljom gol razlikom"), 0, 2);
		gpStatistika.add(new Label("Broj neresenih utakmica"), 0, 3);
		
		gpStatistika.add(tfUkGolova, 1, 0);
		gpStatistika.add(tfTimNajGolova, 1, 1);
		gpStatistika.add(tfTimNajGolRazlika, 1, 2);
		gpStatistika.add(tfBrNeresenih, 1, 3);
		
		gpStatistika.setAlignment(Pos.CENTER);
		gpStatistika.setHgap(5);
		gpStatistika.setVgap(5);
		
		btnStatistika.setAlignment(Pos.CENTER);
		btnUkrstanje.setAlignment(Pos.CENTER);
		
		setPadding(new Insets(10));
		setSpacing(10);
		getChildren().addAll(hbFilter, tvUtakmice, btnStatistika, gpStatistika, btnUkrstanje);
		setAlignment(Pos.CENTER);
	}

	public TableView<Utakmica> getTvUtakmice() {
		return tvUtakmice;
	}

	public TextField getTfUkGolova() {
		return tfUkGolova;
	}

	public TextField getTfTimNajGolova() {
		return tfTimNajGolova;
	}

	public TextField getTfTimNajGolRazlika() {
		return tfTimNajGolRazlika;
	}

	public TextField getTfBrNeresenih() {
		return tfBrNeresenih;
	}
}
