/**
 * 
 */

/**
 * @author katie
 *
 */

public class Cards {
    public String cardType;
    public String cardName;
    public boolean inCaseFile;
    public boolean inPlayerHand;
    
    public Cards(String type, String name) {
        this.cardName = name;
        this.cardType = type;
        this.inCaseFile = false;
        this.inPlayerHand = false;
    }
    
    public String getCardName() {
         return this.cardName;
    }
    
    public String getCardType() {
        return this.cardType;
    }
    
    public void setInCaseFile() {
        this.inCaseFile = true;
    }
    
    public void setInPlayerHand() {
        this.inPlayerHand = true;
    }
    
    public String toString() {
        String output;
        
        output = "Card: " + this.cardName + ", " + this.cardType + "\n";
        
        return output;
    }

}
