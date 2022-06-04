package controller;

import java.io.File;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.ArtikalKorpa;
import model.Baza;

public class StampajRacunController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		File file = new File("racun.txt");
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(file, "UTF8");
			
			for(ArtikalKorpa ak : Baza.getKorpa()) {
				String ispis = ak.getOgArtikal().ispisZaRacun();
				pw.print(ispis);
				for(int i = 0; i < 60 - ispis.length(); i++)
					pw.print("_");
				pw.print(ak.getUkCena() + "\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
}
