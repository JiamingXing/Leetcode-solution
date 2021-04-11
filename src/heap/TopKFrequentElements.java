package heap;

import java.util.*;

//错误写法啊明显
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int n : map.keySet()) {
            //这样是错的把。。。
            //应该先进 然后大于K的时候poll把
            if (pq.size() == k) {
                pq.poll();
            }
            pq.add(new int[]{n, map.get(n)});
        }
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll()[0]);
        }
        return res;
    }
}



//上面那个pq的写法是错的 这个是对的 全部入pq之后就根据顺序排列完成 我们poll n个数出来就好了
//上面的思路是我不断维持pq k的size 一边入队 一边出队 这样不能保证是最大的两个
//比如1，2，3 应该是1，2 但是如果我1，2先入队了 这个时候3要入队发现2出队 这个时候答案就是错的
/*
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int n : map.keySet()) {
            pq.add(new int[]{n, map.get(n)});
        }
        List<Integer> res = new ArrayList<>();
        while (k > 0) {
            res.add(pq.poll()[0]);
            k--;
        }
        return res;
    }
}
*/



/*
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            max = Math.max(max, map.get(n));
        }
        //下面的写法是错误的 正确的应该是：
        //List<Integer>[] bucket = new ArrayList[max + 1];

        List<Integer>[] bucket = new ArrayList<>[max + 1];
        for (int n : map.keySet()) {
            if (bucket[map.get(n)] == null) {
                bucket[map.get(n)] = new ArrayList<>();
            }
            bucket[map.get(n)].add(n);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = max; i >= 0; i --) {
            if (bucket[i] == null) {
                continue;
            }
            for (int n : bucket[i]) {
                k--;
                res.add(n);
                if (k == 0) {
                    break;
                }
            }
            if (k == 0) {
                break;
            }
        }
        return res;
    }
}
*/