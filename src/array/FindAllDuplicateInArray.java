package array;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicateInArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] == -1 || nums[i] == i+1) {
                i++;
            } else if (nums[nums[i]-1] == nums[i]) {
                //duplicate
                res.add(nums[i]);
                //duplicate settled
                nums[i] = -1;
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        FindAllDuplicateInArray f = new FindAllDuplicateInArray();
        System.out.println(f.findDuplicates(nums));
    }
}
