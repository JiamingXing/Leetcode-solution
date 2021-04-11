package bfs;

import java.util.*;

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
