package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Baza;
import view.TerminiView;

public class Main extends Application {

	private static Stage window;
	
	public static void main(String[] args) {
		Baza.getInstance();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Scene scene = new Scene(new TerminiView(), 500, 550);
		window.setScene(scene);
		window.show();
	}

	public static Stage getWindow() {
		return window;
	}
}
