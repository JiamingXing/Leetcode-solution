package hashtable;

import java.util.Map;
import java.util.TreeMap;

//自己思考的时候其实思路已经很接近了 但是还是没想到 其实是一道比较简单的题
//当时有想到 我们这些value可能要进行sort
//那么我们应该能顺理成章想到 用一个TreeMap来记录每个元素的数量
//对next number进行比较 抵消。。。 然后分析好corner case 比如当前key是小于0的时候 next不存在的时候 以及因为是int 所以奇数的时候...

public class ArrayofDoublePairs {

    public boolean canReorderDoubled(int[] A) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int n : A) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 0) {
                continue;
            }
            //next greater matched pair...
            int next = key > 0 ? key*2 : key/2;
            if (key<0 && key%2 != 0 || map.get(key) > map.getOrDefault(next, 0)) {
                return false;
            }
            map.put(next, map.get(next) - map.get(key));
        }
        return true;
    }
}
