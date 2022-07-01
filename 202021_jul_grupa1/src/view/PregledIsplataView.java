package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import model.*;

public class PregledIsplataView extends VBox {

	private int minIsplata = Integer.MAX_VALUE;
	private int maxIsplata = 0;
	private List<KategorijaIsplata> obradjenoSelektovano = new ArrayList<KategorijaIsplata>();
	
	private Label lblMinIsplata;
	private Label lblMaxIsplata;
	private ListView<KategorijaIsplata> lista;
	private Button btnZatvori;
	
	public PregledIsplataView(List<Akcija> selektovano) {
		obradiSelektovano(selektovano);
		init();
		actions();
		addElements();
	}
	
	private void obradiSelektovano(List<Akcija> selektovano) {
		for(Akcija a : selektovano) {
			if(!(a instanceof Isplata))
				continue;
			
			KategorijaIsplata ki = new KategorijaIsplata(((Isplata)a).getKategorija());
			int index = obradjenoSelektovano.indexOf(ki);
			
			if(index == -1) {
				obradjenoSelektovano.add(ki);
				ki.dodajIsplatu((Isplata)a);
			} else 
				obradjenoSelektovano.get(index).dodajIsplatu((Isplata)a);
			
			//ZA MIN I MAX IZNOS
			if(a.getIznos() > maxIsplata)
				maxIsplata = a.getIznos();
			if(a.getIznos() < minIsplata)
				minIsplata = a.getIznos();
		}
	}
	
	private void init() {
		lblMinIsplata = new Label("Minimalna isplata: " + minIsplata + "RSD");
		lblMaxIsplata = new Label("Maksimalna isplata: " + maxIsplata + "RSD");
		lista = new ListView<KategorijaIsplata>();
		lista.getItems().addAll(obradjenoSelektovano);
		lista.setMaxHeight(150);
		btnZatvori = new Button("Zatvori");
	}
	
	private void actions() {
		btnZatvori.setOnAction(event -> {
			Main.getWindow().setScene(new Scene(new StartView(), 500, 320));
			Main.getWindow().show();
		});
	}
	
	private void addElements() {
		getChildren().addAll(lblMinIsplata, lblMaxIsplata, lista, btnZatvori);
		setSpacing(20);
		setPadding(new Insets(10, 0, 10, 0));
		setAlignment(Pos.CENTER);
	}
}
