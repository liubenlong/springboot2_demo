package leetcode.tree;

import leetcode.链表.ListNode;

/**
 * @author albertliu
 * @className 有序链表转二叉搜索树
 * @description
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 思路：
 * 将链表一分为二，取中间元素作为根结点，对左边的链表和右边的链表递归调用转二叉搜索树得到2个子树p1和p2
 * 将根节点的左指针指向p1,右指针指向p2
 *
 * https://blog.csdn.net/weixin_40673608/article/details/86762206
 *
 * @date 2020/12/30 14:18
 */
public class 有序链表转二叉搜索树 {

    public static void main(String[] args) {
        ListNode listNode = buildList();

//        方法一：直接链表二分法
        二叉树层次遍历.printcengci(list2tree(listNode, null));

        //方法二：list先转数组，更方便
        int[] nums = list2array(listNode);
        二叉树层次遍历.printcengci(list2tree1(nums, 0, nums.length - 1));
    }

    private static int[] list2array(ListNode listNode) {
        ListNode node = listNode;
        int count = 0;
        while (node != null) {
            count++;
            node = node.getNext();
        }
        int[] nums = new int[count];
        node = listNode;
        count = 0;
        while (node != null) {
            nums[count] = node.getVal();
            node = node.getNext();
            count++;
        }
        return nums;
    }

    private static Node list2tree1(int[] nums, int begin, int end) {
        if (begin < 0 || end > nums.length || begin > end) return null;
        int middle = (end - begin + 1) / 2 + begin;
        Node node = new Node(nums[middle]);
        node.setLeft(list2tree1(nums, begin, middle - 1));
        node.setRight(list2tree1(nums, middle + 1, end));
        return node;
    }

    private static Node list2tree(ListNode begin, ListNode end) {
        if (begin == null || begin == end) return null;
        ListNode slow = begin;
        ListNode fast = begin.getNext();

        while (fast != null && fast != end) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (fast != null && fast != end) {
                fast = fast.getNext();
            }
        }

        Node node = new Node(slow.getVal());
        node.setLeft(list2tree(begin, slow));
        node.setRight(list2tree(slow.getNext(), end));
        return node;
    }

    private static ListNode buildList() {
        ListNode listNode1 = new ListNode(-10);
        ListNode listNode2 = new ListNode(-3);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(10);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);
        listNode4.setNext(listNode5);
        listNode5.setNext(listNode6);

        return listNode1;
    }

}
