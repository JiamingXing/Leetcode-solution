package stack;

//可以用stack做 主要思路也是当前位置装水的多少取决于左边最高的bar以及右边最高的bar
//你能不能发现 对于某一个点装的水 应该是左边的最大值和右边的最大值取最小然后减去当前的高度
//根据这个思路 二刷的时候写了一个two pass的版本 和多米诺骨牌那些题比较像

//class Solution {
//    public int trap(int[] height) {
//        int n = height.length;
//        int[] leftMax = new int[n];
//        int[] rightMax = new int[n];
//        int max = 0, res = 0;
//        for (int i = 0; i < n; i++) {
//            if (max <= height[i]) {
//                leftMax[i] = height[i];
//                max = height[i];
//            } else {
//                leftMax[i] = max;
//            }
//        }
//        max = 0;
//        for (int i = n-1; i >= 0; i--) {
//            if (max <= height[i]) {
//                max = height[i];
//                rightMax[i] = height[i];
//            } else {
//                rightMax[i] = max;
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            if (Math.min(leftMax[i], rightMax[i]) >= height[i]) {
//                res += (Math.min(leftMax[i], rightMax[i]) - height[i]);
//            }
//        }
//        return res;
//    }
//}

//其实和上面的版本是一样的。。合并了两个pass写出来而已
//

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
