package ludo.domainmodel.spielbrett;

import ludo.domainmodel.spieler.SpielerFarbe;

/**
 * Definiert die Richtungen in die sich eine Spielfigur bewegen kann,
 *
 */
//TODO rename
public enum Bewegungsrichtung {

	LINKS, RECHTS, OBEN, UNTEN;
	
	@Override
	public String toString()
	{
		if(this == Bewegungsrichtung.LINKS)
		{
			return "Links";
		} 
		else if(this == Bewegungsrichtung.RECHTS)
		{
			return "Rechts";
		} 
		else if(this == Bewegungsrichtung.UNTEN)
		{
			return "Unten";
		}
		else if(this == Bewegungsrichtung.OBEN)
		{
			return "Oben";
		}
		else
		{
			return "Undefined";
		}
	}
}
