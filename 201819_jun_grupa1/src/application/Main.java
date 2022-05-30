package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Baza;
import utility.FileUtil;
import view.MainStage;


public class Main extends Application {

	public static void main(String[] args) {
		Baza.getInstance();
		FileUtil.getInstance().ucitajStudente("studenti.txt");
		FileUtil.getInstance().ucitajPoene("pitanje1.txt");
		FileUtil.getInstance().ucitajPoene("pitanje2.txt");
		FileUtil.getInstance().ucitajPoene("pitanje3.txt");

		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainStage.getInstance();
	}

}
