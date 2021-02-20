package leetcode;

/**
 * @author albertliu
 * @className 寻找两个正序数组的中位数
 * @description TODO
 * @date 2020/12/8 20:04
 */
public class 寻找两个正序数组的中位数 {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}));
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int aleft = 0, bright = 0, i = 0, j = 0;

        for (int m = 0; m < length / 2 + 1; m++) {
            aleft = bright;
            if (i < nums1.length && (j >= nums2.length || nums1[i] < nums2[j])) {
                bright = nums1[i++];
            } else {
                bright = nums2[j++];
            }
        }

        if (length % 2 == 0) {
            return (aleft + bright) / 2.0;
        } else {
            return bright;
        }
    }
}
