package twopointers;

//这道题的方法还是挺巧妙的 如果想不到就记住吧
//最多两次，和cur-2位置的值比较...

public class RemoveDuplicatesfromSortedArratII {
    public int removeDuplicates(int[] nums) {
        int insertPos = 0;
        for (int n : nums) {
            if (insertPos < 2 || n != nums[insertPos-2]) {
                nums[insertPos++] = n;
            }
        }
        return insertPos;
    }
}
