package unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberofIslandsII {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parent = new int[m*n];
        boolean[][] visited = new boolean[m][n];
        Arrays.fill(parent, -1);
        int count = 0;
        List<Integer> res = new ArrayList<>();
        for (int[] position : positions) {
            count ++;
            int x = position[0];
            int y = position[1];
            visited[x][y] = true;
            int index = x * n + y;
            for (int[] dir : dirs) {
                int row = x + dir[0];
                int col = y + dir[1];
                if (row < 0 || col < 0 || row >= m || col >= n || !visited[row][col]) {
                    continue;
                }
                int temp = row * n + col;
                if (find(parent, index) == find(parent, temp)) {
                    continue;
                }
                parent[find(parent, index)] = find(parent, temp);
                count --;
            }
            res.add(count);
        }
        return res;
    }
    private int find(int[] parent, int id) {
        if (parent[id] == -1) {
            return id;
        }
        parent[id] = find(parent, parent[id]);
        return parent[id];
    }
}
