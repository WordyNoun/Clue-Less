
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class Player{

    private int playerID;
    private int otherPlayer1;
    private int otherPlayer2;
    private int otherPlayer3;
    private Hand playerHand;
    private int turnsMade;
    private boolean isDisabled;
    private String playerName;
    private ClientSideConnection csc;
    
    public Player() {       
        this.playerHand = null;
        this.turnsMade = 0;  
        this.isDisabled = false;
        this.playerName = "";
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    
    public void connectToServer() {
        csc = new ClientSideConnection();
    }
    
//    public void setUpButtons() {
//        ActionListener al = new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                JButton b = (JButton) ae.getSource();
//                int bNum = Integer.parseInt(b.getText());
//                
//                message.setText("You clicked button# " + bNum + ". Now wait for player #");
//                turnsMade++;
//                System.out.println("Turns made: " + turnsMade);
//                
//                buttonsEnabled = false;
//                //toggleButtons();
//                
//                //csc.sendButtonNum(bNum);
//                
//                
//            }
//        };
//        
//        study.addActionListener(al);
//        hallway1.addActionListener(al);
//        hall.addActionListener(al);
//        hallway2.addActionListener(al);
//        lounge.addActionListener(al);
//    }
//    
//    public void toggleButtons() {
//        study.setEnabled(buttonsEnabled);
//        hallway1.setEnabled(buttonsEnabled);
//        hall.setEnabled(buttonsEnabled);
//        hallway2.setEnabled(buttonsEnabled);
//        lounge.setEnabled(buttonsEnabled);
//        
//    }
    
    // client Connection Inner Class
    private class ClientSideConnection {
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        public ClientSideConnection() {
            System.out.println("-----Client-----");
            try {
                Scanner userInput = new Scanner(System.in);
                socket = new Socket("localhost", 51738);
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                playerID = dataIn.readInt();
                System.out.println("Connected to server as Player #: " + playerID + ".");
                
//                maxTurns = dataIn.readInt() / 6;
                //values[0] = dataIn.readInt();
                //values[1] = dataIn.readInt();
                //System.out.println("maxTurns: " + maxTurns);
                //System.out.println("Value # 1 is " + values[0]);
                //System.out.println("Value # 2 is " + values[1]);
                
            } catch (IOException ex) {
                System.out.println("IO Exception from CSC constructor");
            }
            
        }
        
        
        public void sendButtonNum(int n) {
            try {
                dataOut.writeInt(n);
                dataOut.flush();
                
            } catch (IOException ex) {
                System.out.println("IOException from sendButtonNum() CSC");
            }
            
        }
    }
    
    public static void main(String[] args) {
        Player p = new Player();
        p.connectToServer();        
    }
}
