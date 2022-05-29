package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Baza;
import utils.FileUtil;
import view.MainView;

public class Main extends Application {
	
	public static void main(String[] args) {
		Baza.getInstance();
		FileUtil.ucitajStudente("studenti.txt");
		FileUtil.ucitajPoene("pitanje1.txt");
		FileUtil.ucitajPoene("pitanje2.txt");
		FileUtil.ucitajPoene("pitanje3.txt");
		
		//CISTIMO MAPE JER NAM VISE NE TREBAJU
		Baza.getNeupareniEmailoviMapa().clear();
		Baza.getPotencijalniEmailovi().clear();
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(MainView.getInstance(), 700, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
