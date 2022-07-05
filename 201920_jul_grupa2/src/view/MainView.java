package view;

import java.util.Collections;
import java.util.List;

import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class MainView extends Stage{

	private static MainView instance;
	private static BorderPane bpView;
	private static VBox vbLevo;
	private static ListView<AkcijaStatistika> lvStatistika;
	private static GridPane gpCentar;
	private static ComboBox<String> cmbFilter;
	private static TextField tfDanFilter;
	private static TextField tfMesecFilter;
	private static TextField tfGodinaFilter;
	private static Button btnResetuj;
	private static Button btnFiltriraj;
	private static Button btnPrikazi;
	private static Button btnObracun;
	private static TableView<Polaznik> tvPolaznici;
	
	private static ObservableList<AkcijaStatistika> olStatistika = FXCollections.observableList(Baza.getStatistika());
	private static ObservableList<Polaznik> olPolaznici = FXCollections.observableArrayList();
	
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
	
	private void gridPaneCentar() {
		gpCentar = new GridPane();
		cmbFilter = new ComboBox<String>(FXCollections.observableArrayList(List.of("pre", "posle")));
		cmbFilter.getSelectionModel().select(0);
		tfDanFilter = new TextField();
		tfMesecFilter = new TextField();
		tfGodinaFilter = new TextField();
		btnResetuj = new Button("Resetuj");
		btnFiltriraj = new Button("Filtriraj");
		btnPrikazi = new Button("Prikazi");
		btnObracun = new Button("Obracun");
		
		gpCentar.addRow(0, new Label("Filter akcija"));
		gpCentar.add(new Label("Ubaci samo one koji imaju"), 0, 1, 2, 1);
		gpCentar.addRow(2, new Label(""), new Label("Dan"), new Label("Mesec"), new Label("Godina"));
		gpCentar.addRow(3, cmbFilter, tfDanFilter, tfMesecFilter, tfGodinaFilter);
		gpCentar.addRow(4, btnResetuj, btnFiltriraj, btnPrikazi);
		gpCentar.addRow(5, btnObracun);
		gpCentar.setHgap(10);
		gpCentar.setVgap(10);
	}
	
	private void tableViewPolaznici() {
		tvPolaznici = new TableView<Polaznik>(olPolaznici);
		TableColumn<Polaznik, String> tcIme = new TableColumn<Polaznik, String>("Ime");
		TableColumn<Polaznik, String> tcPrezime = new TableColumn<Polaznik, String>("Prezime");
		TableColumn<Polaznik, Integer> tcBrPuta = new TableColumn<Polaznik, Integer>("Broj puta");
		TableColumn<Polaznik, String> tcIznos = new TableColumn<Polaznik, String>("Iznos");
		
		tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
		tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		tcBrPuta.setCellValueFactory(new PropertyValueFactory<>("brPuta"));
		tcIznos.setCellValueFactory(new PropertyValueFactory<>("iznos"));
		tvPolaznici.getColumns().addAll(List.of(tcIme, tcPrezime, tcBrPuta, tcIznos));
	}
	
	private void init() {
		Collections.sort(olStatistika);
		lvStatistika = new ListView<AkcijaStatistika>(olStatistika);
		lvStatistika.getSelectionModel().select(0);
		vbLevo = new VBox(5, new Label("Akcije"), lvStatistika, new Label("Polaznici"));
		gridPaneCentar();
		tableViewPolaznici();
	}
	
	private void actions() {
		btnResetuj.setOnAction(new ResetujController());
		btnFiltriraj.setOnAction(new FiltrirajController(cmbFilter, tfDanFilter, tfMesecFilter, tfGodinaFilter, lvStatistika));
		btnPrikazi.setOnAction(new PrikaziController(lvStatistika, tvPolaznici));
		btnObracun.setOnAction(event -> ObracunView.getInstance());
	}
	
	private void addElements() {
		bpView = new BorderPane(gpCentar, null, null, tvPolaznici, vbLevo);
		BorderPane.setMargin(gpCentar, new Insets(10));
		bpView.setPadding(new Insets(10));
		
		Scene scene = new Scene(bpView, 850, 550);
		setScene(scene);
		show();
	}

	public static ObservableList<Polaznik> getOlPolaznici() {
		return olPolaznici;
	}

	public static ObservableList<AkcijaStatistika> getOlStatistika() {
		return olStatistika;
	}
}
