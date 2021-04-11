package binarysearch;

//挑战一下这么道题 尽量能通过
//觉得思路有 但是代码写不出来...



public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        if (l % 2 == 1) {
            return helper(nums1, nums2, 0, 0, (l+1)/2);
        } else {
            return (helper(nums1, nums2, 0, 0, l/2)
                    + helper(nums1, nums2, 0, 0, l/2+1)) / 2;
        }
    }
    private double helper(int[] A, int[] B, int startA, int startB, int k) {
        //什么时候return？
        if (startA >= A.length) {
            return B[startB+k-1];
        }
        if (startB >= B.length) {
            return A[startA+k-1];
        }
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }
        int aKey = startA+k/2-1 < A.length? A[startA+k/2-1] : Integer.MAX_VALUE;
        int BKey = startB+k/2-1 < B.length? B[startB+k/2-1] : Integer.MAX_VALUE;
        if (aKey < BKey) {
            return helper(A, B, startA+k/2, startB, k-k/2);
        } else {
            return helper(A, B, startA, startB+k/2, k-k/2);
        }
    }
}

/*
public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        if (l % 2 == 1) {
            return helper(nums1, nums2, 0, 0, (l+1)/2);
        } else {
            return (helper(nums1, nums2, 0, 0, l/2) + helper(nums1, nums2, 0, 0, l/2+1)) / 2;
        }
    }
    //我总是很纠结奇偶数处理的问题
    //比如我要找第3大的数
    //3/2=1 那我只能舍弃不到一半...但是其实不用纠结 最后的时间复杂度只要是logn就可以
    //而且我们只要处理好 我们剩下的是多少就行了
    private double helper(int[] A, int[] B, int startA, int startB, int k) {
        //什么时候return？
        if (startA >= A.length) {
            return B[startB+k-1];
        }
        if (startB >= B.length) {
            return A[startA+k-1];
        }
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }
        //如果超出边界的话 感觉很难想 超出必然舍弃另外没有超出的这边...
        //这点很难想 因为心里膈应感觉是不是要取个超出数组的最大值做比较 就会打破我们递归的节奏
        //但是其实想一想就算我们超出部分的都比没超出的部分小 后面迟早还是要舍弃的...
        //假设我们要找第K大的数 那么我们取A和B各K/2 小的部分我们舍弃 舍弃K/2
        //我们A超出了 然而其实A的元素比B的小 那B的那K/2的元素我们必然也是要舍弃的 因为如果你不舍弃
        //极端考虑 如果A只有一个元素 B还有很多元素 A的那个元素很小肯定要舍弃 但是B里面的K/2个元素也是要舍弃的
        //如果A的元素比较大 我们必然不能舍弃 还是要舍弃B的那K/2的部分
        int aKey = startA+k/2-1 < A.length? A[startA+k/2-1] : Integer.MAX_VALUE;
        int BKey = startB+k/2-1 < B.length? B[startB+k/2-1] : Integer.MAX_VALUE;
        if (aKey < BKey) {
            return helper(A, B, startA+k/2, startB, k-k/2);
        } else {
            return helper(A, B, startA, startB+k/2, k-k/2);
        }
    }
}

 */
