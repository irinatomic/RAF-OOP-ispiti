package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import view.MainStage;

public class PovratakNaMainStageController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Main.getWindow().setScene(new Scene(new MainStage(), 600, 600));
	}

}
