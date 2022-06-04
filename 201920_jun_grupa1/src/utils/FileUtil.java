package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Artikal;
import model.Baza;
import model.Tip;
import model.Zanr;

public class FileUtil {

	public static void ucitajKatalog(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				
				//PRAVIMO NOVI ARTIKL I DODAJEMO GA U BAZU
				Tip tip = Tip.valueOf(args[6]);
				Artikal a = new Artikal(args[0], args[1], Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]), tip);
				Baza.getArtikli().add(a);
				
				//PRAVIMO NOVI ZANR
				Zanr z = null;
				if(tip.equals(Tip.nova))
					z = new Zanr(args[4], 1, 0);
				else
					z = new Zanr(args[4], 0, 1);
				
				//AKO NEMAMO TAJ ZANR U BAZI DODAJEMO GA
				if(!Baza.getZanrovi().contains(z)) {
					z.dodajArtikal(a);
					Baza.getZanrovi().add(z);
					continue;
				}
				
				//AKO VEC IMAMO TAJ ZANR
				for(Zanr bazaZ : Baza.getZanrovi()) {
					if(z.equals(bazaZ)) {
						bazaZ.setBrNovih(bazaZ.getBrNovih() + z.getBrNovih());
						bazaZ.setBrPolovnih(bazaZ.getBrPolovnih() + z.getBrPolovnih());
						bazaZ.dodajArtikal(a);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
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
