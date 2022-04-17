/**
 * 
 */

/**
 * @author katie
 *
 */

import java.io.*;
import java.net.*;
import java.util.Random;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;
    private ServerSideConnection player3;    
    private int turnsMade;
    private gameManager curGame;
    //private int maxTurns;
    

    
    public GameServer() {
        System.out.println("_____________Game Server _______________");
        numPlayers = 3;
        turnsMade = 0;
        curGame = new gameManager(numPlayers); 
            
        
//        maxTurns = 12;
//        values = new int[2];
//        
//        for (int i = 0; i < values.length; i++) {
//            values[i] = (int) Math.ceil(Math.random() * 100);
//            System.out.println("Value #" + (i + 1) + " is " + values[i]);
//        }
        
        try {
            ss = new ServerSocket(51738);            
        } catch (IOException ex) {
            System.out.println("IOException from GameServer Constructor");
            ex.printStackTrace();
        }               
    }
    


    
    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
            while (numPlayers <= 3) {             
                Socket s = ss.accept();
                numPlayers++;                
                System.out.println("Player #" + numPlayers + " has connected.");
                
                ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);
                
                if (numPlayers == 1) {
                    player1 = ssc;
                } else if (numPlayers == 2) {
                    player2 = ssc;
                    
                } else {
                    player3 = ssc;
                
                }
                
                Thread t = new Thread(ssc);
                t.start();
                
                
            }
            System.out.println("No longer accepting connections.");
        } catch (IOException ex) {
            System.out.println("IOException from acceptConnections()");           
        }
    }
    
    
       
    private class ServerSideConnection implements Runnable {
        
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;

        
        public ServerSideConnection(Socket s, int id) {
            socket = s;
            playerID = id;           
            try {
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                
                
            } catch (IOException ex) {
                System.out.println("IOException from SSC constructor");
            }
        }
        
        public void run() {
            try {
                  dataOut.writeInt(playerID);
//                dataOut.writeInt(num);
//                dataOut.writeInt(values[0]);
//                dataOut.writeInt(values[1]);
//                dataOut.flush();
                
                while (true) {
                    
                }
            } catch (IOException ex) {
                System.out.println("IOException from run() SSC");
            }
       }
     

     
     public void chooseCardinHand(int n) {
         try {
             dataOut.writeInt(n);
             dataOut.flush();
         } catch (IOException ex) {
             System.out.println("IOException from sendButtonNum() ssc");
         }
     }
    }
    
    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
        

       
    }
}
    
