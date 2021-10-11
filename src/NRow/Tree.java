package NRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree class used for building a tree
 */

public class Tree {

    public Node root;  // root Node of the Tree


    /**
     * Constructor for building the tree
     * @param init_board initial board
     * @param gameN     gameN attribute of the game
     * @param playerId  player's type, 1 or 2
     * @param depth     maximum depth of tree to be built
     */
    public Tree(Board init_board, int gameN, int playerId, int depth) {

        this.root = buildTree(init_board, playerId, gameN, depth);
    }


    /**
     * Method to build the game tree recursively with defined depth.
     * @param board
     * @param playerId
     * @param gameN
     * @param depth
     * @return a Node or only a Leaf. If the game is not over or the depth is not reached
     * then return a Node, a tree again, else return a Leaf
     */
    public Node buildTree(Board board, int playerId, int gameN, int depth) {

        Node node;
        int winner = Game.winning(board.getBoardState(), gameN);  //gets the result of a boardstate
        boolean isOver = winner != 0; // if the result is not 0, then the game is over
        node = new Node(board, playerId); // creates a new node for tree
        if (!(isOver || depth==0)) // If game is not over or the depth is not reached 
                                  //then it is a Node and the tree must continue to grow
        {
            for (int i = 0; i < board.width; i++) { // for every possible move
                Board cloneBoard = new Board(board);  // create a clone Board
                if (cloneBoard.play(i, playerId)) { // and make the move if it is possible
                    int nextPlayer = (playerId == 1) ? 2 : 1; 
                    Node child = buildTree(cloneBoard, nextPlayer, gameN, (depth-1)); // create the child node
                    node.children.add(child);   // with clone board and next player and decreased depth
                                               // and add it to the children of node    
                }
            }

        } 
        // if the game is over or the depth is reached it is a leaf it only returns the node itself.
        return node; // if it is not it recursively continue to build the tree and returns the node with children.
    }

    /**
     * Inner class of Node
     */
    public class Node {

        public Board board; // saves the board state
        public List<Node> children; // saves the children of a node, if it is a leaf then it is empty
        public int player; // saves the playerID 1 or 2
        /**
         * Constructor method for Node
         * @param board the board state to save
         * @param player the playerID to save
         */
        Node(Board board, int player) {
            this.board = board;
            this.player = player;
            children = new ArrayList<>();
        }
    }

}
