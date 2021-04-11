package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return dist(o2) - dist(o1);
            }
        });
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
    private int dist(int[] o) {
        return o[0] * o[0] + o[1] * o[1];
    }
}


//这道题自己用PQ写出来完全没有难度
//当时强烈建议看一下lc里第一个post的总结 对于K problem的
//https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
//再次强调 强烈建议....
//3种做法 直接1. sort整个array  2. K size的max heap   3. quick selection