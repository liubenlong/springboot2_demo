import org.junit.Test;

import java.util.Arrays;

/**
 * java实现各种排序算法
 * 原理参见博客：https://it007.blog.csdn.net/article/details/54908419
 */
public class Sort {


    /**
     * 直接插入排序
     *
     * @param nums
     */
    public static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int moveindes = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[moveindes] < nums[j]) {
                    int temp = nums[moveindes];
                    nums[moveindes] = nums[j];
                    nums[j] = temp;
                    moveindes--;
                } else {
                    break;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 直接插入排序另一种写法
     *
     * @param ages
     */
    public static void sort1(int[] ages) {
        for (int i = 1; i < ages.length; i++) {
            for (int j = 0; j < i; j++) {//与前面排好序的数字进行逐个比较，寻找合适的位置进行插入
                if (ages[j] > ages[i]) {//全部移位
                    int temp = ages[i];
                    int tempIndex = i;
                    while (tempIndex > j) {
                        ages[tempIndex] = ages[tempIndex - 1];
                        tempIndex--;
                    }
                    ages[j] = temp;
                }
            }
        }
    }

    @Test
    public void test1() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0};
        sort1(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 二分查找, 返回比target大的第一个数的索引
     *
     * @param nums
     * @param target
     * @return
     */
    public static int query(int[] nums, int target, int begin, int end) {
        if (nums[end] < target) return -1;//全部比目标值小
        while (end >= begin) {
            int middle = (begin + end) / 2;
            if (nums[middle] == target) return middle;
            if (nums[middle] > target) {
                end = middle - 1;
            } else {
                begin = middle + 1;
            }
        }
        return begin;
    }

    @Test
    public void query() {
        int[] nums = {1, 3, 4, 7, 8, 11};
        System.out.println(query(nums, 5, 0, 2));
    }

    /**
     * 直接插入排序-升级版：二分插入排序
     *
     * @param nums
     */
    public static void sort2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int index = query(nums, nums[i], 0, i - 1);
            if (index == -1) continue;
            int temp = nums[i];
            for (int j = i; j > index; j--) {
                nums[j] = nums[j - 1];
            }
            nums[index] = temp;
        }
    }

    @Test
    public void test2() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0};
        sort2(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 希尔排序
     *
     * @param nums
     */
    public static void sort3(int[] nums) {
        int x = 0;
        for (int step = nums.length / 2; step >= 1; step = step / 2) {
            for (int i = 0; i < step; i++) {//步长遍历
                //一次插入排序
                for (int j = i + step; j < nums.length; j += step) {
                    int moveindex = j;
                    while (moveindex - step >= 0 && nums[moveindex] < nums[moveindex - step]) {
                        int temp = nums[moveindex];
                        nums[moveindex] = nums[moveindex - step];
                        nums[moveindex - step] = temp;
                        moveindex = moveindex - step;
                        x++;
                    }
                }
            }
        }
        System.out.println(x);
    }


    @Test
    public void test3() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0, 11, 66, 33, 22, 98, 12, 45, 23, 34, 32};
        sort3(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 希尔排序, 另一种实现
     *
     * @param a
     */
    public static void sort4(int[] a) {
        int i, j, step, temp;
        int x = 0;
        for (step = a.length / 2; step >= 1; step /= 2) {
            for (i = step; i < a.length; i++) {
                temp = a[i];
                j = i - step;
                while (j >= 0 && temp < a[j]) {
                    a[j + step] = a[j];
                    j -= step;
                    x++;
                }
                a[j + step] = temp;
            }
        }
        System.out.println(x);
    }

    @Test
    public void test4() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0, 11, 66, 33, 22, 98, 12, 45, 23, 34, 32};
        sort4(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 简单选择排序
     *
     * @param nums
     */
    public static void sort5(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int minIndex = i;
                if (nums[j] < nums[minIndex]) minIndex = j;

                if (minIndex != i) {
                    int temp = nums[i];
                    nums[i] = nums[minIndex];
                    nums[minIndex] = temp;
                }
            }
        }
    }

    @Test
    public void test5() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0, 11, 66, 33, 22, 98, 12, 45, 23, 34, 32};
        sort5(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 冒泡排序
     *
     * @param nums
     */
    public static void sort6(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    @Test
    public void test6() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0, 11, 66, 33, 22, 98, 12, 45, 23, 34, 32};
        sort6(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 快速排序
     *
     * @param nums
     */
    public static void sort7(int[] nums, int begin, int end) {
        if (begin > end) return;
        int base = nums[begin];
        int beginTemp = begin, endTemp = end;
        while (begin < end) {
            while (nums[end] > base && begin < end) {
                end--;
            }
            nums[begin] = nums[end];
            while (nums[begin] < base && begin < end) {
                begin++;
            }
            nums[end] = nums[begin];
        }
        nums[begin] = base;

        sort7(nums, begin + 1, endTemp);
        sort7(nums, beginTemp, end - 1);
    }

    @Test
    public void test7() {
        int[] nums = {4, 3, 5, 2, 1, 6, 0, 11, 66, 33, 22, 98, 12, 45, 23, 34, 32};
        sort7(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 堆排序算法
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        if (array == null || array.length == 0) return;

        buildMaxHeap(array);//先构造一次大顶堆

        //升序排序：逐个取最大值移动到末尾
        for (int i = 0; i < array.length - 1; i++) {
            switchNode(array, 0, array.length - 1 - i);
            maxHeap(array, 0, array.length - 1 - i);
        }

    }

    /**
     * 构建大顶堆
     *
     * @param array
     */
    private static void buildMaxHeap(int[] array) {
        int half = array.length / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(array, i, array.length);
        }
    }

    private static void maxHeap(int[] array, int parentIndex, int arrayLength) {
        int left = parentIndex * 2 + 1;
        int right = parentIndex * 2 + 2;

        if (left < arrayLength && array[left] > array[parentIndex]) {
            switchNode(array, left, parentIndex);
            maxHeap(array, left, arrayLength);
        }
        if (right < arrayLength && array[right] > array[parentIndex]) {
            switchNode(array, right, parentIndex);
            maxHeap(array, right, arrayLength);
        }
    }

    private static void switchNode(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    @Test
    public void test8() {
        int[] array = {4, 3, 5, 2, 1, 6, 0, 11, 66, 33, 22, 34, 66, 98, 12, 45, 23, 34, 32};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }


}
