package application;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.FileUtils;
import view.MainStage;

public class Main extends Application {

	public static void main(String[] args) {
		FileUtils.getInstance().ucitajTermine("raspored.txt");
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainStage.getInstance();
	}

}
