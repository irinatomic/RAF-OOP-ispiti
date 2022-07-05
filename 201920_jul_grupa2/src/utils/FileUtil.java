package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				String[] tipAkcijeArgs = args[0].split("-");
				String tipAkcije = tipAkcijeArgs[0];
				double iznosAkcije = 0;
				if(tipAkcijeArgs.length == 2)
					iznosAkcije = Double.parseDouble(tipAkcijeArgs[1]);
				
				Date datum = sdf.parse(args[1]);
				String ime = args[2];
				String prezime = args[3];
				
				//PRAVIMO POLAZNIKA I TRAZIMO GA U BAZI
				Polaznik p = new Polaznik(ime, prezime);
				int indexPolaznik = Baza.getPolaznici().indexOf(p);
				if(indexPolaznik == -1)
					Baza.getPolaznici().add(p);
				else
					p = Baza.getPolaznici().get(indexPolaznik);
				
				//PRAVIMO AKCIJU, DODAJEMO JE U BAZU I POLAZNIKU DODAJEMO AKCIJU
				Akcija a = new Akcija(p, datum, tipAkcije, iznosAkcije);
				Baza.getAkcije().add(a);
				p.getAkcije().add(a);
				
				//PRAVIMO NOVU AKCIJASTATISTIKA I TRAZIMO JE U BAZI, DODAJEMO JOJ OBICNU AKCIJU
				AkcijaStatistika as = new AkcijaStatistika(tipAkcije);
				int indexAS = Baza.getStatistika().indexOf(as);
				if(indexAS == -1)
					Baza.getStatistika().add(as);
				else
					as = Baza.getStatistika().get(indexAS);
				as.dodajAkciju(a);
			}
					
		} catch (IOException | ParseException e) {
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