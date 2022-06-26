package view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ArtikalKorpa;
import model.Baza;

public class MojaKolekcijaView extends Stage{

	private static MojaKolekcijaView instance;
	private VBox vbView;
	private TableView<ArtikalKorpa> tvKorpa;
	
	private GridPane gpStatistika;
	private TextField tfVinyl;
	private TextField tfCD;
	private TextField tfUkKomada;
	private TextField tfPolovnih;
	private TextField tfNovih;
	private TextField tfVrednost;
	
	public static MojaKolekcijaView getInstance() {
		if(instance == null)
			instance = new MojaKolekcijaView();
		return instance;
	}
	
	private MojaKolekcijaView() {
		init();
		addElements();
	}
	
	private void tabelaKorpa() {
		tvKorpa = new TableView<ArtikalKorpa>();
		tvKorpa.getItems().addAll(Baza.getKupljeniArtikli());
		
		TableColumn<ArtikalKorpa, String> tcIzvodjac = new TableColumn<ArtikalKorpa, String>("Naziv izvodjaca");
		TableColumn<ArtikalKorpa, List<String>> tcAlbumi = new TableColumn<ArtikalKorpa, List<String>>("Albumi");
		tcIzvodjac.setCellValueFactory(new PropertyValueFactory<>("izvodjac"));
		tcAlbumi.setCellValueFactory(new PropertyValueFactory<>("albumi"));
		tvKorpa.getColumns().addAll(tcIzvodjac, tcAlbumi);
	}
	
	private void izracunajStatistiku() {
		int brVinyl = 0;
		int brCD = 0;
		int ukKomada = 0;
		int brPolovnih = 0;
		int vrednost = 0;
		
		for(ArtikalKorpa ak : Baza.getKupljeniArtikli()) {
			brVinyl += ak.getBrVinyl();
			brCD += ak.getBrCD();
			ukKomada += ak.getUkKomada();
			vrednost += ak.getVrednost();
			brPolovnih += ak.getBrPolovnih();
		}
		
		tfVinyl.setText(brVinyl + "");
		tfCD.setText(brCD + "");
		tfUkKomada.setText(ukKomada + "");
		tfVrednost.setText(vrednost + "");
		
		double polovnProcenat = brPolovnih * 100 / ukKomada;
		tfPolovnih.setText(polovnProcenat + "%");
		tfNovih.setText((100 - polovnProcenat) + "%");
	}

	private void gridPaneStatistika() {
		gpStatistika = new GridPane();
		tfVinyl = new TextField();
		tfCD = new TextField();
		tfUkKomada = new TextField();
		tfPolovnih = new TextField();
		tfNovih = new TextField();
		tfVrednost = new TextField();
		izracunajStatistiku();
		
		gpStatistika.add(new Label("Vinyl"), 0, 0);
		gpStatistika.add(tfVinyl, 1, 0);
		gpStatistika.add(new Label("Polovnih"), 2, 0);
		gpStatistika.add(tfPolovnih, 3, 0);
		
		gpStatistika.add(new Label("CD"), 0, 1);
		gpStatistika.add(tfCD, 1, 1);
		gpStatistika.add(new Label("Novih"), 2, 1);
		gpStatistika.add(tfNovih, 3, 1);
		
		gpStatistika.add(new Label("Ukupno komada"), 0, 2);
		gpStatistika.add(tfUkKomada, 1, 2);
		gpStatistika.add(new Label("Vrednost Kolekcije"), 2, 2);
		gpStatistika.add(tfVrednost, 3, 2);
		
		gpStatistika.setAlignment(Pos.CENTER);
		gpStatistika.setHgap(20);
		gpStatistika.setVgap(20);
	}
	
	private void init() {
		vbView = new VBox(20);
		tabelaKorpa();
		gridPaneStatistika();
	}
	
	private void addElements() {
		vbView.getChildren().addAll(tvKorpa, gpStatistika);
		vbView.setAlignment(Pos.CENTER);
		vbView.setPadding(new Insets(20, 20, 0, 20));
		
		setTitle("Moja kolekcija");
		Scene scene = new Scene(vbView, 600, 400);
		setScene(scene);
		show();
	}
}
