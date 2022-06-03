package model;

public class Sat {

	private int sat;
	private int min;
	
	public Sat(String s) {
		String[] args = s.split(":");
		
		try {
			this.sat = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if(args.length == 1)
			this.min = 0; 
		else {
			try {
				this.min = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
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
