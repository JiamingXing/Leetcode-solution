package backtracking;

//1-a 2-b .... 26-z
//decode way 进阶 要求打印所有可能

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecodeWaysII {
    public List<String> decode(String s, Map<Integer, String> table) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        return helper(s, map, table);
    }
    private List<String> helper(String s, Map<String, List<String>> map, Map<Integer, String> table) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (s.isEmpty()) {
            res.add("");
            return res;
        }
        if (s.length() == 1) {
            res.add(table.get(Integer.parseInt(s)));
        } else {
            String prev = s.substring(0, 1);
            int num = Integer.parseInt(prev);
            if (num == 0) {
                map.put(s, res);
                return res;
            }
            sb.append(table.get(Integer.parseInt(prev)));
            int len = sb.length();
            List<String> list= helper(s.substring(1), map, table);
            for (String temp : list) {
                sb.append(temp);
                res.add(sb.toString());
                sb.setLength(len);
            }
            sb = new StringBuilder();
            prev = s.substring(0, 2);
            num = Integer.parseInt(prev);
            if (num >= 10 && num <= 26) {
                sb.append(table.get(num));
                len = sb.length();
                List<String> next= helper(s.substring(2), map, table);
                for (String temp : next) {
                    sb.append(temp);
                    res.add(sb.toString());
                    sb.setLength(len);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
