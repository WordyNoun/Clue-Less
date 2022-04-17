/**
 * 
 */

/**
 * @author katie
 *
 */
public class Character {
    public String characterName;
    public PlayerDummy playedBy;
    public boolean playedByPlayer;
    
    public Character(String name) {
        this.characterName = name;
        this.playedBy = null;
        this.playedByPlayer = false;
    }
    
    public void setPlayedBy(PlayerDummy playedBy) {
        this.playedBy = playedBy;
        this.playedByPlayer = true;
    }
    
    public String getChracterName() {
        return this.characterName;
    }
    
    public boolean getPlayed() {
        return this.playedByPlayer;
    }   
    
    public String toString() {
        String output;
        
        output = this.characterName;
        
        return output;
    }
}
