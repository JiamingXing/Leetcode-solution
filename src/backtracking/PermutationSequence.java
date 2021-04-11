package backtracking;
import java.util.ArrayList;
import java.util.List;

/*
    LeetCode 60. Permutation Sequence
 */

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> frac = new ArrayList<>();
        int[] num = new int[n+1];
        num[0] = 1;
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            frac.add(i);
            sum *= i;
            num[i] = sum;
        }
        StringBuilder sb = new StringBuilder();
        k --;
        while (n != 0) {
            int ind = k / num[n-1];
            sb.append(frac.get(ind));
            frac.remove(ind);
            k = k % num[n-1];
            n --;
        }
        return sb.toString();
    }
}
