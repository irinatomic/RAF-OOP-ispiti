package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.*;

public class SnimiUFajl {

	public static void snimiUFajl() {
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter("snimljeno.txt");
			pw = new PrintWriter(fw);
			for(PozicijaStatistika ps : Baza.getPozicijeStatistika()) {
				pw.print(ps.getPozicija() + "\n");
				for(Zaposleni z : ps.getZaposleni())
					pw.print(z.ispisUFajl() + "\n");
			}
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
