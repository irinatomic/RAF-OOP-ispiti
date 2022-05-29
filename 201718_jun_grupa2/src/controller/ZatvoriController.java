package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import view.MainView;

public class ZatvoriController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Scene scene = new Scene(new MainView(), 600, 450);
		Main.getWindow().setScene(scene);
	}

}
