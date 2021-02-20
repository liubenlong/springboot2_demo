package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author albertliu
 * @className 深度
 * @description TODO
 * @date 2020/12/22 14:33
 */
public class 深度_树高 {

    public static void main(String[] args) {
        //最大深度，也就是树高
        System.out.println(maxShenDu(buildTree()));
        System.out.println(maxShenDu(buildTree1()));
        System.out.println(maxShenDu(buildTree2()));

        System.out.println("------------");

        System.out.println(minShenDu(buildTree()));
        System.out.println(minShenDu(buildTree1()));
        System.out.println(minShenDu(buildTree2()));
    }

    private static int maxShenDu(Node node) {
        if (null == node) return 0;
        return Math.max(maxShenDu(node.getLeft()), maxShenDu(node.getRight())) + 1;
    }

    private static int minShenDu(Node node) {
        if (null == node) return 0;

        int left = minShenDu(node.getLeft());
        int right = minShenDu(node.getRight());

        if (left == 0)
            return right + 1;
        if (right == 0)
            return left + 1;

        return Math.min(left, right) + 1;
    }

    private static Node buildTree2() {
        Node node7 = new Node(7);
        return node7;
    }

    private static Node buildTree1() {
        Node node7 = new Node(7);
        Node node3 = new Node(3);

        node7.setLeft(node3);

        return node7;
    }

    private static Node buildTree() {
        Node node7 = new Node(7);
        Node node3 = new Node(3);
        Node node1 = new Node(1);
        Node node4 = new Node(4);
        Node node15 = new Node(15);
        Node node9 = new Node(9);
        Node node20 = new Node(20);
        Node node6 = new Node(6);

        node7.setLeft(node3);
        node7.setRight(node15);

        node3.setLeft(node1);
        node3.setRight(node4);

//        node15.setLeft(node9);
//        node15.setRight(node20);

        node1.setLeft(node6);

        return node7;
    }

}
