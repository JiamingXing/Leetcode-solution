package vote;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int num1 = nums[0];
        int num2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int len = nums.length;
        // find the most two elements
        for (int n : nums) {
            if (n == num1) {
                count1 ++;
            } else if (n == num2) {
                count2 ++;
            } else if (count1 == 0) {
                num1 = n;
                count1 ++;
            } else if (count2 == 0) {
                num2 = n;
                count2 ++;
            } else {
                count1 --;
                count2 --;
            }
        }
        //initialize to 0 to count exact number of these two elements
        count1 = 0;
        count2 = 0;
        for (int n : nums) {
            if (n == num1) {
                count1 ++;
            } else if (n == num2) {
                count2 ++;
            }
        }
        if (count1 > len / 3) {
            res.add(num1);
        }
        if (count2 > len / 3) {
            res.add(num2);
        }
        return res;
    }
}
