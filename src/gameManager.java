import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 */

/**
 * @author katie
 *
 */
public class gameManager {     
    public CaseFile gameCaseFile;
    public Cards[] weaponCards;
    public Cards[] roomCards;
    public Cards[] characterCards;
    public Cards[] allCards;
    public Character[] allCharacters;
    private Weapon[] allWeapons;
    public Room[] allRooms;
    public Hallway[] allHallways;
    public SecretPassage[] allSecretPassages;
    public String gameBoard;
    public HashMap<PlayerDummy, Hand> playerHands;
    public boolean gameWon;
    public boolean gameStart;
    public int numPlayers;
    public Cards[] extraCards;
    public boolean extraCardsExist;
    public HashMap<PlayerDummy, Character> playerCharacter;
    public HashMap<PlayerDummy, ArrayList<Suggestion>> playerSuggestion;
    public PlayerDummy currentPlayerTurn;
    public PlayerDummy[] playerOrder;
    public PlayerDummy disprovePlayerTurn;
    
    public gameManager(int numP) {
        playerCharacter = new HashMap<PlayerDummy, Character>();
        playerHands = new HashMap<PlayerDummy, Hand>();
        playerSuggestion = new HashMap<PlayerDummy, ArrayList<Suggestion>>();
        numPlayers = numP;
        playerOrder = new PlayerDummy[numP];
        gameWon = false;
        currentPlayerTurn = null;
        disprovePlayerTurn = null;
        extraCardsExist = false;
        extraCards = null;
        weaponCards = new Cards[6];
        roomCards = new Cards[9];
        characterCards = new Cards[6];
        allCards = new Cards[21];
        allCharacters = new Character[6];
        allWeapons = new Weapon[6];
        allRooms = new Room[9];
        allHallways = new Hallway[12];
        allSecretPassages = new SecretPassage[2];
        weaponCards = createWeaponCards();
        roomCards = createRoomCards();
        characterCards = createCharacterCards();
        allCharacters = createCharacters();
        allWeapons = createWeapons();
        allRooms = createRooms();
        allHallways = createHallways();
        allSecretPassages = createSecretPassages();
        gameBoard = "This is the map of the game board: \n";
        gameBoard += "[Study]        [Hallway1]  [Hall]          [Hallway2]  [Lounge]\n";
        gameBoard += "[Hallway3]     [empty]     [Hallway4]      [empty]     [Hallway5]\n";
        gameBoard += "[Library]      [hallway6]  [Billiard Room] [Hallway7]  [Dining Room]\n";
        gameBoard += "[Hallway8]     [empty]     [Hallway9]      [empty]     [Hallway10]\n";
        gameBoard += "[Conservatory] [Hallway11] [Ballroom]      [Hallway12] [Kitchen]\n";
        
        int i = 0;
        
         for (int k = 0; k < weaponCards.length; k++) {
             allCards[i] = weaponCards[k];
             i++;
         }
         
         for (int k = 0; k < characterCards.length; k++) {
             allCards[i] = characterCards[k];
             i++;
         }
         
         for (int k = 0; k < roomCards.length; k++) {
             allCards[i] = roomCards[k];
             i++;
         }
         
         createCaseFile();
    }
    
    public Character chooseCharacter(PlayerDummy client, String charName) {
        Character chosenCharacter = null;
        
        charName = charName.strip();
        charName = charName.toUpperCase();
        
        for(int i=0; i < allCharacters.length; i++) {
            if (charName.equals(allCharacters[i].characterName.toUpperCase())) {
                if (allCharacters[i].playedByPlayer == false) {
                    playerCharacter.put(client, allCharacters[i]);  
                    chosenCharacter = allCharacters[i];
                    allCharacters[i].setPlayedBy(client);
                }
            }
        }
        
        return chosenCharacter;
    }
    
    public String printFreeCharacters() {
        String freeCharacters;
        
        freeCharacters = "Please choose from the following characters by typing in their name: \n";
        
        for (int i = 0; i < allCharacters.length; i++) {
            if(allCharacters[i].getPlayed() == false) {
                freeCharacters += allCharacters[i].toString() + "\n";
            }
        }
        
        return freeCharacters;
    }
    
    public SecretPassage[] createSecretPassages() {
        SecretPassage passage1;
        SecretPassage passage2;        
        SecretPassage[] secretPassageArray;
        secretPassageArray = new SecretPassage[2];
        
        passage1 = new SecretPassage("Passage 1", allRooms[0], allRooms[8]);
        passage2 = new SecretPassage("Passage 2", allRooms[2], allRooms[6]);
        
        secretPassageArray[0] = passage1;
        secretPassageArray[1] = passage2;
        
        return secretPassageArray;
    }
    
    public Hallway[] createHallways(){
        Hallway hallway1;
        Hallway hallway2;
        Hallway hallway3;
        Hallway hallway4;
        Hallway hallway5;
        Hallway hallway6;
        Hallway hallway7;
        Hallway hallway8;
        Hallway hallway9;
        Hallway hallway10;
        Hallway hallway11;
        Hallway hallway12;
        Hallway[] hallwayArray;
        hallwayArray = new Hallway[12];
        
        hallway1 = new Hallway("Hallway1", allRooms[0], allRooms[1]);
        hallway2 = new Hallway("Hallway2", allRooms[1], allRooms[2]);
        hallway3 = new Hallway("Hallway3", allRooms[0], allRooms[3]);
        hallway4 = new Hallway("Hallway4", allRooms[1], allRooms[4]);
        hallway5 = new Hallway("Hallway5", allRooms[2], allRooms[5]);
        hallway6 = new Hallway("Hallway6", allRooms[3], allRooms[4]);
        hallway7 = new Hallway("Hallway7", allRooms[4], allRooms[5]);
        hallway8 = new Hallway("Hallway8", allRooms[3], allRooms[5]);
        hallway9 = new Hallway("Hallway9", allRooms[4], allRooms[7]);
        hallway10 = new Hallway("Hallway10", allRooms[5], allRooms[8]);
        hallway11 = new Hallway("Hallway11", allRooms[6], allRooms[7]);
        hallway12 = new Hallway("Hallway12", allRooms[7], allRooms[8]);
        
        hallwayArray[0] = hallway1;
        hallwayArray[1] = hallway2;
        hallwayArray[2] = hallway3;
        hallwayArray[3] = hallway4;
        hallwayArray[4] = hallway5;
        hallwayArray[5] = hallway6;
        hallwayArray[6] = hallway7;
        hallwayArray[7] = hallway8;
        hallwayArray[8] = hallway9;
        hallwayArray[9] = hallway10;
        hallwayArray[10] = hallway11;
        hallwayArray[11] = hallway12;
        
        return hallwayArray;
    }
    
    public Weapon[] createWeapons() {
        Weapon rope;
        Weapon lead;
        Weapon knife;
        Weapon wrench;
        Weapon candle;
        Weapon revolver;
        Weapon[] weaponArray;
        weaponArray = new Weapon[6];
        
        rope = new Weapon("Rope");
        lead = new Weapon("Lead Pipe");
        knife = new Weapon("Knife");
        wrench = new Weapon("Wrench");
        candle = new Weapon("Candle Stick");
        revolver = new Weapon("Revolver");
        
        weaponArray[0] = rope;
        weaponArray[1] = lead;
        weaponArray[2] = knife;
        weaponArray[3] = wrench;
        weaponArray[4] = candle;
        weaponArray[5] = revolver;
      
        
        return weaponArray;
    }
    
    public Room[] createRooms() {
        Room study;
        Room hall;
        Room lounge;
        Room library;
        Room billiard;
        Room dining;
        Room conservatory;
        Room ballroom;
        Room kitchen;
        Room[] roomArray;
        roomArray = new Room[9];
        
        study = new Room("Study");
        hall = new Room("Hall");
        lounge = new Room("Lounge");
        library = new Room("Library");
        billiard = new Room("Billiard Room");
        dining = new Room("Dining Room");
        conservatory = new Room("Conservatory");
        ballroom = new Room("Ballroom");
        kitchen = new Room("Kitchen");
       
        roomArray[0] = study;
        roomArray[1] = hall;
        roomArray[2] = lounge;
        roomArray[3] = library;
        roomArray[4] = billiard;
        roomArray[5] = dining;
        roomArray[6] = conservatory;
        roomArray[7] = ballroom;
        roomArray[8] = kitchen;
        
        return roomArray;
    }
    
    public void createExtraCards() {
        int k = 0;
        if (numPlayers == 4 || numPlayers == 5) {
            extraCardsExist = true;
            if (numPlayers == 4) {
                extraCards = new Cards[2];  
            } else {
                extraCards = new Cards[3];
            }

            while (k <= 1) {
              for(int i = 0; i < allCards.length; i++) {
                   if(allCards[i].inCaseFile == false && allCards[i].inPlayerHand == false) {
                       extraCards[k] = allCards[i];
                       k++;
                   }
              }
            
            }
        }
    }
    
    public Character[] createCharacters() {
        Character scarlet;
        Character mustard;
        Character white;
        Character green;
        Character peacock;
        Character plum;
        Character[] characterArray;
        characterArray = new Character[6];
        
        scarlet = new Character("Miss Scarlet");
        mustard = new Character("Col. Mustard");
        white = new Character("Mrs. White");
        green = new Character("Mr. Green");
        peacock = new Character("Mrs. Peacock");
        plum = new Character("Prof. Plum");
        
        characterArray[0] = scarlet;
        characterArray[1] = mustard;
        characterArray[2] = white;
        characterArray[3] = green;
        characterArray[4] = peacock;
        characterArray[5] = plum;
        
        return characterArray;
    }
    
    public void createCaseFile() {
        Cards selectedWeaponCard;
        Cards selectedCharacterCard;
        Cards selectedRoomCard;
        Random generator = new Random();
        int randomWeapon;
        int randomCharacter;
        int randomRoom;
        
        randomWeapon = generator.nextInt(weaponCards.length);
        randomCharacter = generator.nextInt(characterCards.length);
        randomRoom = generator.nextInt(roomCards.length);
        
        selectedWeaponCard = weaponCards[randomWeapon];
        selectedCharacterCard = characterCards[randomCharacter];
        selectedRoomCard = roomCards[randomRoom];
        
        selectedWeaponCard.setInCaseFile();
        selectedCharacterCard.setInCaseFile();
        selectedRoomCard.setInCaseFile();
        
        gameCaseFile = new CaseFile(selectedWeaponCard, selectedRoomCard, selectedCharacterCard);
             
    }
    
    public Hand createPlayerHand(PlayerDummy player) {
        int numCards;        
        Cards[] hand;
        Hand clientHand;
        
        if (numPlayers == 3) {
            numCards = 6;
        } else if (numPlayers == 4){
            numCards = 4;
        } else {
            numCards = 3;
        }
        

         hand = putCardsInPlayerHand(numCards);
         clientHand = new Hand(numCards,hand, player);
         playerHands.put(player, clientHand);            
        
         return clientHand;
    }
    
    public Cards[] putCardsInPlayerHand(int numCards) {
        Random generator = new Random();
        Cards[] hand;
        int randomCard; 
        int k = 0;               
        hand = new Cards[numCards];
        
        while (k < numCards) {
            randomCard = generator.nextInt(allCards.length);
            if (allCards[randomCard].inCaseFile == false) {
                if (allCards[randomCard].inPlayerHand == false) {
                    hand[k] = allCards[randomCard];
                    allCards[randomCard].setInPlayerHand();
                    k ++;
                }
            }
        }
        
        return hand;
    }
    
    public Cards[] createWeaponCards() {
        Cards ropeCard;
        Cards leadCard;
        Cards knifeCard;
        Cards wrenchCard;
        Cards candleCard;
        Cards revolverCard;
        Cards[] weaponCardArray;
        weaponCardArray = new Cards[6];
        
        ropeCard = new Cards("Rope", "Weapon");
        leadCard = new Cards("Lead Pipe", "Weapon");
        knifeCard = new Cards("Knife", "Weapon");
        wrenchCard = new Cards("Wrench", "Weapon");
        candleCard = new Cards("Candle Stick", "Weapon");
        revolverCard = new Cards("Revolver", "Weapon");
        
        weaponCardArray[0] = ropeCard;
        weaponCardArray[1] = leadCard;
        weaponCardArray[2] = knifeCard;
        weaponCardArray[3] = wrenchCard;
        weaponCardArray[4] = candleCard;
        weaponCardArray[5] = revolverCard;
        
        return weaponCardArray;
    }
    
    public Cards[] createCharacterCards() {
        Cards scarletCard;
        Cards plumCard;
        Cards mustardCard;
        Cards peacockCard;
        Cards greenCard;
        Cards whiteCard;
        Cards[] characterCardArray;
        characterCardArray = new Cards[6];
        scarletCard = new Cards("Miss Scarlet", "Character");
        plumCard = new Cards("Prof. Plum", "Character");
        mustardCard = new Cards("Col. Mustard", "Character");
        peacockCard = new Cards("Mrs. Peacock", "Character");
        greenCard = new Cards("Mr. Green", "Character");
        whiteCard = new Cards("Mrs. White", "Character");
        
        characterCardArray[0] = scarletCard;
        characterCardArray[1] = plumCard;
        characterCardArray[2] = mustardCard;
        characterCardArray[3] = peacockCard;
        characterCardArray[4] = greenCard;
        characterCardArray[5] = whiteCard;
        
        return characterCardArray;
    }
    
    public Cards[] createRoomCards() {
        Cards studyCard;
        Cards hallCard;
        Cards loungeCard;
        Cards libraryCard;
        Cards billiardCard;
        Cards diningCard;
        Cards conservatoryCard;
        Cards ballCard;
        Cards kitchenCard;
        Cards[] roomCardArray;
        roomCardArray =  new Cards[9];

        studyCard = new Cards("Study", "Room");
        hallCard = new Cards("Hall", "Room");
        loungeCard = new Cards("Lounge", "Room");
        libraryCard = new Cards("Library", "Room");
        billiardCard = new Cards("Billiard Room", "Room");
        diningCard = new Cards("Dining Room", "Room");
        conservatoryCard = new Cards("Conservatory", "Room");
        ballCard = new Cards("BallRoom", "Room");
        kitchenCard = new Cards("Kitchen", "Room");
        
        roomCardArray[0] = studyCard;
        roomCardArray[1] = hallCard;
        roomCardArray[2] = loungeCard;
        roomCardArray[3] = libraryCard;
        roomCardArray[4] = billiardCard;
        roomCardArray[5] = diningCard;
        roomCardArray[6] = conservatoryCard;
        roomCardArray[7] = ballCard;
        roomCardArray[8] = kitchenCard;
        
        return roomCardArray;
    }
    
    public String printPlayerHand (PlayerDummy client) {
        Hand playerHand;
        String output;
        playerHand = playerHands.get(client);
        output = playerHand.toString();
        return output;
    }
    
    public String printGameBoard() {
        return gameBoard;
    }
    
    public String printExtraCards() {
        String output;
        
        
        if (extraCards != null) {
            output = "The following cards are extra cards on the table: \n";
            for (int i = 0; i < extraCards.length; i++) {
                output += extraCards[i].toString();
            }
        } else {
            output = "There are no extra cards on the table to see.\n";
        }
        
        
        return output;
    }
    
    public String printPreviousSuggestions(PlayerDummy player) {
        String output = "";
        ArrayList<Suggestion> playerSug = new ArrayList<Suggestion>();
        
        playerSug = playerSuggestion.get(player);
        
        if (playerSug == null) {
            output = "You have made no suggestions yet.";
        } else {
            output = "You have made the following Suggestions: \n";
            for(Suggestion aSuggestion: playerSug){
                output += aSuggestion.printFinalizedSuggestionPlayer();
            }
        }
        
        return output;
    }
    
    public ArrayList<Weapon> getAvailableSuggestionAccusationWeapons(PlayerDummy player) {
        Hand playerHand;
        Cards[] weaponCardHand;
        ArrayList<Weapon> availableWeapons;
        ArrayList<Suggestion> playerSug = new ArrayList<Suggestion>();        
        playerSug = playerSuggestion.get(player);
        
        availableWeapons = new ArrayList<Weapon>();
        
        for(int i=0; i < allWeapons.length; i++) {
            availableWeapons.add(allWeapons[i]);
        }
        
        // A weapon is able to be suggested if
        // The player doesn't have the weapon in their hand
        // the weapon isn't in the extra cards on the table
        // The player has not suggested the weapon before and it was disproven
        
        //Get the weapon cards in the players hand
        playerHand = playerHands.get(player);
        weaponCardHand = playerHand.getWeaponCards();
        
        for (int k = 0; k < allWeapons.length; k++) {
            
            //remove the weapon from the array list if they have that weapon card in their hand
            if (weaponCardHand != null) {
                for (int i = 0; i < weaponCardHand.length; i++) {
                    if (allWeapons[k].weaponName.equals(weaponCardHand[i].getCardName())) {
                        availableWeapons.remove(allWeapons[k]);
                    }
                }
            }
            
            //if there are extra cards on the table then remove the weapon from the array list if the weapon is on a card in the extra cards
            if (extraCardsExist == true) {
                for (int i = 0; i < extraCards.length; i++) {
                    if (allWeapons[k].weaponName.equals(extraCards[i].getCardName())) {
                        availableWeapons.remove(allWeapons[k]);
                    }
                }
            }
            
            //if the player has suggested the weapon before in a suggestion then remove it from the array list
            if (playerSug != null) {
                //for each suggestion that the player made look for suggestions that had a disproven weapon
                for(Suggestion aSuggestion: playerSug) {
                    if (aSuggestion.disproven == true && aSuggestion.disprovenObject == "Weapon") {
                        if (allWeapons[k].weaponName.equals(aSuggestion.getSuggestedWeapon().getWeaponName())) {
                            availableWeapons.remove(allWeapons[k]);
                        }
                    }
                    
                }
            }
        }
        
        return availableWeapons;
           
    }
    
    public String printAvailableWeaponsSuggstionAccusation(PlayerDummy player) {
        String output = "";
        int weaponNumber = 1;
        ArrayList<Weapon> availableWeapons;
        
        
        //Call the method to get available weapons
        availableWeapons = getAvailableSuggestionAccusationWeapons(player);
        
        
        //If there are available weapons print each available weapon
        if (availableWeapons != null) {
            output = "The following weapons are available. Please type the weapon name to select it: \n";
            for (Weapon aWeapon: availableWeapons) {
                output += weaponNumber + ") " + aWeapon.weaponName + "\n";
                weaponNumber++;
            }
        } else {
            output = "There are no available weapons to suggest please make an accusation.\n";
        }
        
        return output;
    }
    
    public ArrayList<Character> getAvailableSuggestionAccusationCharacters(PlayerDummy player) {
        Hand playerHand;
        Cards[] characterCardHand;
        ArrayList<Character> availableCharacters;
        ArrayList<Suggestion> playerSug = new ArrayList<Suggestion>();        
        playerSug = playerSuggestion.get(player);
        
        availableCharacters = new ArrayList<Character>();
        
        for(int i=0; i < allCharacters.length; i++) {
            availableCharacters.add(allCharacters[i]);
        }
        
        // A character is able to be suggested if
        // The player doesn't have the character in their hand
        // the character isn't in the extra cards on the table
        // The player has not suggested the character before and it was disproven
        
        //Get the character cards in the players hand
        playerHand = playerHands.get(player);
        characterCardHand = playerHand.getCharacterCards();
        
        
        for (int k = 0; k < allCharacters.length; k++) {
                       
            //remove the character from the array list if they have that character card in their hand
            if(characterCardHand != null) {
                for (int i = 0; i < characterCardHand.length; i++) {
                    if (allCharacters[k].characterName.equals(characterCardHand[i].getCardName())) {
                        availableCharacters.remove(allCharacters[k]);
                    }
                }
            }
            
            //if there are extra cards on the table then remove the character from the array list if the character is on a card in the extra cards
            if (extraCardsExist == true) {
                for (int i = 0; i < extraCards.length; i++) {
                    if (allCharacters[k].characterName.equals(extraCards[i].getCardName())) {
                        availableCharacters.remove(allCharacters[k]);
                    }
                }
            }
            
            //if the player has suggested the character before in a suggestion then remove it from the array list
            if (playerSug != null) {
                //for each suggestion that the player made look for suggestions that had a disproven Character
                for(Suggestion aSuggestion: playerSug) {
                    if (aSuggestion.disproven == true && aSuggestion.disprovenObject == "Character") {
                        if (allCharacters[k].characterName.equals(aSuggestion.getSuggestedCharacter().characterName)) {
                            availableCharacters.remove(allCharacters[k]);
                        }
                    }
                    
                }
            }
        }
        
        return availableCharacters;
           
    }
    
    public String printAvailableCharactersSuggestionAccusation(PlayerDummy player) {
        String output = "";
        int characterNumber = 1;
        ArrayList<Character> availableCharacters;
        
        
        //Call the method to get available characters
        availableCharacters = getAvailableSuggestionAccusationCharacters(player);
        
        
        //If there are available characters print each available character
        if (availableCharacters != null) {
            output = "The following characters are available. Please type the character name to select it: \n";
            for (Character aCharacter: availableCharacters) {
                output += characterNumber + ") " + aCharacter.characterName + "\n";
                characterNumber++;
            }
        } else {
            output = "There are no available characters to suggest please make an accusation.\n";
        }
        
        return output;
    }
    
    public ArrayList<Room> getAvailableSuggestionAccusationRooms(PlayerDummy player) {
        Hand playerHand;
        Cards[] roomCardHand;
        ArrayList<Room> availableRooms;
        ArrayList<Suggestion> playerSug = new ArrayList<Suggestion>();        
        playerSug = playerSuggestion.get(player);
        
        availableRooms = new ArrayList<Room>();
        
        for(int i=0; i < allRooms.length; i++) {
            availableRooms.add(allRooms[i]);
        }
        
        // A Room is able to be suggested if
        // The player doesn't have the room in their hand
        // the room isn't in the extra cards on the table
        // The player has not suggested the room before and it was disproven
        
        //Get the room cards in the players hand
        playerHand = playerHands.get(player);
        roomCardHand = playerHand.getRoomCards();
        
        for (int k = 0; k < allRooms.length; k++) {
            
            //remove the room from the array list if they have that room card in their hand
            if (roomCardHand != null) {
                for (int i = 0; i < roomCardHand.length; i++) {
                    if (allRooms[k].roomName.equals(roomCardHand[i].getCardName())) {
                        availableRooms.remove(allRooms[k]);
                    }
                }
            }
            
            //if there are extra cards on the table then remove the room from the array list if the room is on a card in the extra cards
            if (extraCardsExist == true) {
                for (int i = 0; i < extraCards.length; i++) {
                    if (allRooms[k].roomName.equals(extraCards[i].getCardName())) {
                        availableRooms.remove(allRooms[k]);
                    }
                }
            }
            
            //if the player has suggested the room before in a suggestion then remove it from the array list
            if (playerSug != null) {
                //for each suggestion that the player made look for suggestions that had a disproven room
                for(Suggestion aSuggestion: playerSug) {
                    if (aSuggestion.disproven == true && aSuggestion.disprovenObject == "Room") {
                        if (allRooms[k].roomName.equals(aSuggestion.getSuggestedRoom().roomName)) {
                            availableRooms.remove(allRooms[k]);
                        }
                    }
                    
                }
            }
        }
        
        return availableRooms;
           
    }
    
    public String printAvailableRoomsSuggstionAccusation(PlayerDummy player) {
        String output = "";
        int roomNumber = 1;
        ArrayList<Room> availableRooms;
        
        
        //Call the method to get available rooms
        availableRooms = getAvailableSuggestionAccusationRooms(player);
        
        
        //If there are available rooms print each available room
        if (availableRooms != null) {
            output = "The following rooms are available. Please type the room name to select it: \n";
            for (Room aRoom: availableRooms) {
                output += roomNumber + ") " + aRoom.roomName + "\n";
                roomNumber++;
            }
        } else {
            output = "There are no available rooms to suggest please make an accusation.\n";
        }
        
        return output;
    }
    
    public boolean checkRoomForSuggestion(PlayerDummy player, Room currentRoom) {
        boolean canMakeSuggestionFromRoom = false;
        ArrayList<Room> availableRooms;
        availableRooms = new ArrayList<Room>();
        
        availableRooms = getAvailableSuggestionAccusationRooms(player);
        for(Room aRoom: availableRooms) {
            if (currentRoom.roomName.equals(aRoom.roomName)) {
                canMakeSuggestionFromRoom = true;
            }
        }
        
        return canMakeSuggestionFromRoom;
    }
    
    public Room getRoomFromName(String roomName) {
        Room roomObjReturn = null;
        
        for (int i = 0; i < allRooms.length; i++) {
            if (allRooms[i].roomName.toUpperCase().equals(roomName.trim().toUpperCase())) {
                roomObjReturn = allRooms[i];
            }
        }
        
        return roomObjReturn;
    }
    
    public Weapon getWeaponFromName(String weaponName) {
        Weapon weaponObjReturn = null;
        
        for (int i = 0; i < allWeapons.length; i++) {
            if (allWeapons[i].weaponName.toUpperCase().equals(weaponName.trim().toUpperCase())) {
                weaponObjReturn = allWeapons[i];
            }
        }        
        return weaponObjReturn;
    }
    
    public Character getCharacterFromName(String characterName) {
        Character characterObjReturn = null;
        
        for (int i = 0; i < allCharacters.length; i++) {
            if (allCharacters[i].characterName.toUpperCase().equals(characterName.trim().toUpperCase())) {
                characterObjReturn = allCharacters[i];
            }
        }        
        return characterObjReturn;
    }
    
    public Cards getCardFromName(String cardName) {
        Cards cardObjReturn = null;
        
        for (int i = 0; i < allCards.length; i++) {
            if (allCards[i].cardName.toUpperCase().equals(cardName.trim().toUpperCase())) {
                cardObjReturn = allCards[i];
            }
        }        
        return cardObjReturn;
    }
    
    
    public void createSuggestion(PlayerDummy player, Room chosenRoom, Weapon chosenWeapon, Character chosenCharacter) {
        Suggestion newPlayerSuggestion;
        ArrayList<Suggestion> playerSug;
        playerSug = new ArrayList<Suggestion>();
        
        newPlayerSuggestion = new Suggestion(chosenRoom, chosenWeapon, chosenCharacter, player); 
        playerSug = playerSuggestion.get(player);
        
        //if the player has not made any previous suggestions
        if (playerSug != null) {
            playerSug.add(newPlayerSuggestion);
            playerSuggestion.replace(player, playerSug);            
        } else {
            playerSug.add(newPlayerSuggestion);
            playerSuggestion.put(player, playerSug);
        }
        
    }
    
    public void changePlayerTurn() {
        int nextPlayer = 0;
        
        //if this is the first player turn then the first player to go will be the first in order
        if (currentPlayerTurn == null) {
            currentPlayerTurn = playerOrder[0];
        } else {
        
           for(int i = 0; i < playerOrder.length; i++) {
               if(playerOrder[i].playerName.equals(currentPlayerTurn.playerName)) {
                   nextPlayer = i + 1;
                   if (nextPlayer == numPlayers) {
                       currentPlayerTurn = playerOrder[0];
                       break;
                       
                   } else {
                       currentPlayerTurn = playerOrder[nextPlayer];
                       break;
                   }
               }
           }
        }
    }
    
    public void changeDisprovePlayerTurn(PlayerDummy curPlayer) {
        int nextPlayer = 0;
        
        for(int i = 0; i < playerOrder.length; i++) {
            if(playerOrder[i].playerName.equals(curPlayer.playerName)) {
                nextPlayer = i + 1;
                if (nextPlayer == numPlayers) {
                    disprovePlayerTurn = playerOrder[0];
                    break;
                    
                } else {
                    disprovePlayerTurn = playerOrder[nextPlayer];
                    break;
                }
            }
        }
     }
    
    
    public void determinePlayerOrder(){
        int k = 0;
        
        for(int i= 0; i < allCharacters.length; i++) {
            if(allCharacters[i].characterName.equals("Miss Scarlet") && allCharacters[i].playedByPlayer == true) {
                playerOrder[k] = allCharacters[i].playedBy;
                k++;
            } 
            if(allCharacters[i].characterName.equals("Col. Mustard") && allCharacters[i].playedByPlayer == true) {
                playerOrder[k] = allCharacters[i].playedBy;
                k++;
            }
            if(allCharacters[i].characterName.equals("Mrs. White") && allCharacters[i].playedByPlayer == true) {
                playerOrder[k] = allCharacters[i].playedBy;
                k++;
            }
            if(allCharacters[i].characterName.equals("Mr. Green") && allCharacters[i].playedByPlayer == true) {
                playerOrder[k] = allCharacters[i].playedBy;
                k++;
            }
            if(allCharacters[i].characterName.equals("Mrs. Peacock") && allCharacters[i].playedByPlayer == true) {
                playerOrder[k] = allCharacters[i].playedBy;
                k++;
            }
            if(allCharacters[i].characterName.equals("Prof. Plum") && allCharacters[i].playedByPlayer == true) {
                playerOrder[k] = allCharacters[i].playedBy;
                k++;
            }
            
            
         }
    }
    
    public String printPlayerOrder() {
        String output = "";
        
        output = "This is the order of player turns: \n";
        for (int i = 0; i<playerOrder.length; i++) {
            output += playerOrder[i].toString();
        }
        
        return output;
    }
    
    public void disproveSuggestion() {
        
    }
}
