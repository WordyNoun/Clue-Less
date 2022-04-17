/**
 * 
 */

/**
 * @author katie
 *
 */
public class Hand {
    public Cards[] playerHand;
    public PlayerDummy player;
    public int size;
    
    public Hand(int handSize, Cards[] cardSet, PlayerDummy player) {
        this.playerHand = new Cards[handSize];
        this.playerHand = cardSet;
        this.size = handSize;
        this.player = player;
    }
    
    public Cards[] getWeaponCards() {
        int i;
        int numCard = 0;
        int k = 0;
        Cards[] weaponCardHand = null;
        
        for (i = 0; i < size; i++) {
            if (playerHand[i].getCardType() == "Weapon") {
                numCard++;
            }
        }
                
        if (numCard > 0) {
            
            weaponCardHand = new Cards[numCard];
            
            for (i = 0; i < size; i++) {
                if (playerHand[i].getCardType() == "Weapon") {
                    weaponCardHand[k] = playerHand[i];
                    k++;
                }
            }
            
        }
        
        return weaponCardHand;
    }
    
    
    public Cards[] getCharacterCards() {
        int i;
        int numCard = 0;
        int k = 0;
        Cards[] characterCardHand = null;
        
        for (i = 0; i < size; i++) {
            if (playerHand[i].getCardType() == "Character") {
                numCard++;
            }
        }
                
        if (numCard > 0) {
            
            characterCardHand = new Cards[numCard];
            
            for (i = 0; i < size; i++) {
                if (playerHand[i].getCardType() == "Character") {
                    characterCardHand[k] = playerHand[i];
                    k++;
                }
            }
            
        }
        
        return characterCardHand;
    }
    
    public Cards[] getRoomCards() {
        int i;
        int numCard = 0;
        int k = 0;
        Cards[] roomCardHand = null;
        
        for (i = 0; i < size; i++) {
            if (playerHand[i].getCardType() == "Room") {
                numCard++;
            }
        }
                
        if (numCard > 0) {
            
            roomCardHand = new Cards[numCard];
            
            for (i = 0; i < size; i++) {
                if (playerHand[i].getCardType() == "Room") {
                    roomCardHand[k] = playerHand[i];
                    k++;
                }
            }
            
        }
        
        return roomCardHand;
    }
    
    public String printWeaponCards() {
        int i;
        String output;
        
        output = "These are the Weapon cards in your hand: \n";
        
        for (i = 0; i < size; i++) {
            if (playerHand[i].getCardType() == "Weapon") {
                output += playerHand[i].toString() + "\n";
            }
        }                
        return output;
      
    }
    
    
    public String printCharacterCards() {
        int i;
        String output;
        
        output = "These are the Character cards in your hand: \n";
        
        for (i = 0; i < size; i++) {
            if (playerHand[i].getCardType() == "Character") {
                output += playerHand[i].toString() + "\n";
            }
        }                
        return output;
      
    }
    
    public String printRoomCards() {
        int i;
        String output;
        
        output = "These are the Room cards in your hand: \n";
        
        for (i = 0; i < size; i++) {
            if (playerHand[i].getCardType() == "Room") {
                output += playerHand[i].toString() + "\n";
            }
        }                
        return output;
      
    }
    
    public String toString() {
        int i;
        String output;
        
        output = "These are the cards in your hand: \n";
        
        for (i = 0; i < size; i++) {           
             output += playerHand[i].toString() + "\n";
        }        
        
        return output;
    }
    
    
}
