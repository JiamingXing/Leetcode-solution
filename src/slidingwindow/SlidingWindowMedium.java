package slidingwindow;

import java.util.PriorityQueue;

//corner case : [2147483647,2147483647] K = 2
//output : [-1.0000]  expected : [[2147483647.0]]
//O(nK) complexity
//如果想实现O(nlogk) 我们需要用TreeSet 因为PQ上的remove的时间复杂度是O(n)

public class SlidingWindowMedium {

    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    public SlidingWindowMedium() {
        this.min = new PriorityQueue<>();
        //this.max = new PriorityQueue<>((a,b) -> b-a);
        this.max = new PriorityQueue<>((a,b) -> b.compareTo(a));
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        if (nums.length - k + 1 <= 0) {
            return new double[0];
        }
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                res[i-k] = getMedian();
                remove(nums[i-k]);
            }
            if (i < nums.length) {
                add(nums[i]);
            }
        }
        return res;
    }

    private void add(int n) {
        if (n < getMedian()) {
            max.offer(n);
        } else {
            min.offer(n);
        }
        if (max.size() > min.size()) {
            min.offer(max.poll());
        } else if (min.size() - 1 > max.size()) {
            max.offer(min.poll());
        }
    }

    private void remove(int n) {
        if (n < getMedian()) {
            max.remove(n);
        } else {
            min.remove(n);
        }
        if (max.size() > min.size()) {
            min.offer(max.poll());
        } else if (min.size() - 1 > max.size()) {
            max.offer(min.poll());
        }
    }

    private double getMedian() {
        if (min.size() == 0 && max.size() == 0) {
            return 0.0;
        }
        if (min.size() == max.size()) {
            return (min.peek() + max.peek()) / 2.0;
        } else {
            return (double)min.peek();
        }
    }
}



//    public double[] medianSlidingWindow(int[] nums, int k) {
//        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
//        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
//        TreeSet<Integer> right = new TreeSet<>(comparator);
//
//        Supplier<Double> median = (k % 2 == 0) ?
//                () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
//                () -> (double) nums[right.first()];
//
//        // balance lefts size and rights size (if not equal then right will be larger by one)
//        Runnable balance = () -> { while (left.size() > right.size()) right.add(left.pollFirst()); };
//
//        double[] result = new double[nums.length - k + 1];
//
//        for (int i = 0; i < k; i++) left.add(i);
//        balance.run(); result[0] = median.get();
//
//        for (int i = k, r = 1; i < nums.length; i++, r++) {
//            // remove tail of window from either left or right
//            if(!left.remove(i - k)) right.remove(i - k);
//
//            // add next num, this will always increase left size
//            right.add(i); left.add(right.pollFirst());
//
//            // rebalance left and right, then get median from them
//            balance.run(); result[r] = median.get();
//        }
//
//        return result;
//    }
