package rand;

//这道题很有意思 做起来
//思路是从别人那里来的
//题目要求尽可能少的使用Math.random() 得到不在blacklist中的数
//能想到的是我们首先要处理一下这个blacklist
//如果我们遍历这个blacklist我们能得到什么 所有存在的数 我们放在一个set中， 然后每次random产生一个随机数去check这个set的话
//我们就知道这个set是否是合理的 但是如果不合理我们就要再用random产生一个新的数.....
//如何可以保证我们一次就能产生我们要的数
//遍历一遍这个blacklist我们还能知道的是这个 blacklist的size 也就是说我们合理的随机数共有N-size个
//很巧妙地利用一个map 把所有blacklist中的数都放到 0-N靠后的位置。。。然后我们在前M个数中产生随机数 通过map就能得到我们想要的数

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomPickwithBlacklist {
    Map<Integer, Integer> map = new HashMap<>();
    Random rand;
    int M;

    public RandomPickwithBlacklist(int N, int[] blacklist) {
        for (int b : blacklist) {
            map.put(b, -1);
        }
        //how many numbers of qualified number from 0-N
        this.M = N - map.size();
        for (int b : blacklist) {
            //if b > M, already in the right position
            if (b < M) {
                //find first position from end side
                while (map.containsKey(N-1)) {
                    N--;
                }
                map.put(b, N-1);
                //remember to N-- cause we put current N to value of map.. but need to check key...
                N--;
            }
        }
    }

    public int pick() {
        int i = rand.nextInt(M);
        //if map has key i, we return the relative value from end side.... like change position
        if (map.containsKey(i)) {
            return map.get(i);
        }
        //if not, i is qualified, not in blacklist...
        return i;
    }
}
