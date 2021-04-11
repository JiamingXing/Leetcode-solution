package hashtable;

public class SubarraySumDivisiblebyK {
    public int subarraysDivByK(int[] A, int K) {
        int res = 0;
        int sum = 0;
        int[] mod = new int[K];
        mod[0] = 1;
        for (int a : A) {
            sum = (sum + a) % K;
            if (sum < 0) {
                sum += K;
            }
            res += mod[sum];
            mod[sum] ++;
        }
        return res;
    }
}
