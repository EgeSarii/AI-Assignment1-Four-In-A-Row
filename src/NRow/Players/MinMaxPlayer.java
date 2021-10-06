package NRow.Players;

import NRow.Board;
import NRow.Game;
import NRow.Tree;
import NRow.Heuristics.Heuristic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import NRow.Tree.*;




public class MinMaxPlayer extends PlayerController {

    Scanner scanner = new Scanner(System.in);
    private int depth;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }


    /**
   * Implement this method yourself!
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {
        // TODO: implement minmax player!
        // HINT: use the functions on the 'board' object to produce a new board given a specific move
        // HINT: use the functions on the 'heuristic' object to produce evaluations for the different board states!
        
        System.out.println(board);
        
       
    
        if (heuristic != null)
        {
            
            int maxValue = Integer.MIN_VALUE;
            int maxMove = 0;
            for(int i = 0; i < board.width; i++) 
            { //for each of the possible moves
                if(board.isValid(i)) { //if the move is valid
                    Board newBoard = board.getNewBoard(i, playerId); // Get a new board resulting from that move
                    Tree gameTree = new Tree(newBoard, gameN, playerId,depth, heuristic);
                    int value = miniMax(gameTree); //evaluate that new board to get a heuristic value from it
                    if(value > maxValue) 
                    {
                        maxValue = value;
                        maxMove = i;
                    }
                }
            }
            
            System.out.println("Heuristic: " + heuristic + " calculated the best move is: "
            + (maxMove + 1));
        }
        
        System.out.println("Player " + this + "\nWhich column would you like to play in?");
        
        int column = scanner.nextInt();
        
        System.out.println("Selected Column: " + column);
        
       
        return column - 1;        

    }
    public int miniMax(Tree tree)
    {
        return (miniMax(tree.root));
    }

    private int miniMax(Node node) {
        List<Integer> valuesOfChildren = new ArrayList<>();
        if (node.children.isEmpty()) // it is Leaf or depth is reached
        {
            return node.value;
        } else { // it is Node

            for (Node child : node.children) {
                valuesOfChildren.add(miniMax(child));
            }
            if (node.player == 1)// MAX
            {
                return Collections.max(valuesOfChildren);
            } else // MIN
            {
                return Collections.min(valuesOfChildren);
            }

        }

    }


}
