package leetcode.tree;

import java.util.Stack;

/**
 * @author albertliu
 * @className 二叉搜索树迭代器
 * @description https://blog.csdn.net/qq_40163148/article/details/89947235
 * <p>
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 * 示例：
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 * 提示：
 * <p>
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * <p>
 * 思路：由于只能用 O(h) 的内存，所以不能把整棵树的先序遍历存下来，只能存部分指针，
 * 我们把树的根到树的最左结点的路径压入到一个栈中，每次从栈顶取一个指针，
 * 把指针的右子树的根到右子树的最左结点压入到栈中，然后返回指针指向的值，
 * 这样，最多就值需要保存O(h) 个结点，满足题目要求
 * @date 2020/12/21 15:02
 */
public class 二叉搜索树迭代器 {


    private static Node node = null;
    private static Stack<Node> stack = new Stack();

    public static void main(String[] args) {
        node = buildTree();

        iterator();

        while (hasNext()) {
            System.out.println(next());
        }
    }

    /**
     * 初始化
     */
    private static void iterator() {
        Node left = node;
        while (left != null) {
            stack.push(left);
            left = left.getLeft();
        }
    }

    /**
     * 二叉搜索树迭代器。
     * 前提：树是二叉搜索树
     * <p>
     * 思路：现初始化：将根节点的所有左子树入栈；
     * 然后，依次出栈，如果出栈的节点有右子树，那么将右子树的所有左节点入栈
     */
    private static int next() {
        Node n = stack.pop();
        if (n.getRight() != null) {
            Node left = n.getRight();
            while (left != null) {
                stack.push(left);
                left = left.getLeft();
            }
        }

        return n.getValue();
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
