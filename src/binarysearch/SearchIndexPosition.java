package binarysearch;

//no duplicate sorted array
//find the first element larger than or equal to the target
//binary search最关键的地方是怎么根据题目 移动我的pointer 判断一些临界条件

public class SearchIndexPosition {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target){
            return end;
        } else {
            return end + 1;
        }
    }
}




//其实binary search不用太拘谨与某一个固定的模板
//核心就是判断中点的关系 左移右移，循环退出条件，return的位置 都可以根据具体题目进行灵活判断。
/*
class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] >= target) {
            return start;
        } else {
            return start + 1;
        }
    }
}
*/
