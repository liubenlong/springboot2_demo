package leetcode.回溯_递归;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author albertliu
 * @className 全排列
 * @description
 * 全排列是什么问题？直接举例说明
 * 1，2，3的全排列有
 * 1，2，3
 * 1，3，2
 * 2，1，3
 * 2，3，1
 * 3，1，2
 * 3，2，1
 *
 * https://blog.csdn.net/weixin_42220532/article/details/90900815
 * @date 2020/12/22 15:53
 */
public class 难_全排列 {


    public static void main(String[] args) {
        int arr[] = {1, 2, 3};

        pailie(arr, 0, arr.length - 1);

//        perm(arr, new Stack<>());
    }

    public static void perm(int[] array, Stack<Integer> stack) {
        if (array.length <= 0) {
            //进入了叶子节点，输出栈中内容
            System.out.println(stack);
        } else {
            for (int i = 0; i < array.length; i++) {
                //tmepArray是一个临时数组，用于就是Ri
                //eg：1，2，3的全排列，先取出1，那么这时tempArray中就是2，3
                int[] tempArray = new int[array.length - 1];
                System.arraycopy(array, 0, tempArray, 0, i);
                System.arraycopy(array, i + 1, tempArray, i, array.length - i - 1);
                stack.push(array[i]);
                perm(tempArray, stack);
                stack.pop();
            }
        }
    }

    public static void pringArray(int[] arr) {
        // 打印数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        // 交换函数
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void pailie(int[] nums, int begin, int end) {
        if (begin == end) { //  一次递归结束。将整个数组打印出来
            pringArray(nums);
        }
        // for循环将数组中所有的元素和第i个元素进行交换。然后进行全排列。
        for (int i = begin; i <= end; i++) {
            swap(nums, i, begin);
            // 把剩下的元素都做全排列。
            pailie(nums, begin + 1, end);
            // 然后再交换回去，数组还原，保证下一次不会有重复交换。
            swap(nums, i, begin);
        }
    }


}