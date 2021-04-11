package heap;

//最直接的思路肯定是把所有可能的pair都放到k size的max PQ中
//如果array的size是n 我们共有O(n^2)的pair...
//但是如何优化 我们如果把array先sort一下对我们的算法有改进吗

import java.util.Arrays;

//别人的思路是我们遍历一遍数组就可以知道min dif 和 max dif 然后以这两个dif作为上下界 进行binary search
//这个思路的时间复杂度是多少...真的有优化吗？
public class FindKthSmallestPairDistance {
    private int countPairs(int[] nums, int target) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i+1;
            while (nums[j] - nums[i] <= target) {
                j ++;
            }
            res += j - i - 1;
        }
        return res;
    }
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int max = nums[nums.length-1] - nums[0];
        int min = nums[1] - nums[0];
        for (int i = 1; i < nums.length-1; i++) {
            min = Math.min(min, nums[i+1] - nums[i]);
        }
        while (min < max) {
            int mid = min + (max - min) / 2;
            if (countPairs(nums, mid) < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
    //优化一下countPair
    /*
    private int upperBound(int[] a, int low, int high, int key) {
        if (a[high] <= key) return high + 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (key >= a[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Returns number of pairs with absolute difference less than or equal to mid.
    private int countPairs(int[] a, int mid) {
        int n = a.length, res = 0;
        for (int i = 0; i < n; i++) {
            res += upperBound(a, i, n - 1, a[i] + mid) - i - 1;
        }
        return res;
    }

     */
}

//burte force 把n^2个pair 都放到PQ中 每次想到PQ的时间复杂度就很复杂
//因为看到过一篇数学推导 n个元素来建立这个PQ的话时间复杂度应该是O(n)
//但是如果我们把一个元素push到一个queue中 的时间复杂度应该是O(logn) 这个n是当前PQ size
//那我们poll()的操作应该是多少？ 我理解的PQ poll应该是O(1) 因为只要把peek的元素推出来就可以了
//但是具体上还是要看我们的数据结构是怎么实现的 会有不同吗？
/*
public class FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                pq.offer(nums[j]-nums[i]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.poll();
    }
}
 */
