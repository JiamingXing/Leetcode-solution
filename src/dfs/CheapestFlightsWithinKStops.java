package dfs;

import java.util.*;

//超时代表有太多重复的操作 所以我们需要添加一些限制条件减少运算
//我们用一个n长度的数组记录到达每个destiny的最小price 因为到某个destiny会有不同的路径，如果当前路径的price > 当前最下的
//我们就不再考虑之后所有的路径

public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<PriceDest>> map = new HashMap<>();
        int[] minPrice = new int[n];
        for (int i = 0; i < flights.length; i++) {
            int start = flights[i][0];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(new PriceDest(flights[i][1], flights[i][2]));
        }
        Queue<PriceDest> Q = new LinkedList<>();
        Q.offer(new PriceDest(src, 0));
        Arrays.fill(minPrice, Integer.MAX_VALUE);
        K ++;
        while (!Q.isEmpty()) {
            int size = Q.size();
            K --;
            for (int i = 0; i < size; i++) {
                PriceDest cur = Q.poll();
                for (PriceDest temp : map.get(cur.destiny)) {
                    if (minPrice[temp.destiny] > cur.price + temp.price) {
                        Q.add(new PriceDest(temp.destiny, cur.price + temp.price));
                        minPrice[temp.destiny] = temp.price + cur.price;
                    }
                }
            }
            if (K == 0) {
                break;
            }
        }
        return minPrice[dst] == Integer.MAX_VALUE ? -1 : minPrice[dst];
    }
    private class PriceDest {
        int destiny;
        int price;
        PriceDest (int destiny, int price) {
            this.destiny = destiny;
            this.price = price;
        }
    }
}



/*
public class CheapestFlightsWithinKStops {
    int res = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<PriceDest>> map = new HashMap<>();
        for (int i = 0; i < flights.length; i++) {
            int start = flights[i][0];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(new PriceDest(flights[i][1], flights[i][2]));
        }
        helper(map, src, dst, K+1, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private void helper(Map<Integer, List<PriceDest>> map, int src, int dst, int K, int curPrice) {
        if (!map.containsKey(src)) {
            return;
        }
        if (K == 0) {
            return;
        }
        for (PriceDest next : map.get(src)) {
            if (next.destiny == dst) {
                res = Math.min(res, curPrice + next.price);
            } else {
                helper(map, next.destiny, dst, K-1, curPrice + next.price);
            }
        }
    }
    private class PriceDest {
        int destiny;
        int price;
        PriceDest (int destiny, int price) {
            this.destiny = destiny;
            this.price = price;
        }
    }
}
*/



//BFS同样超时
/*
class Solution {
    int res = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<PriceDest>> map = new HashMap<>();
        for (int i = 0; i < flights.length; i++) {
            int start = flights[i][0];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(new PriceDest(flights[i][1], flights[i][2]));
        }
        Queue<PriceDest> Q = new LinkedList<>();
        Q.offer(new PriceDest(src, 0));
        K = K + 1;
        while (!Q.isEmpty() && K != 0) {
            K --;
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                PriceDest cur = Q.poll();
                if (!map.containsKey(cur.destiny)) {
                    continue;
                }
                for (PriceDest next : map.get(cur.destiny)) {
                    if (next.destiny == dst) {
                        res = Math.min(res, next.price+cur.price);
                    } else {
                        Q.offer(new PriceDest(next.destiny, cur.price+next.price));
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private class PriceDest {
        int destiny;
        int price;
        PriceDest (int destiny, int price) {
            this.destiny = destiny;
            this.price = price;
        }
    }
}
*/
