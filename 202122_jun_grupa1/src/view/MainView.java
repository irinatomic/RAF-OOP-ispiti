package view;

import java.util.List;

import controller.*;
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
import utils.FileUtil;

public class MainView extends Stage {

	private static MainView instance;
	private BorderPane bpView;
	private VBox vbVanzemaljci;
	private HBox hbFilter;
	private ComboBox<Kontinent> cmbKontinenti;
	private CheckBox cbPronadjen;
	private CheckBox cbIzgubljen;
	private Button btnFiltriraj;
	private TableView<Vanzemaljac> tvVanzemaljci;
	private VBox vbPronadjen;
	private ComboBox<Drzava> cmbDrzave;
	private Button btnUpisi;
	private Label lblPoruka;
	private Button btnSacuvaj;
	
	private static ObservableList<Vanzemaljac> olVanzemaljci = FXCollections.observableArrayList(Baza.getVanzemaljci());
	private static ObservableList<Kontinent> olKontinenti = FXCollections.observableList(Baza.getKontinenti());
	private static ObservableList<Drzava> olDrzave = FXCollections.observableList(Baza.getDrzave());

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
	
	private void HBoxFilter() {
		cmbKontinenti = new ComboBox<Kontinent>(olKontinenti);
		cmbKontinenti.getSelectionModel().select(0);
		cbPronadjen = new CheckBox("Pronadjen");
		cbIzgubljen = new CheckBox("Izgubljen");
		btnFiltriraj = new Button("Filtriraj");
		hbFilter = new HBox(5, new Label("Filter"), cmbKontinenti, cbPronadjen, cbIzgubljen, btnFiltriraj);
		hbFilter.setAlignment(Pos.CENTER);
	}
	
	private void tableViewVanzemaljci() {
		tvVanzemaljci = new TableView<Vanzemaljac>(olVanzemaljci);
		TableColumn<Vanzemaljac, Integer> tcId = new TableColumn<Vanzemaljac, Integer>("Id");
		TableColumn<Vanzemaljac, String> tcDrzava = new TableColumn<Vanzemaljac, String>("Drzava");
		TableColumn<Vanzemaljac, String> tcKontinent = new TableColumn<Vanzemaljac, String>("Kontinent");
		
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcDrzava.setCellValueFactory(new PropertyValueFactory<>("drzava"));
		tcKontinent.setCellValueFactory(new PropertyValueFactory<>("kontinent"));
		tvVanzemaljci.getColumns().addAll(List.of(tcId, tcKontinent, tcDrzava));
	}
	
	private void VBoxPronadjen() {
		cmbDrzave = new ComboBox<Drzava>(olDrzave);
		cmbDrzave.getSelectionModel().select(0);
		btnUpisi = new Button("Upisi");
		lblPoruka = new Label("");
		lblPoruka.setWrapText(true);
		lblPoruka.setMaxWidth(90);
		vbPronadjen = new VBox(5, new Label("Pronadjen"), cmbDrzave, btnUpisi, lblPoruka);
		vbPronadjen.setAlignment(Pos.CENTER);
	}
	
	private void init() {
		HBoxFilter();
		tableViewVanzemaljci();
		vbVanzemaljci = new VBox(5, hbFilter, tvVanzemaljci);
		vbVanzemaljci.setAlignment(Pos.CENTER);
		VBoxPronadjen();
		btnSacuvaj = new Button("Sacuvaj");
		
		bpView = new BorderPane(vbVanzemaljci, null, vbPronadjen, btnSacuvaj, null);
		bpView.setPadding(new Insets(10));
		BorderPane.setMargin(vbVanzemaljci, new Insets(10));
		BorderPane.setAlignment(btnSacuvaj, Pos.CENTER);
	}
	
	private void actions() {
		btnFiltriraj.setOnAction(new FiltrirajController(cmbKontinenti, cbPronadjen, cbIzgubljen));
		btnUpisi.setOnAction(new UpisiContoller(tvVanzemaljci, cmbDrzave, lblPoruka));
		btnSacuvaj.setOnAction(event -> {
			Baza.obracunajPronadjene();
			FileUtil.ispisiUFajl("regije.txt");
		});
	}
	
	private void addElements() {
		Scene scene = new Scene(bpView, 700, 500);
		setScene(scene);
		show();
	}

	public static ObservableList<Vanzemaljac> getOlVanzemaljci() {
		return olVanzemaljci;
	}
}
