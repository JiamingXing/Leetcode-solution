package graph;

import java.util.*;

//这里有个限制条件是最多k stops
//所以最好的处理最多K Stops的方法应该是 用BFS的方法
//BFS的好处在于 我们可以通过BFSlevel的概念很好的处理我们这个K stops的条件

public class CheapestFlightsWithinKSteps {
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
                if (map.get(cur.destiny) == null) {
                    continue;
                }
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



//一开始自己写的BFS 没考虑好最多停K stops的情况 因为我通过判断路径大小可能会出现没考虑短路径大距离的情况
/*
public class CheapestFlightsWithinKSteps {
    private class PriceDest {
        int dest;
        int price;
        public PriceDest(int dest, int price) {
            this.dest = dest;
            this.price = price;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<PriceDest>> map = new HashMap<>();
        //establish directed graph with map
        for (int[] flight : flights) {
            if (!map.containsKey(flight[0])) {
                map.put(flight[0], new ArrayList<>());
            }
            map.get(flight[0]).add(new PriceDest(flight[1], flight[2]));
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dfs(map, dist, src, dst, K, -1, 0);
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
    private void dfs(Map<Integer, List<PriceDest>> map, int[] dist, int cur, int dst, int K, int steps, int sum) {
        if (steps > K) {
            return;
        }
        dist[cur] = sum;
        if (cur == dst) {
            return;
        }
        if (!map.containsKey(cur)) {
            return;
        }
        for (PriceDest iter : map.get(cur)) {
            if (sum + iter.price >= dist[iter.dest]) {
                continue;
            }
            dfs(map, dist, iter.dest, dst, K, steps+1, sum+iter.price);
        }
    }
}
*/


//看看人家写的pq 你看看你的为啥这么卡
/*
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[] {0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }

 */
