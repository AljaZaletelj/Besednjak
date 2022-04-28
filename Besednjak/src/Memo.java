
public class Memo {

	protected String geslo;
	protected String[][] poskusi; // dejansko se bo v poskusih zapisovala zgodovina igre.
	// primer poskusa izgleda :
	//{{bela, bela, zelena, rumena, bela},  {<-},  {<-},  zmaga,  {null, null, null, null, null}}
	protected int steviloPoskusov = 5;
	protected String crke = "ABCÈDEFGHIJKLMNOPRSŠTUVZŽabcèdefghijklmnoprsštuvzž";
	protected String zelena = "z";
	protected String rumena = "r";
	protected String bela = "b";
	protected int steviloNapak;
	protected String[] zmaga = {zelena, zelena, zelena, zelena, zelena};
	protected String[] zacetek;
	protected String[] poraz = {"Izgubil si"};
	protected String[] stanje;
	
	
	public Memo(String geslo) {
		this.geslo = geslo;
		this.poskusi = new String[steviloPoskusov][5];
		this.steviloNapak = 0;
		this.stanje = zacetek;
	}
	
	@Override  
	public String toString() {
		return "Besednjak: " + geslo + " " + poskusi;
	}
	
	public boolean preveriVnos(String poskus) {
		if (poskus.length() != 5) return false;
		char[] ch = poskus.toCharArray();
		
		for (char crka : ch) {
			if (crke.indexOf(crka) == -1 ) return false;
		}
		return true;
	}
	
	public String[] preveriVrstico (String poskus) {
		String[] output = new String[5];
		char[] ch = poskus.toCharArray();
		char[] g = geslo.toCharArray();
		for (int i=0; i < 5; i++) {
			if (ch[i] == g[i]) output[i] = zelena; 
			else if (geslo.indexOf(ch[i]) != -1) output[i] = rumena;
			else output[i] = bela;
		}
		
		return output;	
	}
	
	public boolean zmaga(String poskus) {
		if (preveriVrstico(poskus) == zmaga) return true;
		return false;
	}
	
	public boolean poraz() {
		return steviloNapak > steviloPoskusov;
	}
	
	public void igraj(String poskus) {
        if (zmaga(poskus)) stanje = zmaga;  		
        else if (poraz()) stanje = poraz;
        else stanje = preveriVrstico(poskus);
        this.poskusi[steviloNapak] = stanje;
        
	}
	
}
