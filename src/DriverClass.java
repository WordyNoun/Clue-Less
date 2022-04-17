import java.util.Random;

public class DriverClass {
 
    public static void main(String[] args) {
        int numPlayers;
        Cards[] weaponCards;
        Cards[] roomCards;
        Cards[] characterCards;
        Cards[] allCards;
        CaseFile gameCaseFile;
        Character[] allCharacters;
        Weapon[] allWeapons;
        Room[] allRooms;
        Hallway[] allHallways;
        SecretPassage[] allSecretPassages;
        String gameBoard;
        Hand player1Hand;
        Hand player2Hand;
        Hand player3Hand;
        Hand player4Hand;
        Hand player5Hand;
        Hand player6Hand;
        
        numPlayers = 6;

        
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
        allHallways = createHallways(allRooms);
        allSecretPassages = createSecretPassages(allRooms);
        gameBoard = "[Study]        [Hallway1]  [Hall]          [Hallway2]  [Lounge]\n";
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
        
         gameCaseFile = createCaseFile(weaponCards, characterCards, roomCards);
         System.out.print(gameCaseFile.toString());
         
         player1Hand = new Hand(3, putCardsInPlayerHand(numPlayers, allCards), 1);
         System.out.print(player1Hand.toString());
         
         System.out.print(gameBoard);
    }
    
    public static SecretPassage[] createSecretPassages(Room[] allRooms) {
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
    public static Hallway[] createHallways(Room[] allRooms){
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
    
    public static Weapon[] createWeapons() {
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
    
    public static Room[] createRooms() {
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
    
    public static Character[] createCharacters() {
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
    
    public static CaseFile createCaseFile(Cards[] weaponCards, Cards[] characterCards, Cards[] roomCards) {
        Cards selectedWeaponCard;
        Cards selectedCharacterCard;
        Cards selectedRoomCard;
        Random generator = new Random();
        int randomWeapon;
        int randomCharacter;
        int randomRoom;
        CaseFile gameCaseFile;
        
        
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
        
        return gameCaseFile;
    }
    
 
    public static Cards[] putCardsInPlayerHand(int numPlayers, Cards[] allCards) {
        Random generator = new Random();
        int numCards;
        Cards[] hand;
        int randomCard; 
        int k = 0; 
        if (numPlayers == 3) {
            numCards = 6;
        } else if (numPlayers == 4){
            numCards = 4;
        } else {
            numCards = 3;
        }
        
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
    
    public static Cards[] createWeaponCards() {
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
        knifeCard = new Cards("knife", "Weapon");
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
    
    public static Cards[] createCharacterCards() {
        Cards scarletCard;
        Cards plumCard;
        Cards mustardCard;
        Cards peacockCard;
        Cards greenCard;
        Cards whiteCard;
        Cards[] characterCardArray;
        characterCardArray = new Cards[6];
        scarletCard = new Cards("Miss Scarlet", "Character");
        plumCard = new Cards("Prof. plum", "Character");
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
    
    public static Cards[] createRoomCards() {
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

}
