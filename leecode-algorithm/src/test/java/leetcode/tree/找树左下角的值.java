package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author albertliu
 * @className 找树左下角的值
 * @description 思路：借助层次遍历
 * @date 2020/12/29 17:43
 */
public class 找树左下角的值 {
    public static void main(String[] args) {
        zuoxiajiao(buildTree());
        System.out.println();
    }

    private static void zuoxiajiao(Node head) {
        if (null == head) return;

        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();

        queue1.add(head);

        Node lastLeft = digui(queue1, queue2);

        System.out.println();
        System.out.println(lastLeft.getValue());
    }

    private static Node digui(Queue<Node> queue1, Queue<Node> queue2) {
        Node lastLeft = null;
        boolean flag = false;
        while (!queue1.isEmpty()) {
            Node node = queue1.poll();
            System.out.print(node.getValue() + " ");
            if (!flag) lastLeft = node;
            flag = true;

            if (null != node.getLeft()) queue2.add(node.getLeft());
            if (null != node.getRight()) queue2.add(node.getRight());
        }

        if (!queue2.isEmpty()) {
            return digui(queue2, queue1);
        } else {
            //当queue2为空时，说明刚刚输出的是最后一行了，把lastLeft即可
            return lastLeft;
        }
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

        node4.setLeft(node6);

        return node7;
    }
}
