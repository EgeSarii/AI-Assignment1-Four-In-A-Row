package NRow;

import NRow.Heuristics.Heuristic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {

    public Node root;

    public Tree(Board init_board, int gameN, int playerId, int depth,Heuristic heuristic) {

        this.root = buildTree(null, init_board, playerId, gameN, depth, heuristic);
    }

    public Node buildTree(Node parent, Board board, int playerId, int gameN, int depth, Heuristic heuristic) {

        Node node;
        int winner = Game.winning(board.getBoardState(), gameN);
        boolean isOver = winner != 0;
        int value = heuristic.evaluateBoard(playerId, board);
        node = new Node(board, playerId, value);
        if (!(isOver || depth==0)) // If game is over then it is a leaf or depth is reached
        {
            for (int i = 0; i < board.width; i++) {

                Board cloneBoard = new Board(board);    
                if (cloneBoard.play(i, playerId)) {
                    int nextPlayer = (playerId == 1) ? 2 : 1;
                    Node child = buildTree(node, cloneBoard, nextPlayer, gameN, (depth-1), heuristic);
                    node.children.add(child);
                }
            }

        } 
        
        return node;

    }

    public void showTree() {
        showTree(this.root);
    }

    private void showTree(Node node) {
        System.out.println(node.board);
        System.out.println(node.value);
        for (Node child : node.children) {
            showTree(child);
        }
    }

    

    public class Node {

        Board board;
        public List<Node> children;
        public int player;
        public int value;

        Node(Board board, int player, int value) {
            this.board = board;
            this.player = player;
            this.value = value;
            children = new ArrayList<>();
        }
    }

}
