public class lich extends monster 
{
	
	private String[] nameList = {"Vizigar", "Doombringer", "Horazon", "Hamon-Re", "Apophis"};
	private final int lichHealthMin = 40;
	private final int lichHealthMod = 100;
	private final int lichAttackMod = 60;
	private final int lichAttackMin = 40;
	private final int lichDefenseMod = 20;
	private final int lichDefenseMin = 40;
	private final int lichRewardMod = 100;
	private final int lichRewardMin = 40;
	
	
	public lich()
	{
		super();
	}
	
	public void createMonster()
	{
		name = nameList[rng.nextInt(numberOfNames)];
		health = lichHealthMin + rng.nextInt(lichHealthMod);
		attackMod = lichAttackMin + rng.nextInt(lichAttackMod);
		defenseMod = lichDefenseMin + rng.nextInt(lichDefenseMod);
		reward =  lichRewardMin + rng.nextInt(lichRewardMod);
	}
}
	
