package design;

//看了别人的思路 用到3个hashmap 和bucket的思想在里面

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    Map<Integer, Integer> keyValPair;
    Map<Integer, Integer> keyCountPair;
    Map<Integer, LinkedHashSet<Integer>> bucket;
    int min;
    int cap;

    public LFUCache(int capacity) {
        this.keyValPair = new HashMap<>();
        this.keyCountPair = new HashMap<>();
        this.bucket = new HashMap<>();
        this.min = 0;
        this.cap = capacity;
    }

    public int get(int key) {
        //if not contains key ->  return -1
        if (!keyValPair.containsKey(key)) {
            return -1;
        }
        //if contains key
        //get val and count of key ++
        //get method will never add the capacity of cache
        int count = keyCountPair.get(key);
        bucket.get(count).remove(key);
        if (count == min && bucket.get(count).size() == 0) {
            min ++;
        }
        if (!bucket.containsKey(count+1)) {
            bucket.put(count+1, new LinkedHashSet<>());
        }
        bucket.get(count+1).add(key);
        keyCountPair.put(key, count+1);
        return keyValPair.get(key);
    }

    public void put(int key, int value) {
        //if add new key-value pair
        //check capacity and try to delete the min count key-value pair
        if (!keyValPair.containsKey(key)) {
            if (keyValPair.size() == this.cap) {
                int keyToRemove = bucket.get(min).iterator().next();
                bucket.get(min).remove(keyToRemove);
                keyValPair.remove(keyToRemove);
                keyCountPair.remove(keyToRemove);
            }
            min = 1;
            keyCountPair.put(key, 1);
            keyValPair.put(key, value);
            if (!bucket.containsKey(1)) {
                bucket.put(1, new LinkedHashSet<>());
            }
            bucket.get(1).add(key);
        } else {
            keyValPair.put(key, value);
            //check whether min
            int count = keyCountPair.get(key);
            bucket.get(count).remove(key);
            if (count == min && bucket.get(count).size() == 0) {
                min ++;
            }
            if (!bucket.containsKey(count+1)) {
                bucket.put(count+1, new LinkedHashSet<>());
            }
            bucket.get(count+1).add(key);
            keyCountPair.put(key, count+1);
        }
        //if update
        //update and count ++..
    }

    public static void main(String[] args) {
        LFUCache lf = new LFUCache(2);
        lf.put(3,1);
        lf.put(2,1);
        lf.put(2,2);
        lf.put(4,4);
        System.out.println(lf.get(2));
    }
}
