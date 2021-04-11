package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//这道题的一些follow up 比较值得思考
//1. Imagine you are given a real file system, how will you search files? DFS or BFS?
//2. If the file content is very large (GB level), how will you modify your solution?
//3. If you can only read the file by 1kb each time, how will you modify your solution?
//4. What is the time complexity of your modified solution? What is the most time-consuming
// part and memory consuming part of it? How to optimize?
//5. How to make sure the duplicated files you find are not false positive?

//对于这些实际file system 有些时候会限制空间等等 毫无经验 不知道从何入手 如果面试问到这样的问题
//你会怎么解答？
//看到网上的解答 ： 这题不是算法题，而是系统设计题，题目本身并没有任何挑战性，而是思考follow up部分
//系统设计的关键，通常是代码复用，接口扩展，以及计算并行化。

public class FindDuplicateFileinSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] dirs = path.split(" ");
            String prefix = dirs[0];
            for (int i = 1; i < dirs.length; i++) {
                String cur = dirs[i];
                int j = cur.length() - 1;
                while (j >= 0 && cur.charAt(j) != '(') {
                    j --;
                }
                String content = cur.substring(j+1, cur.length() - 1);
                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>());
                }
                map.get(content).add(prefix + "/" + cur.substring(0, j));
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                res.add(map.get(key));
            }
        }
        return res;
    }
}
