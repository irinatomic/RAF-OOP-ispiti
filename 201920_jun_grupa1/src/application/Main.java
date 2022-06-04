package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Baza;
import view.MainView;

public class Main extends Application {

	public static void main(String[] args) {
		Baza.getInstance();		
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView view = new MainView();
		Scene scene = new Scene(view, 800, 600);
		
		primaryStage.setTitle("OOP - JUN - 2020");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
}
