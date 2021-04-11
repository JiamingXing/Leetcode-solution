package tree;

import java.util.*;

//假如你通过这个list 可以建好一个多叉树结构的图 你会怎么做
//问题是不是就转换成了lowest common ancestor of tree?
//变成我们dfs所有的child  如果碰到region1 或者 region2我们就return region1/region2
//那么所有的child  如果有两个返回结果不为null  返回当前node
//如果只有一个不为null  就返回那个非bull的node
//如果都是null那就返回 null
//这么一想好像行的通啊

public class SmallestCommonRegion {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, List<String>> tree = buildTree(regions);
        String root = findRoot(regions);
        return dfs(tree, root, region1, region2);
    }
    private Map<String, List<String>> buildTree(List<List<String>> regions) {
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> region : regions) {
            String root = region.get(0);
            if (!map.containsKey(root)) {
                map.put(root, new ArrayList<>());
            }
            for (int i = 1; i < region.size(); i++) {
                map.get(root).add(region.get(i));
            }
        }
        return map;
    }
    private String findRoot(List<List<String>> regions) {
        Set<String> set = new HashSet<>();
        for (List<String> region : regions) {
            set.add(region.get(0));
        }
        for (List<String> region : regions) {
            for (int i = 1; i < region.size(); i++) {
                set.remove(region.get(i));
            }
        }
        return set.isEmpty() ? null : set.iterator().next();
    }
    private String dfs(Map<String, List<String>> tree, String cur, String region1, String region2) {
        if (cur.equals(region1) || cur.equals(region2)) {
            return cur;
        }
        if (!tree.containsKey(cur)) {
            return null;
        }
        int count = 0;
        String res = null;
        for (String child : tree.get(cur)) {
            String next = dfs(tree, child, region1, region2);
            if (next != null) {
                res = count++ == 0 ? next : null;
            }
        }
        if (count == 2) {
            return cur;
        } else if (count == 1) {
            return res;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        //hard coding这个例子就为了找一个很小很小的笔误的bubg。。。哎不仔细啊
        SmallestCommonRegion s = new SmallestCommonRegion();
        Map<String, List<String>> map = new HashMap<>();
        map.put("Earth", new ArrayList<>());
        map.get("Earth").add("NA");
        map.get("Earth").add("SA");
        map.put("SA", new ArrayList<>());
        map.get("SA").add("Brazil");
        map.put("NA", new ArrayList<>());
        map.get("NA").add("United States");
        map.get("NA").add("Canada");
        map.put("United States", new ArrayList<>());
        map.get("United States").add("New York");
        map.get("United States").add("Boston");
        map.put("Canada", new ArrayList<>());
        map.get("Canada").add("Ontario");
        map.get("Canada").add("Quebec");
        System.out.println(s.dfs(map, "Earth", "Canada", "Quebec"));
    }
}


//这道题记得经常复习吧 你要知道lowest common ancestor原来可以这么做 XD。。。

//别人的做法感觉被秒杀。。。
//Build family tree from offsprings to their parents;
//Use a HashSet to construct ancestry history of region1;
//Retrieve ancestry of region2 by family tree till find the first common ancestry in ancestry history of region1.
/*
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> parents = new HashMap<>();
        Set<String> ancestryHistory = new HashSet<>();
        for (List<String> region : regions)
            for (int i = 1; i < region.size(); ++i)
                parents.put(region.get(i), region.get(0));
        while (region1 != null) {
            ancestryHistory.add(region1);
            region1 = parents.get(region1);
        }
        while (!ancestryHistory.contains(region2))
            region2 = parents.get(region2);
        return region2;
    }

 */
