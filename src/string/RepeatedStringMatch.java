package string;

public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() < B.length()) {
            sb.append(A);
            count ++;
        }
        if (sb.toString().indexOf(B) != -1) {
            return count;
        } else if (sb.append(A).toString().indexOf(B) != -1) {
            return ++count;
        } else {
            return -1;
        }
    }
}
