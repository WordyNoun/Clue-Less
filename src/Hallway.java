/**
 * 
 */

/**
 * @author katie
 *
 */
public class Hallway {
    public String hallwayName;
    public boolean occupied;
    public Room connectingRoom1;
    public Room connectingRoom2;
    
    public Hallway(String name, Room connectingRoom1, Room connectingRoom2) {
        this.occupied = false;
        this.hallwayName = name;
        this.connectingRoom1 = connectingRoom1;
        this.connectingRoom2 = connectingRoom2;
    }
    
    public void setOccupied() {
        this.occupied = true;
    }
    
    public boolean getOccupied() {
        return this.occupied;
    }
    
    public Room getConnectingRoom1() {
        return this.connectingRoom1;
    }
    
    public Room getConnectingRoom2() {
        return this.connectingRoom2;
    }
    
    public String getHallwayName() {
        return this.hallwayName;
    }
    
    public String toString() {
        String output;
        
        output = this.hallwayName;
        
        return output;
    }
}
