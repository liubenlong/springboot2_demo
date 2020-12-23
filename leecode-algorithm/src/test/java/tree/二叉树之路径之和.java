package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树--路径之和
 * <p>
 * 给定一个二叉树和一个sum, 找出所有从根节点到叶子节点的路径，这些路径上的所有节点的累加值为sum。
 * https://blog.csdn.net/asmartkiller/article/details/96431106
 */
public class 二叉树之路径之和 {

    @Test
    public void test1() {
        Node t1 = new Node(10);
        Node t2 = new Node(5);
        Node t3 = new Node(12);
        Node t4 = new Node(4);
        Node t5 = new Node(7);

        t1.setLeft(t2);
        t1.setRight(t3);
        t2.setLeft(t4);
        t2.setRight(t5);

        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> current = new Stack<>();

        System.out.println(findPathcunzai(t1, 10));
        System.out.println(findPathcunzai(t1, 28));
        System.out.println(findPathcunzai(t1, 5));
        System.out.println(findPathcunzai(t1, 19));
        System.out.println(findPathcunzai(t1, 22));

        findPath(t1, 22, result, current);

        for (List<Integer> list : result) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    /**
     * 判断是否存在
     * @param root
     * @param target
     * @return
     */
    private static boolean findPathcunzai(Node root, int target) {
        if (root == null) return false;

        target -= root.getValue();
        if (target == 0 && root.getLeft() == null && root.getRight() == null) return true;

        return findPathcunzai(root.getLeft(), target) ||
                findPathcunzai(root.getRight(), target);

    }

    /**
     * 找出所有路径
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

        //递归回到父节点时需要在路径中删除上一个节点信息**
        current.pop();
    }
}
