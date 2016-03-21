
public class armor {
	String name;
	int defenseMod;
	int cost;
		
	public armor()
	{
		name = "Normal Clothes";
		defenseMod = 0;
		cost = 0;
	}
	
	
	public int getDefenseMod()
	{
		return defenseMod;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCost()
	{
		return cost;
	}
	
}