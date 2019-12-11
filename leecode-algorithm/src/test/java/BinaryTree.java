import com.alibaba.fastjson.JSONObject;
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
        Queue<Node> queue = new ArrayDeque<>();
        if (node != null) queue.add(node);

        while (!queue.isEmpty()) {
            Node pop = queue.remove();
            System.out.print(pop.getData() + ",");
            if (pop.getLeftNode() != null) queue.add(pop.getLeftNode());
            if (pop.getRightNode() != null) queue.add(pop.getRightNode());
        }
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class NodeWithLevel {
        private Node node;
        private int level;//层次

    }

    /**
     * 侧面观察二叉树（右侧）
     * <p>
     * 思路：层次遍历，输出每层最后一个节点即可
     * <p>
     * 如何记录每层最后一个节点：通过额外的level字段记录了入队的每个node的level层次信息。每当新的一层开始时，输出上一层最后一个节点即可
     * <p>
     * 通过比较level的变化来确认是否是新的一层
     */
    public static void youce(Node node) {
        Queue<NodeWithLevel> deque = new ArrayDeque<>();
        NodeWithLevel preNodeWithLevel = null;//记录上一次弹出的节点，用于比较level

        if (node != null) {
            NodeWithLevel nodeWithLevel = NodeWithLevel.builder().level(1).node(node).build();
            deque.add(nodeWithLevel);
        }

        while (!deque.isEmpty()) {
            NodeWithLevel nodeWithLevel = deque.remove();

            //上一次弹出的节点的level和这次弹出的不同，则说明是新的一层，此时preNodeWithLevel就是上一层的最后一个节点，输出即可。
            if (null != preNodeWithLevel && nodeWithLevel.level != preNodeWithLevel.level) {
                System.out.print(preNodeWithLevel.node.data + "   ");
            }

            preNodeWithLevel = nodeWithLevel;//记录上一次弹出的节点。每新开始一层，则打印上一层最后一个节点

            if (nodeWithLevel.node.getLeftNode() != null)
                deque.add(NodeWithLevel.builder().level(preNodeWithLevel.level + 1).node(nodeWithLevel.node.getLeftNode()).build());
            if (nodeWithLevel.node.getRightNode() != null)
                deque.add(NodeWithLevel.builder().level(preNodeWithLevel.level + 1).node(nodeWithLevel.node.getRightNode()).build());
        }

        if (null != preNodeWithLevel)//打印最后一个节点，因为上面不会打印最后的一个节点
            System.out.print(preNodeWithLevel.node.data + "   ");
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


    /**
     * 判断是否是平衡二叉树
     */
    public static int isBalancedBinaryTree(JSONObject jsonObject, Node node) {
        if (null == node) return 0;
        if (!jsonObject.getBooleanValue("isBalancedBinaryTree")) return 0;//已经判断不是了，停止计算

        int leftDeep = isBalancedBinaryTree(jsonObject, node.getLeftNode());
        int rightDeep = isBalancedBinaryTree(jsonObject, node.getRightNode());
        if (Math.abs(leftDeep - rightDeep) > 1) {
            jsonObject.put("isBalancedBinaryTree", false);
        }
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
        System.out.println("下面是层次遍历：");
        levelOrder(tree.getRoot());

        System.out.println();
        System.out.println("下面是右侧遍历：");
        youce(tree.getRoot());

        System.out.println();
        System.out.println("下面是计算深度：" + deep(tree.getRoot()));


        /**
         * 是否是平衡二叉树
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isBalancedBinaryTree", true);
        isBalancedBinaryTree(jsonObject, tree.getRoot());
        System.out.println(jsonObject.getBooleanValue("isBalancedBinaryTree") ? "是平衡二叉树" : "不是平衡二叉树");
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
