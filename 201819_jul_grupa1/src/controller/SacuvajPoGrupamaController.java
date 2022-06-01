package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Baza;
import model.Selekcija;
import model.Termin;

public class SacuvajPoGrupamaController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		try {
			FileWriter fw = new FileWriter("selekcija.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(Selekcija s : Baza.getInstance().getSelekcija()) {
				bw.append(s.getGrupa() + "\n");
				for(Termin t : s.getTermini())
					bw.append(t + "\n");
				bw.append("\n");
			}
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
