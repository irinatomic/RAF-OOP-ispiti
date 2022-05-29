package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Baza;
import model.Student;

public class SnimiPoeneController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		FileWriter fr = null;
		BufferedWriter br = null;
		
		try {
			fr = new FileWriter("poeni.txt");
			br = new BufferedWriter(fr);
			for(Student s : Baza.getUpareniStudenti())
				br.append(s.toString()).append(" " + s.getPoeni() + " poena \n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.flush();
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
