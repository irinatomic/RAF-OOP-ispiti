package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Baza;
import view.MainView;

public class PrikaziSveController implements EventHandler<ActionEvent> {

	private MainView view;
	
	public PrikaziSveController(MainView view) {
		super();
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		view.getCmbFilter().setValue("");
		view.getTfFilter().setText("");
		view.getTvUtakmice().getItems().clear();
		view.getTvUtakmice().getItems().addAll(Baza.getSpisakUtakmica());
	}
}
