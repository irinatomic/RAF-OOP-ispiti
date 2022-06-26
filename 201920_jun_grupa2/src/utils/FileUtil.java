package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import model.Artikal;
import model.ArtikalKorpa;
import model.Baza;
import model.Kategorija;

public class FileUtil {

	public static List<Artikal> ucitajKatalog(String fileName){
		List<Artikal> artikli = new ArrayList<Artikal>();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String zanr = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				if(args.length == 1) {
					zanr = args[0];
					continue;
				}
				Artikal a = new Artikal(args[0], args[1], Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]), Kategorija.valueOf(args[5]), zanr);
				artikli.add(a);
				
				if(!Baza.getIzvodjaci().contains(args[0]))
					Baza.getIzvodjaci().add(args[0]);
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
		
		return artikli;
	}
	
	public static void ispisiKolekciju() {
		File file = new File("kolekcija.txt");
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(file, "UTF8");
			for(ArtikalKorpa ak : Baza.getKupljeniArtikli()) {
				pw.println(ak.getIzvodjac());
				for(String album : ak.getAlbumi())
					pw.println("\t" + album);
			}
			pw.println("Ukupna vrednost kolekcije: " + Baza.getUkVrednostKupljenihArtikala() + " dinara");
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
}
