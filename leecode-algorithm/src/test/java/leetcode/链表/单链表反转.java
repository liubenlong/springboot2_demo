package leetcode.链表;

/**
 * @author albertliu
 * @className 单链表反转
 * @description TODO
 * @date 2020/12/10 11:41
 */
public class 单链表反转 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(5);
        ListNode listNode4 = new ListNode(7);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);

        ListNode listNode = reserve(listNode1);
        while (listNode != null) {
            System.out.print(listNode.getVal() + "->");
            listNode = listNode.getNext();
        }
    }

    private static ListNode reserve(ListNode listNode) {
        if (null == listNode) return null;
        ListNode c = listNode;
        ListNode n = c.getNext();
        ListNode nn = null;
        if (n != null) {
            nn = n.getNext();
        }

        while (n != null) {
            if (c == listNode) c.setNext(null);
            n.setNext(c);

            c = n;
            n = nn;
            if (nn != null) nn = nn.getNext();
        }

        return c;
    }
}
