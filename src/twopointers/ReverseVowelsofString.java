package twopointers;

import java.util.HashSet;
import java.util.Set;

//这道题主要在于你要从两头开始移动两根指针 然后同时找到是元音字符的那个 通过while循环

public class ReverseVowelsofString {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        String vowels = "aeiouAEIOU";
        char[] vowChar = vowels.toCharArray();
        for (int i = 0; i < vowChar.length; i++) {
            set.add(vowChar[i]);
        }
        char[] ch = s.toCharArray();
        int i = 0;
        int j = ch.length - 1;
        while (i < j) {
            if (!set.contains(ch[i])) {
                i ++;
            } else if (!set.contains(ch[j])) {
                j --;
            } else {
                swap(ch, i, j);
                i ++;
                j --;
            }
        }
        return String.valueOf(ch);
    }
    private void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}


/*
public class ReverseVowelsofString {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        String vowels = "aeiouAEIOU";
        char[] vowChar = vowels.toCharArray();
        for (int i = 0; i < vowChar.length; i++) {
            set.add(vowChar[i]);
        }
        char[] ch = s.toCharArray();
        for (int i = 0, j = ch.length -1; i < j; i++, j-- ) {
            //从左到右找元音
            while (i < j && !set.contains(ch[i])) {
                i ++;
            }
            //从右到左找元音
            while (i < j && !set.contains(ch[j])) {
                j--;
            }
            //交换
            swap(ch, i, j);
        }
        return String.valueOf(ch);
    }
    private void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
*/
