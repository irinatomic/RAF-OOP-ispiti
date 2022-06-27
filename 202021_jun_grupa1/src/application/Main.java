package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Baza;
import view.StartView;

public class Main extends Application {
	
	private static Stage window;
	
	public static void main(String[] args) {
		Baza.getInstance();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Scene scene = new Scene(new StartView());
		window.setScene(scene);
		window.show();
	}

	public static Stage getWindow() {
		return window;
	}
}
