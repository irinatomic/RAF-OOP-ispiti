package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Baza;
import model.Termin;

public class FileUtil {

	public static List<Termin> ucitajTermine(String fileName){
		FileReader fr = null;
		BufferedReader br = null;
		List<Termin> sviTermini = new ArrayList<Termin>();
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				Termin t = new Termin(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
				sviTermini.add(t);
				
				if(!Baza.getDani().contains(args[4]))
					Baza.getDani().add(args[4]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		
		return sviTermini;
	}
}
