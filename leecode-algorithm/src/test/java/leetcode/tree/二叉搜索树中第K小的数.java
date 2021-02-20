package leetcode.tree;

import java.util.Stack;

/**
 * @author albertliu
 * @className 二叉搜索树中第K小的数
 * @description 原理类似于二叉搜索树迭代器  https://blog.csdn.net/weixin_40673608/article/details/86603601
 * @date 2020/12/29 19:29
 */
public class 二叉搜索树中第K小的数 {

    private static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) {
        int n = 6;
        Node head = buildTree();

        Node node = head;
        while (null != node) {
            stack.push(node);
            node = node.getLeft();
        }

        int count = 0;//计数器
        while (hasNext()) {
            Node next = next();
            if (++count == n) {
                System.out.print(next.getValue() + " ");
                break;
            }
        }
    }

    private static Node next() {
        Node node = stack.pop();
        Node right = node.getRight();
        if (null != right) {
            stack.push(right);
            Node left = right.getLeft();
            while (null != left) {
                stack.push(left);
                left = left.getLeft();
            }
        }
        return node;
    }

    private static boolean hasNext() {
        return !stack.empty();
    }

    private static Node buildTree() {
        Node node7 = new Node(7);
        Node node3 = new Node(3);
        Node node1 = new Node(1);
        Node node4 = new Node(4);
        Node node6 = new Node(6);
        Node node15 = new Node(15);
        Node node9 = new Node(9);
        Node node20 = new Node(20);

        node7.setLeft(node3);
        node7.setRight(node15);

        node3.setLeft(node1);
        node3.setRight(node4);

        node4.setRight(node6);

        node15.setLeft(node9);
        node15.setRight(node20);

        return node7;
    }


}
