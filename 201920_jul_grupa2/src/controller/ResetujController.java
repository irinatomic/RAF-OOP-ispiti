package controller;

import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.*;
import view.MainView;

public class ResetujController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		for(AkcijaStatistika as : Baza.getStatistika())
			as.resetuj();
		Collections.sort(MainView.getOlStatistika());
	}
}
