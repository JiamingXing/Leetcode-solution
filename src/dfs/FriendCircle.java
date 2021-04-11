package com.jimmy.dfs;

/*
    547. Friend Circle
 */

import java.util.LinkedList;
import java.util.Queue;

public class FriendCircle {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> Q = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            Q.add(i);
            visited[i] = true;
            res ++;
            while (!Q.isEmpty()) {
                int cur = Q.poll();
                for (int j = 0; j < n; j++) {
                    if (visited[j]) {
                        continue;
                    } else if (M[cur][j] == 1) {
                        visited[j] = true;
                        Q.add(j);
                    }
                }
            }
        }
        return res;
    }
}
