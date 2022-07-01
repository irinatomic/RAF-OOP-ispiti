package view;

import java.util.Date;
import java.util.List;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import utils.SnimiPodatke;

public class StartView extends VBox{

	private Label lblStanje;
	private int trenutnoStanje;
	private TableView<Akcija> tvAkcije;
	private HBox hbDugmad;
	private Button btnNovaIsplata;
	private Button btnPregledIsplata;
	private Button btnSnimi;
	
	private static ObservableList<Akcija> olAkcije = FXCollections.observableList(Baza.getAkcije());
	
	public StartView() {
		init();
		actions();
		addElements();
	}
	
	private void izracunajTrenutnoSTanje() {
		trenutnoStanje = 0;
		for(Akcija a : Baza.getAkcije()) {
			if(a instanceof Uplata)
				trenutnoStanje += a.getIznos();
			else
				trenutnoStanje -= a.getIznos();
		}
	}
	
	private void tabelaAkcija() {
		tvAkcije = new TableView<Akcija>();
		TableColumn<Akcija, Integer> tcIznos = new TableColumn<Akcija, Integer>("Iznos");
		TableColumn<Akcija, String> tcUplatilacPrimalac = new TableColumn<Akcija, String>("Uplatilac/Primalac");
		TableColumn<Akcija, String> tcDatum = new TableColumn<Akcija, String>("Datum");
		TableColumn<Akcija, String> tcTip = new TableColumn<Akcija, String>("Tip");
		
		tcIznos.setCellValueFactory(new PropertyValueFactory<>("iznos"));
		tcUplatilacPrimalac.setCellValueFactory(new PropertyValueFactory<>("uplatilacPrimalac"));
		tcDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
		tcTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
		tvAkcije.getColumns().addAll(tcIznos, tcUplatilacPrimalac, tcDatum, tcTip);
		tvAkcije.setItems(olAkcije);
		
		tvAkcije.setFixedCellSize(30);
		tvAkcije.setMaxHeight(8 * tvAkcije.getFixedCellSize());
		tvAkcije.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	private void init() {
		izracunajTrenutnoSTanje();
		lblStanje = new Label("Trenutno stanje: " + trenutnoStanje + " RSD");
		tabelaAkcija();
		
		hbDugmad = new HBox(20);
		btnNovaIsplata = new Button("Nova isplata");
		btnPregledIsplata = new Button("Pregled isplata");
		btnSnimi = new Button("Snimi");
		hbDugmad.getChildren().addAll(btnNovaIsplata, btnPregledIsplata, btnSnimi);
		hbDugmad.setAlignment(Pos.CENTER);
	}
	
	private void actions() {
		btnNovaIsplata.setOnAction(event -> {
			Main.getWindow().setScene(new Scene(new NovaIsplataView()));
			Main.getWindow().show();
		});
		
		btnPregledIsplata.setOnAction(event -> {
			List<Akcija> selektovano = tvAkcije.getSelectionModel().getSelectedItems();
			Main.getWindow().setScene(new Scene(new PregledIsplataView(selektovano)));
			Main.getWindow().show();
		});
		
		btnSnimi.setOnAction(event -> SnimiPodatke.snimiPodatke());
	}
	
	private void addElements() {
		getChildren().addAll(lblStanje, tvAkcije, hbDugmad);
		setSpacing(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 0, 10, 0));
	}

	public static ObservableList<Akcija> getOlAkcije() {
		return olAkcije;
	}
}
