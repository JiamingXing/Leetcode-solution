package binarysearch;

//还是老问题 不要被tag迷惑 其次对于这种新鲜的题 最好是学会慢慢脱掉外套找到题目的本质

public class KEmptySlots {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i]-1] = i+1;
        }
        int left = 0;
        int right = left + k + 1;
        int res = Integer.MAX_VALUE;
        for (int i = 1; right < days.length; i++) {
            if (days[i] > days[left] && days[i] > days[right]) {
                continue;
            }
            if (i == right) {
                res = Math.min(res, Math.max(days[left], days[right]));
            }
            left = i;
            right = i + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
