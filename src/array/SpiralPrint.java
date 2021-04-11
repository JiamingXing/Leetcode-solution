package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpiralPrint {
    private static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    List<Integer> spiralPrint(int[][] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int m = nums.length;
        int n = nums[0].length;
        Random rand = new Random();
        int x = rand.nextInt(m);
        int y = rand.nextInt(n);
        res.add(nums[x][y]);
        int step = 1;
        int count = 0;
        int dirIndex = 0;
        while (true) {
            int[] dir = dirs[dirIndex];
            x += dir[0];
            y += dir[1];
            if (x < 0 || y < 0 || x >= m || y >= n) {
                break;
            }
            res.add(nums[x][y]);
            if (++count == step) {
                dirIndex = dirIndex+1 == 4 ? 0 : dirIndex+1;
                count = 0;
                if (dirIndex == 2 || dirIndex == 0) {
                    step++;
                }
            }
        }
        return res;
    }
}
