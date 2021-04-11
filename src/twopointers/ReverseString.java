package com.jimmy.twopointers;

//做这种题不能更简单了但是最后return的室友总想着写成chs.toString()
//一个是 String.valueOf()  另一个是char[].toString() 有什么区别
//看toString() 返回的好像是地址？
//正解是char[] 是一个class extends from Object这个类() 但是他并没有override toString()这个方法

public class ReverseString {
    public String reverseString(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        char[] chs = s.toCharArray();
        int i = 0;
        int j = chs.length - 1;
        while (i < j) {
            char temp = chs[i];
            chs[i] = chs[j];
            chs[j] = temp;
            i ++;
            j --;
        }
        return String.valueOf(chs);
        //return chs.toString();
    }
}
