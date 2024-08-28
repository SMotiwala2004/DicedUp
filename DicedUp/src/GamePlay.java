import java.util.*;

public class GamePlay {

    static Scanner sc = new Scanner(System.in);

    static Enemy enGob = new Enemy(100, 10, 20, "Enlarged Goblin");
    static Enemy gigTroll = new Enemy(150, 15, 25, "Gigantic Troll");
    static Enemy iceDrag = new Enemy(200, 20, 30, "Powerful Ice Dragon");

    static boolean combat;
    static boolean explore;

    static int combatDiceP;
    static int combatDiceE;
    static int exploreDice;

    static LinkedHashMap<String, Integer> Bosses = new LinkedHashMap<String, Integer>();
        //Main Method
        public static void startGame(Player p) {
            //Add the bosses into the storage set
            implementBosses(Bosses);
            //Option to return back to menu
            System.out.println("""
                    1)Start Game
                    2)Main Menu
                    """);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1" -> coreGameplay(p);
                case "2" -> Main.MainMenu();
            }
        }

        //Method allows the player to pick if they would like to explore, fight a boss, or save the game
        private static void coreGameplay(Player p) {
            System.out.println("""
                    How would you like to progress?
                    1) Explore
                    2) Boss Battle
                    3) Save Game
                    """);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1" -> explore(p);
                case "2" -> bossFight(p);
                case "3" -> saveGame(p);
            }
        }

        //Allow the player to level up, explore, etc.
        private static void explore(Player p) {
            //Used to reference the loop also allows the roll dice method to run properly
            explore = true;
            while(explore) {
                System.out.println(p.getPlayerName() + " is exploring through the lands. Through rigorous mountains, and travelling through the oceans.");
                rollDice();
                switch (exploreDice) {
                    //Nothing found in exploration
                    case 1 -> {
                        System.out.println("You have found nothing from your exploration, but you will gain experience through knowledge of the lands.");
                        p.addEXP(5);
                        System.out.println("You have gained 5 experience points. Your total experience points is: " + p.getEXP());
                    }
                    //Health power up found
                    case 2 -> {
                        System.out.println("You have gained a power-up in your health through your exploration.");
                        p.addHealth(2);
                        p.addEXP(10);
                        System.out.println("You have gained 2 health points, and 10 experience points!\n" +
                                "Your total health is: " + p.getHealth() + "\n" +
                                "Your total experience is: " + p.getEXP());
                    }
                    //Combat power up found
                    case 3 -> {
                        System.out.println("You have gained a combat power-up while traversing!");
                        p.addAttackPower(2);
                        p.addEXP(10);
                        System.out.println("You have gained 2 attack power and 10 experience!\n" +
                                "Your total attack power is now: " + p.getAttackPower() + "\n" +
                                "Your total experience is: " + p.getEXP());
                    }

                    case 4 -> {
                        //Trapped
                        System.out.println("You have fallen into a trap and had to use experience points to get out." + "\n");
                        if (p.getEXP()  < 10) {
                            p.setExp(0);
                            System.out.println("You used your remaining bits of EXP to get out. Your remaining EXP is: " + p.getEXP() + "\n");
                        } else {
                            System.out.println("You used up 10 experience points to get out. Your remaining EXP is: " + p.getEXP() + "\n");
                        }
                    }

                    case 5 -> {
                        System.out.println("You have encountered any enemy on your journey\n");
                        Enemy e = enemyFactory(p);
                        if (e != null) {
                            //Combat between the random enemy and player
                            Combat(e, p);
                        }
                    }

                    case 6 -> {
                        System.out.println("While exploring the lands, " + p.getPlayerName() + " had gotten too tired. You had to go back to sleep.");
                    }
                }
                //End of switch cases
                //Check if the user has the required amount of exp to level up
                if (p.getEXP() >= 100) {
                    p.LevelUp();
                    System.out.println("You have leveled up to level: " + p.getLevel());
                }
                //Prompt user to continue exploring.
                boolean validInput = false;
                while (!validInput) {
                    System.out.println("Would you like to continue exploring?\n(Y/N)");
                    String choice = sc.nextLine().toUpperCase();

                    if (choice.equals("Y")) {
                        validInput = true; // Continue exploring
                    } else if (choice.equals("N")) {
                        explore = false;
                        validInput = true; // Stop exploring
                    } else {
                        System.out.println("Invalid Choice. Please enter 'Y' or 'N'.");
                    }
                }
            } //end of while loop
            //Send back to start game menu
            startGame(p);
        }

        //Boss fights
        private static void bossFight(Player p) {
            int playerLvL = p.getLevel();
            boolean availableBosses = false;
            int index = 1; //Keep track of the number of bosses listed
            //Retrieve boss names and levels
            for (Map.Entry<String, Integer> Entry : Bosses.entrySet()) {

                String bossName = Entry.getKey();
                int reqLevel = Entry.getValue();

                if (playerLvL >= reqLevel) {
                    availableBosses = true;
                    System.out.println(index + ") " + bossName + ", Level required is " + reqLevel); //List every boss fight available
                    index++;
                }
            }
            //Check if there's any available bosses
            if (!availableBosses) {
                System.out.println("There are no available bosses to fight at your current level!");
                startGame(p);
            } else {
                String userInput = sc.nextLine();

                //implement user input
                switch (userInput) {
                    case "1" -> Combat(enGob, p);
                    case "2" -> Combat(gigTroll, p);
                    case "3" -> Combat(iceDrag, p);
                    default -> {
                        System.out.println("Invalid choice.");
                        bossFight(p);
                    }
                }
            }
        }
        //Generate general enemies
    private static Enemy enemyFactory(Player p) {
            int enemyRoll = (int) (Math.random() * 3) + 1;
            Enemy enemy = null;
            switch(enemyRoll) {
                case 1 ->  {
                    enemy  = new Enemy((int)(Math.random() * p.getLevel() )+ 1, "Troll");
                }
                case 2 -> {
                    enemy  = new Enemy((int)(Math.random() * p.getLevel() )+ 1, "Goblin");
                }
                case 3 -> {
                    enemy  = new Enemy((int)(Math.random() * p.getLevel() ) + 1, "Baby Dragon");
                }
                default -> {
                    System.out.println("Error loading enemy");
                }
            }
        return enemy;
    }
    private static void implementBosses(LinkedHashMap<String, Integer> map) {
        map.put("Enraged Goblin", 5);
        map.put("Gigantic Troll", 15);
        map.put("Powerful Ice Dragon", 25);
    }

        private static void rollDice() {
            //Explore dice
            if(explore) {
                exploreDice = (int) (Math.random() * 6) + 1;
            }
            //Combat Dice
            if(combat) {
                 combatDiceP = (int) (Math.random() * 21) + 1;
                 combatDiceE = (int) (Math.random() * 21) + 1;
            }
        }

         private static void Combat(Enemy e, Player p) {
             combat = true;
             int playerHealth = p.getHealth(); //store player health before battle
             int enemyHealth = e.getHealth(); //store enemy health before battle
             while(p.getHealth() > 0 && e.getHealth() > 0) {
                 //Roll the dice for enemies
                 rollDice();
                 int enemyDice = combatDiceE;
                 int playerDice = combatDiceP;
                 //List out the dice numbers rolled
                 System.out.println("The enemy has rolled a " + enemyDice );
                 System.out.println("You have rolled a " + playerDice);
                 //If enemey had a greater number then player
                 if (enemyDice > playerDice) {
                     //Negative value to remove health
                     int damage = (enemyDice - playerDice) + e.getAttackPower();
                     p.addHealth(-damage);
                     System.out.println("You have " + p.getHealth() + " health remaning");
                     //If the player had a greater number then the enemy
                 } else if (playerDice > enemyDice) {
                     //Negative value to remove health
                     int damage = (playerDice-enemyDice) + p.getAttackPower();
                     e.addHealth(-damage);
                     System.out.println(e.getName() + " has " +  e.getHealth() + " health remaning");
                 }
                 //If they had the same numbers
                 else
                 { System.out.println("Both attacks were negated");}
                //Check if the player has enough health to remain alive
                 if (p.getHealth() <= 0) {
                     System.out.println(p.getPlayerName() + " Has Died.");
                     p.setExp(0);
                     p.addHealth(playerHealth);
                     combat = false;
                 }
                 //Check if the enemy has enough health to remain alive
                 if (e.getHealth() <= 0) {
                     System.out.println(e.getName() + " Has Fallen");
                     //Boss Checks
                     if (e.getName().equals("Enlarged Goblin")) {
                         p.addEXP(30);
                         System.out.println(p.getPlayerName() + " has gained 30 exp");
                     }
                     //Boss Checks
                     if (e.getName().equals("Gigantic Troll")) {
                         p.addEXP(40);
                         System.out.println(p.getPlayerName() + " has gained 40 exp");
                     }
                     //Boss Checks
                     if (e.getName().equals("Powerful Ice Dragon")) {
                         p.addEXP(45);
                         System.out.println(p.getPlayerName() + " has gained 45 exp");
                     } else {
                         //Randomize EXP gain from enemies
                         p.addEXP((int)(Math.random() * 20) + 1);
                     }
                     combat = false;
                 }
             }
             p.setHealth(playerHealth);
             e.setHealth(enemyHealth);
             coreGameplay(p);
        } // End of combat function
    private static void saveGame(Player p) {
        Main.saveOverwrite(p);
    }

} // End of class
