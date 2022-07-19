package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import model.*;

public class FileUtil {

	public static void ucitajFajl(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			String staRadimo = "";
			String kontinent = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split("[:;]");
				String prvaRec = args[0];
				
				if(prvaRec.equalsIgnoreCase("Kontinent")) {
					staRadimo = "KONTINENT";
					Baza.getKontinenti().add(new Kontinent(args[1].trim()));
					kontinent = args[1].trim();
					continue;
				} else if(prvaRec.equalsIgnoreCase("Pronadjeni")) {
					staRadimo = "PRONADJENI";
					continue;
				} else if(prvaRec.equalsIgnoreCase("Izgubljeni")) {
					staRadimo = "IZGUBLJENI";
					continue;
				}
				
				if(staRadimo.equals("KONTINENT")) {
					Kontinent k = new Kontinent(kontinent);
					Drzava d = new Drzava(args[0]);
					int indexKontinenta = Baza.getKontinenti().indexOf(k);
					Baza.getKontinenti().get(indexKontinenta).getDrzave().add(d);
					Baza.getDrzave().add(d);
					
				} else if(staRadimo.equals("PRONADJENI")) {
					Drzava d = null;
					for(Drzava dBaza : Baza.getDrzave()) {
						if(dBaza.getNaziv().equals(args[1]))
							d = dBaza;
					}
					Kontinent k = null;
					for(Kontinent kBaza : Baza.getKontinenti()) {
						if(kBaza.getDrzave().contains(d))
							k = kBaza;
					}
					Vanzemaljac v = new Vanzemaljac(Integer.parseInt(args[0]), k, d, false);
					Baza.getVanzemaljci().add(v);
					
				} else if(staRadimo.equals("IZGUBLJENI")) {
					Kontinent k = null;
					for(Kontinent kBaza : Baza.getKontinenti()) {
						if(kBaza.getNaziv().equals(args[1]))
							k = kBaza;
					}
					Vanzemaljac v = new Vanzemaljac(Integer.parseInt(args[0]), k, null, true);
					Baza.getVanzemaljci().add(v);
				}
			}
		} catch (IOException e) {
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
	
	public static void ispisiUFajl(String fileName) {
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);
			Collections.sort(Baza.getKontinenti());
			Collections.sort(Baza.getDrzave());
			
			for(Kontinent k : Baza.getKontinenti())
				pw.println(k.getNaziv() + " " + k.getBrPronadjenih());
			pw.println();
			for(Drzava d : Baza.getDrzave())
				pw.println(d.getNaziv() + " " + d.getBrPronadjenih());
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