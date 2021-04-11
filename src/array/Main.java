package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.computeIfAbsent(1, x->new ArrayList<>()).add(2);
        System.out.println(map.get(1).get(0));
    }
}
