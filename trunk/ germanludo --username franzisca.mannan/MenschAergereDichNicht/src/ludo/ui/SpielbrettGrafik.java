package ludo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import sun.awt.RepaintArea;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import ludo.domainmodel.Counter;
import ludo.domainmodel.GameBoard;
import ludo.domainmodel.Player;
import ludo.domainmodel.SpielerFarbe;
import ludo.ui.controls.FigurenListener;
import ludo.ui.controls.MenuItemListener;
import ludo.ui.controls.WuerfelListener;

/**
 *Stellt das Spielbrett grafisch dar
 * 
 */
public class SpielbrettGrafik {

	private static SpielbrettGrafik self = null;
	
	private JMenu spielMenu;
	private JMenuBar menuBar;	
	private JButton wuerfel;	
	private JPanel background;	
	private JFrame frame;
	
	
	public static SpielbrettGrafik getInstance()
	{
		if(self == null)
		{
			self = new SpielbrettGrafik();
		}
		return self;
	}
		
	public void spielbrettAnzeigen()
	{
		frame = new JFrame("Mensch ärgere dich nicht");
		frame.setMinimumSize(new Dimension(680, 750));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set Image - evtl muss repaint implementiert werden
		background = new ImagePanel("SpielbrettVorlage.jpg");		
		background.setLayout(null);
		
		//Würfel einbinden und einen Listener dran hängen um Ereignisse abzuzfangen
		wuerfel = new JButton("Würfel");
		wuerfel.addActionListener(new WuerfelListener());
		wuerfel.setBounds(292, 320, 75, 75);
		background.add(wuerfel);
		
		//Add menu
		menuBar = new JMenuBar();
		menuBar.setVisible(true);
		
		//Hauptmenü
		spielMenu = new JMenu("Menu");
		
		//Menüeinträge
		JMenuItem spielBeenden = new JMenuItem("Spiel beenden");
		spielBeenden.setActionCommand("Beenden");
		spielBeenden.addActionListener(new MenuItemListener());
		
		//Komponenten zusammensetzen
		spielMenu.add(spielBeenden);
		menuBar.add(spielMenu);
				
		//Add all components to frame
		frame.getContentPane().add(background);
		frame.setJMenuBar(menuBar);
		
		refresh();
	}

	/**
	 * Updates the location of a {@link Counter} and draws it accordingly on the
	 * graphical {@link GameBoard}.
	 */
	public void zeichneSpielfigur(Counter counter)
	{		
		background.remove(counter.getPlayerImage());
		counter.getPlayerImage().setBounds(
				((int) counter.getCurrentLocation().getX()),
				((int) counter.getCurrentLocation().getY()), 50, 50);		
		background.add(counter.getPlayerImage());
				
		refresh();
	}
	
	/**
	 * Displays a status message in the status bar.
	 */
	public void displayStatusMessage(String message)
	{
//		throw new NotImplementedException();
	}
	
	/**
	 * Refresh the current picture
	 */
	public void refresh()
	{
		background.setVisible(true);
		background.repaint();
		frame.pack();
		frame.repaint();
		frame.setVisible(true);		
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

	public String getDiceValue() {
		return wuerfel.getText();
	}

	public void setDiceValue(String wert) {
		wuerfel.setText(wert);
	}
	
	/**
	 * Exits the application.
	 */
	public void beenden()
	{
		self = null;
		frame.setVisible(false);
		frame.dispose();
	}
	
	public void dispose()
	{
		background.removeAll();
		refresh();
	}

	/**
	 * Draws the names for all currently existing players. This requires the
	 * coordinates for the name labels to be set in advance.
	 */
	public void drawPlayerNames(LinkedList<Player> players)
	{
		for(Player player : players)
		{
			JLabel spielerName = new JLabel (player.getPlayerName());
			spielerName.setBounds((int) player.getPlayerNameLocation().getX(),
					(int) player.getPlayerNameLocation().getY(), 150, 30);		
			background.add(spielerName);
		}
	}
	
	public void zeichneMedaille(int x, int y, ImageIcon icon)
	{		
		JLabel medaille = new JLabel(icon);
		medaille.setBounds(x, y, 100, 100);
		background.add(medaille);		
		refresh();
	}
}
