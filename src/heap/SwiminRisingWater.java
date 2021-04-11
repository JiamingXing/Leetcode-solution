package heap;

//做这种比较难的题的时候可能会觉得很困难 但是我们要离开舒适区刷题 所以要尽可能思考思路 去靠近答案
//最初的想法有：对于一个点可能从四个方向到这个点

//看solution用DFS比较naieve的做法是 既然我们要找最小的time 我们就设置一个阈值time 把它作为参数传进DFS
//如果我们能找到一条路线 所有的点都小于等于这个time 并且抵达终点我们就表示我们找到了我们的答案 从0开始一直一个个尝试
//但是感觉上这个做法应该不是最佳做法..如果答案很大的时候 这个时间复杂度会很高感觉

//有一个Union Find的思路 我们可以放在union find的topic的时候学习一下 这个也算是半个只是盲区了...

//主要是想想这道题有没有用PQ的思路

import java.util.PriorityQueue;

//使用PQ 类似Dijkstra's algorithm
//这个思路也不是自己写的 还不是很懂...

public class SwiminRisingWater {
    public int swimInWater(int[][] grid) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (grid[a[0]][a[1]] - grid[b[0]][b[1]]));
        pq.add(new int[]{0, 0});
        int level = 0;
        int n = grid.length;
        int[][] nexts = {{0 ,1}, {0, -1},{1, 0}, {-1, 0}};
        boolean[][] isVisited = new boolean[n ][n];
        while (!pq.isEmpty()){
            int[] top = pq.poll();
            level = Math.max(level, grid[top[0]][top[1]]);
            if (top[0] == n - 1 && top[1] == n - 1){
                break;
            }

            for (int[] next : nexts){
                int x = top[0] + next[0];
                int y = top[1] + next[1];
                if (!(x < 0|| x > n - 1 || y < 0 || y > n - 1)  && !isVisited[x][y]){
                    isVisited[top[0]][top[1]] = true;
                    pq.add(new int[]{x, y});
                }
            }
        }

        return level;
    }
}





/*
class Solution {
    class UF {
        int[] id;
        public UF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }
        public int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
        public boolean isConnected(int p, int q) {
            return root(p)==root(q);
        }
        public void union(int p, int q) {
            if (isConnected(p, q)) return;
            id[root(p)] = root(q);
        }
    }
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        UF uf = new UF(N*N);
        int time = 0;
        while(!uf.isConnected(0, N*N-1)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] > time) continue;
                    if (i < N-1 && grid[i+1][j]<=time) uf.union(i*N+j, i*N+j+N);
                    if (j < N-1 && grid[i][j+1]<=time) uf.union(i*N+j, i*N+j+1);
                }
            }
            time++;
        }
        return time - 1;
    }
}
*/
