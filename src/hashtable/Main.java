package hashtable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        int[][] gird = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
//        NumberOfDistinctIslands n = new NumberOfDistinctIslands();
//        System.out.println(n.numDistinctIslands(gird));

        List<Integer> res = new ArrayList<>();
        System.out.println(System.identityHashCode(res));
        System.out.println("res has 0 element now");
        helper(res);
        System.out.println(res.size());
        System.out.println("res now still have 2 element now");
    }

    static void helper(List<Integer> res) {
        System.out.println(System.identityHashCode(res));
        res.add(1);
        res.add(2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("list in method has 10 elements");
        res = list;
        System.out.println(System.identityHashCode(res));
        System.out.println("let copy of reference point to list");
    }
}
