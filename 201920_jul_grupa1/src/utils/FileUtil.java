package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.*;

public class FileUtil {

	public static void ucitajFajl(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				Date datum = sdf.parse(args[0]);
				
				Polaznik polaznik = new Polaznik(args[1], args[2]);
				Akcija novaAkcija = new Akcija(polaznik, datum, args[3]);
				polaznik.dodajAkciju(novaAkcija);
				Baza.getAkcije().add(novaAkcija);
				
				//AKO NEMAMO TOG POLAZNIKA U BAZI
				if(!Baza.getPolaznici().contains(polaznik)) {
					Baza.getPolaznici().add(polaznik);
					continue;
				}
				
				//AKO IMAMO POLAZNIKA U BAZI, TRAZIMO GA I DODAJEMO MU AKCIJU
				for(Polaznik p : Baza.getPolaznici()) {
					if(p.equals(polaznik))
						p.dodajAkciju(novaAkcija);
				}
				
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
	}
	
}
