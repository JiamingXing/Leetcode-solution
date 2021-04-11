package stack;

//要求构造一个max freq stack
//pop出来的永远是频率最高的那个
//并且如果出现tie 最后进来的element出栈

import java.util.*;

public class MaximumFrequencyStack {

    //这里用dequeue不可行啊 无法保证stack的性质
    //其实用一个stack就行了
    //就是比如你有3个5 那么1，2，3三个位置三个stack中都有5
    //我的天。。。这一步好巧妙 没有想到
    //我只想到了转移。。。
    Map<Integer, Stack<Integer>> bucket;
    Map<Integer, Integer> freqMap;
    int max = 0;

    public MaximumFrequencyStack() {
        this.bucket = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.max = 0;
    }

    public void push(int x) {
        freqMap.put(x, freqMap.getOrDefault(x,0)+1);
        if (freqMap.get(x) > max) {
            max = freqMap.get(x);
            bucket.put(max, new Stack<>());
        }
        bucket.get(freqMap.get(x)).add(x);
    }

    public int pop() {
        if (!bucket.containsKey(max)) {
            return -1;
        }
        int res = bucket.get(max).pop();
        freqMap.put(res, freqMap.get(res)-1);
        if (bucket.get(max).size() == 0) {
            max --;
        }
        return res;
    }
}


//自己的错误的想法
/*
public class MaximumFrequencyStack {

    //这里用dequeue不可行啊 无法保证stack的性质
    //其实用一个stack就行了
    //就是比如你有3个5 那么1，2，3三个位置三个stack中都有5
    //我的天。。。这一步好巧妙 没有想到
    //我只想到了转移。。。
    Map<Integer, Deque<Integer>> bucket;
    Map<Integer, Integer> freqMap;
    int max = 0;

    public MaximumFrequencyStack() {
        this.bucket = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.max = 0;
    }

    public void push(int x) {
        freqMap.put(x, freqMap.getOrDefault(x, 0)+1);
        int count = freqMap.get(x);
        if (!bucket.containsKey(count)) {
            bucket.put(count, new ArrayDeque<>());
        }
        bucket.get(count).add(x);
        if (count > max) {
            max = count;
        }
    }

    public int pop() {
        if (!bucket.containsKey(max)) {
            return -1;
        }
        Deque<Integer> dq = bucket.get(max);
        int res = dq.peekLast();
        if (freqMap.get(res) == 1) {
            freqMap.remove(res);
        } else {
            freqMap.put(res, freqMap.get(res)-1);
            bucket.computeIfAbsent(freqMap.get(res), x->new ArrayDeque<>()).add(res);
        }
        if (dq.size() == 0) {
            max = max - 1;
        }
        return res;
    }
}

 */
