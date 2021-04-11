package hashtable;

//很简单的一道题
//一个是提醒你要分析时间空间复杂度 不同的做法
//一个是java 8 新的Stream的用法

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        return Arrays.stream(nums).anyMatch(num -> !seen.add(num));
    }
}
