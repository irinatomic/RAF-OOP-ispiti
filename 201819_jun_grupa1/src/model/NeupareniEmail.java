package model;

public class NeupareniEmail {

	private String email;
	private int pitanje1;
	private int pitanje2;
	private int pitanje3;
	
	public NeupareniEmail(String email) {
		this.email = email;
	}
	
	public void dodajPoene(int brPitanja, int poeni) {
		if(brPitanja == 1)
			pitanje1 = poeni;
		else if(brPitanja == 2)
			pitanje2 = poeni;
		else if(brPitanja == 3)
			pitanje3 = poeni;
		else
			System.out.println(this + " nevalidan broj pitanja");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPitanje1() {
		return pitanje1;
	}

	public int getPitanje2() {
		return pitanje2;
	}

	public int getPitanje3() {
		return pitanje3;
	}

	@Override
	public String toString() {
		return email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof NeupareniEmail)) return false;
		
		if(email.equals(((NeupareniEmail)obj).email)) 
				return true;
		return false;
	}
	
}
