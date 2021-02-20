package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author albertliu
 * @className 二叉搜索树迭代器
 * @description TODO
 * @date 2020/12/21 15:02
 */
public class 二叉树层次遍历 {

    public static void main(String[] args) {
        Node node = buildTree();

        printcengci(node);
        System.out.println();
    }


    /**
     * 层次遍历--双队列法
     *
     * @param node
     */
    public static void printcengci(Node node) {
        if (null == node) return;
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();

        queue1.add(node);

        digui(queue1, queue2);

        System.out.println();
    }

    private static void digui(Queue<Node> queue1, Queue<Node> queue2) {
        while (!queue1.isEmpty()) {
            Node node = queue1.poll();
            System.out.print(node.getValue()+" ");

            if (null != node.getLeft()) queue2.add(node.getLeft());
            if (null != node.getRight()) queue2.add(node.getRight());
        }
        if (!queue2.isEmpty()) digui(queue2, queue1);
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

        node15.setLeft(node9);
        node15.setRight(node20);

        node1.setLeft(node6);

        return node7;
    }

}
