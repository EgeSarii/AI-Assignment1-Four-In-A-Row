package NRow.Players;

import NRow.Board;
import NRow.Tree;
import NRow.Heuristics.Heuristic;
import java.util.Scanner;
import NRow.Tree.*;


/**
 * Player class for MinMaxPlayer
 */

public class MinMaxPlayer extends PlayerController {

    Scanner scanner = new Scanner(System.in);
    private int depth;
    final int Player_MAX = 1; //the maximizing player X
    final int Player_MIN = 2; // the minimizing player O

    /**
     * Constructor method for MinMaxPlayer
     * @param playerId   playerID 1 or 2
     * @param gameN      gameN of the game
     * @param depth      depth of the tree to be built
     * @param heuristic  heuristics to be used for evaluation
     */
    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) 
    {
        super(playerId, gameN, heuristic);
        this.depth = depth;
    }


    /**
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {
        System.out.println(board);
        if (heuristic != null)
        {
            int maxValue = Integer.MIN_VALUE; // assign the maximum value negative infinity at first
            int maxMove = 0; // assign the column number zero at first
            for(int i = 0; i < board.width; i++) 
            { //for each of the possible moves
                if(board.isValid(i)) { //if the move is valid
                    Board newBoard = board.getNewBoard(i, playerId); // Get a new board resulting from that move
                    Tree gameTree = new Tree(newBoard, gameN, playerId,depth); // create a game tree based on that board as the root
                    int minMaxvalue = minMax(gameTree); //evaluate that new board to get a heuristic value from it by using minMax algorithm
                    if(maxValue < minMaxvalue) // if minMax returns a higher value then the other column option does
                    {
                        maxValue = minMaxvalue; // then the maximumValue is this value
                        maxMove = i; // and the optimal column is this column
                    }
                }
            }
            // Show which heuristic calculated which move is the best
            System.out.println("Heuristic: " + heuristic + " calculated the best move is: "
            + (maxMove + 1));
        }
        // Show which player is playing and ask for the next move
        System.out.println("Player " + this + "\nWhich column would you like to play in?");
        int column = scanner.nextInt();
        System.out.println("Selected Column: " + column);
        return column - 1;        
    }
    /**
     * public method of minMax algorithm. It is used for the game trees itself and called once.
     * @param tree the tree to be applied minMax
     * @return the miniMax value of the tree itself
     */
    public int minMax(Tree tree)
    {
        return (minMax(tree.root));
    }

    /**
     * private method of minMax algorithm. It is used for the evaluation of nodes and leafs. It is called recursively.
     * @param node node to be evaluated and/or applied minMax
     * @return the evaluated value of a leaf or the minMax value of a node
     */
    private int minMax(Node node) {
        if (node.children.isEmpty()) // it is Leaf or depth is reached
        {
            return heuristic.evaluateBoard(node.player, node.board); // return the evaluated value
        } else { // If it has child(ren) then it must be a node

            if (node.player == Player_MAX) // the playerID of the node is equal to maximizing player, 1 
            {
                int maximumVal = Integer.MIN_VALUE; // initialize the maximum value as negative infinity
                for (Node child : node.children) // for each child the node has
                {
                    int minMaxvalue = minMax(child); // assign the value to minMax value of the child
                    maximumVal = Math.max(minMaxvalue, maximumVal); // if the maximum value is bigger than the minMax value, don't change  
                                                             // the maximum value, else update the maximum value with minMax value
                }
                return maximumVal;  // return the maximum value as the minMax value

                
            }
            else // the playerID of the node is equal to maximizing player, 2
            {
                int minimumVal = Integer.MAX_VALUE; // initialize the minimum value as positive infinity           
                for (Node child : node.children) // for each child the node has
                {
                    int minMaxvalue = minMax(child); // assign the value to minMax value of the child
                    minimumVal = Math.min(minMaxvalue, minimumVal);// if the minimum value is less than the minMax value, don't change  
                                                            // the minimum value, else update the minimum value with minMax value
                }
                return minimumVal;  // return the minimum value as the minMax value
            }

        }

    }


}
