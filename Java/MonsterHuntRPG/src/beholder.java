public class beholder extends monster 
{
	
	private String[] nameList = {"Xanthos", "Varzigon", "Z'jor", "He-Who-Sees", "Straksis"};
	private final int beholderHealthMin = 30;
	private final int beholderHealthMod = 75;
	private final int beholderAttackMod = 40;
	private final int beholderAttackMin = 30;
	private final int beholderDefenseMod = 15;
	private final int beholderDefenseMin = 30;
	private final int beholderRewardMod = 80;
	private final int beholderRewardMin = 30;
	
	
	public beholder()
	{
		super();
	}
	
	public void createMonster()
	{
		name = nameList[rng.nextInt(numberOfNames)];
		health = beholderHealthMin + rng.nextInt(beholderHealthMod);
		attackMod = beholderAttackMin + rng.nextInt(beholderAttackMod);
		defenseMod = beholderDefenseMin + rng.nextInt(beholderDefenseMod);
		reward =  beholderRewardMin + rng.nextInt(beholderRewardMod);
	}
}
	

