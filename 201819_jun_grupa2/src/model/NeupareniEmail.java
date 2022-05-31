package model;

public class NeupareniEmail {

	private String email;
	private int poeni;
	
	public NeupareniEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof NeupareniEmail)) return false;
		
		return email.equals(((NeupareniEmail)obj).email);
	}
	
	@Override
	public String toString() {
		return email;
	}

	public String getEmail() {
		return email;
	}

	public int getPoeni() {
		return poeni;
	}

	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
		
}
