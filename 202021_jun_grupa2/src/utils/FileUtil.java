package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class FileUtil {

	public static List<Ucionica> ucitajUcionice(String fileName){
		List<Ucionica> ucionice = new ArrayList<Ucionica>();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				String[] args = line.split("-");
				Ucionica u = new Ucionica(args[0], Integer.parseInt(args[1]));
				ucionice.add(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return ucionice;
	}
	
	public static void ucitajStudente(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
				
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				Student student = new Student(args[4], args[3], args[1], args[2], args[0]);
				Ucionica ucionica = null;
				
				//TRAZIMO UCIONICU U SPISKU IZ BAZE
				for(Ucionica u : Baza.getUcionice()) {
					if(u.getNaziv().equals(args[6])) {
						ucionica = u;
						break;
					}
				}
				
				Termin termin = new Termin(ucionica, Integer.parseInt(args[5]));
				int indexTermina = Baza.getTermini().indexOf(termin);
				if(indexTermina == -1)
					Baza.getTermini().add(termin);
				else
					termin = Baza.getTermini().get(indexTermina);
				
				termin.getStudenti().add(student);
				student.setTermin(termin);
				Baza.getStudenti().add(student);
				Baza.dodajSatnicu(Integer.parseInt(args[5]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public static void upisiUFajl(String fileName) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);
			for(Termin t : Baza.getTermini()) {
				for(Student s : t.getStudenti())
					pw.print(s.ispisiUFajl() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
