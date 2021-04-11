package hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomPickWithBlacklist {
    Map<Integer, Integer> map = new HashMap<>();
    int M;
    Random rand = new Random();

    public RandomPickWithBlacklist(int N, int[] blacklist) {
        for (int b : blacklist) {
            map.put(b, -1);
        }
        this.M = N-map.size();
        for (int b : blacklist) {
            if (b < M) {
                while (map.containsKey(N-1)) {
                    N--;
                }
                map.put(b, N-1);
                N--;
            }
        }
    }

    public int pick() {
        int i = rand.nextInt(M);
        if (map.containsKey(i)) {
            return map.get(i);
        }
        return i;
    }
}
