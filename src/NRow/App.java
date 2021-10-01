package NRow;

import NRow.Heuristics.MiniMaxHeuristics;
import NRow.Players.MinMaxPlayer;
import NRow.Players.PlayerController;

public class App {
    public static void main(String[] args) throws Exception {
        int gameN = 2;
        int boardWidth = 5;
        int boardHeight = 4;

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
        MiniMaxHeuristics heuristic1 = new MiniMaxHeuristics(n);
        MiniMaxHeuristics heuristic2 = new MiniMaxHeuristics(n);

        PlayerController human = new MinMaxPlayer(1, n, heuristic1);
        PlayerController human2 = new MinMaxPlayer(2, n, heuristic2);

        //TODO: Implement other PlayerControllers (MinMax, AlphaBeta)

        PlayerController[] players = { human, human2 };

        return players;
    }
}
