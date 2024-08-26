  import java.io.*;
  import java.nio.Buffer;
  import java.util.Scanner;

public class Main {
  
    static Scanner sc = new Scanner(System.in);
    static final String SAVE_DIR = "saves";
  
    public static void main(String[] args) {
        File SD = new File(SAVE_DIR);
        if(!SD.exists()) {
            SD.mkdir();
        }

        System.out.println("Welcome to DicedUp");
        System.out.println("---------------------");
        //Call the Main Menu
         MainMenu();
    }

    public static void MainMenu() {
        while(true) {
            System.out.println("""
                    What would you like to do today?
                    1)Start a new save
                    2)Load a pre existing save
                    3)Remove a pre existing save
                    4)How to play DicedUp
                    5)Exit Game""");
            //Helps pick up whitespace as well as receive input
            String userInput = sc.nextLine();

            // Receive the UserInput
            switch (userInput) {
                case "1" -> {
                    newSave();
                    return;
                }
                case "2" -> {
                    loadSave();
                    return;
                }
                case "3" -> {
                    deleteSave();
                    return;
                }
                case "4" -> {
                    howTo();
                    return;
                }
                case "5" -> {
                    exitGame();
                    return;
                }
                default -> System.out.println("Invalid Input. Pick an appropriate option");
            }

            }
        }

    private static void howTo() {
        //Access the Instructions class through the start method to encapsulate the entirety of the class
        Instructions Instructions = new Instructions();
        Instructions.Start();
    }

    private static void exitGame() {
        //Exit Game
        System.out.println("---------Exiting Game---------");
        System.exit(0);
    }

    private static void deleteSave() {
        File SD = new File(SAVE_DIR);
        String[] savedFiles = SD.list((dir, name) -> name.endsWith(".txt"));
        //Parameters to check if any save files exists
        if(savedFiles == null || savedFiles.length == 0) {
            System.out.println("There are no saved files");
        }
      
        //List the saves currently available
        System.out.println("---------Available Saves---------");
        for (int i = 0; i < savedFiles.length; i++) {
            System.out.println(i + 1 + ")" +savedFiles[i]);
        }
      
        //Store the choice of which save file to delete
        System.out.println("Enter the number of the save file you like to delete.");
        int delete = Integer.parseInt(sc.nextLine()) - 1;

        if(delete < 0 || delete >= savedFiles.length) {
            System.out.println("Invalid Choice.");
        }
      
        //Confirmation code to delete the save file
        System.out.println("Are you sure you would like to delete the save?\n(Y/N)");
        String confirm = sc.nextLine();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Cancelling");
            return;
        }
      
        //Code to delete the save file
        String saveFilePath = SAVE_DIR + File.separator + savedFiles[delete];
        File saveFile = new File(saveFilePath);
        if (saveFile.delete()) {
            System.out.println("The save has successfully been deleted!");
        } else {
            System.out.println("Failed to delete this save file!");
        }
    }

    private static void loadSave() {
        File SD = new File(SAVE_DIR);
        String[] savedFiles = SD.list(((dir, name) -> name.endsWith(".txt")));
      
        //Check if the directory is existent or not
        if (savedFiles == null || savedFiles.length == 0) {
            System.out.println("No saves have been found");
            return;
        }
      
        System.out.println("---------Available Saves---------");
        //Iterate through and list each saved file and its listed number
        for (int i = 0; i < savedFiles.length; i++) {
            System.out.println(i + 1 + ")" + savedFiles[i]);
        }
      
        System.out.println("Enter the number of the save file you would like to load.");
        int load = Integer.parseInt(sc.nextLine()) - 1;
      
        //Check Parameters
        if(load < 0 || load >= savedFiles.length) {
            System.out.println("Invalid Choice.");
            return;
        }

        String saveFilePath = SAVE_DIR + File.separator + savedFiles[load];
      
        try(BufferedReader reader = new BufferedReader(new FileReader(saveFilePath))) {
          //Load player data
          Player p  = new Player();
          String playerName = reader.readLine();
          //Load Player Name
          p.setPlayerName(playerName);
            System.out.println("Loaded player: " + p.getPlayerName());
          //Load Player Health
          p.setHealth(Integer.parseInt(reader.readLine()));
          System.out.println("Health: " + p.getHealth());
          //Load Player Level
          p.setLevel(Integer.parseInt(reader.readLine()));
          System.out.println("Level: " + p.getLevel());
          //Load Player EXP
          p.setEXP(Integer.parseInt(reader.readLine()));
          System.out.println("EXP: " + p.getEXP());
          //Load Player Attack Power
          p.setAttackPower(Integer.parseInt(reader.readLine()));
            System.out.println("Attack Power: " + p.getAttackPower());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void newSave() {
        Player p = new Player();
      
        try {
            System.out.println("Enter a name for your save file");
            String saveFileName = sc.nextLine();
            String saveFilePath = SAVE_DIR + File.separator + saveFileName + ".txt";
            File saveFile = new File(saveFilePath);
            //Check if the save file exists with the matching name
            if (saveFile.exists()) {
                System.out.println("A save file with this current name already exists.");
                return;
            }
          
            System.out.println("Enter your player name");
            String playerName = sc.nextLine();
          
            //set the inputted player name
            p.setPlayerName(playerName);
          
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath))) {
                //Write all statistics of the player to the save file
                writer.write(p.getPlayerName() + "\n"); // Save player's name
                writer.write(p.getHealth() + "\n");     // Save player's health
                writer.write(p.getLevel() + "\n");      // Save player's level
                writer.write(p.getEXP() + "\n");        // Save player's experience points
                writer.write(p.getAttackPower() + "\n");// Save player's attack power
                System.out.println("Player has been created and saved successfully as " + saveFileName + ".txt.");
              
            } catch (IOException e) {
                System.out.println("Error saving game" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
