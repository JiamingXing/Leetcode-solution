package unionfind;

//初步想法所有的word 互相比较？O(m^2) * n的时间复杂度 或者对于每个单词 我们check 所有可能的similar word 然后去set查询
//加上union find解决问题 貌似还可以做个trade off  根据word的长度已经dictionary的size 可以选用哪一种
//有没有更好的办法？

public class SimilarStringGroups {
    public int numSimilarGroups(String[] A) {
        int n = A.length, res = n;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                //加这一步会快一点 因为不用去check 是否similar了 find只是O(1)的复杂度
                //两个word已经是一个group的
                if (find(parent, i) == find(parent, j)) {
                    continue;
                }
                if (isSimilar(A[i], A[j])) {
                    if(union(parent, i, j)) {
                        res --;
                    }
                }
            }
        }
        return res;
    }
    private boolean isSimilar(String s1, String s2) {
        int res = 0;
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) != s2.charAt(i)) {
                res ++;
            }
        }
        return res == 2 || res == 0;
    }
    private boolean union(int[] parent, int i, int j) {
        int root_i = find(parent, i);
        int root_j = find(parent, j);
        if (root_i != root_j) {
            parent[root_i] = root_j;
            return true;
        }
        return false;
    }
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
