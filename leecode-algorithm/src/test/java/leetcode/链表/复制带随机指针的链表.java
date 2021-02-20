package leetcode.链表;

import java.util.HashMap;
import java.util.Map;

/**
 * @author albertliu
 * @className 复制带随机指针的链表
 * @description
 * https://blog.csdn.net/weixin_40673608/article/details/86762227
 * https://zhuanlan.zhihu.com/p/100388274
 * @date 2020/12/30 15:53
 */
public class 复制带随机指针的链表 {
    public static void main(String[] args) {
        ListNodeRandom oldList = buildList();
        ListNodeRandom newList = copy(oldList);
        ListNodeRandom newList1 = copy1(oldList);

        System.out.println();
    }

    /**
     * 思路：
     * 1->2
     * 升级为：
     * 1->1'->2->2'
     * 然后处理完随机指针后，再将链表拆分
     * @param oldList
     * @return
     */
    private static ListNodeRandom copy1(ListNodeRandom oldList) {
        ListNodeRandom oldIndex = oldList;

        while (oldIndex != null) {
            ListNodeRandom node = new ListNodeRandom(oldIndex.getVal());
            node.setNext(oldIndex.getNext());
            oldIndex.setNext(node);
            oldIndex = node.getNext();
        }

        oldIndex = oldList;
        while (oldIndex != null) {
            if (oldIndex.getRandom() != null) {
                oldIndex.getNext().setRandom(oldIndex.getRandom().getNext());
            }
            oldIndex = oldIndex.getNext().getNext();
        }

        //链表拆分
        oldIndex = oldList;
        ListNodeRandom newList = oldList.getNext();
        ListNodeRandom newIndex = newList;
        while (oldIndex != null) {
            oldIndex.setNext(newIndex.getNext());
            oldIndex = newIndex.getNext();
            if (oldIndex != null) {
                newIndex.setNext(oldIndex.getNext());
                newIndex = oldIndex.getNext();
            }
        }

        return newList;
    }

    /**
     * 思路：第一次遍历使用一个map记录新旧链表所有节点的对应关系。然后第二次遍历处理随机指针
     * @param oldList
     * @return
     */
    private static ListNodeRandom copy(ListNodeRandom oldList){
        ListNodeRandom newList = null;

        //用于保存<老链表节点，新链表节点>的对应关系
        Map<ListNodeRandom, ListNodeRandom> map = new HashMap();

        ListNodeRandom oldIndex = oldList;
        ListNodeRandom newPreNode = null;//新链表前一个节点，用于下面循环将新链表串联起来

        while (oldIndex != null) {
            ListNodeRandom newNode = new ListNodeRandom(oldIndex.getVal());
            if (newPreNode != null) {
                newPreNode.setNext(newNode);
            } else {//设置新链表头结点
                newList = newNode;
            }
            newPreNode = newNode;

            ListNodeRandom oldNode = oldIndex;//这里用新的指针应用节点，因为oldIndex会变化
            map.put(oldNode, newNode);

            oldIndex = oldIndex.getNext();
        }

        //第二次遍历，添加随机指针
        oldIndex = oldList;
        ListNodeRandom newIndex = newList;
        while (oldIndex != null) {
            if (oldIndex.getRandom() != null) {
                //关键点：通过map来设置随机指针
                newIndex.setRandom(map.get(oldIndex.getRandom()));
            }

            newIndex = newIndex.getNext();
            oldIndex = oldIndex.getNext();
        }

        return newList;
    }

    private static ListNodeRandom buildList() {
        ListNodeRandom listNode1 = new ListNodeRandom(1);
        ListNodeRandom listNode2 = new ListNodeRandom(2);
        ListNodeRandom listNode3 = new ListNodeRandom(3);
        ListNodeRandom listNode4 = new ListNodeRandom(4);
        ListNodeRandom listNode5 = new ListNodeRandom(5);
        ListNodeRandom listNode6 = new ListNodeRandom(6);

        listNode1.setNext(listNode2);
//        listNode2.setNext(listNode3);
//        listNode3.setNext(listNode4);
//        listNode4.setNext(listNode5);
//        listNode5.setNext(listNode6);

        listNode2.setRandom(listNode1);
//        listNode3.setRandom(listNode6);
//        listNode6.setRandom(listNode2);

        return listNode1;
    }


}
