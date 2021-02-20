package leetcode;

/**
 * @author albertliu
 * @className 归并排序
 * @description TODO
 * @date 2020/12/8 11:00
 */
public class 归并排序 {
    public static void main(String[] args) {

        int[] nums1 = new int[]{3, 5, 5, 9};
        int[] nums2 = new int[]{2, 4, 5, 10, 13, 16};
        int[] result = merge(nums1, nums2);
        for (int i : result) {
            System.out.print(i + ", ");
        }
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        if (null == nums1) return nums2;
        if (null == nums2) return nums1;

        /*
        i:nums1的指针
        j:nums2的指针
        index: result的指针
         */
        int i = 0, j = 0, index = 0;
        int[] result = new int[nums1.length + nums2.length];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                result[index++] = nums1[i++];
            } else {
                result[index++] = nums2[j++];
            }
        }

        //上面while结束后，肯定有一个指针走到了数组的末尾，那么result就拼接上另一个数组剩余的部分即可
        if (i >= nums1.length) {
            for (int id = j; id < nums2.length; id++) {
                result[index++] = nums2[id];
            }
        } else {
            for (int id = i; id < nums1.length; id++) {
                result[index++] = nums1[id];
            }
        }
        return result;
    }

}
