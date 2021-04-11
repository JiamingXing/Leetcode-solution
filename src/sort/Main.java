package sort;

public class Main {
    public static void main(String[] args) {
        QuickSort s = new QuickSort();
        int[] nums = new int[]{3,6,1,4,1,8};
        s.quickSort(nums);
        for (int n : nums) {
            System.out.println(n);
        }
        System.out.println("a".compareTo("b"));
    }
}
