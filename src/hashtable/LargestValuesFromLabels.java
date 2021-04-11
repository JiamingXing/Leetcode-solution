package hashtable;

//一个label 有多个可能的value 还有可能重复
//greedy的做法把 从value的从大到小并且保证 选用的label不超标 所以我们要建立

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LargestValuesFromLabels {
    class Pair {
        int value;
        int label;
        Pair(int value, int label) {
            this.value = value;
            this.label = label;
        }
    }
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        Pair[] pairs = new Pair[values.length];
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            pairs[i] = new Pair(values[i], labels[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.value - o1.value;
            }
        });
        int res = 0;
        int i = 0;
        //忘了考虑如果我们没法选满num_wanted的情况
        while (num_wanted > 0 && i < pairs.length) {
            if (count.containsKey(pairs[i].label) && count.get(pairs[i].label) >= use_limit) {
                i ++;
                continue;
            }
            res += pairs[i].value;
            count.put(pairs[i].label, count.getOrDefault(pairs[i].label, 0)+1);
            num_wanted--;
            i ++;
        }
        return res;
    }
}
