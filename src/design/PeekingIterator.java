package design;

import java.util.Iterator;

//需要一个peek() 我们就要像需要一根指针指向当前的元素 或者我们可以赢next()先得到next的元素 暂时存起来
//如果我们执行peekingiterator的next()的时候再找下一个元素
//就是这种设计题你看AC的solution 感觉很简单 但是真的自己想起来就会觉得完全没有思路？

public class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iter;
    Integer next;
    boolean done = false;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iter = iterator;
        if (iterator.hasNext()) {
            next = iterator.next();
        } else {
            done = true;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        if (iter.hasNext()) {
            next = iter.next();
        } else {
            next = null;
            done = true;
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return !done;
    }
}
