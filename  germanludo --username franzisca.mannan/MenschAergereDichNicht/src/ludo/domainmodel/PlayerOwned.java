package ludo.domainmodel;

import ludo.domainmodel.spieler.Player;

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
	public Player getBesitzer();

	/**
	 * 
	 * @param neuerBesitzer der neue Besitzer (i.e. Spieler)
	 */
	public void setBesitzer(Player neuerBesitzer);
	
	
}
