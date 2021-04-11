package twopointers;

//这道题很有意思 有贪心算法的思想在里面 很难想到
//因为要求最大continer 我们从两头开始 计算公式是min(height[left], height[right]) * (right-left)
//然后移动两个指针，我们比较左右高度 因为一旦移动指针 那么(right - left)一定减小 所以我们应该尽量增大min()..
//所以我们要移动小的那一边

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return res;
    }
}
