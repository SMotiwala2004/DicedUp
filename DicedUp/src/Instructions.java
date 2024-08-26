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
        System.out.println("The core gameplay in 'DicedUp'' consists of exploring, fighting, and progressing. Although the game doesn't provide visuals or extra information\n" +
                "while exploring, The exploration feature in the game is RNG based where the chances of discovering power-ups and enemies can be found. The game explains your \n" +
                "setting while you are exploring to allow the player to use their imagination as they continue to play.");
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
