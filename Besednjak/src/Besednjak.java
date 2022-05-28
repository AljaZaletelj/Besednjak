public class Besednjak {

	protected String geslo;
	protected Barva[][] poskusi; // v poskusih se zapisuje zgodovina igre. - rezultati (barve)
	// primer poskusa izgleda :
	//{{bela, bela, zelena, rumena, bela},  {bela, bela, zelena, rumena, bela},  {bela, bela, zelena, rumena, bela},  zmaga,  {null, null, null, null, null}}
	protected static int STEVILO_POSKUSOV = 5;
	protected static int DOLZINA_BESEDE = 5;
	protected char[][] ugibanja; // podobno kot poskusi, le da zapisuje črke ne pa barve
	protected String crke = "ABCČDEFGHIJKLMNOPRSŠTUVZŽabcčdefghijklmnoprsštuvzž";
	protected int steviloNapak;
	protected static Barva[] zacetek;
	protected static Barva[] zmaga = {Barva.ZELENA, Barva.ZELENA, Barva.ZELENA, Barva.ZELENA, Barva.ZELENA};
	protected static Barva[] poraz = {Barva.RDECA, Barva.RDECA, Barva.RDECA, Barva.RDECA, Barva.RDECA};
	protected Barva[] stanje; // Označuje rezultat našega zadnjega poskusa	
	
	public Besednjak(String geslo) {
		this.geslo = geslo;
		this.steviloNapak = 0;
		this.stanje = zacetek;
		this.poskusi = new Barva[STEVILO_POSKUSOV][DOLZINA_BESEDE];
		this.ugibanja = new char[STEVILO_POSKUSOV][DOLZINA_BESEDE];
	}
	
	@Override  
	public String toString() {
		return "Besednjak: " + geslo + " " + ugibanja;
	}
	
	public boolean preveriVnos(String poskus) {
		// funkcija, ki preveri, če je dani vnos ustrezen
		if (poskus.length() != DOLZINA_BESEDE) return false;
		char[] ch = poskus.toCharArray();
		
		for (char crka : ch) {
			if (crke.indexOf(crka) == -1 ) return false;
		}
		return true;
	}
	
	public Barva[] preveriVrstico (String poskus) {
		// preveri vrstico in vrne rezultat v barvah
		Barva[] output = new Barva[DOLZINA_BESEDE];
		if (preveriVnos(poskus)) {
			char[] ch = poskus.toCharArray();
			this.ugibanja[steviloNapak] = poskus.toCharArray();
			char[] g = geslo.toCharArray();
			for (int i=0; i < DOLZINA_BESEDE; i++) {
				if (ch[i] == g[i]) output[i] = Barva.ZELENA; 
				else if (geslo.indexOf(ch[i]) != -1) output[i] = Barva.RUMENA;
				else output[i] = Barva.BELA;
		}
			return output;	
		}
		return null;
		
	}
		
	public boolean zmaga (String poskus) {
		// preveri, če smo zmagali
		Barva[] v = preveriVrstico(poskus);
		for ( int i = 0;  i < DOLZINA_BESEDE; ++i) {
			if (v[i] != Barva.ZELENA) return false ;
		}
		return true;
	
	}
	
	public boolean poraz() {
		return STEVILO_POSKUSOV < steviloNapak + 2;
	}
	
	public void igraj(String poskus) {
		// glavna funkcija
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