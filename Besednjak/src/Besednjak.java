public class Besednjak {

	protected String geslo;
	protected Barva[][] poskusi; // dejansko se bo v poskusih zapisovala zgodovina igre. - rezultati
	// primer poskusa izgleda :
	//{{bela, bela, zelena, rumena, bela},  {<-},  {<-},  zmaga,  {null, null, null, null, null}}
	protected static int STEVILO_POSKUSOV = 5;
	protected char[][] ugibanja; // podobno kot poskusi, le da ne zapisuje rezultatov paè pa samo zgodovino 
	protected String crke = "ABCÈDEFGHIJKLMNOPRSŠTUVZŽabcèdefghijklmnoprsštuvzž";
	protected int steviloNapak;
	protected static Barva[] zmaga = {Barva.ZELENA, Barva.ZELENA, Barva.ZELENA, Barva.ZELENA, Barva.ZELENA};
	protected static Barva[] zacetek;
	protected static Barva[] poraz = {Barva.RDECA, Barva.RDECA, Barva.RDECA, Barva.RDECA, Barva.RDECA};
	protected Barva[] stanje;
	
	
	public Besednjak(String geslo) {
		this.geslo = geslo;
		this.poskusi = new Barva[STEVILO_POSKUSOV][5];
		this.steviloNapak = 0;
		this.stanje = zacetek;
		this.ugibanja = new char[STEVILO_POSKUSOV][5];
	}
	
	
	@Override  
	public String toString() {
		return "Besednjak: " + geslo + " " + ugibanja;
	}
	
	public boolean preveriVnos(String poskus) {
		if (poskus.length() != 5) return false;
		char[] ch = poskus.toCharArray();
		
		for (char crka : ch) {
			if (crke.indexOf(crka) == -1 ) return false;
		}
		return true;
	}
	
	public Barva[] preveriVrstico (String poskus) {
		Barva[] output = new Barva[5];
		if (preveriVnos(poskus)) {
			char[] ch = poskus.toCharArray();
			this.ugibanja[steviloNapak] = poskus.toCharArray();
			char[] g = geslo.toCharArray();
			for (int i=0; i < 5; i++) {
				if (ch[i] == g[i]) output[i] = Barva.ZELENA; 
				else if (geslo.indexOf(ch[i]) != -1) output[i] = Barva.RUMENA;
				else output[i] = Barva.BELA;
		}
			return output;	
		}
		return null;
		
	}
		
	public boolean preveriZmago (String poskus) {
		Barva[] v = preveriVrstico(poskus);
		for ( int i = 0;  i < 5; ++i) {
			if (v[i] != Barva.ZELENA) return false ;
		}
		return true;
	
	}
	
	public boolean zmaga(String poskus) {
		if (preveriZmago(poskus)) return true;
		return false;
	}
	
	public boolean poraz() {
		return STEVILO_POSKUSOV < steviloNapak + 2;
	}
	
	public void igraj(String poskus) {
		poskus = poskus.toUpperCase();
        if (zmaga(poskus)) { 
        	stanje = zmaga;
        	this.poskusi[steviloNapak] = stanje;
        	}		
        else if (poraz()) {
        	stanje = poraz;
        	this.poskusi[steviloNapak] = preveriVrstico(poskus);
        }
        else {
        	if (preveriVrstico(poskus) != null) {
        	stanje = preveriVrstico(poskus);
        	this.poskusi[steviloNapak] = stanje;
        	steviloNapak += 1;
        	}
        }   
	}

}