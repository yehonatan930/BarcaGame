/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcamodel;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Player {
    private String playerName;
    private int playerScore;//????
    protected final GamePiece [] playerPieces;
    private boolean white;//???
    private final int[][] BLACKPLACES =  {{0,4},{0,5},{1,3},{1,6},{1,4},{1,5}};
    private final int[][] WHITEPLACES =  {{9,4},{9,5},{8,3},{8,6},{8,4},{8,5}};
    //constructors:
    public Player(String name, boolean white){
        this.white = white;
        setPlayerName(name);
        setPlayerScore(0);
        playerPieces = new GamePiece[]{new ElephantPiece(white),new ElephantPiece(white),new LionPiece(white),new LionPiece(white),new MousePiece(white),new MousePiece(white)};
    }
    
    public void placeOnBoard(BarcaBoard b){      
        int x =0;
        if (white){
            for (int p[] : WHITEPLACES){
                b.getSquare(p[0], p[1]).placePiece(this.playerPieces[x]);
                this.playerPieces[x++].setSquare(b.getSquare(p[0], p[1]));
            }
        }
        else{
            for (int t[] : BLACKPLACES){
                b.getSquare(t[0], t[1]).placePiece(this.playerPieces[x]);
                this.playerPieces[x++].setSquare(b.getSquare(t[0], t[1]));
            }
        }
    }
    //methods:
    //getters
    public String getPlayerName() {
        return this.playerName;
    }
    
    public int getPlayerScore() {
        return this.playerScore;
    }
    
    public boolean hasScaredPiece(BarcaBoard b){
        ArrayList<GamePiece> scaredPieces = new ArrayList<GamePiece>();
        for(GamePiece p : playerPieces){
            p.square.getScaryNeighbor();
            if(p.getScared() && !p.isStuck(b)){
                //System.out.println("p");
                scaredPieces.add(p);
            }
        }
        //System.out.println(scaredPieces.isEmpty());
        return !scaredPieces.isEmpty();
    }
    
    //setters
    public void setPlayerScore(int playerScore) {    
        this.playerScore = playerScore;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
}
