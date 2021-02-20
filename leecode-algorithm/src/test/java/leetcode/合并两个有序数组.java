package leetcode;

/**
 * @author albertliu
 * @className 合并两个有序数组
 * @description 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 说明：
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * @date 2020/12/8 11:54
 */
public class 合并两个有序数组 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,5,0};
        int[] nums2 = new int[]{1};

        int m = 2, n = 1;
        merge(nums1, m, nums2, n);

        for (int i : nums1) {
            System.out.print(i + ", ");
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length < m + n) {
            System.out.println("nums1长度不足以保存nums2");
            return;
        }

        //两个指针，m  n  从后向前移。将大的写入到nums1（从后往前）
        m = m - 1;
        n = n - 1;
        int index = m + n + 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[index--] = nums1[m--];
            } else {
                nums1[index--] = nums2[n--];
            }
        }

        //这里只需要处理nums2有剩余的情况即可
        if (m <= 0) {
            while (n >= 0) {
                nums1[index--] = nums2[n--];
            }
        }
    }

}
