package NRow;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

    private Node root;

    public Tree(Board init_board){

       this.root = buildTree(null, init_board, 1);

    }

    public Node buildTree( Node parent, Board board, int playerId){

        Node node = new Node(board);

        if(board.moveLeft(board))
        {
            int currentPlayer = playerId;
            for(int i=0; i<board.width; i++)
                {
                    Board cloneBoard = new Board (board);
                    if(cloneBoard.play(i, currentPlayer))
                    {
                        int nextPlayer = (currentPlayer == 1) ? 2 : 1;
                        Node child = buildTree(node, cloneBoard,nextPlayer);
                        node.children.add(child);
                    }
                }
        }
        return node;

    }

    public void showTree()
    {
        showTree (this.root);
    }
    private void showTree(Node node)
    {
        for(Node child : node.children)
        {
            System.out.println(child.data);
        }
    }

    class Node {

        Board data;
        ArrayList<Node> children;
        
        Node(Board data){
            this.data = data;
            children = new ArrayList<>();
        }
    }



}