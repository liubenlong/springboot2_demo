package tree;

import lombok.Data;

@Data
public class Node {
    private Node left;
    private Node right;
    private int value;

    public Node(int value){
        this.value=value;
    }
}
