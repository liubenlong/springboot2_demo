import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树 相关算法
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

        public Node(int data) {
            this.data = data;
        }
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
     * <p>
     * 递归
     */
    public static int deep(Node node) {
        if (null == node) return 0;

        int leftDeep = deep(node.getLeftNode());
        int rightDeep = deep(node.getRightNode());
        return 1 + (leftDeep > rightDeep ? leftDeep : rightDeep);
    }


    @Test
    public void test1() {
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


    /**
     * 二叉搜索树中（没有重复值时） 查找比target大的最小值
     *
     * @param node
     * @param target
     */
    public Integer find1(Node node, int target) {
        if (null == node) return null;
        if (node.data <= target) {
            return find1(node.rightNode, target);//这里目的是从根节点向下找，直到找到第一个比target大的节点。
        } else {
            //找到第一个比target大的节点（记为node0）后，比target大的最小值肯定在node0的左子树内。所以查找左子树，返回值为空说明左子树没有结果，则去当前节点值
            Integer x = find1(node.leftNode, target);
            return (null == x) ? node.data : x;
        }
    }

    /**
     * 二叉搜索树中（没有重复值时） 查找比target小的最大值
     *
     * @param node
     * @param target
     */

    public Integer find2(Node node, int target) {
        if (node == null) return null;
        if (node.data >= target) {
            return find2(node.leftNode, target);
        } else {
            Integer x = find2(node.rightNode, target);
            return (x == null) ? node.data : x;
        }
    }

    @Test
    public void findtest() {
        Node node10 = new Node(10);//root
        Node node5 = new Node(5);
        node10.leftNode = node5;
        Node node18 = new Node(18);
        node10.rightNode = node18;
        Node node1 = new Node(1);
        Node node6 = new Node(6);
        Node node17 = new Node(17);
        Node node19 = new Node(19);
        Node node7 = new Node(7);
        Node node16 = new Node(16);
        node5.leftNode = node1;
        node5.rightNode = node6;
        node6.rightNode = node7;
        node18.leftNode = node17;
        node18.rightNode = node19;
        node17.leftNode = node16;

        int target = 10;
        System.out.println("比 " + target + "大的最小值是：" + find1(node10, target));
        System.out.println("比 " + target + "小的最大值是：" + find2(node10, target));
    }
}
