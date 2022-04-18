/**
 * GameBoardLocation defines a location where a player can reside and whether it is occupied if it is a hallway
 *
 */
public class GameBoardLocation {
    public String GameBoardLocationName;
    public String GameBoardLocationType;
    public boolean hallwayOccupied;
    
    public GameBoardLocation(String locName, String locType, boolean occupied) {
    	GameBoardLocationName = locName;
    	GameBoardLocationType = locType;
    	hallwayOccupied = occupied;
    }
    
    public String getLocationName() {
    	return GameBoardLocationName;
    }
    
    public String getLocationType() {
    	return GameBoardLocationType;
    }
    
    public boolean getOccupied() {
    	return hallwayOccupied;
    }
    
    public void changeOccupiedState(boolean occupied) {
    	hallwayOccupied = occupied;
    }
    
    @Override
    public String toString() {
    	return "Name: " + GameBoardLocationName + " Type: " + GameBoardLocationType + " Hallway Occupied: " + hallwayOccupied; 
    }
}