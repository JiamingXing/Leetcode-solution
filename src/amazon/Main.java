package amazon;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] st = {"abc", "def"};
        boolean b = true;
        List<String> list = Arrays.asList(st);
        System.out.println(String.valueOf(b));


//        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[1] - o2[1];
//            }
//        });
//        int[][] nums = {{1,2000},{2,1000},{3,5000},{4,500}};
//        Arrays.sort(nums, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[1] - o2[1];
//            }
//        });
//        Arrays.sort(nums, (a, b) -> a[1] - b[1]);
//        for (int[] ar : nums) {
//            System.out.println(ar[1]);
//        }



//        String[][] ss = {{"grfg", "echo", "dot","zzz"},{"grfg", "echo", "dot"},{"a4fg", "kiddle", "paperwhite"},{"1345", "amazon", "basics"}};
//        List<List<String>> orders = new ArrayList<>();
//        for (String[] s : ss) {
//            List<String> list = new ArrayList<>();
//            for (String detail : s) {
//                list.add(detail);
//            }
//            orders.add(list);
//        }
//        SortAmazonOrders or = new SortAmazonOrders();
//        or.sortOrders(orders);
//        for (List<String> list : orders) {
//            for (String detail : list) {
//                System.out.println(detail);
//            }
//        }
//
//        //you can define a char variable as class field without assigning values
//        //but you can not define a char variable without assgining values in method scope?
//        System.out.println(or.c == ' ');
//        System.out.println(or.c == '\u0000');
    }
}
