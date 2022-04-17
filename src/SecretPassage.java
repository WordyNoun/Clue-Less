/**
 * 
 */

/**
 * @author katie
 *
 */
public class SecretPassage {
    public String secretPassageName;
    public Room connectingRoom1;
    public Room connectingRoom2;
    
    public SecretPassage(String name, Room connectingRoom1, Room connectingRoom2) {
        this.secretPassageName = name;
        this.connectingRoom1 = connectingRoom1;
        this.connectingRoom2 = connectingRoom2;
    }
    
    public String getSecretPassageWayName() {
        return this.secretPassageName;
    }
    
    public Room getConnectingRoom1() {
        return this.connectingRoom1;
    }
    
    public Room getConnectingRoom2() {
        return this.connectingRoom2;
    }
    
    public String toString() {
        String output;
        
        output = this.secretPassageName;
        
        return output;
    }
}
