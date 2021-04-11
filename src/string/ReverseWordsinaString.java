package string;

//这道题主要还是在于题意的理解 word的定义 会不会有特殊字符 还是只以空格区分
//这道题的关键在于 对regex的熟悉程度 思路会很直接我们需要根据" "把string slit成一个string的array
//那么如果用split(" ")是一个空格分离一次，那么如果开头有空格也会影响string[]的长度 而且如果"try    this word"中间
//有很多空格也会影响string的长度
//所以我们要用到trim()以及"\\s+"

public class ReverseWordsinaString {
    public String reverseWords(String s) {
        //如果要通过 要这么改。。 看别人答案得到的
        //s.trim().split("\\s+")
        //trim()表示return a copy of this string with leading and trailing
        //white space removed
        // \\s+ 是regex expression 表示group所有的空格作为界定符
        String[] st = s.split(" ");
        for (int i = 0, j = st.length - 1; i < j; i++, j--) {
            String temp = st[i];
            st[i] = st[j];
            st[j] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < st.length; i++) {

            if (i == st.length - 1) {
                sb.append(st[i]);
            } else {
                sb.append(st[i]).append(" ");
            }
        }
        return sb.toString();
    }
}
