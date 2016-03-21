public class dragon extends monster 
{
	
	private String[] nameList = {"Vermithrax", "Glaurung", "Porunga", "Shenron", "Firkraag"};
	private final int dragonHealthMin = 60;
	private final int dragonHealthMod = 150;
	private final int dragonAttackMod = 100;
	private final int dragonAttackMin = 60;
	private final int dragonDefenseMod = 40;
	private final int dragonDefenseMin = 60;
	private final int dragonRewardMod = 200;
	private final int dragonRewardMin = 60;
	
	
	public dragon()
	{
		super();
	}
	
	public void createMonster()
	{
		name = nameList[rng.nextInt(5)];
		health = dragonHealthMin + rng.nextInt(dragonHealthMod);
		attackMod = dragonAttackMin + rng.nextInt(dragonAttackMod);
		defenseMod = dragonDefenseMin + rng.nextInt(dragonDefenseMod);
		reward =  dragonRewardMin + rng.nextInt(dragonRewardMod);
	}
}
	
