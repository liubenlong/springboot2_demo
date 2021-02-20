package leetcode.链表;

/**
 * @author albertliu
 * @className 两数之和
 * @description 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @date 2020/12/8 15:07
 */
public class 大数相加 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);

        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
//        ListNode listNode7 = new ListNode(4);
//        ListNode listNode8 = new ListNode(9);
        listNode5.setNext(listNode6);
//        listNode6.setNext(listNode7);
//        listNode7.setNext(listNode8);

        ListNode listNode = addTwoNumbers(listNode1, listNode5);
        while (listNode != null) {
            System.out.println(listNode.getVal());
            listNode = listNode.getNext();
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, index = null;
        int flag = 0;

        while (l1 != null && l2 != null) {
            int temp = l1.getVal() + l2.getVal();
            if (flag == 1) {
                temp++;
                flag = 0;
            }
            if (temp >= 10) {
                flag = 1;
                temp = temp % 10;
            }
            ListNode listNode = new ListNode(temp);
            if (head == null) {
                head = listNode;
            } else {
                index.setNext(listNode);
            }
            index = listNode;

            l1 = l1.getNext();
            l2 = l2.getNext();
        }

        if (l1 == null) {
            while (l2 != null) {
                int temp = l2.getVal();
                if (flag == 1) {
                    temp++;
                    flag = 0;
                }

                if (temp >= 10) {
                    flag = 1;
                    temp = temp % 10;
                }

                l2.setVal(temp);
                index.setNext(l2);
                index = l2;
                l2 = l2.getNext();
            }
        } else {
            while (l1 != null) {
                int temp = l1.getVal();
                if (flag == 1) {
                    temp++;
                    flag = 0;
                }

                if (temp >= 10) {
                    flag = 1;
                    temp = temp % 10;
                }

                l1.setVal(temp);
                index.setNext(l1);
                index = l1;
                l1 = l1.getNext();
            }
        }
        if (flag == 1) {
            index.setNext(new ListNode(1));
        }
        return head;
    }

}
