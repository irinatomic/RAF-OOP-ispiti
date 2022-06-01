package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Baza;
import model.Termin;

public class FileUtils {

	private static FileUtils instance;
	
	private FileUtils() {}
	
	public static FileUtils getInstance() {
		if(instance == null)
			instance = new FileUtils();
		return instance;
	}
	
	public void ucitajTermine(String fileName) {
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF8");
			BufferedReader br = new BufferedReader(isr);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] args = line.split("[,]");
				Termin t = new Termin(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
				Baza.getInstance().getSviTermini().add(t);
				String ucionica = args[6];
				if(!Baza.getInstance().getUcionice().contains(ucionica))
					Baza.getInstance().getUcionice().add(ucionica);
			}
			
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
