package backtracking;

import java.util.*;

public class Main {
    public String toString() {
        return "How address print works: " + this + "\n";
    }
//    public int[] tryToArray() {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        // return list.toArray(new Integer[0]);
//    }
    public static void main(String[] args) {
//        char[] ch = new char[5];
//        System.out.println(ch[0] == '\u0000');
//        char[][] board = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            Arrays.fill(board[i], '.');
//        }
        List<Integer> list = new LinkedList<>();
        LinkedList<Integer> lList = new LinkedList<>();
        list.add(1);
        // list.getFirst(); error... List interface does not have this method
        lList.getFirst();

        //System.out.println(list.size() == 0);
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.toString();
        Map<String, String> map = new HashMap<>();
        map.put("Dog", "Jacob");
        map.put("Cat", "Tony");
        map.put("Lion", "Jimmy");
        System.out.println(map);
        System.out.println(list);

    }
}
