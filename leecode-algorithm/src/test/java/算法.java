import lombok.Data;
import org.junit.Test;

import java.util.*;

public class 算法 {

    /**
     * 示例：
     * 给出数组：{1,3,5,7,2,6,9,8} 【数组中无重复值】  和 目标值6
     * 求数组中两者相差值为6的index。
     * <p>
     * 如index=0与index=3的差是6.
     * 如index=1与index=6的差是6.
     * <p>
     * <p>
     * 要求时间复杂度O(n)
     * O(n)是考察重点。空间换取时间
     */
    public static List<Integer[]> getChongfu(int[] datas, int target) {
        Map<Integer, Integer> map = new HashMap<>();//<差值，index>
        for (int i = 0; i < datas.length; i++) {
            map.put(datas[i], i);
        }

        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            int sum = target + datas[i];
            if (map.get(sum) != null) {
                Integer[] result = new Integer[]{i, map.get(sum)};
                list.add(result);
            }
        }

        return list;
    }

    @Test
    public void test() {
        int[] datas = {1, 3, 5, 7, 2, 6, 9, 8};//没有重复值

        List<Integer[]> list = getChongfu(datas, 6);
        for (Integer[] integers : list) {
            System.out.println(Arrays.toString(integers));
        }
    }


    @Data
    class Node {
        private Node left;
        private Node right;
        private int data;

        public Node(int data) {
            this.data = data;
        }
    }




    /**
     * 两个有序链表合并
     */
    @Test
    public void test1() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode4;

        ListNode listNode11 = new ListNode(1);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode14 = new ListNode(4);
        ListNode listNode15 = new ListNode(5);
        listNode11.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;

        ListNode head = mergeTwoLists(listNode1, listNode11);
        while (head != null) {
            System.out.print(head.val + "   ");
            head = head.next;
        }
    }

    /**
     * Definition for singly-linked list
     */
    class ListNode {
        int val;//数据内容
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) return l2;
        if (null == l2) return l1;

        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }


    @Test
    public void test2() {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode14 = new ListNode(4);
        ListNode listNode15 = new ListNode(5);
        listNode11.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;

        print(listNode11);
    }

    private void print(ListNode node) {
        if (node.next != null) {
            print(node.next);
        }
        System.out.println(node.val);
    }


    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
     * 重复的结点不保留，返回链表头指针。(认真审题)
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * <p>
     * 从头遍历整个链表 如果当前节点pNode的值与下一个节点相同
     * 那么他们就是重复的节点  需要被删除
     * 为了保证删除之后的链表仍然是相连的
     * 需要把当前节点的前一个节点pPreNode保存起来
     * 循环的过程中确保pPreNode始终与下一个没有重复的节点连接在一起
     */
    @Test
    public void test3() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(4);
        ListNode listNode6 = new ListNode(4);
        ListNode listNode7 = new ListNode(4);
        ListNode listNode8 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode6.next = listNode7;
        listNode7.next = listNode8;

        ListNode head = deleteDuplication(listNode1);
        while (head != null) {
            System.out.print(head.val + "   ");
            head = head.next;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode pPreNode = null;
        ListNode pNode = pHead;
        while (pNode != null) {
            ListNode pNext = pNode.next;
            boolean needDelete = false;
            if (pNext != null && pNext.val == pNode.val)
                needDelete = true;
            if (!needDelete) {
                pPreNode = pNode;
                pNode = pNode.next;
            } else {
                ListNode pDeleteNode = pNode;
                int value = pDeleteNode.val;
                while (pDeleteNode != null && pDeleteNode.val == value) {
                    pNext = pDeleteNode.next;
                    pDeleteNode = pNext;
                }
                if (null== pPreNode )
                    pHead = pNext;
                else
                    pPreNode.next = pNext;
                pNode = pNext;
            }
        }

        return pHead;
    }




}
