package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class FileUtil {

	public static void ucitajFajl(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null && !(line = line.trim()).equals("")) {
				String[] args = line.split(",");
				
				//PRAVIMO PREDMET
				String naslov = args[0];
				List<String> autori = new ArrayList<String>();
				int i = 1;
				while(!args[i].matches(".*\\d.*")) 	//.* - any number of occurences, \\d - digit
					autori.add(args[i++]);
	
				int razred = Integer.parseInt(args[i].substring(0, args[i++].indexOf('.')));
				String izdavac = args[i++];
				String predmet = args[i++];
				Double cenaPrimerka = Double.parseDouble(args[i++]);
				int brPrimeraka = Integer.parseInt(args[i]);
				Udzbenik u = new Udzbenik(naslov, autori, razred, izdavac, cenaPrimerka, brPrimeraka);
				Baza.getUdzbenici().add(u);
				
				//PRAVIMO PREDMET I STAVLJAMO GA U BAZI ILI TRAZIMO AKO VEC POSTOJI
				Predmet p = new Predmet(razred, predmet);
				int indexPredmeta = Baza.getPredmeti().indexOf(p);
				if(indexPredmeta == -1)
					Baza.getPredmeti().add(p);
				else
					p = Baza.getPredmeti().get(indexPredmeta);
				p.dodajUdzbenik(u);
				
				//UDZBENIKU SETUJEMO ZA KOJI JE PREDMET
				u.setPredmet(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
