package design;

import java.util.Iterator;

public class ArrayIterator implements Iterator<Integer>{
    int p1;
    int p2;
    int[] num;
    int count;
    public ArrayIterator(int[] nums) {
        this.num = nums;
        p1 = 0;
        p2 = 1;
        this.count = 0;
    }

    @Override
    public boolean hasNext() {
        if (p2 > num.length) {
            return false;
        }
        return true;
    }

    @Override
    public Integer next() {
        int cur = num[p1];
        int targetCount = num[p2];
        if (++count == targetCount) {
            count = 0;
            p1 += 2;
            p2 += 2;
        }
        return cur;
    }
}
