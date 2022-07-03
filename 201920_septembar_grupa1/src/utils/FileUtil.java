package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class FileUtil {

	public static void ucitajPodatke(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				int argsNo = args.length - 1;
				
				int idBroj = Integer.parseInt(args[0]);
				String naziv = args[1];
				int razred = Integer.parseInt(args[2].substring(0, args[2].indexOf('.')));
				String predmet = args[4];
				int brPrimeraka = Integer.parseInt(args[argsNo--]);
				double cena = Double.parseDouble(args[argsNo--]);
				
				List<String> autori = new ArrayList<String>();
				for(int i = 5; i <= argsNo; i++)
					autori.add(args[i]);
				
				Udzbenik noviUdzbenik = new Udzbenik(idBroj, naziv, razred, predmet, autori, cena, brPrimeraka);
				Baza.getUdzbenici().add(noviUdzbenik);
				
				//PRAVIMO NOVOG IZDAVACA ILI TRAZIMO GA U SPISKU AKO VEC POSTOJI
				Izdavac i = new Izdavac(args[3]);
				int indexIzdavaca = Baza.getIzdavaci().indexOf(i);
				if(indexIzdavaca == -1)
					Baza.getIzdavaci().add(i);
				else
					i = Baza.getIzdavaci().get(indexIzdavaca);
				i.dodajUdzbenik(noviUdzbenik);
				
				noviUdzbenik.setIzdavac(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void snimiUFajl(String fileName) {
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);
			for(Udzbenik u : Baza.getUdzbenici())
				pw.print(u.getIspisZaFajl() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
