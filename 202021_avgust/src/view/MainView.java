package view;

import java.util.List;

import controller.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import utils.*;

public class MainView extends Stage {

	private static MainView instance;
	private static BorderPane bpView;
	private static ListView<Pitanje> lvPitanja;
	private static TableView<Rezultat> tvRezultati;
	private static HBox hbDugmad;
	private static ComboBox<String> cmbPredmeti;
	private static Button btnUcitajZaPredmet;
	private static Button btnDodajPitanje;
	private static Button btnSnimi;
	private static Button btnIgraj;
	
	private static ObservableList<Pitanje> olPitanja = FXCollections.observableArrayList(Baza.getPitanja());
	private static ObservableList<Rezultat> olRezultati = FXCollections.observableList(Baza.getRezultati());

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
	
	private void tableViewRezultati() {
		tvRezultati = new TableView<Rezultat>();
		TableColumn<Rezultat, String> tcPredmet = new TableColumn<Rezultat, String>("Predmet");
		TableColumn<Rezultat, String> tcDatum = new TableColumn<Rezultat, String>("Datum");
		TableColumn<Rezultat, Integer> tcSkor = new TableColumn<Rezultat, Integer>("Skor");
		
		tcPredmet.setCellValueFactory(new PropertyValueFactory<>("predmet"));
		tcDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
		tcSkor.setCellValueFactory(new PropertyValueFactory<>("skor"));
		tvRezultati.getColumns().addAll(List.of(tcPredmet, tcDatum, tcSkor));
		tvRezultati.setItems(olRezultati);
	}
	
	private void init() {
		lvPitanja = new ListView<Pitanje>(olPitanja);
		lvPitanja.setPrefWidth(600);
		tableViewRezultati();
		cmbPredmeti = new ComboBox<String>(FXCollections.observableList(Baza.getPredmeti()));
		cmbPredmeti.setValue("Svi predmeti");
		btnUcitajZaPredmet = new Button("Ucitaj za predmet");
		btnDodajPitanje = new Button("Dodaj pitanje");
		btnSnimi = new Button("Snimi");
		btnIgraj = new Button("Igraj");
		hbDugmad = new HBox(10, cmbPredmeti, btnUcitajZaPredmet, btnDodajPitanje, btnSnimi, btnIgraj);
		hbDugmad.setAlignment(Pos.CENTER);
		bpView = new BorderPane(lvPitanja, null, tvRezultati, hbDugmad, null);
	}
	
	private void actions() {
		btnUcitajZaPredmet.setOnAction(new FilterController(cmbPredmeti));
		btnDodajPitanje.setOnAction(event -> DodajPitanjeView.getInstance());
		btnSnimi.setOnAction(event -> FileUtil.upisiPitanjaUFajl("pitanja.txt"));
		btnIgraj.setOnAction(event -> IgrajView.getInstance(cmbPredmeti.getSelectionModel().getSelectedItem()));
	}
	
	private void addElements() {
		BorderPane.setMargin(lvPitanja, new Insets(20));
		BorderPane.setMargin(tvRezultati, new Insets(20));
		BorderPane.setMargin(hbDugmad, new Insets(20));
		bpView.setPadding(new Insets(25));
		
		Scene scene = new Scene(bpView);
		setScene(scene);
		show();
	}

	public static ObservableList<Pitanje> getOlPitanja() {
		return olPitanja;
	}

	public static ObservableList<Rezultat> getOlRezultati() {
		return olRezultati;
	}

	public static ComboBox<String> getCmbPredmeti() {
		return cmbPredmeti;
	}
}
