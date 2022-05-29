package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Baza;
import utils.FileUtil;
import view.MainView;

public class Main extends Application {

	private static Stage window;
	
	public static void main(String[] args) {
		Baza.getInstance();
		FileUtil.getInstance().ucitajFajl("sp.txt");
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		Scene scene = new Scene(new MainView(), 600, 450);
		window.setScene(scene);
		window.show();
	}

	public static Stage getWindow() {
		return window;
	}
}
