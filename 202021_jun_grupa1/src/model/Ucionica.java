package model;

public class Ucionica {

	private String ime;
	private TipUcionice tipUcionice;
	
	public Ucionica(String ucionica) {
		String args[] = ucionica.split("-");
		this.ime = args[0];
		
		if(args.length == 1)
			this.tipUcionice = TipUcionice.NULL;
		else
			this.tipUcionice = TipUcionice.valueOf(args[1]);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append(ime);
		if(!tipUcionice.equals(TipUcionice.NULL))
			sb.append("(" + tipUcionice.toString() + ")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Ucionica)) return false;
		
		if(ime.equals(((Ucionica)obj).getIme()))
			return true;
		return false;
	}
	
	public String getIme() {
		return ime;
	}

	public TipUcionice getTipUcionice() {
		return tipUcionice;
	}
}
