package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Akcija {

	private int iznos;
	private Date datum;
	
	public Akcija(int iznos, Date datum) {
		this.iznos = iznos;
		this.datum = datum;
	}

	public int getIznos() {
		return iznos;
	}

	public String getDatum() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return sdf.format(datum);
	}
	
	public abstract String ispisUFajl();
}
