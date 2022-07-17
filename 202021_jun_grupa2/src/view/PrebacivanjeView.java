package view;

import controller.PrebaciController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.*;

public class PrebacivanjeView extends VBox {

	private Label lblSelektUcionica;
	private ListView<Student> lvStudenti;
	private GridPane gpPrebacivanje;
	private ComboBox<Integer> cmbSatnice;
	private ComboBox<Ucionica> cmbUcionice;
	private Button btnPrebaci;
	private Label lblPoruka;
	
	public PrebacivanjeView() {
		init();
		addElements();
	}
	
	private void gpPrebacivanje() {
		gpPrebacivanje = new GridPane();
		cmbSatnice = new ComboBox<Integer>();
		cmbSatnice.getItems().clear();
		cmbSatnice.getItems().addAll(Baza.getSatnice());
		cmbUcionice = new ComboBox<Ucionica>();
		cmbUcionice.getItems().clear();
		cmbUcionice.getItems().addAll(Baza.getUcionice());
		btnPrebaci = new Button("Prebaci");
		lblPoruka = new Label("");
		
		gpPrebacivanje.addRow(0, new Label("Termin"), cmbSatnice);
		gpPrebacivanje.addRow(1, new Label("Ucionica"), cmbUcionice);
		gpPrebacivanje.add(btnPrebaci, 1, 2);
		gpPrebacivanje.add(lblPoruka, 1, 3);
		gpPrebacivanje.setHgap(5);
		gpPrebacivanje.setVgap(5);
		gpPrebacivanje.setAlignment(Pos.CENTER);
	}
	
	private void init() {
		lblSelektUcionica = new Label(Baza.getIzabranTermin().getInfo());		
		lvStudenti = new ListView<Student>();
		lvStudenti.getItems().clear();
		lvStudenti.getItems().addAll(Baza.getIzabranTermin().getStudenti());
		lvStudenti.setFixedCellSize(20);
		lvStudenti.setMaxHeight(lvStudenti.getFixedCellSize() * 8);
		lvStudenti.setMinWidth(400);
		lvStudenti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		gpPrebacivanje();
		
		//AKCIJE
		btnPrebaci.setOnAction(new PrebaciController(cmbSatnice, cmbUcionice, lvStudenti, lblPoruka));
	}
	
	private void addElements() {
		getChildren().addAll(lblSelektUcionica, lvStudenti, gpPrebacivanje);
		setPadding(new Insets(20));
		setSpacing(10);
	}
}
