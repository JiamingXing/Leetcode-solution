package twopointers;

//拿到这道题之后 你的第一反应是什么？
//这道题思路想到了 但是不知道怎么写下来 是因为对priorityQueue不熟悉

//这道题的思路本质是 sliding window 我们按照顺序把array中的元素拿过来，直到保证每个array都有元素在我们当前的rage中
//然后从前面开始discard，直到不满足条件（缺少某一array中的元素）
//但是直到这个思路了 怎么写出这个implementation是一个问题....

import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {
    private class Element {
        int row;
        int col;
        int val;

        public Element(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Element> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        int range = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int k = nums.size();
        //先让每个list的第一个元素进PQ
        for (int i = 0; i < k; i++) {
            pq.offer(new Element(i, 0, nums.get(i).get(0)));
            //记录PQ中的最大元素 方便计算range
            max = Math.max(max, nums.get(i).get(0));
        }
        int start = -1, end = -1;
        while (pq.size() == k) {
            Element e = pq.poll();
            if (max - e.val < range) {
                range = max - e.val;
                start = e.val;
                end = max;
            }
            if (e.col + 1 < nums.get(e.row).size()) {
                e.col = e.col + 1;
                e.val = nums.get(e.row).get(e.col);
                max = Math.max(max, e.val);
                pq.offer(e);
            }
        }
        return new int[] {start, end};
    }
}





//discuss里用一个辅助类 并且用priority queue不断pop pull的写法
//思路和自己想的一模一样 但是当时不会用priorityqueue写
//这个思路的写法可以说非常非常巧妙了....自己根本想不到... 先把每个array的第一个元素push到PQ中，同时记录一个max 表示这几个元素中最大的那个
//同时poll出最小的元素....因为我们通过poll出的元素 可以知道这个元素属于哪个array 也就是说我们下一个要重新补进来的元素就是这个array的下一个元素
//同时我们可能需要更新我们的max 因为我们push进来一个新的元素 不知道在什么样的位置
//循环直到我们的pq无法满足 同时拥有4个array的元素的时候就可以停止了... 同时还定义了一个start end 来更新smallest range的头和尾
//学习一下...很巧妙的写法
/*
    public int[] smallestRange(int[][] nums) {
        PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {
            public int compare(Element a, Element b) {
                return a.val - b.val;
            }
        });
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            Element e = new Element(i, 0, nums[i][0]);
            pq.offer(e);
            max = Math.max(max, nums[i][0]);
        }
        int range = Integer.MAX_VALUE;
        int start = -1, end = -1;
        while (pq.size() == nums.length) {

            Element curr = pq.poll();
            if (max - curr.val < range) {
                range = max - curr.val;
                start = curr.val;
                end = max;
            }
            if (curr.idx + 1 < nums[curr.row].length) {
                curr.idx = curr.idx + 1;
                curr.val = nums[curr.row][curr.idx];
                pq.offer(curr);
                if (curr.val > max) {
                    max = curr.val;
                }
            }
        }

        return new int[] { start, end };
    }

class Element {
    int val;
    int idx;
    int row;

    public Element(int r, int i, int v) {
        val = v;
        idx = i;
        row = r;
    }
}
*/
