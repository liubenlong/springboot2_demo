package leetcode.链表;

import lombok.Getter;
import lombok.Setter;

/**
 * 这里不能使用lombok的@Data，否则会出现栈溢出。
 */
@Getter
@Setter
class ListNodeRandom {
    private int val;
    private ListNodeRandom next;
    private ListNodeRandom random;

    public ListNodeRandom() {
    }

    public ListNodeRandom(int _val, ListNodeRandom _next, ListNodeRandom _random) {
        val = _val;
        next = _next;
        random = _random;
    }

    public ListNodeRandom(int val) {
        this.val = val;
    }

};