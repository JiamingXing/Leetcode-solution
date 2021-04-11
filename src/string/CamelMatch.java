package string;

import java.util.ArrayList;
import java.util.List;

public class CamelMatch {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        char[] pt = pattern.toCharArray();
        for (String query : queries) {
            char[] qt = query.toCharArray();
            res.add(helper(qt, pt));
        }
        return res;
    }
    private boolean helper(char[] qt, char[] pt) {
        int j = 0;
        for (int i = 0; i < qt.length; i++) {
            if (j < pt.length && qt[i] == pt[j]) {
                j ++;
            } else if (Character.isUpperCase(qt[i])) {
                return false;
            }
        }
        return j == pt.length;
    }
}
