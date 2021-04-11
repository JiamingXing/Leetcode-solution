package design;

import java.util.Iterator;
import java.util.List;

//这道题很关键一点在于要考虑 有没有空的情况 如果我们只用Iterator来写的话
//[[], [3]] 以及[[], []]的corner case

public class Vector2D implements Iterator<Integer>{
    Iterator<List<Integer>> iters;
    Iterator<Integer> curIter;
    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d == null || vec2d.size() == 0) {
            return;
        }
        this.iters = vec2d.iterator();
        this.curIter = iters.next().iterator();
        //在constructor的时候先执行一次getNext为了跳过List的情况
        getNext();
    }

    private void getNext() {
        while (!curIter.hasNext() && iters.hasNext()) {
            curIter = iters.next().iterator();
        }
    }

    @Override
    public Integer next() {
        int next = curIter.next();
        getNext();
        return next;
    }

    @Override
    public boolean hasNext() {
        return curIter != null && curIter.hasNext();
    }
}
