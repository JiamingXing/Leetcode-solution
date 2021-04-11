package binarysearch;

//find last element <= target

public class LastElementSortedArray {

    public int searchRange(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        /*
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                //自己写的时候 start = mid 死循环了 为什么会死循环？
                //我如果用模板写 最后留下start和end两个pointer 进行判断得出结果就肯定不会出现死循环的情况...
                start = mid;
                //start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
         */
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        LastElementSortedArray l = new LastElementSortedArray();
        System.out.println(1);
        System.out.println(l.searchRange(nums, 8));
    }
}
