package view;

import java.util.Collections;

import controller.FiltrirajController;
import controller.SnimiPoeneController;
import controller.UpariController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Baza;
import model.NeupareniEmail;
import model.Student;

public class MainView extends VBox {

	private static MainView instance;
	private static ObservableList<Student> olUpareniStudenti = FXCollections.observableList(Baza.getUpareniStudenti());
	private static ObservableList<NeupareniEmail> olNeupareniEmailovi = FXCollections.observableList(Baza.getNeupareniEmailovi());
 	
	private TableView<Student> tvUpareniStudenti;
	private VBox vbSviStudenti;
	private HBox hbDonjiDeo;
	private HBox hbFilter;
	private ComboBox<String> cmbSmer;
	private ComboBox<String> cmbGodina;
	private Button btnFiltriraj;
	private ListView<Student> lvSviStudenti;
	
	private Button btnUpari;
	private ListView<NeupareniEmail> lvNeupareniEmailovi;
	private Button btnSnimiPoene;
	
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
		tvUpareniStudenti = new TableView<Student>();
		tvUpareniStudenti.setItems(olUpareniStudenti); 		//zelimo direktnu vezu
		
		TableColumn<Student, String> tcIme = new TableColumn<Student, String>("Ime");
		TableColumn<Student, String> tcPrezime = new TableColumn<Student, String>("Prezime");
		TableColumn<Student, String> tcGodina = new TableColumn<Student, String>("Godina");
		TableColumn<Student, String> tcBroj = new TableColumn<Student, String>("Broj");
		TableColumn<Student, String> tcSmer = new TableColumn<Student, String>("Smer");
		TableColumn<Student, Integer> tcPoeni = new TableColumn<Student, Integer>("Poeni kviz");
		
		tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
		tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		tcGodina.setCellValueFactory(new PropertyValueFactory<>("godUpisa"));
		tcBroj.setCellValueFactory(new PropertyValueFactory<>("brIndexa"));
		tcSmer.setCellValueFactory(new PropertyValueFactory<>("smer"));
		tcPoeni.setCellValueFactory(new PropertyValueFactory<>("poeni"));
		tvUpareniStudenti.getColumns().addAll(tcIme, tcPrezime, tcGodina, tcBroj, tcSmer, tcPoeni);
		
		//FILTER ZA LIST VIEW SVIH STUDENATA
		cmbSmer = new ComboBox<String>();
		cmbSmer.getItems().addAll(Baza.getSmerovi()); 			//ne treba nam direktna veza, imamo button za filtriranje
		cmbSmer.setValue("");
		cmbGodina = new ComboBox<String>();
		Collections.sort(Baza.getGodine());
		cmbGodina.getItems().addAll(Baza.getGodine()); 			//ne treba nam direktna veza, imamo button za filtriranje
		cmbGodina.setValue("");
		btnFiltriraj = new Button("Filtriraj");
		hbFilter = new HBox(10);
		hbFilter.setAlignment(Pos.CENTER);
		
		lvSviStudenti = new ListView<Student>();
		lvSviStudenti.getItems().addAll(Baza.getSviStudenti()); 		//ne treba nam direktna veza, imamo button za uparivanje
		btnUpari = new Button("Upari");
		lvNeupareniEmailovi = new ListView<NeupareniEmail>();
		lvNeupareniEmailovi.setItems(olNeupareniEmailovi); 				//direktna veza
		vbSviStudenti = new VBox(5);
		vbSviStudenti.setAlignment(Pos.CENTER);
		
		hbDonjiDeo = new HBox(20);
		hbDonjiDeo.setAlignment(Pos.CENTER);
		btnSnimiPoene = new Button("Snimi poene");
		
		//AKCIJE
		btnFiltriraj.setOnAction(new FiltrirajController(cmbSmer, cmbGodina, lvSviStudenti));
		btnUpari.setOnAction(new UpariController(lvSviStudenti, lvNeupareniEmailovi));
		btnSnimiPoene.setOnAction(new SnimiPoeneController());
	}
	
	private void addElements() {
		hbFilter.getChildren().addAll(new Label("Smer"), cmbSmer, new Label("Godina"), cmbGodina, btnFiltriraj);
		vbSviStudenti.getChildren().addAll(hbFilter, lvSviStudenti);
		hbDonjiDeo.getChildren().addAll(vbSviStudenti, btnUpari, lvNeupareniEmailovi);

		getChildren().addAll(tvUpareniStudenti, hbDonjiDeo, btnSnimiPoene);
		setPadding(new Insets(20));
		setSpacing(20);
		setAlignment(Pos.CENTER);
	}

	public static ObservableList<Student> getOlUpareniStudenti() {
		return olUpareniStudenti;
	}

	public static ObservableList<NeupareniEmail> getOlNeupareniEmailovi() {
		return olNeupareniEmailovi;
	}
	
}
