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
	
	
	public Platno(int sirina, int visina) {
		super();
		setPreferredSize(new Dimension(1000, 600));
		setBackground(Color.cyan);
		
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
		
		for (int i = 0; i < 6; i++) {	
			g.drawLine(round(x1), round(y1 + (i * visinaKvadrata)) , round(x6 - 3), round(y1 + (i * visinaKvadrata)));
			g.drawLine(round(x1 + (i*sirinaKvadrata)), round(y1) , round(x1 + (i*sirinaKvadrata)), round(y6));
		
		for (int v = 0; v < 5; v++) {
			for (int j = 0; j < 5; j++) {
				g.drawString(
						String.valueOf(Okno.besednjak.ugibanja[v][j]), 
						round(x1 + (j*sirinaKvadrata)), 
						round(y1 + (v * visinaKvadrata)));
		}}
			
		
		
		repaint();
		
		
		}
		
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
