package string;

import java.util.ArrayList;
import java.util.List;

//这种题目拿到手都会感觉没有一点思路 我们怎么区分这些String
//下面的代码是来自别人的思路 就是我把所有的String加在一起 但是每个字符串前面加一个"tag"
//这个tag由字符串的长度以及一个/组成 我们decode可以通过读取第一个/以及前面所表示的长度找到下一个
//字符串开始的位置 循环操作就可以decode （听说有点像压缩算法？）

public class EncodeandDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            int len = s.length();
            sb.append(len);
            sb.append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int start = 0;
        int pos = s.indexOf('/', start);
        while (pos != -1) {
            int len = Integer.parseInt(s.substring(start, pos));
            res.add(s.substring(pos+1, pos+len+1));
            start = pos+len+1;
            pos = s.indexOf('/', start);
        }
        return res;
    }
}
