import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Logika {
	
	
	public static String izberiGeslo(String imeVhod) throws IOException {
		BufferedReader vhod = new BufferedReader(new FileReader(imeVhod));
		String geslo = "";
		
		while (vhod.ready()) {
			String vrstica = vhod.readLine().trim();
			// spremenit v UTF-8 ker ne delajo šumniki
			String[] besede = vrstica.split(",");
			int max = besede.length;
			int randomNum = ThreadLocalRandom.current().nextInt(0, max);
			geslo = besede[randomNum];
		}
		
		
		vhod.close();
		return geslo.toUpperCase();
	}
	
	
	public static Besednjak novaIgra() {
		String geslo = "";
		try {
			geslo = izberiGeslo("src/besedle.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Besednjak igra = new Besednjak(geslo);
		return igra;
	}
	
	
}