package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

//根据rate排序之后 第K个开始往后都是candidate...
//并且找到i之前最小的k-1个quality 使得和最小...

public class MinimumCosttoHireKWorkers {
    private class Pair {
        double rate;
        int quality;
        public Pair(double rate, int quality) {
            this.rate = rate;
            this.quality = quality;
        }
    }
    public double minCostToHireWorkers(int[] quality, int[] wage, int K) {
        Pair[] pairs = new Pair[quality.length];
        double res = Double.MAX_VALUE;
        for (int i = 0; i < quality.length; i++) {
            pairs[i] = new Pair((double)wage[i]/quality[i], quality[i]);
            //第一遍这么写出来的是啥..完全没在意double
            //pairs[i] = new Pair(wage[i]/quality[i], quality[i]);
        }
        //这么用lambda不行...
        //Arrays.sort(pairs, (a,b) -> a.rate == b.rate ? a.quality - b.quality : (int)(a.rate - b.rate));
        //Arrays.sort(pairs, (a,b) -> (int)(a.rate - b.rate));
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.rate > o2.rate) {
                    return 1;
                } else if (o1.rate < o2.rate){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        for (int i = 0; i < K-1; i++) {
            pq.offer(pairs[i].quality);
        }
        for (int i = K-1; i < pairs.length; i++) {
            double select = pairs[i].rate;
            double cur = select * pairs[i].quality;
            if (pq.size() == K) {
                pq.poll();
            }
            Iterator<Integer> iter = pq.iterator();
            while (iter.hasNext()) {
                cur += select * iter.next().doubleValue();
            }
            pq.offer(pairs[i].quality);
            res = Math.min(cur, res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] quality = {3,1,10,10,1};
        int[] wage = {4,8,2,2,7};
        MinimumCosttoHireKWorkers m = new MinimumCosttoHireKWorkers();
        System.out.println(m.minCostToHireWorkers(quality, wage, 3));
    }
}


//看看别人这代码 如此简洁...
/*
    public double mincostToHireWorkers(int[] q, int[] w, int K) {
        double[][] workers = new double[q.length][2];
        for (int i = 0; i < q.length; ++i)
            workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker: workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            if (pq.size() > K) qsum += pq.poll();
            if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }


 */

//一开始的思路是错误的
/*
public class MinimumCosttoHireKWorkers {
    private class Pair {
        double rate;
        int quality;
        public Pair(double rate, int quality) {
            this.rate = rate;
            this.quality = quality;
        }
    }
    public double minCostToHireWorkers(int[] quality, int[] wage, int K) {
        Pair[] pairs = new Pair[quality.length];
        double res = 0.0;
        for (int i = 0; i < quality.length; i++) {
            pairs[i] = new Pair(wage[i]/quality[i], quality[i]);
        }
        Arrays.sort(pairs, (a,b) -> a.rate == b.rate ? a.quality - b.quality : (int)(a.rate - b.rate));
        double select = pairs[K-1].rate;
        for (int i = 0; i < K; i++) {
            res += pairs[i].quality*select;
        }
        return res;
    }
}

 */
