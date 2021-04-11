package binarysearch;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][n-1] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (matrix[start][n-1] == target) {
            return true;
        } else if (matrix[start][n-1] < target) {
            return false;
        }
        int row = start;
        start = 0;
        end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (matrix[row][start] == target) {
            return true;
        } else {
            return false;
        }
    }
}


//相当于把二维数组转换成一维 一一对应关系
/*
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (matrix[start/n][start%n] == target) {
            return true;
        } else if (matrix[end/n][end%n] == target) {
            return true;
        } else {
            return false;
        }
    }
}
*/