package sort;

public class HIndex {
    public int hIndex(int[] citations) {
        return 1;
    }
}



//第一时间想到的是binary search的思路 但是随便一个case就会出错比如[100]这个例子我应该找到的是1
//不能光按照数组中的值进行划分 但是其实做这道题的时候很容易想到和快排很像
/*
public class HIndex {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int len = citations.length;
        int start = 0;
        int end = len-1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int h = citations[mid];
            int count = numRight(citations, h);
            if (count == h) {
                return h;
            } else if (count > h) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (numRight(citations, citations[end]) == citations[end]) {
            return citations[end];
        } else if (numRight(citations, citations[start]) == citations[start]) {
            return citations[start];
        } else {
            return 0;
        }
    }
    private int numRight(int[] citations, int target) {
        int i = 0;
        int len = citations.length;
        while (i < len && citations[i] < target) {
            i ++;
        }
        return len - i;
    }
}
*/
