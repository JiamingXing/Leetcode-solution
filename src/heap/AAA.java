package heap;

import java.util.ArrayList;
import java.util.List;

public class AAA {
    int i;
    int j;
    public AAA (int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static void main(String[] args) {
//        Set<AAA> set = new HashSet<>();
//        AAA a = new AAA(1, 1);
//        System.out.println(a.toString());
//        set.add(new AAA(1, 1));
//        set.add(new AAA(1, 1));
//        System.out.println(set.size());
//        int[] ch = {1,2,3};
//        int[] arr = {1,2,3};
//        System.out.println(Arrays.equals(ch, arr));
        char c = 'a';
        char c2 = 'A';
        System.out.println(Character.isLetter(c));
        System.out.println(Character.isLetter(c2));
        System.out.println(Character.isAlphabetic(c));
        System.out.println(Character.isAlphabetic(c2));
        Character.toLowerCase(c);
        List<Integer> list = new ArrayList<>();
        list.hashCode();
    }
}
