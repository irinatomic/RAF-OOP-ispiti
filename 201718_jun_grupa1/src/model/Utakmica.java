package model;

import java.util.Random;

public class Utakmica {

	private String tim1;
	private String tim2;
	private int goloviTim1;
	private int goloviTim2;
	private String grupa;
	
	public Utakmica(String tim1, String tim2, int goloviTim1, int goloviTim2, String grupa) {
		this.tim1 = tim1;
		this.tim2 = tim2;
		this.goloviTim1 = goloviTim1;
		this.goloviTim2 = goloviTim2;
		this.grupa = grupa;
	}
	
	public String getPobednik() {
		if(goloviTim1 > goloviTim2)
			return tim1;
		else if(goloviTim1 < goloviTim2)
			return tim2;
		else {
			if(new Random().nextBoolean())
				return tim1;
			return tim2;
		}
	}

	public String getTim1() {
		return tim1;
	}

	public String getTim2() {
		return tim2;
	}

	public int getGoloviTim1() {
		return goloviTim1;
	}

	public int getGoloviTim2() {
		return goloviTim2;
	}

	public String getGrupa() {
		return grupa;
	}
	
	@Override
	public String toString() {
		return tim1 + " - " + tim2 + " " + goloviTim1 + ":" + goloviTim2;
	}
}
