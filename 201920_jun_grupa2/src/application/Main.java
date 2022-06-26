package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Baza;
import view.MainView;

public class Main extends Application {

	public static void main(String[] args) {
		Baza.getInstance();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView.getInstance();
	}

}
