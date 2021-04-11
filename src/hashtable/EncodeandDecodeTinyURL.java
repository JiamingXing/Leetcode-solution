package hashtable;

//对于encode decode这种题...一般情况是会想着往data compression方向想
//如何让不同的element 进行一定处理变成unique的一种东西
//但是这道题的思路不应该是这样
//https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/
//这个链接的解答和评论都可以好好看一下
//而是一种把long url 和 short url的key 存在database中的一种一一对应关系
//比如我们从面试官的交流中得到 我们可以用7位的char来组成我们要的short url的key from[a-z A-Z 0-9]那么我们就能对应map 62^7个url

//那么我们的思路是不是转变成 我们有0-(62^7-1)种hashcode 我们只要通过long URL 得到一个unique的hashcode 然后不断除以62
//就可以得到一个unique的short URL key....同时我们也可以用一个hashmap 把他们建立联系
//那么问题在于如何通过一个long url得到一个尽可能少collision的hashcode....
//有一个solution就是把long URL的每一位char加起来...但是觉得collision会非常严重 比如http://www.abcd.com  http://www.adcb.com

//感觉下面的做法还是没有很好的解决collision的问题
//链接的main solution中提到他的这个ID是通过 database的primary key自动生成递增的

//further question:
//如果面试官问你 我需要存储的longUrl很多很多的时候...该如何处理
//If we are dealing with massive data of our service, distributed storage can increase our capacity.
//The idea is to get a hashCode from longUrl and mapping to corresponding machine then process...
//we need to add a additional identifier to shortUrl, so we can find the corresponding machine from the identifier
//than get the right long url....

import java.util.HashMap;
import java.util.Map;

public class EncodeandDecodeTinyURL {
    private final String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int LEN = 7;
    private final String prefix = "http://www.tinyurl.com/";
    private Map<String, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int hCode = longUrl.hashCode();
        StringBuilder sb = new StringBuilder();
        while (hCode > 0 && sb.length() < LEN) {
            sb.append(base.charAt(hCode%base.length()));
            hCode /= base.length();
        }
        map.put(prefix+sb.toString(), longUrl);
        return prefix+sb.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (map.containsKey(shortUrl)) {
            return map.get(shortUrl);
        }
        return null;
    }
}
