package NRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tree {

    private Node root;

    public Tree(Board init_board, int gameN, int playerId) {

        this.root = buildTree(null, init_board, playerId, gameN);
    }

    public Node buildTree(Node parent, Board board, int playerId, int gameN) {

        Node node;
        int winner = Game.winning(board.getBoardState(), gameN);
        boolean isOver = winner != 0;

        if (!isOver) // Game is not over Node is not a Leaf
        {
            node = new Node(board, playerId, -2);

            for (int i = 0; i < board.width; i++) {

                Board cloneBoard = new Board(board);
                if (cloneBoard.play(i, playerId)) {
                    int nextPlayer = (playerId == 1) ? 2 : 1;
                    Node child = buildTree(node, cloneBoard, nextPlayer, gameN);
                    node.children.add(child);

                }
            }
        } else // Game is over, Node is a Leaf
        {
            node = new Node(board, playerId, winner);
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

    public int miniMax()
    {
        return (miniMax(this.root));
    }

    private int miniMax(Node node) {
        ArrayList<Integer> valuesOfChildren = new ArrayList<>();
        int rival = (node.player == 1) ? 2 : 1;
        if (node.value != -2) // it is Leaf
        {
            if (node.value == node.player) // if miniMax player wins return 1
                return 1;
            else if (node.value == rival) // if human player wins return -1
                return -1;
            else // if it is a draw return 0
                return 0;
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

    class Node {

        Board board;
        ArrayList<Node> children;
        int player;
        int value;

        Node(Board board, int player, int value) {
            this.board = board;
            this.player = player;
            this.value = value;
            children = new ArrayList<>();
        }
    }

}