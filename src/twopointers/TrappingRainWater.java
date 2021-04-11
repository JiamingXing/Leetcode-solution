package twopointers;

//拿到这道题之后你会怎么想？先有什么样的思路？
//会闪过用stack的思路？

//下面这种two pointer的思路 是从两头开始 不断更新leftMax以及rightMax
//每次计算leftMax rightMax较小的那边的rain 并且移动那一边直到相遇
//这是discuss里别人的思路 可是为什么能这么想？
//能想到的就是当前位置能装多高的水 是由min(leftMax, rightMax) - curHeight决定的
//首先根据木桶装水原理 两面高度取小 然后要求最多能装多少水 所以我们应该每次移动小的那根指针
//因为我只有移动小的才有可能保证接下来还没有结算的位置装的水尽肯能多

public class TrappingRainWater {
    public int trap(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (start <= end) {
            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);
            if (leftMax < rightMax) {
                max += leftMax - height[start];
                start ++;
            } else {
                max += rightMax - height[end];
                end --;
            }
        }
        return max;
    }
}
