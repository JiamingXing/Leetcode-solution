package dfs;

import java.util.ArrayList;
import java.util.List;

//自己写的TLE
//如果TLE了是否想一下能用Memorization吗？
//大体思路是backtracking没错的

public class ShoppingOffers {
    int min = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //add unit price as special offer
        List<Integer> count = new ArrayList<>();
        for (int i = 0; i < price.size(); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < price.size(); j++) {
                list.add(0);
            }
            list.add(price.get(i));
            list.set(i, 1);
            special.add(list);
            count.add(0);
        }
        helper(special, count, needs, 0);
        return min;
    }
    private void helper(List<List<Integer>> special, List<Integer> count,
                        List<Integer> needs, int cur) {
        if (needs.equals(count)) {
            min = Math.min(min, cur);
            return;
        }
        for (int i = 0; i < special.size(); i++) {
            //if valid
            List<Integer> temp = isValid(special.get(i), count, needs);
            if (temp != null) {
                helper(special, temp, needs, cur+special.get(i).get(special.get(i).size()-1));
            }
        }
    }
    private List<Integer> isValid(List<Integer> offer, List<Integer> cur, List<Integer> needs) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            if (offer.get(i) + cur.get(i) > needs.get(i)) {
                return null;
            }
            res.add(offer.get(i) + cur.get(i));
        }
        return res;
    }
}
