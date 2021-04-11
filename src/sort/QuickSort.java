package sort;

//Lomuto's Partition
public class QuickSort {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        sort(nums, 0, nums.length-1);
    }
    private void sort(int[] nums, int start, int end) {
        //Lomuto's Partition是start>=end的时候return
        //Hoare's Partition是 start==end的时候return
        if (start >= end) {
            return;
        }
        int pivot = partition(nums, start, end);
        sort(nums, start, pivot-1);
        sort(nums, pivot+1, end);
    }
    //Hoare's Partition
    //return the pivot q to seperate array into two parts
    //nums[start,...,q] and nums[q+1,...,end]
    //All elments in the first subarray is smaller than or equal to the second one
    private int partition(int[] nums, int start, int end) {
        int target = nums[end];
        int i = start-1;
        for (int j = start; j < end; j++) {
            if (nums[j] <= target) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i+1, end);
        return i+1;
    }
    //写partition的时候一直有一个非常非常严重的误区，比如当前partition的pivot是5
    //我一直以为partiton之后的两个subarray1，2  1中所有值<=2中所有值
    //这不代表 两个子数组中不能有相同的值，比如5可以同时出现在两个子数组中
    //而不是把5都移动到一边去 即使两边都有5 那么我们通过recursively sort这个两个子数组
    //5会成为左边最大的，右边最小的 一样sort完成！！！ 所以写代码的时候不要进入这个误区
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}




//Hoare's Partition
/*
public class QuickSort {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        sort(nums, 0, nums.length-1);
    }
    private void sort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        int pivot = partition(nums, start, end);
        sort(nums, start, pivot);
        sort(nums, pivot+1, end);
    }
    //Hoare's Partition
    //return the pivot q to seperate array into two parts
    //nums[start,...,q] and nums[q+1,...,end]
    //All elments in the first subarray is smaller than or equal to the second one
    private int partition(int[] nums, int start, int end) {
        int target = nums[start];
        int i = start-1;
        int j = end+1;
        while (i < j) {
            do {
                j--;
            } while (nums[j] > target);
            do {
                i++;
            } while (nums[i] < target);
            if (i < j) {
                swap(nums, i, j);
            } else {
                return j;
            }
        }
        return j;
    }
    //写partition的时候一直有一个非常非常严重的误区，比如当前partition的pivot是5
    //我一直以为partiton之后的两个subarray1，2  1中所有值<=2中所有值
    //这不代表 两个子数组中不能有相同的值，比如5可以同时出现在两个子数组中
    //而不是把5都移动到一边去 即使两边都有5 那么我们通过recursively sort这个两个子数组
    //5会成为左边最大的，右边最小的 一样sort完成！！！ 所以写代码的时候不要进入这个误区
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
*/