package string;

public class ReverseWordsinStringIII {
    public String reverseWords(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        String[] st = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < st.length; i++) {
            sb.append(reverse(st[i]));
            if (i != st.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    private String reverse(String s) {
        char[] ch = s.toCharArray();
        int i = 0;
        int j = ch.length - 1;
        while (i <= j) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i ++;
            j --;
        }
        return String.valueOf(ch);
    }
}
