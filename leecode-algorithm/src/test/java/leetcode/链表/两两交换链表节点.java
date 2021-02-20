package leetcode.链表;

/**
 * @author albertliu
 * @className 两两交换链表节点
 * @description 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 说明:
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * https://blog.csdn.net/qq_17550379/article/details/80675453
 * @date 2020/12/29 20:53
 */
public class 两两交换链表节点 {

    public static void main(String[] args) {
        ListNode listNode = buildList();

        print(listNode);

        //4个指针，循环遍历交换
        ListNode pre = null, c = listNode, n/*next*/ = c.getNext(), nn = null;
        if (n != null) nn = n.getNext();

        ListNode newHead = n;
        while (c != null) {
            n.setNext(c);
            c.setNext(nn);
            if (pre != null) pre.setNext(n);

            pre = c;
            c = nn;
            if (c != null) n = c.getNext();
            if (n != null) nn = n.getNext();
        }

        print(newHead);
    }

    private static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.getVal() + " ");
            listNode = listNode.getNext();
        }
        System.out.println();
    }

    private static ListNode buildList() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);
        listNode4.setNext(listNode5);
        listNode5.setNext(listNode6);

        return listNode1;
    }


}
