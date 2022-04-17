/**
 * 
 */

/**
 * @author katie
 *
 */
public class Suggestion {
    public Room suggestedRoom;
    public Character suggestedCharacter;
    public Weapon suggestedWeapon;
    public PlayerDummy suggestedBy;
    public boolean disproven;
    public PlayerDummy disprovedBy;
    public String disprovenObject;
    
    public Suggestion(Room roomSug, Weapon weaponSug, Character characterSug, PlayerDummy sugBy) {
        this.disprovedBy = sugBy;
        this.suggestedRoom = roomSug;
        this.suggestedWeapon = weaponSug;
        this.suggestedCharacter = characterSug;
        
        this.disprovedBy = null;
        this.disproven = false;
        this.disprovenObject = "";
    }
    
    public boolean checkDisproveCard(Cards disproveCard) {
        String cardName;
        String cardType;
        Boolean disproven;
        
        cardName = disproveCard.getCardName();
        cardType = disproveCard.getCardType();
        
        if (cardName.equals(this.suggestedWeapon.getWeaponName())) {
            disproven = true;
        } else if (cardName.equals(this.suggestedCharacter.getChracterName())) {
            disproven = true;
        } else if (cardName.equals(this.suggestedRoom.getRoomName())) {
            disproven = true;
        } else {
            disproven = false;
        }
                
        return disproven;
    }
    
    public Weapon getSuggestedWeapon() {
        return this.suggestedWeapon;
    }
    
    public Room getSuggestedRoom() {
        return this.suggestedRoom;
    }
    
    public Character getSuggestedCharacter() {
        return this.suggestedCharacter;
    }
    
    public void disproveSuggestion(PlayerDummy disprovedBy, Cards disproveCard) {
        String objectType = disproveCard.getCardType();
        this.disprovedBy = disprovedBy;
        this.disprovenObject = objectType;
        this.disproven = true;
    }
    
    public String printInitialSuggestion() {
        String output;

        output = "A suggestion was made by " + this.suggestedBy.getPlayerName() + ": \n";
        output += "Room: " + this.suggestedRoom.getRoomName() + "\n";
        output += "Suspect: " + this.suggestedCharacter.getChracterName() + "\n";
        output += "Weapon: " + this.suggestedWeapon.getWeaponName() + "\n";
        
        return output;
                
    }
    
    public String printFinalizedSuggestionAll() {
        String output;

        output = "A suggestion was made by " + this.suggestedBy.getPlayerName() + ": \n";
        output += "Room: " + this.suggestedRoom.getRoomName() + "\n";
        output += "Suspect: " + this.suggestedCharacter.getChracterName() + "\n";
        output += "Weapon: " + this.suggestedWeapon.getWeaponName() + "\n";
        
        if (this.disproven == true) {
            output += "This suggestion was disproven by " + this.disprovedBy.getPlayerName() + "\n";
        } else {
            output += "This suggestion was not disproven. \n";
        }
        
        
        return output;
    }
    
    public String printFinalizedSuggestionPlayer() {
        String output;

        output = "Your suggestion: \n";
        output += "Room: " + this.suggestedRoom.getRoomName() + "\n";
        output += "Suspect: " + this.suggestedCharacter.getChracterName() + "\n";
        output += "Weapon: " + this.suggestedWeapon.getWeaponName() + "\n";
        
        if (this.disproven == true) {
            output += "This suggestion was disproven by " + this.disprovedBy.getPlayerName() + ". The " + this.disprovenObject + " was incorrect. \n";
        } else {
            output += "This suggestion was not disproven. \n";
        }
           
        return output;
    }
}
