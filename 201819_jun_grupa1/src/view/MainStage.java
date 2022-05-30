package view;

import java.util.Collections;

import controller.FiltrirajController;
import controller.SnimiPoeneController;
import controller.UpariController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Baza;
import model.NeupareniEmail;
import model.Student;

public class MainStage extends Stage{

	private static MainStage instance;
	
	private VBox vbox;
	private TableView<Student> tvUpareniStudenti;
	
	private VBox maliVBox;
	private HBox hbFilter;
	private TextField tfFilter;
	private Button btnFiltriraj;
	private ListView<Student> lvSviStudenti;
	
	private HBox hbDonjiDeo;
	private Button btnUpari;
	private ListView<NeupareniEmail> lvNeupareniEmail;
	private Button btnSnimiPoene;
	
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
		vbox = new VBox(15);
		tvUpareniStudenti = new TableView<Student>();
	    TableColumn<Student, String> tcIme = new TableColumn<Student, String>("Ime");
		TableColumn<Student, String> tcPrezime = new TableColumn<Student, String>("Prezime");
		TableColumn<Student, String> tcGodina = new TableColumn<Student, String>("Godina");
		TableColumn<Student, String> tcBroj = new TableColumn<Student, String>("Broj");
		TableColumn<Student, String> tcSmer = new TableColumn<Student, String>("Smer");
		TableColumn<Student, Integer> tcPitanje1 = new TableColumn<Student, Integer>("Pitanje1");
		TableColumn<Student, Integer> tcPitanje2 = new TableColumn<Student, Integer>("Pitanje2");
		TableColumn<Student, Integer> tcPitanje3 = new TableColumn<Student, Integer>("Pitanje3");
		
		tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
		tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		tcGodina.setCellValueFactory(new PropertyValueFactory<>("godUpisa"));
		tcBroj.setCellValueFactory(new PropertyValueFactory<>("brIndexa"));
		tcSmer.setCellValueFactory(new PropertyValueFactory<>("smer"));
		tcPitanje1.setCellValueFactory(new PropertyValueFactory<>("pitanje1"));
		tcPitanje2.setCellValueFactory(new PropertyValueFactory<>("pitanje1"));
		tcPitanje3.setCellValueFactory(new PropertyValueFactory<>("pitanje1"));
		tvUpareniStudenti.getColumns().addAll(tcIme, tcPrezime, tcGodina, tcBroj, tcSmer, tcPitanje1, tcPitanje2, tcPitanje3);
		tvUpareniStudenti.setItems(Baza.getOlUpareniStudenti());
		
		maliVBox = new VBox(15);
		hbFilter = new HBox(10);
		tfFilter = new TextField();
		btnFiltriraj = new Button("Filtriraj");
		Collections.sort(Baza.getSviStudenti());
		lvSviStudenti = new ListView<Student>();
		lvSviStudenti.getItems().addAll(Baza.getSviStudenti());
		
		hbDonjiDeo = new HBox(20);
		btnUpari = new Button("Upari");
		lvNeupareniEmail = new ListView<NeupareniEmail>();
		lvNeupareniEmail.setItems(Baza.getOlNeupareniEmailovi());
		btnSnimiPoene = new Button("Snimi poene");
		
		//AKCIJE
		btnFiltriraj.setOnAction(new FiltrirajController(tfFilter, lvSviStudenti));
		btnUpari.setOnAction(new UpariController(lvSviStudenti, lvNeupareniEmail));
		btnSnimiPoene.setOnAction(new SnimiPoeneController());
	}
	
	private void addElements() {
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		
		hbFilter.getChildren().addAll(new Label("Filtriraj"), tfFilter, btnFiltriraj);
		maliVBox.getChildren().addAll(hbFilter, lvSviStudenti); 								//DOLE LEVO
		hbDonjiDeo.getChildren().addAll(maliVBox, btnUpari, lvNeupareniEmail);
		hbDonjiDeo.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(tvUpareniStudenti, hbDonjiDeo, btnSnimiPoene);
		
		Scene scene = new Scene(vbox, 700, 600);
		setScene(scene);
		show();
	}
}
