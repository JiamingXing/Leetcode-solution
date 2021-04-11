package string;

public class Main {
    public static void main(String[] args) {
        String s = "aaabbcc";
        String[] dict = {"aaa", "aab", "bc"};
        AddBoldTaginString x = new AddBoldTaginString();
        System.out.println(x.addBoldTag(s, dict));
    }
}
