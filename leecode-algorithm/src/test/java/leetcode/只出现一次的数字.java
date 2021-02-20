package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author albertliu
 * @className 只出现一次的数字
 * @description 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 *
 * 注意：使用异或必须满足：其余元素均出现两次，否则不对
 *
 * @date 2020/12/20 17:12
 */
public class 只出现一次的数字 {

    public static void main(String[] args) {
        int[] ints = {2, 2, 1, 4, 4};
        System.out.println(one(ints));
        System.out.println(two(ints));
    }


    public static int two(int[] n) {
        int result = 0;
        for (int i : n) {
            result ^= i;
        }
        return result;
    }


    @Deprecated
    public static int one(int[] n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : n) {
            Integer integer = map.get(i);
            if (integer == null) map.put(i, 1);
            else {
                map.put(i, map.get(i) + 1);
            }
        }

        System.out.println(map);

        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : set) {
            if (entry.getValue().intValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
