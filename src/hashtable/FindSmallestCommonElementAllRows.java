package hashtable;

import java.util.PriorityQueue;

public class FindSmallestCommonElementAllRows {
    class Element {
        int val;
        int x;
        int y;
        public Element(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
    public int smallestCommonElement(int[][] mat) {
        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        int max = Integer.MAX_VALUE, m = mat.length;
        for (int i = 0; i < m; i++) {
            pq.offer(new Element(mat[i][0], i, 0));
            max = Math.max(max, mat[i][0]);
        }
        while (true) {
            if (pq.peek().val == max) {
                return max;
            }
            Element cur = pq.poll();
            if (cur.y == mat[cur.x].length - 1) {
                break;
            }
            Element next = new Element(mat[cur.x][cur.y+1], cur.x, cur.y+1);
            max = Math.max(next.val, max);
            pq.offer(next);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}};
        FindSmallestCommonElementAllRows s = new FindSmallestCommonElementAllRows();
        System.out.println(s.smallestCommonElement(mat));
    }
}
