package dfs;

//最直挂你的思路就是这样 我碰到一个B我check同一行和同一列所有的元素 如果碰到有B就标记一下 以后碰到的时候不用再去check
//时间复杂度 worst case应该是m*n

public class LonlyPixelI {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B' && !visited[i][j]) {
                    boolean found = false;
                    int row = 0;
                    while (row < m) {
                        if (row != i && picture[row][j] == 'B') {
                            found = true;
                            visited[row][j] = true;
                        }
                        row ++;
                    }
                    int col = 0;
                    while (col < n) {
                        if (col != j && picture[i][col] == 'B') {
                            found = true;
                            visited[i][col] = true;
                        }
                        col ++;
                    }
                    if (!found) {
                        res ++;
                    }
                }
            }
        }
        return res;
    }
}



//O(nm) time complexity O(n+m) space complexity
/*
    public int findLonelyPixel(char[][] picture) {
        int n = picture.length, m = picture[0].length;

        int[] rowCount = new int[n], colCount = new int[m];
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (picture[i][j] == 'B') { rowCount[i]++; colCount[j]++; }

        int count = 0;
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) count++;

        return count;
    }
*/



//O(nm) time complexity O(1) space complexity
/*
    public int findLonelyPixel(char[][] picture) {
        int n = picture.length, m = picture[0].length;

        int firstRowCount = 0;
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (picture[i][j] == 'B') {
                    if (picture[0][j] < 'Y' && picture[0][j] != 'V') picture[0][j]++;
                    if (i == 0) firstRowCount++;
                    else if (picture[i][0] < 'Y' && picture[i][0] != 'V') picture[i][0]++;
                }

        int count = 0;
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (picture[i][j] < 'W' && (picture[0][j] == 'C' || picture[0][j] == 'X')) {
                    if (i == 0) count += firstRowCount == 1 ? 1 : 0;
                    else if (picture[i][0] == 'C' || picture[i][0] == 'X') count++;
                }

        return count;
    }
*/