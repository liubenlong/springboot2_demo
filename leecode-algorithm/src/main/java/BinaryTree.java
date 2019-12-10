import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树
 */
@Data
public class BinaryTree {

    /**
     * 根节点
     */
    private Node root;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Node {
        private Integer data;
        private Node leftNode;
        private Node rightNode;
    }

    public void insert(Integer data) {
        root = insert(root, data);
    }

    /**
     * 构造二叉排序树（又叫二叉搜索树，二叉查找树）
     *
     * @param node
     * @param data
     * @return
     */
    public Node insert(Node node, Integer data) {
        if (node == null) node = Node.builder().data(data).build();
        else {
            if (data > node.getData()) node.setRightNode(insert(node.getRightNode(), data));
            else node.setLeftNode(insert(node.getLeftNode(), data));
        }
        return node;
    }


    /**
     * 先序遍历-递归
     */
    public static void preOrderTraverse(Node node) {
        if (node != null) {
            System.out.print(node.getData() + ",");
            preOrderTraverse(node.getLeftNode());
            preOrderTraverse(node.getRightNode());
        }
    }

    /**
     * 中序遍历-递归
     */
    public static void inOrderTraverse(Node node) {
        if (node != null) {
            inOrderTraverse(node.getLeftNode());
            System.out.print(node.getData() + ",");
            inOrderTraverse(node.getRightNode());
        }
    }

    /**
     * 后序遍历-递归
     */
    public static void afterOrderTraverse(Node node) {
        if (node != null) {
            afterOrderTraverse(node.getLeftNode());
            afterOrderTraverse(node.getRightNode());
            System.out.print(node.getData() + ",");
        }
    }


    /**
     * 先序遍历 - 非递归
     * 思路和递归一样，只是这里将递归使用的 栈 显示的记录而已
     */
    public static void preOrderTraverse1(Node node) {
        Stack<Node> stack = new Stack<>();
        if (null != node) stack.push(node);//添加根节点

        while (!stack.empty()) {
            Node pop = stack.pop();
            System.out.print(pop.getData() + ",");
            //先序 是  跟左右，  所以这里先把右孩子入栈，因为栈是后入先出
            if (null != pop.getRightNode()) stack.push(pop.getRightNode());
            if (null != pop.getLeftNode()) stack.push(pop.getLeftNode());
        }
    }

    /**
     * 中序遍历 - 非递归
     * 思路和递归一样，只是这里将递归使用的栈显示的记录而已
     */
    public static void inOrderTraverse1(Node node) {
        Stack<Node> stack = new Stack<>();
        if (null != node) stack.push(node);//添加根节点

        while (!stack.empty()) {
            while (node.getLeftNode() != null) {
                stack.push(node.getLeftNode());
                node = node.getLeftNode();
            }
            Node pop = stack.pop();
            System.out.print(pop.getData() + ",");
            if (pop.getRightNode() != null) {
                stack.push(pop.getRightNode());
                node = pop.getRightNode();
            }
        }
    }


    /**
     * 层次遍历
     */
    public static void levelOrder(Node node) {
        Queue<Node> deque = new ArrayDeque<>();
        if (node != null) deque.add(node);

        while (!deque.isEmpty()) {
            Node pop = deque.remove();
            System.out.print(pop.getData() + ",");
            if (pop.getLeftNode() != null) deque.add(pop.getLeftNode());
            if (pop.getRightNode() != null) deque.add(pop.getRightNode());
        }
    }


    /**
     * 计算深度
     */
    public static int deep(Node node) {
        if (node != null) {
            int i, j;
            return 1 + ((i = deep(node.getLeftNode())) > (j = deep(node.getRightNode())) ? i : j);
        } else {
            return 0;
        }
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(3);
        tree.insert(6);
        tree.insert(1);
        tree.insert(2);
        tree.insert(9);
        tree.insert(8);
        tree.insert(10);

        preOrderTraverse(tree.getRoot());
        System.out.println();
        inOrderTraverse(tree.getRoot());
        System.out.println();
        afterOrderTraverse(tree.getRoot());

        System.out.println("\n--下面是非递归--");

        preOrderTraverse1(tree.getRoot());
        System.out.println();
        inOrderTraverse1(tree.getRoot());


        System.out.println();
        levelOrder(tree.getRoot());

        System.out.println("\ndeep=" + deep(tree.getRoot()));
    }

}
