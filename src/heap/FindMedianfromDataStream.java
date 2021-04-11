package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianfromDataStream {
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;
    /** initialize your data structure here. */
    public FindMedianfromDataStream() {
        //min heap store the larger part of elements
        this.min = new PriorityQueue<>();
        //max heap store the minimum part of elements
        this.max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    //当总数为奇数时 我们的中位数从min和max出来都是可以的 无所谓
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()) {
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if (min.size() == max.size()) {
            return (min.peek() + max.peek()) / 2.0;
        } else {
            return max.peek();
        }
    }
}
