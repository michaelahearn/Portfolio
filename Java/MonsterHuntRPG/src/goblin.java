public class goblin extends monster 
{
	
	private String[] nameList = {"Gallibrick", "Nikton", "Rithal", "Snikkit", "Ratbag"};
	private final int goblinHealthMin = 15;
	private final int goblinHealthMod = 15;
	private final int goblinAttackMod = 5;
	private final int goblinAttackMin = 1;
	private final int goblinDefenseMod = 5;
	private final int goblinDefenseMin = 1;
	private final int goblinRewardMod = 5;
	private final int goblinRewardMin = 1;
	
	
	public goblin()
	{
		super();
	}
	
	public void createMonster()
	{
		name = nameList[rng.nextInt(numberOfNames)];
		health = goblinHealthMin + rng.nextInt(goblinHealthMod);
		attackMod = goblinAttackMin + rng.nextInt(goblinAttackMod);
		defenseMod = goblinDefenseMin + rng.nextInt(goblinDefenseMod);
		reward =  goblinRewardMin + rng.nextInt(goblinRewardMod);
	}

}
