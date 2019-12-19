import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 二叉树--路径之和
 *
 * 给定一个二叉树和一个sum,找出所有从根节点到叶子节点的路径，这些路径上的所有节点的累加值为sum。
 * https://blog.csdn.net/asmartkiller/article/details/96431106
 *
 */
public class 二叉树之路径之和 {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 这里采用的是保存所有路径，其实还可以使用栈，在遍历的过程中来记录路径，节省空间
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>(); //保存所有符合条件的路径
        ArrayList<Integer> list = new ArrayList<Integer>(); //当前路径
        return SeekPath(listAll, list, root, target);
    }

    public ArrayList<ArrayList<Integer>> SeekPath(ArrayList<ArrayList<Integer>> listAll, ArrayList<Integer> list, TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) { //当前路径和是指定值且当前节点是叶子节点
            listAll.add(new ArrayList<Integer>(list));
        }
        SeekPath(listAll, list, root.left, target);
        SeekPath(listAll, list, root.right, target);
        list.remove(list.size() - 1);  //递归回到父节点时需要在路径中删除上一个节点信息
        return listAll;
    }

    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(12);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        二叉树之路径之和 s = new 二叉树之路径之和();
        System.out.println(s.FindPath(t1, 22));
    }

    @Test
    public void test2() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (Integer x : stack) {
            System.out.print(x + "   ");
        }

        System.out.println();
        for (Integer x : stack) {
            System.out.print(x + "   ");
        }
    }
}
