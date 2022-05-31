package application;

import javafx.application.Application;
import javafx.scene.Scene;
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
		Scene scene = new Scene(MainView.getInstance(), 700, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
