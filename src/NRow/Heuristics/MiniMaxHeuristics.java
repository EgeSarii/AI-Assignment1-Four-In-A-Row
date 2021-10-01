package NRow.Heuristics;

import NRow.Board;
import NRow.Game;
import NRow.Tree;

public class MiniMaxHeuristics extends Heuristic{

    public MiniMaxHeuristics(int gameN) {
        super(gameN);
    }

    @Override
    protected String name() {
        
        return "MiniMax";
    }

    @Override
    protected int evaluate(int player, Board board) {
        Tree gameTree = new Tree(board, gameN, player);
        return gameTree.miniMax();

    }
    
}
