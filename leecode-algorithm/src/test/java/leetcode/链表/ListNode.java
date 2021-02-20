package leetcode.链表;

import lombok.Getter;
import lombok.Setter;

/**
 * @author albertliu
 * @className ListNode
 * @description TODO
 * @date 2020/12/8 15:40
 */
@Getter
@Setter
public class ListNode {
    private int val;
    private ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}
