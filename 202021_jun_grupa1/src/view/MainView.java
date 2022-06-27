package view;

import java.util.ArrayList;
import java.util.List;

import controller.DodeliController;
import controller.SnimiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.*;

public class MainView extends BorderPane {

	private VBox vbLevo;
	private VBox vbDesno;
	private HBox hbTerminFilter;
	private VBox vbDole;
	private Label lblBrNerasporedjenih;
	private ListView<Student> lvNerasporedjeni;
	private Button btnDodeli;
	private ComboBox<String> cmbTermin;
	private ListView<String> lvUcionice;
	
	private TableView<Student> tvRasporedjeni;
	private Button btnSnimi;
	
	private static ObservableList<Student> olNerasporedjeni = FXCollections.observableList(Baza.getNerasporedjeni());
	private static ObservableList<String> olUcionice = FXCollections.observableList(Baza.getTermini().get(0).getUcioniceString());
	private static ObservableList<Student> olRasporedjeni = FXCollections.observableList(Baza.getRasporedjeni());
	
	public MainView() {
		init();
		actions();
		addElements();
	}
	
	private void cmbTermini() {
		List<String> termini = new ArrayList<String>();
		for(Termin t : Baza.getTermini())
			termini.add(t.getTermin());
		
		cmbTermin = new ComboBox<String>();
		cmbTermin.getItems().addAll(termini);
		cmbTermin.setValue("termin1");
	}
	
	private void tabelaRasporedjenih() {
		tvRasporedjeni = new TableView<Student>();
		TableColumn<Student, String> tcStudent = new TableColumn<Student, String>("Student");
		TableColumn<Student, String> tcTermin = new TableColumn<Student, String>("Termin");
		TableColumn<Student, String> tcUcionica = new TableColumn<Student, String>("Ucionica");
		
		tcStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
		tcTermin.setCellValueFactory(new PropertyValueFactory<>("termin"));
		tcUcionica.setCellValueFactory(new PropertyValueFactory<>("ucionica"));
		tvRasporedjeni.getColumns().addAll(tcStudent, tcTermin, tcUcionica);
		tvRasporedjeni.setItems(olRasporedjeni);
		tvRasporedjeni.setMaxHeight(150);
	}
	
	private void init() {
		vbLevo = new VBox(5);
		vbDesno = new VBox(5);
		hbTerminFilter = new HBox(5);
		vbDole = new VBox();
		
		lblBrNerasporedjenih = new Label("Broj nerasporedjenih studenata: " + Baza.getNerasporedjeni().size());
		lvNerasporedjeni = new ListView<Student>();
		lvNerasporedjeni.setItems(olNerasporedjeni);
		lvNerasporedjeni.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		btnDodeli = new Button("Dodeli");
		cmbTermini();
		lvUcionice = new ListView<String>();
		lvUcionice.setItems(olUcionice); 
		
		tabelaRasporedjenih();
		btnSnimi = new Button("Snimi");
	}
	
	private void actions() {
		cmbTermin.setOnAction((event) -> {
			String selektovano = cmbTermin.getSelectionModel().getSelectedItem();
			int redniBroj = Integer.parseInt(selektovano.charAt(selektovano.length() - 1) + "");
			olUcionice = FXCollections.observableList(Baza.getTermini().get(redniBroj - 1).getUcioniceString());
			lvUcionice.getItems().clear();
			lvUcionice.setItems(olUcionice);
		});
		
		btnDodeli.setOnAction(new DodeliController(lvNerasporedjeni, cmbTermin, lvUcionice));
		btnSnimi.setOnAction(new SnimiController());
	}
	
	private void addElements() {
		vbLevo.getChildren().addAll(lblBrNerasporedjenih, lvNerasporedjeni);
		hbTerminFilter.getChildren().addAll(cmbTermin);
		vbDesno.getChildren().addAll(hbTerminFilter, lvUcionice);
		vbDole.getChildren().addAll(tvRasporedjeni, btnSnimi);
		vbDole.setAlignment(Pos.CENTER);
		
		setLeft(vbLevo);
		setRight(vbDesno);
		setCenter(btnDodeli);
		setBottom(vbDole);
		
		setMargin(vbLevo, new Insets(5));
		setMargin(btnDodeli, new Insets(5));
		setMargin(vbDesno, new Insets(5));
		setMargin(vbDole, new Insets(5));
		setPadding(new Insets(10));
	}

	public static ObservableList<Student> getOlNerasporedjeni() {
		return olNerasporedjeni;
	}

	public static ObservableList<String> getOlUcionice() {
		return olUcionice;
	}

	public static ObservableList<Student> getOlRasporedjeni() {
		return olRasporedjeni;
	}
}
