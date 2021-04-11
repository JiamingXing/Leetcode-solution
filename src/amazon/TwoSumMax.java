package amazon;

//two sum to target, if more than one, pick the pair with largest number

//考虑下有哪些corner case 如果有重复元素

//如果没有解 要返回一个空的list

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumMax {
    public ArrayList<Integer> IDsOfPackages(int truckSpace,
                                     List<Integer> packagesSpace)
    {
        // WRITE YOUR CODE HERE
        ArrayList res = new ArrayList();
        Map<Integer, Integer> map = new HashMap<>();
        int target = truckSpace - 30;
        int max = Integer.MIN_VALUE;
        if (target < 0) {
            return res;
        }
        for (int i = 0; i < packagesSpace.size(); i++) {
            int n = packagesSpace.get(i);
            if (map.containsKey(target-n)) {
                if (Math.max(target-n, n) > max) {
                    res.add(map.get(target - n));
                    res.add(i);
                    max = Math.max(target-n, n);
                }
            }
            map.put(n, i);
        }
        return res;
    }
}
