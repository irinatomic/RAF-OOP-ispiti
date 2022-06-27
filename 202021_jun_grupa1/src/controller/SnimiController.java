package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Baza;
import model.Student;

public class SnimiController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Collections.sort(Baza.getRasporedjeni());
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter("snimljeno.txt");
			pw = new PrintWriter(fw);
			for(Student s : Baza.getRasporedjeni())
				pw.print(s.getStudent() + " " + s.getUcionica() + " " + s.getTermin() + "\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
