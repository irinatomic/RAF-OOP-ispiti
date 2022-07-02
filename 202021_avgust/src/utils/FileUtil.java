package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Baza;
import model.Pitanje;

public class FileUtil {

	public static List<Pitanje> ucitajPitanja(String fileName){
		List<Pitanje> pitanja = new ArrayList<Pitanje>();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(";");
				Pitanje p = new Pitanje(args[0], args[1], args[2]);
				pitanja.add(p);
				
				if(!Baza.getPredmeti().contains(args[2]))
					Baza.getPredmeti().add(args[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pitanja;
	}
	
	public static void upisiPitanjaUFajl(String fileName) {
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter(fileName, false);
			pw = new PrintWriter(fw);
			for(Pitanje p : Baza.getPitanja())
				pw.print(p.getIspisZaFajl() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
