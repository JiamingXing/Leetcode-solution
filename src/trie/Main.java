package trie;

public class Main {
    public static void main(String[] args) {
        String[] s1 = {"a","b","c","d"};
        String[] s2 = {"c","b","e"};
        FindRepeatingWordSortRest x = new FindRepeatingWordSortRest();
        String[] res = x.findRpeatingAndSort(s1, s2);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
