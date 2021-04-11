package heap;


import java.util.*;

//这道题主要学习 怎么在两种排序规则下写自定义的comparator

//首先下面的代码是错误的 因为根本没有理解 compare这个函数改写的规则是什么 都是感觉是1-2或者2-1就行了
//好好思考下

//首先这道题有几个地方容易让人混淆：
//首先 要求是K frequent 那我们如果根据频率进行排序 最好是从小到大排 这样我们通过poll把频率小的word排除维持当前queue
//k个word都是相对最大频率
//其次是他要求输出的是频率从大到小排序 那么我们就要用list.add(0, poll) 这样保证小的往后排
//还有一点就是按照字母顺序 字母排序从小到大，但是很关键一点是 这个顺序实在频率从大到小的基础上 所以我们在写Comparator的时候
//字母排序应该是从大到小！

//这个思路理清楚之后，发现自己还是没AC 就是在最后将pq中元素加入到list中的时候 不能用for (temp : pq) -> 而是要一个个poll出来
//这个是为什么？还没完全整明白
//但是关于改写Comparator里的comapre函数 比如O(o1, o2) 假如是return o1 - o2表示这个是按照从小到大进行排序的
//写的时候和o1-o2的具体结果没关系 只和他们顺序谁在前谁在后有关

//关于PQ 时间复杂度怎么分析？ 空间复杂度怎么分析？ O(nlogk)..?
//follow up: try to solve it in O(nlogK) time and O(n) extra space....

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
//        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
//            @Override
//            public int compare(Pair o1, Pair o2) {
//                return o1.count == o2.count ? o2.word.compareTo(o1.word) : o1.count - o2.count;
//            }
//        });
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a,b)->a.count == b.count ? b.word.compareTo(a.word) : a.count-b.count);
        for (String word : map.keySet()) {
            pq.add(new Pair(word, map.get(word)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        for (Pair temp : pq) {
            res.add(0, temp.word);
        }
        return res;
    }
    private class Pair {
        String word;
        int count;
        Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}


//solution的话就不需要自己写一个pair的class
//因为Map本身就有个函数 map.entrySet() 返回的是一个Map.Entry<K, V>类型的set
//Map.Entry中有getKey()和getValue()两个可以调用的实用函数
/*
class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++)
        {
            if(map.containsKey(words[i]))
                map.put(words[i], map.get(words[i])+1);
            else
                map.put(words[i], 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for(Map.Entry<String, Integer> entry: map.entrySet())
        {
            pq.offer(entry);
            if(pq.size()>k)
                pq.poll();
        }

        while(!pq.isEmpty())
            result.add(0, pq.poll().getKey());

        return result;
    }
}
*/
