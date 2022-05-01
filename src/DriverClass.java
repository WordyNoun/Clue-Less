
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
        String playerOrder;

        
        while (numPlayers < 3 || numPlayers > 6){
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
        
        //Determine Player Order
        curGame.determinePlayerOrder();
        
        //Print player order
        System.out.println(curGame.printPlayerOrder());
        
        //print extra line
        System.out.println();
        
        //Starting player turns
        while (curGame.gameWon == false) {
            curGame.changePlayerTurn();
            playerPlayTurn(curGame);
            
        }
        
        
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
                
    }
    
    public static void playerPlayTurn(gameManager curGame) {
        Scanner userInput = new Scanner(System.in);
        int choice;
        PlayerDummy currentPlayer;
        String extraCards;
        
        currentPlayer = curGame.currentPlayerTurn;
        
        System.out.println(curGame.printExtraCards());
        System.out.println(curGame.printPlayerHand(currentPlayer));
        System.out.println(curGame.printGameBoard());
        
        System.out.println(currentPlayer.playerName + " it is your turn. Please choose from the following options by typing in the number: ");
        System.out.println("1) Make a movement");
        System.out.println("2) Make a Suggestion");
        System.out.println("3) Make an accusation");
        
        choice = userInput.nextInt();
        
        //print a blank line
        System.out.println();
        
        //If the player chose suggestion
        if (choice == 2) {
            makeSuggestion(curGame, currentPlayer);
        }
        
        if (choice == 3) {
            makeAccusation(curGame, currentPlayer);
        }
        
        
        
    }
    
    public static void makeSuggestion(gameManager curGame, PlayerDummy currentPlayer) {
        String chosenWeapon;
        String chosenCharacter;
        Scanner userInputWeapon = new Scanner(System.in);
        Scanner userInputCharacter = new Scanner(System.in);
        Boolean roomAvailable = false;
        Scanner userInput = new Scanner(System.in);
        String roomName;
        Suggestion currentSuggestion = null;
        Character characterSug = null;
        Weapon weaponSug = null;
        
        //print previous suggestions made by the player to the player
        curGame.printPreviousSuggestions(currentPlayer);
        System.out.println();
        Room roomSug = null;
        
        //Prompt player to choose from the following characters
        System.out.println(curGame.printAvailableCharactersSuggestionAccusation(currentPlayer));
        chosenCharacter = userInputCharacter.nextLine();        
        characterSug = curGame.getCharacterFromName(chosenCharacter);
        
        //Prompt player to choose from the following weapons
        System.out.println(curGame.printAvailableWeaponsSuggstionAccusation(currentPlayer));
        chosenWeapon = userInputWeapon.nextLine();
        weaponSug = curGame.getWeaponFromName(chosenWeapon);
        
        while (roomAvailable == false){
            System.out.println("Please type in a room for the suggestion: \n");
            roomName = userInput.nextLine();
            roomSug = curGame.getRoomFromName(roomName);
            roomAvailable = curGame.checkRoomForSuggestion(currentPlayer, roomSug);
        }
        
        currentSuggestion = new Suggestion(roomSug, weaponSug, characterSug, currentPlayer);
        
        System.out.println();
        
        System.out.println(currentSuggestion.printInitialSuggestion());
        
        disproveSuggestion(curGame, currentSuggestion, currentPlayer);
        
        
        
    }
    
    public static void disproveSuggestion(gameManager curGame, Suggestion currentSuggestion, PlayerDummy playerSuggesting) {
        Scanner userInput = new Scanner(System.in);
        boolean disproven = false;
        curGame.changeDisprovePlayerTurn(playerSuggesting);
        String cardChosen;
        Cards disproveCard = null;
        String accuseOrFinish;
        
        
        while(disproven == false && !curGame.disprovePlayerTurn.playerName.equals(playerSuggesting.playerName)) {
            curGame.disprovePlayerTurn = curGame.disprovePlayerTurn;
            System.out.println(curGame.disprovePlayerTurn.playerName + " it is your turn to disprove the suggestion. Type in the name of the card you would like to use to disprove the suggestion. If you cannot disprove the suggestion type: pass.");
            System.out.println(curGame.printPlayerHand(curGame.disprovePlayerTurn));
            cardChosen = userInput.nextLine();
            
            if(!cardChosen.trim().toUpperCase().equals("PASS")) {
                disproveCard = curGame.getCardFromName(cardChosen);
                disproven = currentSuggestion.checkDisproveCard(disproveCard);            
            }
            
            if (disproven == true) {
                currentSuggestion.disproveSuggestion(curGame.disprovePlayerTurn, disproveCard);
            }
            
            curGame.changeDisprovePlayerTurn(curGame.disprovePlayerTurn);
        }
        
        System.out.println();
        
        if (disproven == true) {
            System.out.println(currentSuggestion.printFinalizedSuggestionPlayer());
            System.out.println(currentSuggestion.printFinalizedSuggestionAll());
                        
        } else {
            System.out.println(currentSuggestion.printFinalizedSuggestionPlayer());
            System.out.println(currentSuggestion.printFinalizedSuggestionAll());
            System.out.println("Would you like to make an accusation now? Please type yes or no.\n");
            accuseOrFinish = userInput.nextLine();
            if (accuseOrFinish.trim().toUpperCase().equals("YES")) {
                makeAccusation(curGame, playerSuggesting);
            }

        }
        
        System.out.println();
    }
    
    public static void makeAccusation(gameManager curGame, PlayerDummy currentPlayer) {
        String chosenWeapon;
        String chosenCharacter;
        String chosenRoom;
        Weapon accuseWeapon;
        Character accuseCharacter;
        Room accuseRoom;
        Scanner userInputWeapon = new Scanner(System.in);
        Scanner userInputCharacter = new Scanner(System.in);
        Scanner userInputRoom = new Scanner(System.in);
        boolean correctAccusation;
        Accusation curAccusation = null;
        
        System.out.println(curGame.gameCaseFile.toString());
        System.out.println(currentPlayer.playerName + " has chosen to make an accusation. If the accusation is correct they win the game. If it is false they may no longer take a turn.\n");
        
        
        //Prompt player to choose from the following characters
        System.out.println(curGame.printAvailableCharactersSuggestionAccusation(currentPlayer));
        chosenCharacter = userInputCharacter.nextLine();        
        accuseCharacter = curGame.getCharacterFromName(chosenCharacter);
        
        //Prompt player to choose from the following weapons
        System.out.println(curGame.printAvailableWeaponsSuggstionAccusation(currentPlayer));
        chosenWeapon = userInputWeapon.nextLine();
        accuseWeapon = curGame.getWeaponFromName(chosenWeapon);
        
        //Prompt player to choose from the following Rooms
        System.out.println(curGame.printAvailableRoomsSuggstionAccusation(currentPlayer));
        chosenRoom = userInputRoom.nextLine();
        accuseRoom = curGame.getRoomFromName(chosenRoom);
        
        curAccusation = new Accusation(currentPlayer, accuseRoom, accuseWeapon, accuseCharacter, curGame.gameCaseFile, curGame);
        
        correctAccusation = curAccusation.checkAccusation();
        
        System.out.println(curAccusation.printToAccusationPlayer());
        
        System.out.println();
        System.out.println(curAccusation.toString());
        System.out.println();
        
    }
    

}
