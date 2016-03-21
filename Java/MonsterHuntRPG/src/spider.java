public class spider extends monster 
{
	
	private String[] nameList = {"Sszazz", "Krillix", "Azol-Nuab", "Chitterer", "Webrunner"};
	private final int spiderHealthMin = 30;
	private final int spiderHealthMod = 10;
	private final int spiderAttackMod = 10;
	private final int spiderAttackMin = 10;
	private final int spiderDefenseMod = 5;
	private final int spiderDefenseMin = 10;
	private final int spiderRewardMod = 15;
	private final int spiderRewardMin = 10;
	
	
	public spider()
	{
		super();
	}
	
	public void createMonster()
	{
		name = nameList[rng.nextInt(numberOfNames)];
		health = spiderHealthMin + rng.nextInt(spiderHealthMod);
		attackMod = spiderAttackMin + rng.nextInt(spiderAttackMod);
		defenseMod = spiderDefenseMin + rng.nextInt(spiderDefenseMod);
		reward =  spiderRewardMin + rng.nextInt(spiderRewardMod);
	}
}
