package model;

public class Sat {

	private int sat;
	private int min;
	
	public Sat(String s) {
		String[] args = s.split(":");
		this.sat = Integer.parseInt(args[0]);
		if(args.length == 1)
			this.min = 0; 
		else
			this.min = Integer.parseInt(args[1]);
	}
	
	public static int izracunajBrCasova(Sat pocetak, Sat kraj) {
		 int pocetakMin = pocetak.sat * 60 + pocetak.min;
		 int krajMin = kraj.sat * 60 + kraj.min;
		 return Math.round((krajMin - pocetakMin) / 45);
	}
	
	public static int getUkMin(Sat sat) {
		return sat.sat * 60 + sat.min;
	}
	
	@Override
	public String toString() {
		String mins = "";
		if(min == 0)
			mins = "00";
		else
			mins = min + "";
		return sat + ":" + mins;
	}

	public int getSat() {
		return sat;
	}

	public int getMin() {
		return min;
	}
}
