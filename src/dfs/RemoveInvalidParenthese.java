package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//这道题可以先简单一点 问你最少删除多少个符号可以得到valid parenthese

//第一反应想到的思路 就是我们从头到尾遍历 如果当前open>close肯定是合理的 如果close > open 我们就删除当前的) 如果到最后了 open > 0
//就从sb中往前删除(
//这样的思路是不是删除最少的？
//这样的思路只能得到一种答案..如何得到全部可能
//()())()  =>  ()()()   (())()

//想到一种思路...我先得到最少需要删除多少个 然后DFS类似backtracking 每个(或者)删或不删都试过去 如果到最后可以满足valid
//我们就加到结果中 就不去想怎么巧妙的思路 算是暴力的backtracking的感觉

//最后这道题第一次提交！就无bug通过了 超级开心 思考了很久 打破了那个固性思维！

//但是这个不是最优解...

public class RemoveInvalidParenthese {
    public List<String> removeInvalidParenthese(String s) {
        int count = 0;
        int min = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count ++;
            }
            if (c == ')') {
                count --;
            }
            if (count < 0) {
                min ++;
                count ++;
            }
        }
        if (count > 0) {
            min += count;
        }
        Set<String> set = new HashSet<>();
        helper(s, 0, 0, min, "", set);
        List<String> res = new ArrayList<>(set);
        return res;
    }
    private void helper(String s, int pos, int count, int min, String cur, Set<String> res) {
        if (pos == s.length()) {
            if (count == 0 && min == 0) {
                res.add(cur);
            }
            return;
        }
        if (count < 0) {
            return;
        }
        char c = s.charAt(pos);
        //not parenthese continue
        if (c != '(' && c != ')') {
            helper(s, pos+1, count, min, cur+c, res);
            return;
        }
        //min > 0 we are able to remove
        if (min > 0) {
            helper(s, pos+1, count, min-1, cur, res);
        }
        //keep current one
        helper(s,pos+1, c=='('?count+1:count-1, min, cur+c, res);
//        if (min == 0) {
//            helper(s, pos+1, c=='('?count+1:count-1,min,cur+c,res);
//            return;
//        }
//        //min > 0   delete/not
//        helper(s,pos+1, c=='('?count+1:count-1, min, cur+c, res);
//        helper(s, pos+1, count, min-1, cur, res);
    }

    public static void main(String[] args) {
        String s = ")a(";
        RemoveInvalidParenthese r = new RemoveInvalidParenthese();
        for (String temp : r.removeInvalidParenthese(s)) {
            System.out.println(temp);
        }
    }
}
