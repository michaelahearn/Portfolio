import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class world extends JFrame {
	private Color frameColor;//Stores color of frame background. 
	monster monster1;//Generic monster object. 
	player player1 = new player();//Generic player object. 
	weapon weapon1;//Generic weapon object.
	armor armor1 = new armor();//Generic armor object. 
	private String battleReport;//Stores text generated during battle. 
	private int battleReward = 0;//Stores reward given to player if they win the battle. 
	private Timer t;//Timer that runs during a battle. Each tick represents an attack round (player, then monster). 
	boolean battleInProgress = false;//Used to determine whether or not a battle is currently in progress. 
	boolean monsterCreated = false;//Used to determine whether or not a monster has been created. 
	boolean playerCreated = false;//Used to determine whether or not a monster has been created.
	JTextArea huntTextPlayer;//Text area that displays player actions during battle. 
	JTextArea huntTextMonster;//Text area that displays monster actions during battle. 
	JTextField charName;//Used to collect a character name from the user, if they choose to enter one. 
	JTextArea charText;//Text area that displays character stats. 
	JTextArea monstText;//Text area that displays monster stats. 
	JRadioButton optGoblin;//Selecting this option will cause a goblin to be generated when the "Create Monster" button is pressed. 
	JRadioButton optSpider;//Selecting this option will cause a spider to be generated when the "Create Monster" button is pressed. 
	JRadioButton optOgre;//Selecting this option will cause an ogre to be generated when the "Create Monster" button is pressed. 
	JRadioButton optBeholder;//Selecting this option will cause a beholder to be generated when the "Create Monster" button is pressed. 
	JRadioButton optLich;//Selecting this option will cause a lich to be generated when the "Create Monster" button is pressed. 
	JRadioButton optDragon;//Selecting this option will cause a dragon to be generated when the "Create Monster" button is pressed. 
	JComboBox comboWeapon;//Combo box that stores various weapon choices available to the player. 
	JComboBox comboArmor;//Combo box that stores various armor choices available to the player. 
	JPanel charPanel;//Panel that holds the character creation elements. 
	JPanel charButtons;//Panel that holds two buttons on the character creation panel. 
	JPanel armPanel;//Pressing this button creates a character object. 
	JPanel nbPanel;//Panel that holds the armory elements. 
	JPanel monsterChoices;//Panel that holds the monster creation option boxes. 
	JPanel huntPanel;//Panel that holds elements related to the battle between player / monster. 
	JButton butDisplayChar;//This button displays the character stats.
	JButton butCreateChar;//This button displays the character stats.
	JButton butCreateMonster;//This button creates a monster, based on the option selected. 
	JButton butUpgradeWeapon;//This button upgrades the players weapon. 
	JButton butUpgradeArmor;//This button upgrades the players armor. 
	JButton butBattle;//This button commences the battle between the player / monster. 
	JTabbedPane jtp;//This tabbed pane holds all of the panels. 
	
	public world()
	{
		setSize(450, 300);//Set size of entire frame. 
		setLocationRelativeTo(null);//Centers frame in the middle of the screen. 
		setTitle("Monster Hunter");//Create title. 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets default close operation ie. program will close if X is pressed. 
		frameColor = getBackground();//Set framecolor to background color of frame. 			
		jtp = new JTabbedPane();//Create tabbed pane object. 
		add(jtp);//Add tabbed pane object to frame. 
	//Start of Character Creation tab.
		charPanel = new JPanel();//Create character panel.
		//Character name text field. 
		charName = new JTextField(14);//Create character name text field. 
		charName.setBorder(new TitledBorder(new EtchedBorder(), "Enter name or leave blank"));//Set character text box border.
		//Character stat text area. 
		charText = new JTextArea(9, 14);//Create character stats text area. 
		charText.setEditable(false);//Prevent character text area from being edited. 
		charText.setBorder(new TitledBorder(new EtchedBorder(), "Character"));//Set character text box border. 
		charText.setBackground(frameColor);//Set background color of character textbox to same color as frame. 
		//Character button panel.
		charButtons = new JPanel();//Create panel object used to organize character creation buttons. 
		charButtons.setBorder(new TitledBorder(new EtchedBorder(), ""));//Set border of button panel object. 
		//Create character button. 		
		butCreateChar = new JButton("Create Character");//Create button object used to generate character. 
		characterCreator createCharacter = new characterCreator();//Create character creator object. 
		butCreateChar.addActionListener(createCharacter);//Add action listener to character creator object. Will fire when create character button is pressed. 
		//Display character button. 
		butDisplayChar = new JButton("Display Character");//Create button object used to display character stats. 
		characterDisplay displayCharacter  = new characterDisplay();//Create character display object.
		butDisplayChar.addActionListener(displayCharacter);//Add action listener to character display object. Will fire when create display button is pressed.
		//Adding elements to frame. 
		charPanel.add(charName);
		charButtons.add(butCreateChar);//Add character creation button to button panel.
		charButtons.add(butDisplayChar);//Add character display button to button panel.
		charPanel.add(charText);//Add character text box to character panel.
		charPanel.add(charButtons);//Add character button panel to character panel.
		jtp.addTab("Character", null, charPanel, "Create and review your character.");//Add character creation panel to tabbed pane.
	//End of Character Creation tab.

	//Start of Armory tab.
		//Create armory panel.
		armPanel = new JPanel(new GridLayout(2, 1));
		JPanel armWeaponPanel = new JPanel();
		armWeaponPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		JPanel armArmorPanel = new JPanel();
		armArmorPanel.setBorder(new TitledBorder(new EtchedBorder(), "Armor"));
		//Weapon upgrade combo box. 
		comboWeapon = new JComboBox();
		comboWeapon.addItem("Steel Sword - 10 Gold");
		comboWeapon.addItem("Mythril Sword - 50 Gold");
		comboWeapon.addItem("Magic Sword - 100 Gold");
		//Weapon upgrade button.
		butUpgradeWeapon = new JButton("Upgrade Weapon");
		weaponUpgrader upgradeWeapon = new weaponUpgrader();
		butUpgradeWeapon.addActionListener(upgradeWeapon);
		//Armor upgrade combo box. 
		comboArmor = new JComboBox();
		comboArmor.addItem("Steel Armor - 10 Gold");
		comboArmor.addItem("Mythril Armor - 50 Gold");
		comboArmor.addItem("Magic Armor - 100 Gold");
		//Armor upgrade button.
		butUpgradeArmor = new JButton("Upgrade Armor");
		armorUpgrader upgradeArmor = new armorUpgrader();
		butUpgradeArmor.addActionListener(upgradeArmor);
		//Add components to Armory Panel.
		armWeaponPanel.add(comboWeapon);
		armWeaponPanel.add(butUpgradeWeapon);
		armArmorPanel.add(comboArmor);
		armArmorPanel.add(butUpgradeArmor);
		armPanel.add(armWeaponPanel);
		armPanel.add(armArmorPanel);
		//Add Armory panel to tabbedpane. 
		jtp.addTab("Armory", null, armPanel, "Upgrade your weapon and armor.");
	//End of Armory tab.
	//Start of Notice Board tab.
		nbPanel = new JPanel(); //Create notice board panel.
		//Monster text area. 
		monstText = new JTextArea(5, 11);//Create monster text area object. 
		monstText.setEditable(false);//Prevent monster text area from being edited. 
		monstText.setBorder(new TitledBorder(new EtchedBorder(), "Monster"));//set border of monster text area. 
		monstText.setBackground(frameColor);//Set background of monster text area. 
		//Monster option buttons. 
		optGoblin = new JRadioButton("Goblin");//Create goblin button object. 
		optSpider = new JRadioButton("Giant Spider");//Create spider button object. 
		optOgre = new JRadioButton("Ogre");//Create ogre button object. 
		optBeholder = new JRadioButton("Beholder");//Create beholder button object. 
		optLich = new JRadioButton("Lich");//Create lich button object. 
		optDragon = new JRadioButton("Dragon");//Create dragon button object. 
		//Monster option button group.
		ButtonGroup monsterOptions = new ButtonGroup();//Create button group - organizes monster option buttons. 
		monsterOptions.add(optGoblin);//Add goblin button. 
		monsterOptions.add(optSpider);//Add spider button. 
		monsterOptions.add(optOgre);//Add ogre button. 
		monsterOptions.add(optBeholder);//Add beholder button. 
		monsterOptions.add(optLich);//Add lich button. 
		monsterOptions.add(optDragon);//Add dragon button. 
		//Create monster button. 
		butCreateMonster = new JButton("Search Contracts");
		monsterCreator createMonster = new monsterCreator();
		butCreateMonster.addActionListener(createMonster);
		//Add monster options to monster choice panel.
		monsterChoices = new JPanel();//Create panel containing monster options. 
		monsterChoices.add(optGoblin);//Add goblin option
		monsterChoices.add(optSpider);//Add spider option
		monsterChoices.add(optOgre);//Add ogre option
		monsterChoices.add(optBeholder);//Add beholder option
		monsterChoices.add(optLich);//Add lich option
		monsterChoices.add(optDragon);//Add dragon option
		monsterChoices.setBorder(new TitledBorder(new EtchedBorder(), "Choose Monster Type"));//Set border of monster option panel.
		nbPanel.add(monsterChoices);//Add monster option panel to notice board panel.
		nbPanel.add(monstText);//Add monster stat text area to notice board panel.
		nbPanel.add(butCreateMonster);//Add create monster button to notice board panel.
		jtp.addTab("Notice Board", null, nbPanel, "Choose hunter contract.");//Add notice board panel to tabbed pane.
	//End of Notice Board tab.
	//Start of Start Hunt tab.
		huntPanel = new JPanel();//Create hunt panel
		huntTextPlayer = new JTextArea(3, 17);//Create player text area. 
		huntTextPlayer.setEditable(false);//Prevent player text area from being edited.  
		huntTextPlayer.setBorder(new TitledBorder(new EtchedBorder(), "Player"));//Set player text border.
		huntTextPlayer.setBackground(frameColor);//Set player text area background color to frame background color. 
		huntTextMonster = new JTextArea(3, 17);//Create monster text area.
		huntTextMonster.setEditable(false);//Prevent monster text area from being edited. 
		huntTextMonster.setBorder(new TitledBorder(new EtchedBorder(), "Monster"));//Set monster text border.
		huntTextMonster.setBackground(frameColor);//Set monster text area background color to frame background color. 
		battleTimer bTimer = new battleTimer();//Create battle timer action listener. 
		t = new Timer(1000, bTimer);//Create battle timer object. 
		butBattle = new JButton("Start Battle");//Create start battle button.
		battleStarter startBattle = new battleStarter();//Create start battle button action listener. 
		butBattle.addActionListener(startBattle);//Add start battle action listener to start battle button. 
		huntPanel.add(huntTextPlayer);//Add player text area to hunt panel.
		huntPanel.add(huntTextMonster);//Add monster text area to hunt panel.
		huntPanel.add(butBattle);//Add start battle button to hunt panel. 
		jtp.addTab("Start Hunt", null, huntPanel, "Battle chosen monster.");//Add hunt panel to tabbed pane. 
	//End of Start Hunt tab.		
		setVisible(true);//Set elements to visible. 
	}

	private class battleTimer implements ActionListener //This action listener fires the following event when the battle timer starts. 
	{

		public void actionPerformed(ActionEvent Event)
		{
			int damage;//variable used to calculate damage and pass it to player / monster objects. 
			battleReport = "";//String used to display information related to battle. 
				if(player1.isAlive() == true && monster1.isAlive == true)//Start / continue the battle as long both participants are alive. 
				{ 
					if(player1.isAlive() == true)//If the player is alive. 
					{	
						damage = (player1.playerAttack() - monster1.monsterDefend());//Attack monster. 
						if(damage<0)//If the damage calculation produces a negative number, convert it to 0. Otherwise damage would heal a high-defense monster. 
						{
							damage = 0;
						}
						else
						{
						}
						battleReport += (monster1.displayMonsterName() + " receives " + damage + " damage. \n");//Add damage received by monster to battle report string. 
						battleReport += (monster1.displayMonsterName() + "'s health is now: " + (monster1.takeDamage(damage)) + "\n");//Add current health of monster to battle report string.  
						huntTextMonster.setText(battleReport);//Display battle report string in monster text area. 
						battleReport = "";//Reset battle report in preparation for next entry. 
						
					}
					else
					{
					
					}
					if(monster1.isAlive == true)//If the monster is alive. 
					{
						damage = (monster1.monsterAttack() - player1.playerDefend());//Attack player. 
						if(damage<0)//If the damage calculation produces a negative number, convert it to 0. Otherwise damage would heal a high-defense player.
						{
							damage = 0;
						}
						else
						{
							
						}
					
						battleReport += (player1.displayName() + " receives " + damage + " damage. \n");//Add damage received by player to battle report string.
						battleReport += (player1.displayName() + "'s health is now: " + (player1.takeDamage(damage)) + "\n");//Add current health of player to battle report string.
						huntTextPlayer.setText(battleReport);//Display battle report string in player text area.
						battleReport = "";//Reset battle report in preparation for next entry.
						
						
					}
								
				}
				else
				{
					if(player1.isAlive() == true)//If player is alive when battle is over. 
					{
						battleReward = monster1.giveReward();//Calculate reward using method in monster class, and pass it to battle reward variable. 
						player1.collectReward(battleReward);//Pass the battle reward variable to the player collect reward method.
						player1.healPlayer();//Heal the player back to their starting health. 
						battleReport +=(player1.displayName() + " has won.\n" + player1.displayName() + " has earned " + battleReward + " gold.");//Add player win message to battle report string. 
						huntTextPlayer.setText(battleReport);//Set player text area to battle report. 
						battleReport = "";//Reset battle report in preparation for monster message. 
						battleReport +=(monster1.displayMonsterName() + " is dead. \n");//Set battle report to monster loss message. 
						huntTextMonster.setText(battleReport);//Set monster text area to battle report. 
						battleInProgress = false;//Battle is no longer in progress. 
						monsterCreated = false;//A new monster must be created before the next battle can be started. 
						t.stop();//Stop battle timer. 
										
					}
					else//If player is dead when battle is over. 
					{
						player1.healPlayer();//Heal the player back to their starting health.
						player1.loseGold();//Player loses all gold when they lose a battle. 
						battleReport +=(player1.displayName() + " has lost, \nbut is brought back by the \nhealers at great cost.");//Set battle report string to player loss message.  
						huntTextPlayer.setText(battleReport);//Display player loss message in player text area. 
						battleReport = "";//Reset battle report string. 
						battleReport +=(monster1.displayMonsterName() + " wins.");//Set battle report string to player win message. 
						huntTextMonster.setText(battleReport);//Set monster text area to monster win message. 
						battleInProgress = false;//Battle is no longer in progress. 
						monsterCreated = false;//A new monster must be created before the next battle can be started. 
						t.stop();//Stop battle timer. 
					}
				}
			}

		
			
	}
	
	private class battleStarter implements ActionListener//This class tests for certain conditions, and starts the battle timer if they are met. 
	{
		public void actionPerformed(ActionEvent e)
		{
				
				if(playerCreated == true && monsterCreated == true && battleInProgress == false)//If a character has been created, a monster has been created, and there is currently no battle in progress. 
					{
						battleInProgress = true;//Set battle in progress to true. 
						t.start();//Start battle timer. 
					}
				else if(monsterCreated == false)//If no monster has been created, or if a previously created monster is now dead. 
					{
						JOptionPane.showMessageDialog(null, "No monster to fight.");//Tell the user that they must create a monster. 			
					}
				else if(playerCreated == false)//If a character hasn't been created yet. 
				{
					JOptionPane.showMessageDialog(null, "Create character.");//Tell the user that they must first create a character. 	
				}
					
				else//If a battle is currently in progress. 
					{
					JOptionPane.showMessageDialog(null, "Battle has already begun.");//Tell the user that there is already an active battle in progress. 
					}

		}
		
	}
	
	private class characterCreator implements ActionListener//This action listener creates a character when the create character button is pressed. 
	{
		public void actionPerformed(ActionEvent e)
		{
			if(player1.displayName().equals(""))//If the player doesn't currently have a name. 
			{
				player1.setName(charName.getText());//Set character name to whatever is currently in character name text box. 
				player1.createCharacter();//Create a character. 
				charText.setText((player1.displayStats()));//Set the character text area to the character's stats. 
				playerCreated = true;//Set player created to true. 
			}
			else//If a character has already been created. 
			{
				JOptionPane.showMessageDialog(null, "Character already created");//Display message. 
			}
		}
		
	}
	
	class characterDisplay implements ActionListener//This action listener displays the player's stats when the display character button is pressed. 
	{
		public void actionPerformed(ActionEvent e)
		{
			if(player1.displayName().equals(""))//If the character doesn't have a name.
			{
				JOptionPane.showMessageDialog(null, "Character not created");//Display message. 
				
			}
			else
			{
				charText.setText((player1.displayStats()));//Display character stats in character text area. 
			}
		}
	
	}
	
	class monsterCreator implements ActionListener//This action listener creates a monster when the create monster button is pressed. 
	{
		public void actionPerformed(ActionEvent e)
		{
		
			huntTextPlayer.setText("");//Remove any previous text in player text area on hunt panel. 
			huntTextMonster.setText("");//Remove any previous text in monster text area on hunt panel. 
			
			if(battleInProgress == false)//If there isn't a battle currently in progress. 
			{
				if(optGoblin.isSelected())//If the goblin option is selected. 
				{
					monster1 = new goblin();//Create a goblin object.
					monster1.createMonster();//Set object values based on class.
					monstText.setText((monster1.displayMonsterStats()));//Display monster stats. 
					
				}
				else if(optSpider.isSelected())//If the spider option is selected. 
				{
					monster1 = new spider();//Create a spider object. 
					monster1.createMonster();//Set object values based on class.
					monstText.setText((monster1.displayMonsterStats()));//Display monster stats. 
				}
				
				else if(optOgre.isSelected())//If the ogre option is selected. 
				{
					monster1 = new ogre();//Create a ogre object.
					monster1.createMonster();//Set object values based on class.
					monstText.setText((monster1.displayMonsterStats()));
				}
				
				else if(optBeholder.isSelected())//If the beholder option is selected. 
				{
					monster1 = new beholder();//Create a spider object.
					monster1.createMonster();//Set object values based on class.
					monstText.setText((monster1.displayMonsterStats()));//Display monster stats. 
				}
				
				else if(optLich.isSelected())//If the lich option is selected. 
				{
					monster1 = new lich();//Create a lich object.
					monster1.createMonster();//Set object values based on class.
					monstText.setText((monster1.displayMonsterStats()));//Display monster stats. 
				}
				else if(optDragon.isSelected())//If the dragon option is selected. 
				{
					monster1 = new dragon();//Create a dragon object.
					monster1.createMonster();//Set object values based on class.
					monstText.setText((monster1.displayMonsterStats()));//Display monster stats. 
				}
				else
				{
					
				}
			
				monsterCreated = true;//Set monster created to true. 
			}
			else//A battle is currently in progress, and must be finished before a new monster can be created. 
			{
				JOptionPane.showMessageDialog(null, "You must finish your current battle.");//Display message. 
			}
			
		}
		
	}
	
	public class weaponUpgrader implements ActionListener//This action listener creates a weapon when the upgrade weapon button is pressed. 
	{
		String selectedWeapon = "";//This string holds the player's weapon choice. 
		public void actionPerformed(ActionEvent e)
		{
			selectedWeapon = (String) comboWeapon.getSelectedItem();//Set the selected weapon string equal to the currently selected weapon. 
			if(selectedWeapon.equals("Steel Sword - 10 Gold"))//If the player has chosen the steel sword. 
			{
				if(player1.getGold()>=10)//If the player has enough gold to purchase the weapon.
				{
					weapon1 = new steelSword();//Create a new weapon object.
					player1.upgradeWeapon(weapon1);//Set the player's weapon to the weapon object. 
					JOptionPane.showMessageDialog(null,  "You bought a Steel Sword.");//Display a message.
					player1.makePurchase(10);//Deduct gold. 
				}
				else//If the player cannot afford the weapon. 
				{
					JOptionPane.showMessageDialog(null,  "You can't afford this.");//Display message. 
				}
				
			}
			
			else if(selectedWeapon.equals("Mythril Sword - 50 Gold"))//If the player has chosen the mythril sword.
			{
				if(player1.getGold()>=50)//If the player has enough gold to purchase the weapon.
				{
					weapon1 = new mythrilSword();//Create a new weapon object.  
					player1.upgradeWeapon(weapon1);//Set the player's weapon to the weapon object. 
					JOptionPane.showMessageDialog(null,  "You bought a Mythril Sword.");//Display a message.
					player1.makePurchase(50);//Deduct gold. 
				}
				else//If the player cannot afford the weapon. 
				{
					JOptionPane.showMessageDialog(null,  "You can't afford this.");//Display message. 
				}
			}
			
			else if(selectedWeapon.equals("Magic Sword - 100 Gold"))
			{
				if(player1.getGold()>=100)//If the player has enough gold to purchase the weapon.
				{
					weapon1 = new magicSword();//Create a new weapon object.  
					player1.upgradeWeapon(weapon1);//Set the player's weapon to the weapon object. 
					JOptionPane.showMessageDialog(null,  "You bought a Magic Sword.");//Display a message.
					player1.makePurchase(100);//Deduct gold. 
				}
				else//If the player cannot afford the weapon. 
				{
					JOptionPane.showMessageDialog(null,  "You can't afford this.");//Display message. 
				}
			}
			else
			{
				
			}
				
			
		}
		
	}
	
	public class armorUpgrader implements ActionListener
	{
		String selectedArmor = "";
		public void actionPerformed(ActionEvent e)
		{
			selectedArmor = (String) comboArmor.getSelectedItem();
			if(selectedArmor.equals("Steel Armor - 10 Gold"))
			{
				if(player1.getGold()>=10)
				{
					armor1 = new steelArmor();
					player1.upgradeArmor(armor1);
					JOptionPane.showMessageDialog(null,  "You bought Steel Armor.");
					player1.makePurchase(10);
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "You can't afford this.");
				}
				
			}
			
			else if(selectedArmor.equals("Mythril Armor - 50 Gold"))
			{
				if(player1.getGold()>=50)
				{
					armor1 = new mythrilArmor();
					player1.upgradeArmor(armor1);
					JOptionPane.showMessageDialog(null,  "You bought Mythril Armor.");
					player1.makePurchase(50);
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "You can't afford this.");
				}
			}
			
			else if(selectedArmor.equals("Magic Armor - 100 Gold"))
			{
				if(player1.getGold()>=100)
				{
					armor1 = new magicArmor();
					player1.upgradeArmor(armor1);
					JOptionPane.showMessageDialog(null,  "You bought Magic Armor.");
					player1.makePurchase(100);
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "You can't afford this.");
				}
			}
			else
			{
				
			}
				
			
		}
		
	}
	
	

	
}
	

