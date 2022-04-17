/**
 * 
 */

/**
 * @author katie
 *
 */
public class Room {
    public String roomName;
    
    public Room(String name) {
        this.roomName = name;        
    }
    
    public String getRoomName() {
        return this.roomName;
    }
    
    public String toString() {
        String output;
        
        output = this.roomName;
        
        return output;
    }
}
