package hashtable;

import java.util.HashSet;
import java.util.Set;

public class LineReflection {
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (int[] point : points) {
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
            String cur = point[0] + ":" + point[1];
            set.add(cur);
        }
        int sum = max + min;
        for (int[] point : points) {
            String cur = (sum-point[0]) + ":" + point[1];
            if (!set.contains(cur)) {
                return false;
            }
        }
        return true;
    }
}
