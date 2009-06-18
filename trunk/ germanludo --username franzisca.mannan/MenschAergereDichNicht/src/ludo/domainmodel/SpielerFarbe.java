package ludo.domainmodel;

/**
 * Die Spielfarben rot, gelb, blau, gruen als enum;
 */
public enum SpielerFarbe {

	ROT, BLAU, GELB, GRUEN;
	
	@Override
	public String toString()
	{
		if(this == SpielerFarbe.ROT)
		{
			return "Rot";
		} 
		else if(this == SpielerFarbe.BLAU)
		{
			return "Blau";
		} 
		else if(this == SpielerFarbe.GELB)
		{
			return "Gelb";
		}
		else if(this == SpielerFarbe.GRUEN)
		{
			return "Gruen";
		}
		else
		{
			return "Undefined";
		}
	}
}
