package design;

import java.util.*;

//用一个LinkedHashSet

public class InsertDeleteGetrandomII {
    Map<Integer, LinkedHashSet<Integer>> map;
    List<Integer> list;
    Random rand;
    /** Initialize your data structure here. */
    public InsertDeleteGetrandomII() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        int index = list.size() - 1;
        if (!map.containsKey(val)) {
            map.put(val, new LinkedHashSet<>());
            map.get(val).add(index);
            return true;
        }
        map.get(val).add(index);
        return false;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).size() == 0) {
            return false;
        }
        int pos = map.get(val).iterator().next();
        int lastPos = list.size() - 1;
        int lastVal = list.get(lastPos);
        map.get(val).remove(pos);
        if (pos != lastPos) {
            map.get(lastVal).remove(lastPos);
            map.get(lastVal).add(pos);
        }
        list.set(pos, lastVal);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if (list.size() == 0) {
            return 0;
        }
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
