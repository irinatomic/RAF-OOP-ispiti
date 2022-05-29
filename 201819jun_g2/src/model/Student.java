package model;

public class Student {

	private String smer;
	private String brIndexa;
	private String godUpisa;
	private String prezime;
	private String ime;
	private String email;
	private int poeni;
	
	public Student(String smer, String brIndexa, String godUpisa, String prezime, String ime) {
		this.smer = smer;
		this.brIndexa = brIndexa;
		this.godUpisa = godUpisa;
		this.prezime = prezime;
		this.ime = ime;
		poeni = 0;
	}
	
	public String getPotEmail() {
		String prezime2 = prezime.toLowerCase().replace(" ", "");
		prezime2 = prezime2.replace("ć", "c");
		prezime2 = prezime2.replace("č", "c");
		prezime2 = prezime2.replace("đ", "dj");
		prezime2 = prezime2.replace("š", "s");
		prezime2 = prezime2.replace("ž", "z");
		return ime.toLowerCase().charAt(0) + prezime2 + brIndexa + godUpisa.substring(2, 4) + smer.toLowerCase() + "@raf.rs";
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(prezime).append(" ").append(ime).append(", ");
		sb.append(smer).append("-").append(godUpisa).append("-").append(brIndexa);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || !(obj instanceof Student)) return false;
		
		Student s = (Student)obj;
		if(brIndexa.equals(s.brIndexa) && godUpisa.equals(s.godUpisa))
			return true;
		return false;
	}

	public String getSmer() {
		return smer;
	}

	public String getBrIndexa() {
		return brIndexa;
	}

	public String getGodUpisa() {
		return godUpisa;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getIme() {
		return ime;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoeni() {
		return poeni;
	}

	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
}
