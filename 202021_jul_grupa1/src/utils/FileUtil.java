package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;

public class FileUtil {

	public static List<Akcija> ucitajPlacanja(String fileName){
		List<Akcija> akcije = new ArrayList<Akcija>();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] args = line.split(";");
				int iznos = Integer.parseInt(args[0]);
				String uplatilacPrimalac = args[1];
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
				Date datum = sdf.parse(args[2]);
				Akcija akcija = null;
				
				if(args.length == 4) {
					akcija = new Isplata(iznos, datum, uplatilacPrimalac, args[3]);
					if(!Baza.getKategorije().contains(args[3]))
						Baza.getKategorije().add(args[3]);
				}
				else
					akcija =  new Uplata(iznos, datum, uplatilacPrimalac);
				
				akcije.add(akcija);
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
		
		return akcije;
	}
}
