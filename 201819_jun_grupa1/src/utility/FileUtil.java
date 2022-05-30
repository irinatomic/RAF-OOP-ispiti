package utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Baza;
import model.NeupareniEmail;
import model.Student;

public class FileUtil {

	private static FileUtil instance;
	
	private FileUtil() {};
	
	public static FileUtil getInstance() {
		if(instance == null)
			instance = new FileUtil();
		return instance;
	}
	
	public void ucitajStudente(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF8");
			BufferedReader br = new BufferedReader(isr);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				Student s = new Student(args[0], args[1], args[2], args[3], args[4]);
				//System.out.println(s.potencijalniEmail());
				Baza.getSviStudenti().add(s);
			}
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ucitajPoene(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF8");
			BufferedReader br = new BufferedReader(isr);
			
			int brPitanja = Integer.parseInt(fileName.charAt(fileName.length() - 5) + "");
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(",");
				String email = args[0];
				
				//trazimo da li je email validan
				boolean pronadjenMejl = false;
				for(Student s : Baza.getSviStudenti()) {
					if(s.potencijalniEmail().equals(email)) {
						s.setEmail(email);
						s.dodajPoene(brPitanja, Integer.parseInt(args[1] + ""));
						if(!Baza.getOlUpareniStudenti().contains(s))
							Baza.getOlUpareniStudenti().add(s);
						pronadjenMejl = true;
						break;
					}
				}
				
				//ako nismo nasli mejl
				if(!pronadjenMejl) {
					boolean postojiVecTakavNeuparen = false;
					for(NeupareniEmail ne : Baza.getNeupareniEmailovi()) {
						if(ne.getEmail().equals(email)) {
							ne.dodajPoene(brPitanja, Integer.parseInt(args[1] + ""));
							postojiVecTakavNeuparen = true;
							break;
						}
					} 
					if(!postojiVecTakavNeuparen) {
						NeupareniEmail ne = new NeupareniEmail(email);
						Baza.getNeupareniEmailovi().add(ne);
						ne.dodajPoene(brPitanja, Integer.parseInt(args[1] + ""));
					}
				}
			}
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
