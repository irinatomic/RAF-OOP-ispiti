package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Baza;
import model.NeupareniEmail;
import model.Student;

public class FileUtil {
	
	public static void ucitajStudente(String fileName) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				Student s = new Student(args[0], args[1], args[2], args[3], args[4]);
				Baza.getSviStudenti().add(s);
				
				if(!Baza.getSmerovi().contains(args[0]))
					Baza.getSmerovi().add(args[0]);
				
				if(!Baza.getGodine().contains(args[2]))
					Baza.getGodine().add(args[2]);
			}
			
			//POPUNJAVAMO MAPU U BAZI RADI LAKSE PRETRAGE ZA MEJLOM PRILIKOM UPISA POENA
			Baza.popuniPotencijalneEmailove();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ucitajPoene(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String args[] = line.split(",");
				
				//AKO JE MEJL VALIDAN
				if(Baza.getPotencijalniEmailovi().keySet().contains(args[0])) {
					Student s = Baza.getPotencijalniEmailovi().get(args[0]);
					s.setPoeni(s.getPoeni() + Integer.parseInt(args[1]));
					s.setEmail(args[0]);
					if(!Baza.getUpareniStudenti().contains(s))
						Baza.getUpareniStudenti().add(s);
					continue;
				} 
				
				//MEJL NIJE VALIDAN I VEC POSTOJI ISTI
				NeupareniEmail ne = new NeupareniEmail(args[0]);
				if(Baza.getNeupareniEmailoviMapa().keySet().contains(args[0])) {
					NeupareniEmail neBaza = Baza.getNeupareniEmailoviMapa().get(args[0]);
					neBaza.setPoeni(neBaza.getPoeni() + Integer.parseInt(args[1]));
					continue;
				}
				
				//MEJL NIJE VALIDAN I PRVI PUT NAILZAMO NA TAKAV
				ne.setPoeni(Integer.parseInt(args[1]));
				Baza.getNeupareniEmailovi().add(ne);
				Baza.getNeupareniEmailoviMapa().put(args[0], ne);
			}
			
;		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
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
