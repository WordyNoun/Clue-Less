/**
 * 
 */

/**
 * @author katie
 *
 */
public class CaseFile {
    public Cards weaponCard;
    public Cards roomCard;
    public Cards characterCard;
    
    public CaseFile(Cards weapon, Cards room, Cards character) {
        this.weaponCard = weapon;
        this.roomCard = room;
        this.characterCard = character;    
        
    }
    
    public Cards getWeaponCard() {
        return this.weaponCard;
    }
    
    public Cards getRoomCard() {
        return this.roomCard;
    }
    
    public Cards getCharacterCard() {
        return this.characterCard;
    }
    
    public String toString() {
        String output;
        
        output = "The cards in the Case File are: \n" + this.weaponCard.toString() + "\n" + this.roomCard + "\n" + this.characterCard.toString() + "\n";
        
        return output;
    }
}
