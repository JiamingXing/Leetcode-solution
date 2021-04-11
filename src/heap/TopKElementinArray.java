package heap;

import java.util.PriorityQueue;

//面试的时候可能会问题 如果不调用sort？
//虽然pq的本质还是用的sort 但是要学着去想什么样的题你可能会去用pq解决 然后时间复杂度的分析
//会不会有比pq更快的办法

public class TopKElementinArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}


/*
public class TopKElementinArray {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int i = nums.length - 1;
        while (i >= 0) {
            if (--k == 0) {
                return nums[i];
            }
            i --;
        }
        return -1;
    }
}
*/
