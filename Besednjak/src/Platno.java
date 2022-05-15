import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;





@SuppressWarnings("serial")
public class Platno  extends JPanel{

	protected Stroke debelinaPovezave;
	protected int fontSize = 36;
	protected Color barvaOzadja = Color.cyan;
	protected static String naslov = "BESEDNJAK";
	Zvok zvok = new Zvok();
	private BufferedImage slika;
	  
	
	public Platno(int sirina, int visina) {
		super();
		setPreferredSize(new Dimension(1000, 600));
		setBackground(barvaOzadja);
		
		this.debelinaPovezave = new BasicStroke(3);
		
		try {
			slika = ImageIO.read(getClass().getResourceAsStream("konfeti.png"));
		}catch (IOException e) {}
		
	}
	
	
	private static int round(double x) {
		return (int)(x + 0.5);
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(debelinaPovezave);
		g.setColor(Color.black);
		
		int sirina = getWidth();
		int visina = getHeight();
		
		double xBeseda = 4 * sirina / 10 ; 
		double yBeseda = 1 * visina / 7;
		g.setFont(new Font("Serif", Font.BOLD, 45)); 
		g.drawString(naslov, round(xBeseda), round(yBeseda));
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
		double y1 = visina/4;
		double y6 = 3 * visina/4;
		double visinaKvadrata = (y6 - y1) / 5;
		
		double x1 = sirina / 7 /2;
		double x6 = 6 * sirina / 7 /2;
		double sirinaKvadrata = x1;
		
		Okno.gumb.setBounds(round(xBeseda), round(6 * yBeseda), 80, 35);
		Okno.textField.setBounds(round(xBeseda + 90), round(6 * yBeseda), 200, 35);
		
		
		for (int i = 0; i < Okno.besednjak.steviloNapak +1; i++) {
			for (int j = 0; j < 5; j++) {
				if (Okno.besednjak.poskusi[i][j] == Barva.RUMENA) g2.setColor(Color.YELLOW);
				else if (Okno.besednjak.poskusi[i][j] == Barva.ZELENA) g2.setColor(Color.GREEN);
				else if (Okno.besednjak.poskusi[i][j] == Barva.BELA) g2.setColor(Color.GRAY);
				else g2.setColor(barvaOzadja);
				g2.fillRect(round(x1 + (j*sirinaKvadrata) + 2), round(y1 + (i*visinaKvadrata) + 2), round(sirina/14 -3), round(visina/10 -3));
			}}
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < 6; i++) {	
			g.drawLine(round(x1), round(y1 + (i * visinaKvadrata)) , round(x6 - 3), round(y1 + (i * visinaKvadrata)));
			g.drawLine(round(x1 + (i*sirinaKvadrata)), round(y1) , round(x1 + (i*sirinaKvadrata)), round(y6));
		
		for (int v = 0; v < Besednjak.STEVILO_POSKUSOV; v++) {
			for (int j = 0; j < 5; j++) {
				g.drawString(
						String.valueOf(Okno.besednjak.ugibanja[v][j]), 
						round(x1 + (j*sirinaKvadrata) + sirinaKvadrata/3), 
						round(y1 + (v * visinaKvadrata) + visinaKvadrata/3 *2 ));
		}}
		
		
		if (Okno.besednjak.stanje == Besednjak.zacetek) {
			g.drawString("ZAÈETEK", round(5 * sirina / 8 ), round(visina/3));
			setBackground(barvaOzadja);
			}
		else if (Okno.besednjak.stanje.equals(Besednjak.zmaga)) {
			g.drawString("ZMAGAL SI", round(5 * sirina / 8), round(visina/3));
			setBackground(new Color(0,121,0));
			g.drawImage(slika, -sirina, 10, 2 * sirina , visina + 100, null);
			}
		else if (Okno.besednjak.stanje == Besednjak.poraz) {
			g.drawString("PORAZ", round(5 * sirina / 8), round(visina/3));
			g.drawString("Geslo je bilo: " + Okno.besednjak.geslo, round(5 * sirina / 8), round(visina/2));
			setBackground(Color.RED);
			}
		else { 
			g.drawString("ŠE VEDNO IGRAŠ", round(5 * sirina / 8), round(visina/3));
			}
			
		
		
		}
		
		
		
		
		repaint();
		
		
	}

	
	
	public void zaigrajGlasbo(int i) {
		try {
			zvok.setFile(i);
			
		} catch (UnsupportedAudioFileException | IOException e) {
		}
		
		zvok.play();
	}
	
	
	
	

		
	
}