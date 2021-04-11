package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(2);
        System.out.println(set.iterator().next());
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.add(2);
        System.out.println(dq.pollLast());
    }
}
