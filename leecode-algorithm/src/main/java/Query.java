public class Query {

    /**
     * 二分查找-非递归
     *
     * @param nums
     */
    public static int query(int[] nums, int target) {
        int begin = 0, end = nums.length - 1, middle = (begin + end) / 2;

        while (end >= begin) {
            if (nums[middle] == target) return middle;
            else if (nums[middle] > target) end = middle - 1;
            else begin = middle + 1;
            middle = (begin + end) / 2;
        }
        return -1;
    }

    /**
     * 二分查找-递归
     *
     * @param nums
     */
    public static int query1(int[] nums, int begin, int end, int target) {
        if(end<begin)return -1;
        int middle = (begin + end) / 2;
        if(nums[middle] == target) return middle;
        else if(nums[middle]>target) return query1(nums, 0, middle - 1, target);
        else return query1(nums, middle+1, end, target);
    }

    public static void main(String[] args) {
        int query = query(new int[]{1, 2, 3, 4, 6}, 5);
        System.out.println(query);

        int[] nums = {1, 2, 3, 4, 6};
        int i = query1(nums, 0, nums.length - 1, 2);
        System.out.println(i);
    }
}
