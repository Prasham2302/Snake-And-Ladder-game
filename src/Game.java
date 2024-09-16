import java.util.*;

public class Game {
    int size;
    Queue<Player> players;
    Dice gameDice;
    HashMap<Integer, Integer> playersPositions; // playerId -> position
    HashMap<Integer, Integer> passage;
    Game(){
        this.size = 100;
        this.players = new LinkedList<>();
        this.gameDice = new Dice(6);
        this.passage = new HashMap<>();
        this.playersPositions = new HashMap<>();
    }
    Game(int size, Queue<Player> Player, Dice gameDice, HashMap<Integer,Integer> passage){
        this.size = size;
        this.players = Player;
        this.gameDice = gameDice;
        this.passage = passage;
        this.playersPositions = new HashMap<>();
        for(Player player: Player){
            playersPositions.put(player.getId(), 0);
        }
    }
    public boolean move(Player player) throws Exception{
        if (!playersPositions.containsKey(player.getId())){
            throw new Exception("Player " + player.getId() + "not part of the players list");
        }
        int diceRoll = gameDice.roll();
        System.out.println("Player: "+player.getName()+" rolled: "+diceRoll);
        int newPosition = playersPositions.get(player.getId()) + diceRoll;
        if(newPosition >= size){
            System.out.println("Winner player: "+player.getName());
            return true;
        }
        if(passage.containsKey(newPosition)){
            System.out.println("Player: "+player.getName()+" moved through a passage");
            if (passage.get(newPosition) < newPosition){
                System.out.println("Oops... you found a snake, moving you to cell: "+passage.get(newPosition));
            }
            if (passage.get(newPosition) > newPosition){
                System.out.println("Yipee... you found a ladder, moving you to cell: "+passage.get(newPosition));
            }
            newPosition = (int) passage.get(newPosition);
        }
        System.out.println("Player: "+player.getName()+" moved to "+newPosition);
        playersPositions.put(player.getId(), newPosition);
        return false;
    }
}
