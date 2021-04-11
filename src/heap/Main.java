package heap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        List<Integer> l = new ArrayList<>();
//        System.out.println(l.iterator().next());

//        Map<String, String> map = new HashMap<>();
//        map.put("dog", "dog");
//        map.put("cat", "cat");
//        System.out.println(map);
//
//        Set<Integer> set = new HashSet<>();
//        set.add(3);
//        set.add(1);
//        set.add(5);
//        set.add(1);
//        set.add(7);
//        System.out.println(set);
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        char c = 'a';
        System.out.println((char)(c+1));

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println();
        ListIterator iter = list.listIterator();
        System.out.println(iter.next());
        System.out.println(iter.hasPrevious());
        System.out.println(iter.previous());
    }
}
