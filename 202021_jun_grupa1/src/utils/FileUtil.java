package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.Ucionica;

public class FileUtil {

	public static List<Student> ucitajStudente(String fileName) {
		List<Student> studenti = new ArrayList<Student>();
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				Student s = new Student(args[4], args[3], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[0]);
				studenti.add(s);
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
		
		return studenti;
	}
	
	public static List<Ucionica> ucitajUcionice(String fileName){
		List<Ucionica> ucionice = new ArrayList<Ucionica>();
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null)
				ucionice.add(new Ucionica(line));
			
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
}
