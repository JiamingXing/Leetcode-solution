package segmenttree;

//一道面经题，给你一些product以及对应的的概率 比如apple 0.02 banana 0.08....这些商品概率之和为1
//让你从这些商品中随机生成一个商品 根据其对应的概率
//思路也是根据这些商品的interval 简历binary index tree

import java.util.Random;

public class SearchProductByFrequency {
    private class SegmentTreeNode {
        double start;
        double end;
        String pro;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }

    private class Interval {
        double start;
        double end;
        public Interval(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }
    SegmentTreeNode root;
    public SearchProductByFrequency(String[] product, double[] freq) {
        Interval[] intervals = new Interval[freq.length];
        double cur = 0.0;
        double left = 0.0;
        for (int i = 0; i < freq.length; i++) {
            cur += freq[i];
            intervals[i] = new Interval(left, cur);
            left = cur;
        }
        root = buildTree(product, intervals, 0, intervals.length-1);
    }

    private SegmentTreeNode buildTree(String[] product, Interval[] intervals, int start, int end) {
        if (start > end) {
            return null;
        } else {
            SegmentTreeNode res = new SegmentTreeNode(intervals[start].start, intervals[end].end);
            if (start == end) {
                res.pro = product[start];
            } else {
                int mid = start + (end - start) / 2;
                res.left = buildTree(product, intervals, start, mid);
                res.right = buildTree(product, intervals, mid+1, end);
            }
            return res;
        }
    }

    public String randomGenerateProduct() {
        Random rand = new Random();
        double target = rand.nextDouble();
        return SearchProductinTree(root, target);
    }

    private String SearchProductinTree(SegmentTreeNode root, double target) {
        if (root.pro != null) {
            return root.pro;
        } else if (root.left.start < target && root.left.end >= target) {
            return SearchProductinTree(root.left, target);
        } else {
            return SearchProductinTree(root.right, target);
        }
    }
}
