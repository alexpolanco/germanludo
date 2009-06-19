package ludo.ui;
import java.awt.*;


/** 
 * JPanel class which has an {@link Image} as a background.
 */
public class ImagePanel extends javax.swing.JPanel 
{	
		private String pfadOfImage = null;
		private Image image = null;
		
	public ImagePanel(String imagePfad) {
		super();
	
		pfadOfImage = imagePfad;
	
		image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("SpielbrettVorlage.jpg"));
	}
	
	public int getImageHeight() {
		return image.getHeight(this);
	}
	
	public int getImageWidth() {
		return image.getWidth(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(
			image, 
			new Double(g.getClipBounds().getCenterX() - (image.getWidth(this) / 2))
				.intValue(), 
			new Double(g.getClipBounds().getCenterY() - (image.getHeight(this) / 2))
				.intValue(), 
			this); 
	}	
}

