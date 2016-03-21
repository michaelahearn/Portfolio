public class ogre extends monster 
{
	
	private String[] nameList = {"Lugnor", "Gorflug", "Snagborg", "Treesnapper", "Flathead"};
	private final int ogreHealthMin = 20;
	private final int ogreHealthMod = 50;
	private final int ogreAttackMod = 20;
	private final int ogreAttackMin = 20;
	private final int ogreDefenseMod = 10;
	private final int ogreDefenseMin = 20;
	private final int ogreRewardMod = 40;
	private final int ogreRewardMin = 20;
	
	
	public ogre()
	{
		super();
	}
	
	public void createMonster()
	{
		name = nameList[rng.nextInt(numberOfNames)];
		health = ogreHealthMin + rng.nextInt(ogreHealthMod);
		attackMod = ogreAttackMin + rng.nextInt(ogreAttackMod);
		defenseMod = ogreDefenseMin + rng.nextInt(ogreDefenseMod);
		reward =  ogreRewardMin + rng.nextInt(ogreRewardMod);
	}
}
	

