package slidingwindow;

//这道题有思路吗？？？？？？？ 暴力解O(nk)

//整体思路是实现一个单调Q 每次进来一个数 我需要做什么？
//第一步是check Q.peek()是否在window之外 如果在window之外就pop掉
//如果在window之内进来一个元素之后 我们要把之前比当前元素小的全部pop掉
//维持一个单调递减的Q  这样保证每次我们的peek都是当前window的结果

//我想要实现一个什么 就是我当前window里的element是放在一个Q中的
//我希望Q的第一个元素 也就是peek 是当前window的最大值
//所以需要一个递减Q 那如何实现一个递减Q 就是当一个新的元素进来的时候 比它小的元素都要被poll掉
//因为我们随着window的移动 比他小的元素 又在他前面 不可能成为window的最大值
//那么当window移动的时候 我们总需要poll掉一个最前面的元素 最前面的元素如果不是当前最大值 无所谓
//如果是就poll掉 剩下的元素还是递减

//要注意哦 进去的是index  我们保证index是递增的
//为什么用Deque 就是因为Deque可以从两头插入以及remove

//那假如是sliding window minimum呢？ 我就需要一个递增Q 每次新来元素我们比他大的都poll掉
//无论如何先poll掉一定要poll的可能的元素 再进来新的元素

import java.util.ArrayDeque;
import java.util.Deque;

public class SldiingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n+1-k];
        int iter = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //remove element out of window
            while (!q.isEmpty() && q.peek() < i+1-k) {
                q.pollFirst();
                //q.poll();
            }
            //remove element smaller than current element
            //从tail开始check 因为Q是单调递减的
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.add(i);
            if (i >= k-1) {
                res[iter++] = nums[q.peek()];
            }
        }
        return res;
    }
    //自己写的用LinkedList 反正
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        if (nums == null || nums.length == 0) {
//            return new int[0];
//        }
//        int n = nums.length;
//        int[] res = new int[n-k+1];
//        int iter = 0;
//        LinkedList<Integer> q = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            if (i >= k && q.peekFirst() == i-k) {
//                q.pollFirst();
//            }
//            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) {
//                q.pollLast();
//            }
//            q.add(i);
//            if (i >= k-1) {
//                res[iter++] = nums[q.peekFirst()];
//            }
//        }
//        return res;
//    }
}
