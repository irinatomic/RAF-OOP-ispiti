package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.ObracunUtil;

public class ObracunView extends Stage {

	private static ObracunView instance;
	private GridPane gpView;
	private Label lblUplacenIznos;
	private Label lblDug;
	private Label lblBrojPretplacenih;

	public static ObracunView getInstance() {
		if(instance == null)
			instance = new ObracunView();
		return instance;
	}
	
	private ObracunView() {
		init();
		addElements();
	}
	
	private void init() {
		gpView = new GridPane();
		lblUplacenIznos = new Label(ObracunUtil.getUkupanUplacenIznos());
		lblDug = new Label(ObracunUtil.getUkupanDugZaCasove());
		lblBrojPretplacenih = new Label(ObracunUtil.getBrojPretplacenih());
		
		gpView.addRow(0, new Label("Ukupan uplaceni iznos"), lblUplacenIznos);
		gpView.addRow(1, new Label("Ukupan dug za casove"), lblDug);
		gpView.addRow(2, new Label("Broj pretplacenih polaznika"), lblBrojPretplacenih);
		
		gpView.setHgap(20);
		gpView.setVgap(10);
		gpView.setPadding(new Insets(20));
		gpView.setAlignment(Pos.CENTER);
	}
	
	private void addElements() {
		setTitle("Prva grupa");
		Scene scene = new Scene(gpView);
		setScene(scene);
		show();
	}
}
