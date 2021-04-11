package binarysearch;

//find largest i  -> citations[l-i+1] >= i; citations[l-i] < i
//这个思路可能稍微走错了一些 应该转换成找index 可能更好一点

//这道题有哪些corner case 可以考虑
//[1] [0]

public class HIndexII {
    //脑壳想疼了 出来的错误写法...先放一放不做这个题了
    public int hIndex(int[] citations) {
        int l = citations.length;
        int start = 0;
        int end = l-1;
        while (start+1 < end) {
            int mid = start + (end-start)/2;
            if (citations[mid] < l-mid) {
                start = mid;
            } else if (citations[mid-1] >= l-mid) {
                end = mid;
            }
        }
        if (citations[start] >= l-start) {
            return l- start;
        } else {
            return l-end;
        }
    }

    public static void main(String[] args) {
        HIndexII h = new HIndexII();
        int[] citations = {0};
        System.out.println(h.hIndex(citations));
    }
}



/*
public int hIndex(int[] citations) {
    int len = citations.length;
    int lo = 0, hi = len - 1;
    while (lo <= hi) {
        int med = (hi + lo) / 2;
        if (citations[med] == len - med) {
            return len - med;
        } else if (citations[med] < len - med) {
            lo = med + 1;
        } else {
            //(citations[med] > len-med), med qualified as a hIndex,
            // but we have to continue to search for a higher one.
            hi = med - 1;
        }
    }
    return len - lo;
}
*/