import java.util.*;

public class GameManager {
    GameManager(){

    }
    public void startGame() {
        Game game = prepareGame();
        playGame(game);
        
    }
    public void playGame(Game game) {
        boolean gameFinish = false;
        do{
            Player currPlayer = game.players.poll();
            try{
                gameFinish = game.move(currPlayer);
                game.players.offer(currPlayer);
            }
            catch (Exception e){
                System.out.println("Exception occurred during the game: " + e.getMessage());
            }

        }while(!gameFinish);
    }
    public Game prepareGame() {
        // Initialize game parameters, players, dice, passage, etc.
        // Create and return the game object
        Player p1 =  new Player(0, "p1");
        Player p2 = new Player(1, "p2");
        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        HashMap<Integer, Integer> snakeAndLadders = new HashMap<>();
        snakeAndLadders.put(3, 20);
        snakeAndLadders.put(9, 1);
        snakeAndLadders.put(31, 24);
        snakeAndLadders.put(13, 90);
        snakeAndLadders.put(80, 47);
        return new Game(100, players, new Dice(6), snakeAndLadders);
    }
}
