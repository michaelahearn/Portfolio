import java.util.Random;
public class player {
	
	private String name;
	private int startingHealth;
	private final int minHealth = 21;
	private int health;
	private int strength;
	private final int minStrength = 1;
	private int dexterity;
	private final int minDexterity = 1;
	private int gold; 
	private boolean isAlive = true;
	private String[] nameList = {"Baldrick", "Bedevere", "Tywin", "Gwendolyn", "Gwenevere", "Cersei"};
	private final int numberOfNames = 6;
	private Random rng = new Random();
	private weapon charWeapon;
	private armor charArmor;
	
	public player()
	{
		name = "";
		startingHealth = 0;
		health = 0;
		strength = 0;
		dexterity = 0;
		gold = 0; 
		
	}
	
	public void createCharacter()
	{
		if(name.equals(""))
			{
				name = nameList[rng.nextInt(numberOfNames)];
			}
		else
			{
				
			}
		startingHealth = minHealth + rng.nextInt(20);
		health = startingHealth;
		strength = minStrength + rng.nextInt(10);
		dexterity = minDexterity + rng.nextInt(10);
		charWeapon = new bareHands();
		charArmor = new normalClothes();



	}
	
	public String displayName()
	{
		return name;
	}
	
	public void setName(String enteredName)
	{
		name = enteredName;		
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public void makePurchase(int purchase)
	{
		gold -= purchase;
	}
	
	public String displayStats()
	{
		String characterStats = "";
		characterStats += ("Name: " + name + "\n");
		characterStats += ("Health: " + Integer.toString(startingHealth)+ "\n");
		characterStats += ("Strength: " + Integer.toString(strength)+ "\n");
		characterStats += ("Dexterity: " + Integer.toString(dexterity)+ "\n");
		characterStats += ("Weapon Type: " + charWeapon.getName() + "\n");
		characterStats += ("Max Weapon Damage: " + charWeapon.getDamageMod() + "\n");
		characterStats += ("Armor Type: " + charArmor.getName() + "\n");
		characterStats += ("Max Armor Strength: " + charArmor.getDefenseMod()+ "\n");
		characterStats += ("Gold: " + Integer.toString(gold));
		return characterStats;	
	}
	
	public void collectReward(int reward)
	{
		gold += reward;
	}
	
	public void loseGold()
	{
		gold = 0;
	}
	
	public int playerAttack()
	{
		int attack = 0;
		attack = rng.nextInt(strength + charWeapon.getDamageMod());
		return attack;

	}
	
	public int playerDefend()
	{
		int defend;
		defend = rng.nextInt(dexterity + charArmor.getDefenseMod());
		return defend;

	}
	
	public int takeDamage(int damageTaken)
	{
		health = health - damageTaken;
		if(health<=0)
			{
				health = 0;
				isAlive = false;
			}
		else
			{
				isAlive = true;
			}
		return health;
	}
	
	public boolean isAlive()
	{
		return isAlive;
	}
	
	public void healPlayer()
	{
		health = startingHealth;
		isAlive = true;
	}
	
	public void upgradeWeapon(weapon upgraded)
	{
		charWeapon = upgraded;
	}
	
	public void upgradeArmor(armor upgraded)
	{
		charArmor = upgraded;
	}
}

