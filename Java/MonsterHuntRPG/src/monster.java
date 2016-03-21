import java.util.Random;

public abstract class monster {
	
		String name;
		int health;
		int attackMod;
		int minAttack;
		int defenseMod;
		int minDefense;
		int minReward;
		int reward;
		final int numberOfNames = 5;
		boolean isAlive = true;
		String[] nameList = {"Monster1", "Monster1", "Monster1", "Monster1", "Monster1"};
		Random rng = new Random();
	
	public monster()
	{
		name = "None";
		health = 0;
		attackMod = 0;
		minAttack = 0;
		defenseMod = 0;
		minDefense = 0;
		reward = 0;	
		minReward = 0;
	}
	
	public abstract void createMonster();

	public String displayMonsterName()
	{
		return name;
	}
	
	public String displayMonsterStats()
	{
		String monsterStats = "";
		monsterStats += ("Name: " + name + "\n");
		monsterStats += ("Health: " + Integer.toString(health)+ "\n");
		monsterStats += ("Max Attack: " + Integer.toString(attackMod)+ "\n");
		monsterStats += ("Max Defense: " + Integer.toString(defenseMod)+ "\n");
		monsterStats += ("Reward: " + Integer.toString(reward));
		return monsterStats;	
	}
	
	public int monsterAttack()
	{
		int attack;
		attack = 1 + rng.nextInt(attackMod);
		return attack;

	}
	
	public int monsterDefend()
	{
		int defend;
		defend = rng.nextInt(defenseMod);
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
	
	public int giveReward()
	{
		return reward;
	}
	
	
	
}
