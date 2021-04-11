package hashtable;

import java.util.HashMap;
import java.util.Map;

public class MaxStep {
    public int maxStep(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, helper(i, map));
        }
        return max;
    }
    private int helper(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int res = 0;
        if (n % 2 == 1) {
            res = helper(3*n+1, map) + 1;
        } else {
            res = helper(n/2, map) + 1;
        }
        map.put(n, res);
        return res;
    }
}
