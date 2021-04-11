package string;

import java.util.Arrays;

//这道题要注意的是 可能会超出int的范围 要考虑这一点

public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] ch = (n + "").toCharArray();
        int i = 0;
        while (i + 1 < ch.length && ch[i] >= ch[i+1]) {
            i ++;
        }
        if (i == ch.length - 1) {
            return -1;
        }
        int temp = ch[i] - '0';
        int pos = i++;
        int minMax = Integer.MAX_VALUE;
        int index = 0;
        while (i < ch.length) {
            if (ch[i] - '0' > temp && ch[i] - '0' < minMax) {
                minMax = ch[i] - '0';
                index = i;
            }
            i ++;
        }
        char mid = ch[pos];
        ch[pos] = ch[index];
        ch[index] = mid;
        Arrays.sort(ch, pos+1, ch.length);
        long res = Long.parseLong(String.valueOf(ch));
        return res <= Integer.MAX_VALUE ? (int) res : -1;
    }
}
