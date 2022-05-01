/**
 * 
 */

/**
 * @author katie
 *
 */
public class Accusation {
    public Weapon accusedWeapon;
    public Room accusedRoom;
    public Character accusedCharacter;
    public PlayerDummy playerAccusing;
    public boolean correctAccusation;
    public CaseFile gameCaseFile;
    public gameManager curGame;
    
    public Accusation(PlayerDummy playerAccusing, Room accRoom, Weapon accWeapon, Character accCharacter, CaseFile gameCaseFile, gameManager currentGame) {
        this.accusedWeapon = accWeapon;
        this.accusedRoom = accRoom;
        this.accusedCharacter = accCharacter;
        this.playerAccusing = playerAccusing;
        this.correctAccusation = false;  
        this.gameCaseFile = gameCaseFile;
        this.curGame = currentGame;
    }
    
    public boolean checkAccusation() {
        String murderWeapon;
        String murderer;
        String murderRoom;        
        
        murderer = gameCaseFile.characterCard.getCardName();
        murderWeapon = gameCaseFile.weaponCard.getCardName();
        murderRoom = gameCaseFile.roomCard.getCardName();
        
        if (this.accusedCharacter.getChracterName().equals(murderer) && this.accusedRoom.getRoomName().equals(murderRoom) && this.accusedWeapon.getWeaponName().equals(murderWeapon)) {                
              this.correctAccusation = true;
              this.curGame.gameWon = true; 
        } else {
            this.playerAccusing.disablePlayer();
        }
        
        
        return this.correctAccusation;
    }
    
    public String toString() {
        String output;
        
        output = "An accusation was made by " + this.playerAccusing.getPlayerName() + "\n";
        output += "Room: " + this.accusedRoom.getRoomName() + "\n";
        output += "Character: " + this.accusedCharacter.getChracterName() + "\n";
        output += "Weapon: " + this.accusedWeapon.getWeaponName() + "\n";
        
        if (this.correctAccusation == true) {
            output += "This accusation is correct. Player " + this.playerAccusing.getPlayerName() + " wins the game!\n";
        } else {
            output += "This accusation is not correct. Player " + this.playerAccusing.getPlayerName() + " is no longer in the game.\n";
        }
        
        return output;
    }
    
    public String printToAccusationPlayer() {
        String output;
        
        output = "Your accusation of \n";
        output += "Room: " + this.accusedRoom.getRoomName() + "\n";
        output += "Character: " + this.accusedRoom.getRoomName() + "\n";
        output += "Weapon: " + this.accusedWeapon.getWeaponName() + "\n";
        
        if (this.correctAccusation == true) {
            output += "This accusation is correct. You win!\n";
        } else {
            output += "This accusation is not correct. The murder is " + gameCaseFile.characterCard.getCardName() + ", the murder took place in " + gameCaseFile.roomCard.getCardName() + ", and the murder weapon was " + gameCaseFile.weaponCard.getCardName() + ".";
        }
        
        return output;
    }
}
