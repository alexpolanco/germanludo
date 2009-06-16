package ludo.ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ludo.main.SpielManager;
import ludo.ui.controls.SpielerSetupDialogListener;

/**
 * Modaler Dialog - fragt ab, wieviele Spieler teilnehmen und wie die einzelnen Namen der Spieler lauten.
 * Die Farben werden automatisch zugeteilt.
 *
 */
public class SpielerSetupDialog extends JDialog {
	
	private JTextField roterSpielerTextfeld;
	private JTextField blauerSpielerTextfeld;
	private JTextField gelberSpielerTextfeld;
	private JTextField gruenerSpielerTextfeld;
	
	
	public SpielerSetupDialog(JFrame parent, String title)
	{
		super(parent, title, true);

		//Setup dialog		
		setMinimumSize(new Dimension(450, 300));
		//Location des Dialogs festlege, relativ zum Parent frame
		setLocation(getParent().getSize().height/5, getParent().getSize().width/3);
        SpringLayout layout = new SpringLayout();
        getContentPane().setLayout(layout);
        
        //Label für roten Spieler
        JLabel spielerRotLabel = new JLabel("Roter Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerRotLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerRotLabel, 15, SpringLayout.NORTH, getContentPane());
        //Textfeld für roten Spieler
        roterSpielerTextfeld = new JTextField("", 20);
        roterSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, roterSpielerTextfeld,15, SpringLayout.EAST, spielerRotLabel);
        layout.putConstraint(SpringLayout.NORTH, roterSpielerTextfeld, 15, SpringLayout.NORTH, getContentPane());

        //Label für blauen Spieler
        JLabel spielerBlauLabel = new JLabel("Blauer Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerBlauLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerBlauLabel, 55, SpringLayout.NORTH, getContentPane());
        //Textfeld für blauen Spieler
        blauerSpielerTextfeld = new JTextField("", 20);
        blauerSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, blauerSpielerTextfeld,15, SpringLayout.EAST, spielerBlauLabel);
        layout.putConstraint(SpringLayout.NORTH, blauerSpielerTextfeld, 55, SpringLayout.NORTH, getContentPane());

        //Label für gelben Spieler
        JLabel spielerGelbLabel = new JLabel("Gelber Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerGelbLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerGelbLabel, 95, SpringLayout.NORTH, getContentPane());
        //Textfeld für blauen Spieler
        gelberSpielerTextfeld = new JTextField("", 20);
        gelberSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, gelberSpielerTextfeld,15, SpringLayout.EAST, spielerGelbLabel);
        layout.putConstraint(SpringLayout.NORTH, gelberSpielerTextfeld, 95, SpringLayout.NORTH, getContentPane());
        
        //Label für grünen Spieler
        JLabel spielerGruenLabel = new JLabel("Grüner Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerGruenLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerGruenLabel, 140, SpringLayout.NORTH, getContentPane());
        //Textfeld für blauen Spieler
        gruenerSpielerTextfeld = new JTextField("", 20);
        gruenerSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, gruenerSpielerTextfeld,15, SpringLayout.EAST, spielerGruenLabel);
        layout.putConstraint(SpringLayout.NORTH, gruenerSpielerTextfeld, 140, SpringLayout.NORTH, getContentPane());

        //Buttons für Abbrechen und Bestätigen
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new SpielerSetupDialogListener());
        layout.putConstraint(SpringLayout.WEST, okButton, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, okButton, 190, SpringLayout.NORTH, getContentPane());
        
        JButton abbrechenButten = new JButton("Beenden");
        abbrechenButten.setActionCommand("Beenden");
        abbrechenButten.addActionListener(new SpielerSetupDialogListener());
        layout.putConstraint(SpringLayout.WEST, abbrechenButten, 150, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, abbrechenButten, 190, SpringLayout.NORTH, getContentPane());
        
        //Füge Komponenten zum Dialog hinzu
        getContentPane().add(spielerRotLabel);
        getContentPane().add(spielerBlauLabel);
        getContentPane().add(spielerGelbLabel);
        getContentPane().add(spielerGruenLabel);

        getContentPane().add(roterSpielerTextfeld);
        getContentPane().add(blauerSpielerTextfeld);
        getContentPane().add(gelberSpielerTextfeld);
        getContentPane().add(gruenerSpielerTextfeld);

        getContentPane().add(okButton);
        getContentPane().add(abbrechenButten);

        
        pack();
        setVisible(true);
	}


	public String getRoterSpielerName() {
		return roterSpielerTextfeld.getText();
	}


	public String getBlauerSpielerName() {
		return blauerSpielerTextfeld.getText();
	}


	public String getGelberSpielerName() {
		return gelberSpielerTextfeld.getText();
	}


	public String getGruenerSpielerName() {
		return gruenerSpielerTextfeld.getText();
	}
	
	
	
}
