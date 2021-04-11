package backtracking;

import java.util.ArrayList;
import java.util.List;

//每个字符都选择数字或者字符本身

public class GeneralizedAbbreviation {
    public List<String> generateAbbreviation(String word) {
        List<String> res = new ArrayList<>();
        helper(res, "", 0, 0, word);
        return res;
    }
    private void helper(List<String> res, String cur, int pos, int count, String word) {
        if (pos == word.length()) {
            if (count > 0) {
                cur += count;
            }
            res.add(cur);
        } else {
            helper(res, cur, pos+1,count+1, word);
            helper(res, cur+(count > 0 ? count : "")+word.charAt(pos), pos+1, 0, word);
        }
    }
}
