package binarysearch;

//find starting position in the given sorted array
//if not found return -1

//[5,7,7,8,8,10] target=8 return 3

// -> find first element >= target

public class FirstElementSortedArray {

    public int searchRange(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        //循环结束条件？
        //end as start = end
        while (start < end) {
            int mid = start + (end - start) / 2;
            //如何移动指针
            if (nums[mid] >= target) {
                //from mid+1 -> length-1 discarded
                end = mid;
            } else {
                //from start -> mid discarded
                start = mid + 1;
            }
        }
        //结果是什么？
        if (nums[start] != target) {
            return -1;
        }
        return start;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        FirstElementSortedArray f = new FirstElementSortedArray();
        System.out.println(f.searchRange(nums, 8));
    }
}
