/**
 * 
 */

/**
 * @author katie
 *
 */
public class PlayerDummy {

    /**
     * 
     */
    public Hand playerHand;
    public String playerName;
    public int playerID;
    public Character playerCharacter;
    public boolean disabled;
    
    public PlayerDummy(int id, String name) {
        this.playerID = id;
        this.playerName = name;
        this.playerHand = null;
        this.playerCharacter = null;
        this.disabled = false;
    }
    
    public void setPlayerID(int id) {
        this.playerID = id;
    }
    
    public void setPlayerHand(Hand plyHand) {
        this.playerHand = plyHand;
    }
    
    public void setPlayerCharacter(Character plyCharacter) {
        this.playerCharacter = plyCharacter;
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    public void disablePlayer() {
        this.disabled = true;
    }
    
    public String toString() {
        String output = "";
        
        output = "Player ID: " + this.playerID + " Player name: " + this.playerName + "\n";
        
        return output;
    }

}
