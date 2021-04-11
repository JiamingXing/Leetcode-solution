package twopointers;

//这道题有点难，思路比较巧妙
//如果先换一道题，求number of subarray with at most k distinct characters 应该怎么做？
//转换成At most的话就是典型的sliding window的题，当两个指针之间满足条件 就是distinct number小于等于K的时候
//我们统计从left到当前right 以right结尾的所有subarray个数
//当出现大于K个number的时候 我们就移动左指针来保证left到right之间的number重新为K个....

//然后这个道题要求刚好K个 就等于atMost(K) - atMost(K-1) 这一步是真的一般人都想不到的思路

//但是核心还是sliding window 特别是subarray的题 经常会用sliding window 最重要的是我们知道什么时候移动左指针 什么时候计算结果？

//特别好的一道题 学习一下

import java.util.HashMap;
import java.util.Map;

public class SubarrayswithKDistinctintegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return subarraysAtMostKDistinct(A, K) - subarraysAtMostKDistinct(A, K-1);
    }

    //number of subarrays at most K distinct subarrays
    private int subarraysAtMostKDistinct(int[] A, int K) {
        int left = 0, right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0, res = 0;
        while (right < A.length) {
            int cur = A[right++];
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (map.get(cur) == 1) {
                count ++;
            }
            while (count > K) {
                int remove = A[left++];
                map.put(remove, map.get(remove)-1);
                if (map.get(remove) == 0) {
                    count --;
                }
            }
            res += (right-left);
        }
        return res;
    }
}
