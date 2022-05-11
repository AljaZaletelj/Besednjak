import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.JTextField;




@SuppressWarnings("serial")
public class Platno  extends JPanel{

	protected Stroke debelinaPovezave;
	protected int fontSize = 36;
	protected Color barvaOzadja = Color.cyan;
	
	
	public Platno(int sirina, int visina) {
		super();
		setPreferredSize(new Dimension(1000, 600));
		setBackground(barvaOzadja);
		
		this.debelinaPovezave = new BasicStroke(3);
	}
	
	
	private static int round(double x) {
		return (int)(x + 0.5);
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(debelinaPovezave);
		g.setColor(Color.BLACK);
		
		int sirina = getWidth();
		int visina = getHeight();
		
		double xBeseda = sirina / 2 ; // Tukej bi bilo treba popravit, da bi bil text res v centru
		double yBeseda = visina / 7;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
		g.drawString("BESEDNJAK", round(xBeseda), round(yBeseda));
		
		double y1 = visina/4;
		double y6 = 3 * visina/4;
		double visinaKvadrata = (y6 - y1) / 5;
		
		double x1 = sirina / 7 /2;
		double x6 = 6 * sirina / 7 /2;
		double sirinaKvadrata = x1;
		
		
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
			}
		else if (Okno.besednjak.stanje == Besednjak.poraz) {
			g.drawString("PORAZ", round(5 * sirina / 8), round(visina/3));
			g.drawString("Geslo je bilo: " + Okno.besednjak.geslo, round(5 * sirina / 8), round(visina/2));
			setBackground(Color.RED);
			}
		else g.drawString("ŠE VEDNO IGRAŠ", round(5 * sirina / 8), round(visina/3));
			
		
		
		}
		
		
		
		
		
		repaint();
		
		
	}
	
	
	

		
	
}