package hashtable;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    int x = i, y = j+1;
                    if (x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                        res -= 2;
                    }
                    x = i+1;
                    y = j;
                    if (x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                        res -= 2;
                    }
                }
            }
        }
        return res;
    }
}
