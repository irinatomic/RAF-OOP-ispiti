package model;


public class Student implements Comparable<Student>{
	
	private String smer;
	private String brIndexa;
	private String godUpisa;
	private String prezime;
	private String ime;
	
	private int pitanje1;
	private int pitanje2;
	private int pitanje3;
	private String email;

	public Student(String smer, String brIndexa, String godUpisa, String prezime, String ime) {
		super();
		this.smer = smer;
		this.brIndexa = brIndexa;
		this.godUpisa = godUpisa;
		this.prezime = prezime;
		this.ime = ime;
	}
	
	public String potencijalniEmail() {
		String newPrezime = prezime;
		newPrezime = newPrezime.replaceAll("ć", "c");
		newPrezime = newPrezime.replaceAll("č", "c");
		newPrezime = newPrezime.replaceAll("ž", "z");
		newPrezime = newPrezime.replaceAll("đ", "dj");
		newPrezime = newPrezime.replaceAll("š", "s");
		return ime.toLowerCase().charAt(0) + newPrezime.replace(" ", "").toLowerCase() + brIndexa + godUpisa.substring(2, 4) + smer.toLowerCase() + "@raf.rs";
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
	
	public String zaIspis() {
		return ime + "," + prezime + "," + godUpisa + "," + brIndexa + "," + smer + "," + pitanje1 + "," + pitanje2 + "," + pitanje3;
	}
	
	@Override
	public String toString() {
		return prezime + " " + ime + ", " + smer + "-" + godUpisa + "-" + brIndexa;
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
	
	@Override //za list view
	public int compareTo(Student o) {
		return prezime.compareTo(o.prezime);
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getPitanje1() {
		return pitanje1;
	}

	public int getPitanje2() {
		return pitanje2;
	}

	public int getPitanje3() {
		return pitanje3;
	}

	public String getEmail() {
		return email;
	}

	public void setPitanje1(int pitanje1) {
		this.pitanje1 = pitanje1;
	}

	public void setPitanje2(int pitanje2) {
		this.pitanje2 = pitanje2;
	}

	public void setPitanje3(int pitanje3) {
		this.pitanje3 = pitanje3;
	}
	
	
}
