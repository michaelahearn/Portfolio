
public abstract class weapon {
	String name;
	int damageMod;
	int cost;
		
	public weapon()
	{
		name = "Bare Hands";
		damageMod = 0;
		cost = 0;
	}
	
	
	public int getDamageMod()
	{
		return damageMod;
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
