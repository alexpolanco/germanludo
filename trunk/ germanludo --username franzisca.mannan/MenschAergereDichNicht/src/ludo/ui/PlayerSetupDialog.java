package ludo.ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ludo.ui.controls.PlayerSetupDialogListener;

/**
 * Modal dialog. Allows for choosing the number of players and determine their
 * names.
 * 
 */
public class PlayerSetupDialog extends JDialog {
	
	private JTextField roterSpielerTextfeld;
	private JTextField blauerSpielerTextfeld;
	private JTextField gelberSpielerTextfeld;
	private JTextField gruenerSpielerTextfeld;
	
	
	public PlayerSetupDialog(JFrame parent, String title)
	{
		super(parent, title, true);

		//Setup dialog		
		setMinimumSize(new Dimension(450, 300));
		//Dialog location
		setLocation(getParent().getSize().height/5, getParent().getSize().width/3);
        SpringLayout layout = new SpringLayout();
        getContentPane().setLayout(layout);
        
        //Label for the red player
        JLabel spielerRotLabel = new JLabel("Roter Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerRotLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerRotLabel, 15, SpringLayout.NORTH, getContentPane());
        //text field for the red player
        roterSpielerTextfeld = new JTextField("", 20);
        roterSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, roterSpielerTextfeld,15, SpringLayout.EAST, spielerRotLabel);
        layout.putConstraint(SpringLayout.NORTH, roterSpielerTextfeld, 15, SpringLayout.NORTH, getContentPane());

        //Label for the blue player
        JLabel spielerBlauLabel = new JLabel("Blauer Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerBlauLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerBlauLabel, 55, SpringLayout.NORTH, getContentPane());
        //text field for the blue player
        blauerSpielerTextfeld = new JTextField("", 20);
        blauerSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, blauerSpielerTextfeld,15, SpringLayout.EAST, spielerBlauLabel);
        layout.putConstraint(SpringLayout.NORTH, blauerSpielerTextfeld, 55, SpringLayout.NORTH, getContentPane());

        //Label for the yellow player
        JLabel spielerGelbLabel = new JLabel("Gelber Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerGelbLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerGelbLabel, 95, SpringLayout.NORTH, getContentPane());
        //text field for the yellow player
        gelberSpielerTextfeld = new JTextField("", 20);
        gelberSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, gelberSpielerTextfeld,15, SpringLayout.EAST, spielerGelbLabel);
        layout.putConstraint(SpringLayout.NORTH, gelberSpielerTextfeld, 95, SpringLayout.NORTH, getContentPane());
        
        //Label for the green player
        JLabel spielerGruenLabel = new JLabel("Grüner Spieler");
        layout.putConstraint(SpringLayout.WEST, spielerGruenLabel, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, spielerGruenLabel, 140, SpringLayout.NORTH, getContentPane());
        //text field for the green player
        gruenerSpielerTextfeld = new JTextField("", 20);
        gruenerSpielerTextfeld.setEditable(true);
        layout.putConstraint(SpringLayout.WEST, gruenerSpielerTextfeld,15, SpringLayout.EAST, spielerGruenLabel);
        layout.putConstraint(SpringLayout.NORTH, gruenerSpielerTextfeld, 140, SpringLayout.NORTH, getContentPane());

        //Buttons for cancel and confirm
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new PlayerSetupDialogListener());
        layout.putConstraint(SpringLayout.WEST, okButton, 15, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, okButton, 190, SpringLayout.NORTH, getContentPane());
        
        JButton abbrechenButten = new JButton("Beenden");
        abbrechenButten.setActionCommand("Beenden");
        abbrechenButten.addActionListener(new PlayerSetupDialogListener());
        layout.putConstraint(SpringLayout.WEST, abbrechenButten, 150, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, abbrechenButten, 190, SpringLayout.NORTH, getContentPane());
        
        //Merge compopnents and add them to the dialog
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
