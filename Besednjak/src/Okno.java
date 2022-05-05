import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	protected Platno platno;
	private JMenuItem menuNovaIgra, menuIzhod;
	JButton gumb;
	JTextField textField;
	
	public Okno() {
		super();
		setTitle("Urejevalnik grafov");
		platno = new Platno(1000, 1000);
		
		gumb = new JButton("Poskusi");
		gumb.addActionListener(this);
		gumb.setBounds(100, 100, 300, 50);
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(250, 40));
		
		platno.add(gumb);
		platno.add(textField);
		add(platno);
		
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuDatoteka = dodajMenu(menubar, "Menu");
		
		menuNovaIgra = dodajMenuItem(menuDatoteka, "Nova igra");
		menuIzhod = dodajMenuItem(menuDatoteka, "Izhod");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}
	
	private JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem = new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == menuIzhod) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if (source == menuNovaIgra) {
			// Še za napisat,kaj se zgodi za menuNovaIgra
		}
		else if (e.getSource() == gumb) {
			textField.getText(); 
			// Tukejle se bo povezal z besednjakom, da dobi ugib besednjak in potem zaène.
		}
	}
	
	
}
