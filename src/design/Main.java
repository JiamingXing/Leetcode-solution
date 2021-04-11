package design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
//        int[][] grid = new int[3][3];
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                grid[i][j] = 1;
//            }
//        }
//        System.out.println(grid.length);
//        System.out.println(grid[0].length);
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println(grid[i][j]);
//            }
//        }


        List<Integer> cur = new ArrayList<>();
        Iterator<Integer> iter = cur.iterator();
        System.out.println(iter.hasNext());

        System.out.println(System.currentTimeMillis());

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Iterator<Integer> iter1 = stack.iterator();
        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }
//        rand rand = new rand();
//        System.out.println(rand.nextInt(3));


//        ArrayIterator x = new ArrayIterator(new int[]{4,3,2,1});
//        for (int i = 0; i < 5; i++) {
//            System.out.println(x.hasNext());
//            if (x.hasNext()) {
//                System.out.println(x.next());
//            }
//        }
    }
}
