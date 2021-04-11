package stack;

//其实可以想到这道题的思路 有点greedy的思路在里面
//因为从左到右数字的权重是越来越小的
//我们可以从左到右进行遍历，如果是递增的我们不做改动
//如果碰到某一位比前面小，那么我们删除前面的数字直到大于当前的数字
//删除k个数字就停止 或者我们遍历了整个数组
//看到stack的tag会有点犹豫 但是其实想清楚自己的思路和stack一样
//我们就是不断更新stack 维持一个递增栈

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int len = num.length() - k;
        char[] res = new char[num.length()];
        int pos = 0;
        for (int i = 0; i < num.length(); i++) {
            char cur = num.charAt(i);
            while (pos > 0 && res[pos-1] > cur && k > 0) {
                pos --;
                k --;
            }
            res[pos++] = cur;
        }
        //test leqading zero
        int index = 0;
        while (index < len && res[index] == '0') {
            index ++;
        }
        return index == len ? "0" : String.valueOf(res).substring(index, len);
    }
}
