package leetcode.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author albertliu
 * @className Node
 * @description TODO
 * @date 2020/12/21 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }
}
