package NRow;

import NRow.Heuristics.SimpleHeuristic;
import NRow.Players.MinMaxPlayer;
import NRow.Players.AlphaBetaPlayer;
import NRow.Players.HumanPlayer;
import NRow.Players.PlayerController;

public class App {
    public static void main(String[] args) throws Exception {
        int gameN = 4;
        int boardWidth = 7;
        int boardHeight = 6;

        PlayerController[] players = getPlayers(gameN);

        Game game = new Game(gameN, boardWidth, boardHeight, players);
        game.startGame();
    }

    /**
     * Determine the players for the game
     * @param n
     * @return an array of size 2 with two Playercontrollers
     */
    private static PlayerController[] getPlayers(int n) {

        SimpleHeuristic heuristic1 = new SimpleHeuristic(n);
        SimpleHeuristic heuristic2 = new SimpleHeuristic(n);

        PlayerController human = new HumanPlayer(1, n, heuristic1);  //first player of human
        PlayerController human2 = new HumanPlayer(2, n, heuristic2); //second player of human
        
        
        PlayerController miniMax1 = new MinMaxPlayer(1, n, 2 ,heuristic1); //first player of MinMax 
        PlayerController miniMax2 = new MinMaxPlayer(2, n, 2, heuristic2); //second player of  MinMax

        PlayerController alphaBeta1 = new AlphaBetaPlayer(1, n, 2 ,heuristic1);// first player of Alpha-Beta
        PlayerController alphaBeta2 = new AlphaBetaPlayer(2, n, 2, heuristic2);// second player of Alpha-Beta
        // Choose two players from above
        PlayerController[] players = { alphaBeta1,alphaBeta2 };
        
        return players;
    }
}
