package view;

import java.util.List;

import controller.IzaberiController;
import controller.KupiController;
import controller.RacunController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class MainView extends Stage {

	private static MainView instance;
	private static BorderPane bpView;
	private static TableView<Predmet> tvPredmeti;
	private static Button btnIzaberi;
	private static VBox vbDesno;
	private static ListView<Udzbenik> lvIzabraniUdzbenici;
	private static HBox hbKupi;
	private static TextField tfBrPrimeraka;
	private static Button btnKupi;
	private static VBox vbDole;
	private static TableView<UdzbenikKorpa> tvKorpa;
	private static Button btnRacun;
	
	private static ObservableList<Predmet> olPredmeti = FXCollections.observableList(Baza.getPredmeti());
	private static ObservableList<Udzbenik> olIzabraniUdzbenici = FXCollections.observableArrayList();
	private static ObservableList<UdzbenikKorpa> olKorpa = FXCollections.observableList(Baza.getUdzbeniciKorpa());

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
	
	private void tableViewPredmeti() {
		tvPredmeti = new TableView<Predmet>(olPredmeti);
		TableColumn<Predmet, String> tcRazred = new TableColumn<Predmet, String>("Razred");
		TableColumn<Predmet, String> tcNaziv = new TableColumn<Predmet, String>("Predmet");
		TableColumn<Predmet, Integer> tcBrPrimeraka = new TableColumn<Predmet, Integer>("Broj");
		
		tcRazred.setCellValueFactory(new PropertyValueFactory<>("razred"));
		tcNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		tcBrPrimeraka.setCellValueFactory(new PropertyValueFactory<>("brUdzbenika"));
		tvPredmeti.getColumns().addAll(List.of(tcRazred, tcNaziv, tcBrPrimeraka));
	}

	private void tableViewKorpa() {
		tvKorpa = new TableView<UdzbenikKorpa>(olKorpa);
		TableColumn<UdzbenikKorpa, String> tcNaslov = new TableColumn<UdzbenikKorpa, String>("Naslov");
		TableColumn<UdzbenikKorpa, String> tcIzdavac = new TableColumn<UdzbenikKorpa, String>("Izdavac");
		TableColumn<UdzbenikKorpa, Integer> tcBrPrimeraka = new TableColumn<UdzbenikKorpa, Integer>("Broj primeraka");
		TableColumn<UdzbenikKorpa, Double> tcCena = new TableColumn<UdzbenikKorpa, Double>("Cena");
		
		tcNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
		tcIzdavac.setCellValueFactory(new PropertyValueFactory<>("izdavac"));
		tcBrPrimeraka.setCellValueFactory(new PropertyValueFactory<>("brPrimeraka"));
		tcCena.setCellValueFactory(new PropertyValueFactory<>("cenaUkupno"));
		tvKorpa.getColumns().addAll(List.of(tcNaslov, tcIzdavac, tcBrPrimeraka, tcCena));
	}
	
	private void init() {
		tableViewPredmeti();
		btnIzaberi = new Button("Izaberi");
		lvIzabraniUdzbenici = new ListView<Udzbenik>();
		lvIzabraniUdzbenici.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvIzabraniUdzbenici.setItems(olIzabraniUdzbenici);
		lvIzabraniUdzbenici.setMinWidth(400);
		tfBrPrimeraka = new TextField();
		btnKupi = new Button("Kupi");
		hbKupi = new HBox(5, new Label("Broj primeraka: "), tfBrPrimeraka, btnKupi);
		hbKupi.setAlignment(Pos.CENTER_RIGHT);
		vbDesno = new VBox(5, lvIzabraniUdzbenici, hbKupi);
		
		tableViewKorpa();
		btnRacun = new Button("Racun");
		vbDole = new VBox(5, new Label("Korpa"), tvKorpa, btnRacun);
		vbDole.setMaxHeight(250);
		vbDole.setAlignment(Pos.CENTER);
	}
	
	private void actions() {
		btnIzaberi.setOnAction(new IzaberiController(tvPredmeti));
		btnKupi.setOnAction(new KupiController(lvIzabraniUdzbenici, tvKorpa, tfBrPrimeraka));
		btnRacun.setOnAction(new RacunController());
	}
	
	private void addElements() {
		bpView = new BorderPane(btnIzaberi, null, vbDesno, vbDole, tvPredmeti);
		bpView.setPadding(new Insets(10));
		
		Scene scene = new Scene(bpView, 750, 600);
		setScene(scene);
		show();
	}

	public static ObservableList<Predmet> getOlPredmeti() {
		return olPredmeti;
	}

	public static ObservableList<Udzbenik> getOlIzabraniUdzbenici() {
		return olIzabraniUdzbenici;
	}

	public static ObservableList<UdzbenikKorpa> getOlKorpa() {
		return olKorpa;
	}
	
	
}
