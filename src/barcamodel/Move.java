package barcamodel;

public class Move {
    private final GameSquare src;
    private GameSquare dest;
    public Move(GameSquare src){
        this.src = src;
    }
    
    public Move(GameSquare src, GameSquare dest){
        this.src = src;
        setDest(dest);
    }
    
    public void setDest(GameSquare dest){ //checks if move is valid!
        this.dest = dest;
    }
    

    //methods:
    //getters
    public GameSquare getSrc(){return this.src;}
    
    public GameSquare getDest(){return this.dest;}
    
    //setters

    @Override
    public String toString() {
        return "src: (" + this.src.getX() + ", " + this.src.getY();// + ")/ntarget: (" + this.dest.getX() + ", " + this.dest.getY() + ")/n"; 
    }
    
}