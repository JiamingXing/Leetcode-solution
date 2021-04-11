package sort;

//第一反应能想到的思路是对数组中的元素 按照一定规则进行排序 然后挨个粘贴起来
//其实这个sort的规则很简单。。。只是容易想的比较复杂 只要都瞻粘粘起来相互比较就行

//然后就是改写Comparator这个interface 我们需要改写这个interface里面的compare(T o1, T o2)方法
//规定了如果 o1 > o2我们的结果必须返回一个大于0的值 如果o1 < o2我们必须返回一个小于0的值
//这道题我们希望从大到小排序 所以我们需要把范围的顺序反过来，因为正常的sort是从小到大进行排序 规则是返回 o1 - o2类型
//o1 > o2返回正值，这样的compare函数进入sort中可以让我们要sort的数据结构从小到大排序

//在这里String这个class是imolement Comparable这个interface 所以我们可以改写compareTo函数

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        String[] st = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            st[i] = nums[i] + "";
        }
        //从大到小排序
        Arrays.sort(st, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String st1 = s1 + s2;
                String st2 = s2 + s1;
                return st2.compareTo(st1);
            }
        });
        if (st[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(st[i]);
        }
        return sb.toString();
    }
}
