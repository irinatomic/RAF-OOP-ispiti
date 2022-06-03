package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Baza;
import model.Obracunato;

public class SacuvajController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter("obracunato.txt");
			bw = new BufferedWriter(fw);
			for(Obracunato o : Baza.getObracunato()) {
				bw.append(o.getUcionica() + ": ").append(o.getSatnica() + "\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
