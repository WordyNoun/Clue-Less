import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * GameBoard class defines the gameboard as a directed graph, where the player can move freely to 
 * adjacent GameBoardLocations as long as they are not an occupied hallway
 * NOTE: Hallway names are based off Line 58-63 in gameManager.java
 */
public class GameBoard {
   
   // Map that stores the GameBoardLocation of the GameBoard (rooms/hallways)
   // room name -> GameBoardLocation
   private HashMap<String, GameBoardLocation> locations;
   
   // Map that stores the adjacent locations of each GameBoardLocation
   // room name -> list of adjacent GameBoardLocation
   private HashMap<String, GameBoardLocation[]> adjLocations;
   
   // Map that stores the GameBoardLocation of each player based on their unique name
   // player name -> GameBoardLocation
   private HashMap<String, GameBoardLocation> playerLocations;
   
   // default constructor sets a gameboard with no locations as occupied
   public GameBoard() {
      createGameBoard();
   }
   
   private void createGameBoard() {
      locations = new HashMap<String, GameBoardLocation>();
      adjLocations = new HashMap<String, GameBoardLocation[]>();
      playerLocations = new HashMap<String, GameBoardLocation>();
      
      GameBoardLocation studyLoc = new GameBoardLocation("Study", "room", false);
      GameBoardLocation hallLoc = new GameBoardLocation("Hall", "room", false);
      GameBoardLocation loungeLoc = new GameBoardLocation("Lounge", "room", false);
      GameBoardLocation libraryLoc = new GameBoardLocation("Library", "room", false);
      GameBoardLocation billiardLoc = new GameBoardLocation("Billiard Room", "room", false);
      GameBoardLocation diningLoc = new GameBoardLocation("Dining Room", "room", false);
      GameBoardLocation conservatoryLoc = new GameBoardLocation("Conservatory", "room", false);
      GameBoardLocation ballroomLoc = new GameBoardLocation("Ballroom", "room", false);
      GameBoardLocation kitchenLoc = new GameBoardLocation("Kitchen", "room", false);
      
      locations.put("Study", studyLoc);
      locations.put("Hall", hallLoc);
      locations.put("Lounge", loungeLoc);
      locations.put("Library", libraryLoc);
      locations.put("Billiard Room", billiardLoc);
      locations.put("Dining Room", diningLoc);
      locations.put("Conservatory", conservatoryLoc);
      locations.put("Ballroom", ballroomLoc);
      locations.put("Kitchen", kitchenLoc);
      
      for(int i = 1; i <= 12; i++) {
         locations.put("Hallway" + i, new GameBoardLocation("Hallway" + i, "hallway", false));
      }
      
      adjLocations.put("Study", new GameBoardLocation[] {kitchenLoc, locations.get("Hallway1"), locations.get("Hallway3")});
      adjLocations.put("Hall", new GameBoardLocation[] {locations.get("Hallway1"), locations.get("Hallway2"), locations.get("Hallway4")});
      adjLocations.put("Lounge", new GameBoardLocation[] {conservatoryLoc, locations.get("Hallway2"), locations.get("Hallway5")});
      adjLocations.put("Library", new GameBoardLocation[] {locations.get("Hallway3"), locations.get("Hallway6"), locations.get("Hallway8")});
      adjLocations.put("Billiard Room", new GameBoardLocation[] {locations.get("Hallway4"), locations.get("Hallway6"), locations.get("Hallway7"), locations.get("Hallway9")});
      adjLocations.put("Dining Room", new GameBoardLocation[] {locations.get("Hallway5"), locations.get("Hallway7"), locations.get("Hallway10")});
      adjLocations.put("Conservatory", new GameBoardLocation[] {loungeLoc, locations.get("Hallway8"), locations.get("Hallway11")});
      adjLocations.put("Ballroom", new GameBoardLocation[] {locations.get("Hallway9"), locations.get("Hallway11"), locations.get("Hallway12")});
      adjLocations.put("Kitchen", new GameBoardLocation[] {studyLoc, locations.get("Hallway10"), locations.get("Hallway12")});
      
      adjLocations.put("Hallway1", new GameBoardLocation[] {studyLoc, hallLoc});
      adjLocations.put("Hallway2", new GameBoardLocation[] {hallLoc, loungeLoc});
      adjLocations.put("Hallway3", new GameBoardLocation[] {studyLoc, libraryLoc});
      adjLocations.put("Hallway4", new GameBoardLocation[] {hallLoc, billiardLoc});
      adjLocations.put("Hallway5", new GameBoardLocation[] {loungeLoc, diningLoc});
      adjLocations.put("Hallway6", new GameBoardLocation[] {libraryLoc, billiardLoc});
      adjLocations.put("Hallway7", new GameBoardLocation[] {billiardLoc, diningLoc});
      adjLocations.put("Hallway8", new GameBoardLocation[] {libraryLoc, conservatoryLoc});
      adjLocations.put("Hallway9", new GameBoardLocation[] {billiardLoc, ballroomLoc});
      adjLocations.put("Hallway10", new GameBoardLocation[] {diningLoc, kitchenLoc});
      adjLocations.put("Hallway11", new GameBoardLocation[] {conservatoryLoc, ballroomLoc});
      adjLocations.put("Hallway12", new GameBoardLocation[] {ballroomLoc, kitchenLoc});
   }
   
   
   // Updates the board's state given the player name and location to move to
   // If suggestion is true, then we are moving the suggested player to the specified room
   // and don't need to check if the movement is valid
   public boolean movePlayer(String playerName, String locName, boolean suggestion) {
      // if the board was just initialized, then playerLocations will be empty and needed to be filled up
      // initially/manually
      if(playerLocations.get(playerName) == null) {
         playerLocations.put(playerName, locations.get(locName));
         // updating the occupied state of the GameBoardLocation only matters if it's a hallway
         if(locations.get(locName).getLocationType().equals("hallway")) {
            locations.get(locName).changeOccupiedState(true);
         }
      } else {
         // if we are updating the board state based on suggestion, then player is transported to the room
         // From the instructions, "Whenever a suggestion is made, the room must be the room the one making the suggestion
         // is currently in," meaning that suggestions only move players to other rooms, not hallways
         if(suggestion) {
            // we set the current player's location occupied state to false if they were in a hallway
            if(playerLocations.get(playerName).getLocationType().equals("hallway")) {
               playerLocations.get(playerName).changeOccupiedState(false);
            }
            // update the player's location
            playerLocations.put(playerName, locations.get(locName));
         } else {
            // If no suggestion is being made, then the player is moving and we must check if the move is valid
            if(isValidMovement(playerName, locName)) {
               // we set the current player's location occupied state to false if they were in a hallway
               if(playerLocations.get(playerName).getLocationType().equals("hallway")) {
                  playerLocations.get(playerName).changeOccupiedState(false);
               }
               // move the player to the new location
               playerLocations.put(playerName, locations.get(locName));
               // change the occupied state of the location to true if it is a hallway
               if(locations.get(locName).getLocationType().equals("hallway")) {
                  locations.get(locName).changeOccupiedState(true);
               }
            } else {
               return false;
            }
         }
         
      }
      return true;
   }
   
   // checks if the location that the player is requesting to move to is a valid one
   // NOTE: assumes the player has been assigned a starting location
   public boolean isValidMovement(String playerName, String locName) {
      String currRoom = playerLocations.get(playerName).getLocationName();
      GameBoardLocation[] adjLoc = adjLocations.get(currRoom);
      for(int i = 0; i < adjLoc.length; i++) {
         // if the location that the player is requesting to move to is adjacent to their current location incl. secret passage rooms
         // then return true
         if(adjLoc[i].getLocationName().equals(locName)) {
            // However, if the location is a hallway that is occupied, then the player cannot move there and return false
            if(adjLoc[i].getLocationType().equals("hallway") && adjLoc[i].getOccupied()) {
               return false;
            } else {
               return true;
            }
         }
      }
      // the requested room was not next to the player's current room
      return false;
   }
   
   public void printAdjacentLoc(String locName) {
      GameBoardLocation[] adjLoc = adjLocations.get(locName);
      for(int i = 0; i < adjLoc.length; i++) {
         System.out.println(adjLoc[i]);
      }
      System.out.println();
   }
   
   public String[] getAdjacentLoc(String locName) {
	  GameBoardLocation[] adjLoc = adjLocations.get(locName);
	  String[] retLoc = new String[adjLoc.length];
	  for(int i = 0; i < adjLoc.length; i++) {
	     retLoc[i] = adjLoc[i].getLocationName();
	  }
	  return retLoc;
   }
   
   public String getPlayerLoc(String playerName) {
      return playerLocations.get(playerName).getLocationName();
   }

   public String getPlayerLocationType(String playerName) {
	   return playerLocations.get(playerName).getLocationType();
   }
   
   public void printAllPlayerLoc() {
      for(Map.Entry<String, GameBoardLocation> set : playerLocations.entrySet()) {
         System.out.println(set.getKey() + " -> " + set.getValue());
      }
      System.out.println();
   }
   
   public String getLocation(String locName) {
      return locations.get(locName).toString();
   }
   
   public void printLocations() {
      for(Map.Entry<String, GameBoardLocation> set : locations.entrySet()) {
         System.out.println(set.getKey() + " -> " + set.getValue());
      }
      System.out.println();
   }
   
   // tests the functions of the GameBoard
   public static void main(String args[]) {
      // initializes a new gameboard, which has no players on it
      GameBoard gb = new GameBoard();
      // prints out nothing
      gb.printAllPlayerLoc();
      // places some players on the gameboard
      String playerName = "Miss Scarlet";
      String playerName2 = "Prof. Plum";
      // put Miss Scarlet in her starting position according to the Clue-Less instructions
      gb.movePlayer(playerName, "Hallway2", false);
      gb.printAllPlayerLoc();
      System.out.println();
      // put Prof. Plum in his starting position
      gb.movePlayer(playerName2, "Hallway3", false);
      gb.printAllPlayerLoc();
      System.out.println();
      
      // move Miss Scarlet to Lounge
      if(gb.movePlayer(playerName, "Lounge", false)) {
         System.out.println("Miss Scarlet moved to Lounge");
         System.out.println("Hallway2 is no longer occupied");
         System.out.println(gb.getLocation("Hallway2"));
         gb.printAllPlayerLoc();
         System.out.println();
      }
      
      // Miss Scarlet suggests Prof. Plum in the Lounge, since they need to be in the room they suggest
      // Prof. Plum is transported to the Lounge from Hallway3
      if(gb.movePlayer(playerName2, "Lounge", true)) {
         System.out.println("Miss Scarlet suggests Prof. Plum murdered the suspect in the Lounge");
         System.out.println("Prof. Plum is transported to the Lounge");
         System.out.println("Hallway3 is no longer occupied");
         System.out.println(gb.getLocation("Hallway3"));
         gb.printAllPlayerLoc();
         System.out.println();
      }
      
      // Prof. Plum moves to Hallway2
      if(gb.movePlayer(playerName2, "Hallway2", false)) {
         System.out.println("Prof. Plum moves to Hallway 2");
         System.out.println("Hallway2 is now occupied");
         System.out.println(gb.getLocation("Hallway2"));
         gb.printAllPlayerLoc();
         System.out.println();
         
         // Miss Scarlet tries to follow, but cannot since Hallway2 is occupied
         System.out.println("Miss Scarlet attempts to follow Prof. Plum to Hallway 2");
         if(!gb.movePlayer(playerName, "Hallway2", false)) {
            System.out.println("But Hallway2 is occupied");
            System.out.println("Miss Scarlet stays in the Lounge");
            gb.printAllPlayerLoc();
            System.out.println();
         }
      }
      
      // Miss Scarlet takes the secret passage from the lounge to the conservatory
      if(gb.movePlayer(playerName, "Conservatory", false)) {
         System.out.println("Miss Scarlet takes the secret passage and ends up in the conservatory");
         gb.printAllPlayerLoc();
         System.out.println();
      }
      
      // Prof. Plum attempts to make an illegal move to the Kitchen
      if(!gb.movePlayer(playerName2, "Kitchen", false)) {
         System.out.println("Prof. Plum tried to move to the kitchen, but the kitchen is not a valid adjacent location from Hallway 2");
         System.out.println("Adjacent Locations (Hallway 2):");
         gb.printAdjacentLoc("Hallway2");
         System.out.println();
         gb.printAllPlayerLoc();
         System.out.println();
      }
      
      
   }
   
}

