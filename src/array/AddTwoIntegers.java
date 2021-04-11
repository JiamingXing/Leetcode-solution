package array;

public class AddTwoIntegers {
    public int[] addTwoIntegers(int[] A, int [] B) {
        int m = A.length;
        int n = B.length;
        int i = m-1;
        int j = n-1;
        int carry = 0;
        int[] res = new int[Math.max(m,n) + 1];
        int index = res.length - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = (i < 0 ? 0 : A[i]) + (j < 0 ? 0 : B[j]) + carry;
            res[index--] = sum % 10;
            carry = sum / 10 == 1 ? 1 : 0;
            i = i < 0 ? i : i-1;
            j = j < 0 ? j : j-1;
        }
        //判断第一位是否为0 不是0return res 是0 copy后面出来返回 懒得写了
        return res;
    }
}
