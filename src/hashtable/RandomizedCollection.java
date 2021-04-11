package hashtable;

import java.util.*;

//这是自己用HashSet的做法 但是是不是O(1)貌似有争议 如果不是 那么面试官会问你关于set底层时间复杂度
//希望可以搞清楚

public class RandomizedCollection {
    Map<Integer, LinkedHashSet<Integer>> map;
    List<Integer> list;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
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

//这种做法好像肯定是O(1)没问题啊
//list里不但存储了val还存储了对应的map中的list中的position 我们swap的话 值需要set？
//因为我们每次拿到的position必然是最后一个位置

//public class RandomizedCollection {
//    private Map<Integer, List<Integer>> map;
//    private List<int[]> nums;
//    private Random rnd;
//    /** Initialize your data structure here. */
//    public RandomizedCollection() {
//        map = new HashMap<>();
//        nums = new ArrayList<>();
//        rnd = new Random();
//    }
//
//    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
//    public boolean insert(int val) {
//        boolean res = !map.containsKey(val);
//        if(res) map.put(val, new ArrayList<>());
//        map.get(val).add(nums.size());
//        nums.add(new int[]{val, map.get(val).size() - 1});
//        return res;
//    }
//
//    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
//    public boolean remove(int val) {
//        boolean res = map.containsKey(val);
//        if(res) {
//            List<Integer> valList = map.get(val);
//            int valLastIndex = valList.get(valList.size() - 1);
//
//            int[] swapNum = nums.get(nums.size() - 1);
//            int swapVal = swapNum[0], swapIndex = swapNum[1];
//
//            map.get(swapVal).set(swapIndex, valLastIndex);
//            nums.set(valLastIndex, swapNum);
//
//            if(valList.size() == 1) map.remove(val);
//            else valList.remove(valList.size() - 1);
//
//            nums.remove(nums.size() - 1);
//        }
//        return res;
//    }
//
//    /** Get a random element from the collection. */
//    public int getRandom() {
//        return nums.get(rnd.nextInt(nums.size()))[0];
//    }
//}

