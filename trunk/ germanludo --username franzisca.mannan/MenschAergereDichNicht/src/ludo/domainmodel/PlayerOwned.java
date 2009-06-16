package ludo.domainmodel;

import ludo.domainmodel.spieler.Spieler;

/**
 * 
 * Klassen, welche von Spielern besessen werden (Spielfigur, Spielfeld usw.) implementieren dieses Interface.
 *
 */
public interface PlayerOwned {

	/**
	 * 
	 * @return den aktuellen Besitzer
	 */
	public Spieler getBesitzer();

	/**
	 * 
	 * @param neuerBesitzer der neue Besitzer (i.e. Spieler)
	 */
	public void setBesitzer(Spieler neuerBesitzer);
	
	
}
