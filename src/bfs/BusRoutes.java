package bfs;

import java.util.*;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (routes == null || routes.length == 0) {
            return -1;
        }
        Map<Integer, Set<Integer>> busMap = new HashMap<>();
        Map<Integer, Set<Integer>> stationMap = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            busMap.put(i, new HashSet<>());
            for (int j = 0; j < routes[i].length; j++) {
                busMap.get(i).add(routes[i][j]);
                if (!stationMap.containsKey(routes[i][j])) {
                    stationMap.put(routes[i][j], new HashSet<>());
                }
                stationMap.get(routes[i][j]).add(i);
            }
        }
        boolean[] visited = new boolean[routes.length];
        Queue<Integer> Q = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < routes.length; i++) {
            if (busMap.get(i).contains(S)) {
                Q.offer(i);
                visited[i] = true;
            }
        }
        if (!Q.isEmpty() && S == T) {
            return 0;
        }
        while (!Q.isEmpty()) {
            int size = Q.size();
            res ++;
            for (int i = 0; i < size; i++) {
                int cur = Q.poll();
                if (busMap.get(cur).contains(T)) {
                    return res;
                }
                for (int next : busMap.get(cur)) {
                    for (int j = 0; j < routes.length; j++) {
                        if (visited[j]) {
                            continue;
                        }
                        if (busMap.get(j).contains(next)) {
                            Q.offer(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
