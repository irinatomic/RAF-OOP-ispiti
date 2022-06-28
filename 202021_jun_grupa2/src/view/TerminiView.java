package view;

import java.util.Collections;

import application.Main;
import controller.DodajController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;
import model.Baza;
import model.Termin;
import model.Ucionica;
import utils.FileUtil;

public class TerminiView extends VBox {

	private HBox hbPrebaci;
	private Button btnPrebaci;
	private Button btnSacuvaj;
	private TableView<Termin> tvTermini;
	private GridPane gpDodajSatnicu;
	private TextField tfDodajTermin;
	private ListView<Ucionica> lvUcionice;
	private Button btnDodaj;
	
	private static ObservableList<Termin> olTermini = null;
	
	public TerminiView() {
		init();
		actions();
		addElements();
	}
	
	private void tvTermini() {
		tvTermini = new TableView<Termin>();
		TableColumn<Termin, String> tcUcionice = new TableColumn<Termin, String>("Ucionica");
		TableColumn<Termin, Integer> tcSatnica = new TableColumn<Termin, Integer>("Termin");
		TableColumn<Termin, Integer> tcBrStudenata = new TableColumn<Termin, Integer>("Broj studenata");
		TableColumn<Termin, Integer> tcPrekoracenje = new TableColumn<Termin, Integer>("Prekoracenje");
		TableColumn<Termin, Integer> tcSlobodno = new TableColumn<Termin, Integer>("Slobodno");
		
		tcUcionice.setCellValueFactory(new PropertyValueFactory<>("ucionica"));
		tcSatnica.setCellValueFactory(new PropertyValueFactory<>("satnica"));
		tcBrStudenata.setCellValueFactory(new PropertyValueFactory<>("brStudenata"));
		tcPrekoracenje.setCellValueFactory(new PropertyValueFactory<>("prekoracenje"));
		tcSlobodno.setCellValueFactory(new PropertyValueFactory<>("slobodno"));
		tvTermini.getColumns().addAll(tcUcionice, tcSatnica, tcBrStudenata, tcPrekoracenje, tcSlobodno);
		
		olTermini = FXCollections.observableList(Baza.getTermini());
		tvTermini.getItems().clear();
		tvTermini.setItems(olTermini);
	}
	
	private void gpDodajSatnicu() {
		gpDodajSatnicu = new GridPane();
		tfDodajTermin = new TextField();
		btnDodaj = new Button("Dodaj");
		lvUcionice = new ListView<Ucionica>();
		lvUcionice.getItems().addAll(Baza.getUcionice());
		lvUcionice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//CUSTOM LIST VIEW
		lvUcionice.setCellFactory(new Callback<ListView<Ucionica>, ListCell<Ucionica>>() {
			@Override
			public ListCell<Ucionica> call(ListView<Ucionica> param) {
				ListCell<Ucionica> celija = new ListCell<Ucionica>() {
					protected void updateItem(Ucionica ucionica, boolean empty) {
						super.updateItem(ucionica, empty);
						if(empty || ucionica == null)
							setText(null);
						else
							setText(ucionica.getLVIspis());
					}
				};
				return celija;
			}
		});
		
		gpDodajSatnicu.addRow(0, new Label("Novi termin"), tfDodajTermin);
		gpDodajSatnicu.addRow(1, new Label("Ucionice"), lvUcionice);
		gpDodajSatnicu.add(btnDodaj, 1, 2);
		gpDodajSatnicu.setAlignment(Pos.CENTER_LEFT);
		gpDodajSatnicu.setHgap(5);
		gpDodajSatnicu.setVgap(5);
	}
	
	private void init() {
		hbPrebaci = new HBox(5);
		btnPrebaci = new Button("Prebaci");
		btnSacuvaj = new Button("Sacuvaj");
		hbPrebaci.getChildren().addAll(btnPrebaci, btnSacuvaj);
		hbPrebaci.setAlignment(Pos.CENTER_RIGHT);
		tvTermini();
		gpDodajSatnicu();
	}
	
	private void actions() {
		btnPrebaci.setOnAction(event -> {
			if(tvTermini.getSelectionModel().getSelectedItem() == null) {
				new Alert(AlertType.ERROR, "Selektujte termin koji zelite da izmenite").show();
				return;
			}
			
			Baza.setIzabranTermin(tvTermini.getSelectionModel().getSelectedItem());
			Scene scene = new Scene(new PrebacivanjeView());
			Main.getWindow().setScene(scene);
			Main.getWindow().show();
		});
		
		btnDodaj.setOnAction(new DodajController(tfDodajTermin, lvUcionice));
		
		btnSacuvaj.setOnAction(event -> {
			Collections.sort(Baza.getTermini());
			FileUtil.upisiUFajl("sacuvano.txt");
		});
	}
	
	private void addElements() {
		getChildren().addAll(hbPrebaci, tvTermini, gpDodajSatnicu);
		setPadding(new Insets(10));
		setSpacing(5);
	}

	public static ObservableList<Termin> getOlTermini() {
		return olTermini;
	}
}
