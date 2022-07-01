package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Akcija;
import model.Baza;
import model.Isplata;
import model.Uplata;

public class SnimiPodatke {
	
	public static void snimiPodatke() {
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter("uplate.txt");
			pw = new PrintWriter(fw);
			int ukupnoUplaceno = 0;
			for(Akcija a : Baza.getAkcije()) {
				if(a instanceof Uplata) {
					pw.print(a.ispisUFajl() + "\n");
					ukupnoUplaceno += a.getIznos();
				}
			}
			pw.print("Ukupno uplaceno: " + ukupnoUplaceno + " RSD");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fw = new FileWriter("isplate.txt");
			pw = new PrintWriter(fw);
			int ukupnoIsplaceno = 0;
			for(Akcija a : Baza.getAkcije()) {
				if(a instanceof Isplata) {
					pw.print(a.ispisUFajl() + "\n");
					ukupnoIsplaceno += a.getIznos();
				}
			}
			pw.print("Ukupno isplaceno: " + ukupnoIsplaceno + " RSD");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
