package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


import model.*;

public class FileUtil {

	public static List<Zaposleni> ucitajZaposlene(String fileName){
		List<Zaposleni> zaposleni = new ArrayList<Zaposleni>();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String pozicija = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				if(args[0].isEmpty())
					continue;
				
				if(args.length == 1) {
					pozicija = args[0];
					Baza.getPozicije().add(pozicija);
					continue;
				}
				
				Zaposleni z = new Zaposleni(args[0], args[1], args[2], Integer.parseInt(args[3]), pozicija);
				zaposleni.add(z);
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
		return zaposleni;
	}
	
	public static Map<String, Double> ucitajPlatneKoef(String fileName) {
		Map<String, Double> mapa = new HashMap<String, Double>();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				mapa.put(args[0], Double.parseDouble(args[1]));
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
		return mapa;
	}
}
