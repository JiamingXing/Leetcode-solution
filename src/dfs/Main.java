package dfs;

public class Main {

    public static void main(String[] args) {
//        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
//        UniquePathsIII u = new UniquePathsIII();
//        List<List<String>> res = u.uniquePathsIII(grid);
//        System.out.println(u.res);
//        System.out.println(res.size());
//        for (String s : res.get(0)) {
//            System.out.println(s);
//        }

        int[][] A = {{0,1},{1,0}};
        ShortestBridge s = new ShortestBridge();
        System.out.println(s.shortestBridge(A));

        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString().isEmpty());
    }
}
