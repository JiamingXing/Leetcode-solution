package array;

//给一个array 能不能找到一个triple i<j<k a[i] < a[j] < a[k]
//我们只需要找是否存在这一的triple
//最笨的方法肯定是check所有可能的triple
//时间O(n^3)
//解下来想如何优化 你可能会去想要么优化到O(n^2 logn) 或者O(n^2) logn一般涉及binary search
//因为数组不是sorted的所以你可能需要往O(n^2)去想了
//假如我fix一个i 遍历所有j 这里已经是O(n^2)了。。那么你能快速告诉我是否存在K的问题吗
//后来想到了 如果我用一个max array存从数组最右边到当前的最大值
//因为我只需要知道从j+1开始是否有一个数可以大于a[j]就可以了。。

//这里也可以用一个min 一个max
//min代表从左边到当前的最小值  max代表从右边到当前的最大值
//那么我们check n个 中间值 然后去找 左边的min和右边的max是否满足条件也可以做到
//O(n)的复杂度

public class FindTriple {
    public boolean canFind(int[] arr) {
        int n = arr.length;
        int[] max = new int[n];
        max[n-1] = arr[n-1];
        for (int i = n-1; i >= 0; i--) {
            max[i] = Math.max(max[i+1], arr[i]);
        }
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                if (arr[i] < arr[j] && max[j+1] > arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    //如果follow up我需要找到这个triple的最大值 如果没有返回1怎么做？
    //目前看 好像思路还是一样 也是O(n^2)
    //看到另一种解法 就是我每次fix中间的值
    //然后从两边找 最大值 也是O(n^2)
    public int findMaximumTriple(int[] arr) {
        int n = arr.length;
        int res = 0;
        for (int i = 1; i < n-1; i++) {
            int max1 = 0;
            int max2 = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    max1 = Math.max(arr[j], max1);
                }
            }
            for (int j = i+1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    max2 = Math.max(arr[j], max2);
                }
            }
            res = Math.max(res, arr[i]+max1+max2);
        }
        return res;
    }
}
