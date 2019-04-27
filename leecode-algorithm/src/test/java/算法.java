import lombok.Data;
import org.junit.Test;

import java.util.*;

public class 算法 {

    /**
     * 示例：
     * 给出数组：{1,3,5,7,2,6,9,8} 【数组中无重复值】  和 目标值6
     * 求数组中两者相差值为6的index。
     *
     * 如index=0与index=3的差是6.
     * 如index=1与index=6的差是6.
     *
     *
     * 要求时间复杂度O(n)
     * O(n)是考察重点。空间换取时间
     *
     */
    public static List<Integer[]> getChongfu(int[] datas, int target){
        Map<Integer, Integer> map = new HashMap<>();//<差值，index>
        for (int i = 0 ; i < datas.length ; i ++) {
            map.put(datas[i], i);
        }

        List<Integer[]> list = new ArrayList<>();
        for (int i = 0 ; i < datas.length ; i ++) {
            int sum = target + datas[i];
            if(map.get(sum) != null) {
                Integer [] result = new Integer[]{i, map.get(sum)};
                list.add(result);
            }
        }

        return list;
    }

    @Test
    public void test(){
        int [] datas = {1,3,5,7,2,6,9,8};//没有重复值

        List<Integer[]> list = getChongfu(datas, 6);
        for (Integer[] integers : list) {
            System.out.println(Arrays.toString(integers));
        }
    }





    @Data
    class Node{
        private Node left;
        private Node right;
        private int data;

        public Node(int data){
           this.data=data;
        }
    }

    /**
     * 二叉搜索树中（没有重复值时） 查找比target大的最小值
     * @param node
     * @param target
     */

    public Integer find1(Node node, int target){
        if(node == null)return null;
        if(node.data <= target){
           return find1(node.right, target);
        } else {
            Integer x= find1(node.left, target);
            return (x == null) ? node.data : x;
        }
    }

    /**
     * 二叉搜索树中（没有重复值时） 查找比target小的最大值
     * @param node
     * @param target
     */

    public Integer find2(Node node, int target){
        if(node == null)return null;
        if(node.data >= target){
            return find2(node.left, target);
        } else {
            Integer x= find2(node.right, target);
            return (x == null) ? node.data : x;
        }
    }

    @Test
    public void findtest(){
        Node node10 = new Node(10);//root
        Node node5 = new Node(5);
        node10.left=node5;
        Node node18 = new Node(18);
        node10.right=node18;
        Node node1 = new Node(1);
        Node node6 = new Node(6);
        Node node17 = new Node(17);
        Node node19 = new Node(19);
        Node node7 = new Node(7);
        Node node16 = new Node(16);
        node5.left=node1;
        node5.right=node6;
        node6.right=node7;
        node18.left=node17;
        node18.right=node19;
        node17.left=node16;

        int target = 19;
        System.out.println("比 " + target + "大的最小值是：" + find1(node10, target));
        System.out.println("比 " + target + "小的最大值是：" + find2(node10, target));
    }
}
