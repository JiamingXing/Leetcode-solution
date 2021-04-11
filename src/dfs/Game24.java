package dfs;

//4张1-9的卡 用 + - * / ( ) 能否算出24点
//做出来以后都可以用这个来算能不能玩24点了...再狠一点的话 把结果都print出来

//目前自己的思路就是把4个数的问题变成 1+3 或者 2+2的问题并且memorization一下？
//数组是否排序一下会更好一点？

//其实是最暴力的方法。。4->3->2->1 就是说我们需要三层DFS不断得到所有可能结果。。。。

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game24 {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int i : nums) {
            list.add((double) i);
        }
        return dfs(list);
    }

    // 每次dfs都是选取两张牌
    //4张牌DFS 变成3张牌 再DFS变成2张牌
    private boolean dfs(List<Double> list) {
        if (list.size() == 1) {
            // 如果此时list只剩下了一张牌
            if (Math.abs(list.get(0) - 24.0) < 0.001) {
                return true;
            }
            return false;
        }

        // 选取两张牌
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                // 对于每下一个可能的产生的组合
                for (double c : compute(list.get(i), list.get(j))) {
                    List<Double> nextRound = new ArrayList<>();
                    // 将他们加入到下一个list循环中去
                    nextRound.add(c);
                    for (int k = 0; k < list.size(); k++) {
                        if (k == j || k == i) continue;
                        nextRound.add(list.get(k));
                    }
                    if (dfs(nextRound)) return true;
                }
            }
        }
        return false;
    }
    //因为double的除法计算 分母是允许为0.0的  出来结果是infiniti...
    private List<Double> compute(double a, double b) {
        List<Double> res = Arrays.asList(a + b,a-b,b-a,a*b,a/b,b/a);
        return res;
    }
}
