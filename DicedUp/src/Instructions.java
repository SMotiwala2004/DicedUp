import java.util.Scanner;

public class Instructions {
    Scanner sc = new Scanner(System.in);

    //Call from the Main class to allow cleaner code
    public void Start() {
        DisplayInstruction();
    }
    private void DisplayInstruction() {
        //Display the options
        System.out.println("""
                What would you like to preview?
                1)Core Gameplay
                2)Combat
                3)Objective
                """);

        //Accept Input for the options listed above
        String userInput = sc.nextLine();
        switch (userInput) {
            case "1" -> coreGameplay();
            case "2" -> combatHowTo();
            case "3" -> objectiveHowTo();
            default -> System.out.println("Invalid Choice.");
        }
    }

    private void coreGameplay() {

        //Display the overall gameplay that takes place
        System.out.println("""
                The core gameplay in 'DicedUp' consists of exploring, fighting, and progressing. Although the game doesn't provide visuals or extra information
                while exploring, The exploration feature in the game is RNG based where the chances of discovering power-ups and enemies can be found. The game explains your
                setting while you are exploring to allow the player to use their imagination as they continue to play.""");
        System.out.println("""
                \n1)Back to Instructions menu
                2)Main Menu
                """);

        //Option to go back to main menu or instruction menu
        String userInput = sc.nextLine();
        switch (userInput)
        {
            case "1" -> DisplayInstruction();
            case "2" -> Main.MainMenu();
        }
    }

    //Display combat how to
    private void combatHowTo() {
        System.out.println("""
                The combat experience in DicedUp is similar to many RPGs. However, DicedUp implements a twist in combat. Through every battle sequence
                a dice is rolled for the enemy between 1-21. The number is displayed onto the screen then it's the players turn to roll the dice. 
                If the enemy's number rolled is greater then the players. The player will take the amount of damage of the enemy's
                base attack stat + the difference between the greater number and the smaller number. If the player rolls a greater number
                The enemy will take the damage of the player's base attack stat + the difference between the greater number and the smaller number.
                
                For example: Enemy rolls a 19 with a base attack stat of 6, and the player rolls a 15. The player will be dealt a total damage of 10.
                (19-15) + 6. 
                """);

        System.out.println("""
                \n1)Back to Instructions menu
                2)Main Menu
                """);

        //Option to go back to main menu or instruction menu
        String userInput = sc.nextLine();
        switch (userInput)
        {
            case "1" -> DisplayInstruction();
            case "2" -> Main.MainMenu();
        }
    }

    //Display the point / objective of the game
    private void objectiveHowTo() {
        System.out.println("""
        The objective in DicedUp is to progress through and defeat all the bosses. Although this can be done without any extra help,
        the game offers an RNG based exploration system where the player will travel through different environments. Through these
        different environments, players will have the chance to pick up stat boosting items(power ups). In addition to facing off against
        wild enemies.
        """);

        System.out.println("""
                \n1)Back to Instructions menu
                2)Main Menu
                """);
        
        //Option to go back to main menu or instruction menu
        String userInput = sc.nextLine();
        switch (userInput)
        {
            case "1" -> DisplayInstruction();
            case "2" -> Main.MainMenu();
        }
    }
}
