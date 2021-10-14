package NRow.Players;

import NRow.Board;
import NRow.Tree;
import NRow.Heuristics.Heuristic;
import java.util.Scanner;
import NRow.Tree.*;

/**
 * Player class for AlphaBetaPlayer
 */

public class AlphaBetaPlayer extends PlayerController {
    
    Scanner scanner = new Scanner(System.in);
    private int depth;
    final int Player_MAX = 1; //the maximizing player X
    final int Player_MIN = 2; // the minimizing player O
    final int Alpha_init = Integer.MIN_VALUE; // initial value of alpha
    final int Beta_init = Integer.MAX_VALUE; // initial value of beta

    /**
     * Constructor method for AlphaBetaPlayer
     * @param playerId   playerID 1 or 2
     * @param gameN      gameN of the game
     * @param depth      depth of the tree to be built
     * @param heuristic  heuristics to be used for evaluation
     */

    public AlphaBetaPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
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
                    int minMaxvalue = minMax_Alpha_Beta(gameTree,Alpha_init, Beta_init); //evaluate that new board to get a heuristic value from it by using minMax_Alpha_Beta algorithm
                    if(minMaxvalue > maxValue) // if minMax returns a higher value then the other column option does
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
     * public method of minMax algorithm with pruning. It is used for the game trees itself and called once.
     * @param tree the tree to be applied minMax
     * @param alpha the initial alpha value which is negative infinity
     * @param beta the initial beta value which is positive infinity
     * @return the miniMax value of the tree itself
     */
    public int minMax_Alpha_Beta(Tree tree, int alpha, int beta)
    {
        return (minMax_Alpha_Beta(tree.root, alpha, beta));
    }

    /**
     * private method of minMax algorithm with pruning. It is used for the evaluation of nodes and leafs. It is called recursively.
     * @param node node to be evaluated and/or applied minMax
     * @param alpha the alpha value
     * @param beta the beta value
     * @return the evaluated value of a leaf or the minMax value of a node
     */
    private int minMax_Alpha_Beta(Node node,int alpha, int beta) {
        if (node.children.isEmpty()) // it is Leaf or depth is reached
        {
            return heuristic.evaluateBoard(node.player, node.board); // return the evaluated value
        } else { // If it has child(ren) then it must be a node

            if (node.player == Player_MAX) // the playerID of the node is equal to maximizing player, 1 
            {
                int maximumVal = Integer.MIN_VALUE; // initialize the maximum value as negative infinity
                for (Node child : node.children) // for each child the node has
                {
                    int minMaxvalue = minMax_Alpha_Beta(child, alpha, beta); // assign the value to minMax value of the child
                    maximumVal = Math.max(minMaxvalue, maximumVal); // if the maximum value is bigger than the minMax value, don't change  
                                                             // the maximum value, else update the maximum value with minMax value
                    alpha = Math.max(minMaxvalue, alpha);  // if the alpha is bigger than the minMax value, don't change it
                                                    // if it is not, the minMax value is bigger, then update alpha with the minMax value
                    if(beta <= maximumVal) { // if beta value is less or equal to maximum value
                        break;          // prune the next branches
                    }
                    alpha = Math.max(maximumVal, alpha);  // if the alpha is bigger than the minMax value, don't change it
                                                    // if it is not, the minMax value is bigger, then update alpha with the minMax value
                }
                return maximumVal; // return the maximum value as the minMax value

                
            } 
            else // the playerID of the node is equal to maximizing player, 2
            {
                int minimumVal = Integer.MAX_VALUE; // initialize the minimum value as positive infinity
                for (Node child : node.children) // for each child the node has
                {
                    int minMaxvalue = minMax_Alpha_Beta(child,alpha,beta); // assign the value to minMax value of the child
                    minimumVal = Math.min(minMaxvalue, minimumVal);// if the minimum value is less than the minMax value, don't change  
                                                            // the minimum value, else update the minimum value with minMax value
                    
                    if(minimumVal<= alpha){// if minimum value value is less or equal to alpha
                        break;       // prune the next branches
                    }
                    beta = Math.min(minimumVal, beta);  //if the beta value is less than the minMax value, don't change it
                                                   // if it is not, the minMax value is less, then update beta with the minMax value
                }
                return minimumVal;// return the minimum value as the minMax value
            }

        }

    }


}
