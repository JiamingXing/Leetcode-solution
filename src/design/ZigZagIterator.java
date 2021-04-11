package design;

//有一个指针永远都指向我们有next的下一个点

import java.util.Iterator;
import java.util.List;

public class ZigZagIterator {
    Iterator<Integer>[] iters;
    int pos;

    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        this.iters = new Iterator[2];
        this.pos = 0;
        iters[0] = v1.iterator();
        iters[1] = v2.iterator();
    }

    public int next() {
        int res = iters[pos].next();
        pos = pos + 1 == iters.length ? 0 : pos+1;
        return res;
    }

    public boolean hasNext() {
        if (iters[pos].hasNext()) {
            return true;
        }
        for (int i = pos+1; i < iters.length; i++) {
            if (iters[i].hasNext()) {
                pos = i;
                return true;
            }
        }
        for (int i = 0; i < pos; i++) {
            if (iters[i].hasNext()) {
                pos = i;
                return true;
            }
        }
        return false;
    }
}
