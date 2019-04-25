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
}
