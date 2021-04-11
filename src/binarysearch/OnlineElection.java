package binarysearch;

//这道题当时没过是因为corner case 比如前面4个数是0110 那么领先的是0还是1的问题..

public class OnlineElection {

    int[] leading;
    int[] times;

    public OnlineElection(int[] persons, int[] times) {
        //通过persons形成一个 相同长度的数组记录每个时间点 最多得票
        //有一个选票算法好像

        //自己的算法有个很严重的错误 比如 0 0 1 1 2的时候 最后这个2会变成leading...
        this.times = times;
        leading = new int[persons.length];
        int top = persons[0];
        int count = 1;
        leading[0] = top;
        for (int i = 1; i < persons.length; i++) {
            if (persons[i] == top) {
                count ++;
            } else {
                //if a tie, mose recent voted person is leading
                if (count == 1) {
                    top = persons[i];
                    count --;
                } else {
                    count --;
                }

                if (count <= 1) {
                    top = persons[i];
                    count = count == 1? 0 : count;
                } else {
                    count --;
                }
            }
            leading[i] = top;
        }
    }

    public int q(int t) {
        //binary search find the last index <= t
        int i = binarySearch(times, t);
        return leading[i];
    }

    private int binarySearch(int[] times, int t) {
        int start = 0;
        int end = times.length-1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (times[mid] == t) {
                return mid;
            } else if (times[mid] > t) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (times[end] <= t) {
            return end;
        } else {
            return start;
        }
    }
}

// 正确做法： hashmap记录
/*
class TopVotedCandidate {

    int[] leading;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        leading = new int[persons.length];
        int top = -1;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < persons.length; i++) {
            int cur = persons[i];
            count.put(cur, count.getOrDefault(cur, 0)+1);
            if (i == 0 || count.get(cur) >= count.get(top)) {
                top = cur;
            }
            leading[i] = top;
        }
    }

    public int q(int t) {
        int i = binarySearch(times, t);
        return leading[i];
    }

    private int binarySearch(int[] times, int t) {
        int start = 0;
        int end = times.length-1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (times[mid] == t) {
                return mid;
            } else if (times[mid] > t) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (times[end] <= t) {
            return end;
        } else {
            return start;
        }
    }
}
 */
