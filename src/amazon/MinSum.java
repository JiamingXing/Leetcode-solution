package amazon;

//[2,4,6,8]
//每次选2个数得到一个结果 把每次结果加起来要求最小
//一开始这个做法是错的 因为每次选最小两个相加的结果还要放回去的 如果比剩下的数大...

import java.util.PriorityQueue;

public class MinSum {
    public int minSum(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
//        Arrays.sort(nums);
//        int res = 0;
//        int n = nums.length;
//        for (int i = 1; i < nums.length; i++) {
//            res += nums[i] * (n-i);
//        }
//        res += nums[0] * (n-1);
//        return res;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(n);
        }
        int res = 0;
        while (true) {
            int cur = pq.poll() + pq.poll();
            res += cur;
            if (pq.isEmpty()) {
                break;
            }
            pq.offer(cur);
        }
        return res;
    }
}
