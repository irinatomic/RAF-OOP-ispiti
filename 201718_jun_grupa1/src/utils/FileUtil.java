package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Baza;
import model.Reprezentacija;
import model.Utakmica;

public class FileUtil {

	private static FileUtil instance;
	
	private FileUtil() {}
	
	public static FileUtil getInstance() {
		if(instance == null)
			instance = new FileUtil();
		return instance;
	}
	
	public void ucitajFajl(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			String grupa = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split("[-,:]");
				
				if(args.length == 1) {
					grupa = args[0];
					Baza.getGrupe().add(grupa);
					continue;
				}
				
				String tim1 = args[0];
				String tim2 = args[1];
				int goloviTim1 = Integer.parseInt(args[2]);
				int goloviTim2 = Integer.parseInt(args[3]);
				Utakmica u = new Utakmica(tim1, tim2, goloviTim1, goloviTim2, grupa);
				Baza.getSpisakUtakmica().add(u);
				
				int brPoena1 = 1;
				int brPoena2 = 1;
				if(goloviTim1 > goloviTim2) {
					brPoena1 = 3;
					brPoena2 = 0;
				}
				else if(goloviTim2 > goloviTim1) {
					brPoena1 = 0;
					brPoena2 = 3;
				}
				Reprezentacija r1 = new Reprezentacija(tim1, brPoena1, goloviTim1, goloviTim2, grupa);
				Reprezentacija r2 = new Reprezentacija(tim2, brPoena2, goloviTim2, goloviTim1, grupa);
				Reprezentacija[] zaDodavanje = {r1, r2};
				
				for(Reprezentacija rep : zaDodavanje) {
					//AKO NE POSTOJI TA REPREZENTACIJA U SETU
					if(!Baza.getSpisakReprezentacija().contains(rep))
						Baza.getSpisakReprezentacija().add(rep);
					else {
						//AKO POSTOJI, NA POSTOJECU SAMO NADODAJEMO POTREBNE VREDNOSTIs
						for(Reprezentacija rBaza : Baza.getSpisakReprezentacija()) {
							if(rBaza.equals(rep)) {
								rBaza.setBrojPoena(rBaza.getBrojPoena() + rep.getBrojPoena());
								rBaza.setBrDatihGolova(rBaza.getBrDatihGolova() + rep.getBrDatihGolova());
								rBaza.setBrPrimljenihGolova(rBaza.getBrPrimljenihGolova() + rep.getBrPrimljenihGolova());
							}
						}
					}
				}
				//PRELAZAK NA NAREDNU LINIJU U FAJLU
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
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
