package leetcode.tree;

/**
 * @author albertliu
 * @className 最近公共祖先
 * @description
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * @date 2020/12/29 20:34
 */
public class 最近公共祖先 {
    public static void main(String[] args) {
        Node tree = buildTree();

        System.out.println(lowestCommonAncestor(tree, 3, 15).getValue());
        System.out.println(lowestCommonAncestor(tree, 3, 1).getValue());
        System.out.println(lowestCommonAncestor(tree, 3, 4).getValue());
        System.out.println(lowestCommonAncestor(tree, 3, 7).getValue());
        System.out.println(lowestCommonAncestor(tree, 3, 9).getValue());
        System.out.println(lowestCommonAncestor(tree, 15, 20).getValue());
        System.out.println(lowestCommonAncestor(tree, 9, 20).getValue());
    }

    /**
     * 思路：
     * 1、在左、右子树中分别查找是否包含p或q，如果（两种情况：左子树包含p，右子树包含q/左子树包含q，右子树包含p），
     *   那么此时的根节点就是最近公共祖先
     * 2、如果左子树包含p和q，那么到root->left中查找，最近公共祖先在左子树里面
     * 3、如果右子树包含p和q，那么到root->right中查找，最近公共祖先在右子树里面
     * 4、注意：不可能left和right的返回值同时都是nullptr
     */
    private static Node lowestCommonAncestor(Node root, int m, int n) {
        if (null == root || root.getValue() == m || root.getValue() == n) {
            return root;
        }

        Node left = lowestCommonAncestor(root.getLeft(), m, n);
        Node right = lowestCommonAncestor(root.getRight(), m, n);

        if (left != null && right != null) {
            return root;
        } else if (left == null && right != null) {
            //m和n两个都在右子树，那么返回右子树的结果。
            return right;
        } else {
            return left;
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

        node1.setLeft(node6);

        return node7;
    }
}
