
import java.util.Scanner;

public class DriverClass {
 
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int numPlayers = 0;
        System.out.println("Welcome to Clue-Less!");
        PlayerDummy[] playerList;
        String playerName;
        PlayerDummy player;
        gameManager curGame;
        
        
        while (numPlayers != 3 && numPlayers != 6){
            System.out.println("Please enter the number of players that will be playing (3-6): ");
            numPlayers = userInput.nextInt();
            
        }
        
        userInput.nextLine();
        
        playerList = new PlayerDummy[numPlayers];
        
        for(int i = 0; i<numPlayers; i++) {
            System.out.println("Please enter your player name: ");
            playerName = userInput.nextLine();
            player = new PlayerDummy(i+1, playerName);     
            playerList[i] = player;
        }
        
        
        //Print the player names:
        System.out.println("The following players are playing Clue-Less: ");
        for (int i = 0; i < playerList.length; i++) {
            System.out.println("Player ID: " + playerList[i].playerID + " Player name: " + playerList[i].playerName);            
        }
        
        //print extra line
        System.out.println();
        
        //Start the game
        curGame = new gameManager(numPlayers);
        
        //Assign player hands
        assignPlayerHands(curGame, playerList);
        
        //Create extra cards
        curGame.createExtraCards();
        
        //Have players choose characters
        assignPlayerCharacters(curGame, playerList);                    
    }
    
    public static void assignPlayerHands(gameManager curGame, PlayerDummy[] playerList) {
        Hand playerHand;
        
        //loop through players to create hands
        for (int i=0; i <playerList.length; i++) {
            playerHand  = curGame.createPlayerHand(playerList[i]);
            playerList[i].setPlayerHand(playerHand);
            
        }
        
    }
    
    public static void assignPlayerCharacters(gameManager curGame, PlayerDummy[] playerList) {
        Scanner userInput = new Scanner(System.in);
        String chosenCharacter = "";
        Character characterChosen = null;
        String freeCharacterString = "";
        
        System.out.println("We will now choose characters in the following order: ");
         for(int i = 0; i <playerList.length; i++) {
             System.out.println(playerList[i].playerName);                
         }
        
        System.out.println();
        
        //loop through players to choose characters
        for(int i = 0; i <playerList.length; i++) {
            System.out.println(playerList[i].playerName + " it is your turn to choose");
            freeCharacterString = curGame.printFreeCharacters();
            System.out.println(freeCharacterString);
            chosenCharacter = userInput.nextLine();
            characterChosen = curGame.chooseCharacter(playerList[i], chosenCharacter);
            
            while (characterChosen == null) {
                System.out.println("The character you chose was either incorrect or already chosen. Please try again.");
                chosenCharacter = userInput.nextLine();
                characterChosen = curGame.chooseCharacter(playerList[i], chosenCharacter);
            }
            
            System.out.println();
            playerList[i].setPlayerCharacter(characterChosen);
            System.out.println(playerList[i].playerName + " has chosen " + playerList[i].playerCharacter.characterName);
            System.out.println();
        }
        
        userInput.close();
    }
    
    public static void playerTurn(gameManager curGame, PlayerDummy currentPlayer) {
        Scanner userInput = new Scanner(System.in);
        int choice;
        
        curGame.printExtraCards();
        curGame.printPlayerHand(currentPlayer);
        curGame.printGameBoard();
        
        System.out.println(currentPlayer.playerName + " it is your turn. Please choose from the following options by typing in the number: ");
        System.out.println("1) Make a movement");
        System.out.println("2) Make a Suggestion");
        System.out.println("1) Make an accusation");
        
        choice = userInput.nextInt();
        
        //print a blank line
        System.out.println();
        
        //If the player chose suggestion
        if (choice == 2) {
            makeSuggestion(curGame, currentPlayer);
        }
        
    }
    
    public static void makeSuggestion(gameManager curGame, PlayerDummy currentPlayer) {
        int chosenWeapon = 0;
        int chosenCharacter = 0;
        Scanner userInputWeapon = new Scanner(System.in);
        Scanner userInputCharacter = new Scanner(System.in);
        
        //print previous suggestions made by the player to the player
        curGame.printPreviousSuggestions(currentPlayer);
        System.out.println();
        
        //Prompt player to choose from the following characters
        System.out.println(curGame.printAvailableCharactersSuggestionAccusation(currentPlayer));
        chosenCharacter = userInputCharacter.nextInt();
        
        //Prompt player to choose from the following weapons
        System.out.println(curGame.printAvailableWeaponsSuggstionAccusation(currentPlayer));
        chosenWeapon = userInputWeapon.nextInt();
        
        //curGame.makeSuggestion();
        
    }
    
    //public static void makeAccusation(gameManager, PlayerDummy currentPlayer) {
        
    //}
    

}
