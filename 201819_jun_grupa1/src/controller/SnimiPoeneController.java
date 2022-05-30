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
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter("poeni.txt");
			bw = new BufferedWriter(fw);
			
			for(Student s : Baza.getOlUpareniStudenti())
				bw.append(s.zaIspis() + "\n");
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
