package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author albertliu
 * @className 路径综合
 * @description
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 思路：从根节点向下，依次累加。递归
 *
 * @date 2020/12/23 14:51
 */
public class 路径总和 {
    public static void main(String[] args) {
        Node tree = buildTree();
        System.out.println(hasSum(tree, 7));
        System.out.println(hasSum(tree, 10));
        System.out.println(hasSum(tree, 17));
        System.out.println(hasSum(tree, 31));
        System.out.println(hasSum(tree, 323));


        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> current = new Stack<>();
        findPath(tree, 14, result, current);

        System.out.println(result.size());
        for (List<Integer> list : result) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    /**
     * 判断路径和是否存在
     * @param root
     * @param target 从根节点到叶子节点的和
     * @return
     */
    private static boolean hasSum(Node root, int target) {
        if (root == null) return false;

        target -= root.getValue();
        if (target == 0 && root.getLeft() == null && root.getRight() == null) return true;

        return hasSum(root.getLeft(), target) ||
                hasSum(root.getRight(), target);

    }

    /**
     * 找出路径和为target的所有路径
     * @param root
     * @param target
     * @param result
     * @param current
     */
    private static void findPath(Node root, int target,
                                 List<List<Integer>> result, Stack<Integer> current) {
        if (root == null) return;

        current.push(root.getValue());

        target -= root.getValue();
        if (root.getRight() == null && root.getLeft() == null && target == 0) {
            result.add(new ArrayList<>(current));
        }

        findPath(root.getLeft(), target, result, current);
        findPath(root.getRight(), target, result, current);

        //递归回到父节点时需要在路径中删除上一个节点信息
        current.pop();
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
