package string;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m+n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int pos1 = i + j + 1;
                int pos2 = i + j;
                mul += res[pos1];
                res[pos1] = mul % 10;
                res[pos2] = mul / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int temp : res) {
            if (sb.length() == 0 && temp == 0) {
                continue;
            }
            sb.append(temp);
        }
        //考虑0乘以任何数都是0的情况 整个res里都是0
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
