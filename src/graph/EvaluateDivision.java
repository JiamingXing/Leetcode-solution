package graph;

//首先你要知道如何构图 vertice之间的edge如果保存 以及对应的权重（有向无向）

//写完这道题 首先建图方面没有经验 一开始想的是建立一个graphNode的class 里面有一个list的field和一个当前值得field
//后来仔细想了想还不如用一个map建图 String作为key 然后所有指向的点以及对应的权重存到一个list中
//其次建好图 之后也考虑到了a/b=2 b/a=0.5这种情况双向建图
//那么接下来就要做DFS 在写DFS的时候感觉考虑了很多情况 把自己绕晕了。。。还要再熟练一下
//其实相当于一个backtracking的过程 当前节点访问过了我加入一个visited的set中 避免重复访问
//在这个地方当时脑子里出现了一个很奇怪的思路就是我作为所有邻节点的dfs之后不需要移除？？？？
//发现答案还是加了remove 但是我这里没加因为 确实不管到什么地方重新访问当前的地方都是重复操作(这个不remove可以加分！)
//接下来是返回类型的问题 一开始自己想的也是用double作为返回类型 但是感觉如果找到返回什么找不到返回什么
//在这个地方纠结了很久 又写了个ResultType的class 记录是否找到target以及路径权重值
//其实double也是OK的 discuss里一种写法就是把所有情况都放进dfs里面进行考虑这样写起来代码量更少
//可以参照一下discuss里的写法 但是既然自己的写法也AC了也没问题
//AC之前出了一次错误就是对于a/b c/d 求a/c的情况 其实一开始不在提前考虑的两种case中 但是意味着如果不存在的时候的resulttype返回的值也不能栾秀娥
//必须是-1.0才可以 这个地方当时没考虑到

import java.util.*;

public class EvaluateDivision {
    private class Pair {
        String node;
        double weight;
        public Pair(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    private class ResultType {
        boolean found;
        double result;
        public ResultType(boolean found, double result) {
            this.found = found;
            this.result = result;
        }
    }
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            String num = equation[0];
            String denom = equation[1];
            if (!map.containsKey(num)) {
                map.put(num, new ArrayList<>());
            }
            map.get(num).add(new Pair(denom, values[i]));
            if (!map.containsKey(denom)) {
                map.put(denom, new ArrayList<>());
            }
            map.get(denom).add(new Pair(num, 1.0/values[i]));
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            String num = query[0];
            String denom = query[1];
            if (!map.containsKey(num) || !map.containsKey(denom)) {
                res[i] = -1.0;
                continue;
            }
            if (num.equals(denom)) {
                res[i] = 1.0;
                continue;
            }
            Set<String> visited = new HashSet<>();
            res[i] = dfs(map, visited, num, denom, 1.0).result;
        }
        return res;
    }
    private ResultType dfs(Map<String, List<Pair>> map, Set<String> visited, String cur, String target, double path) {
        if (cur.equals(target)) {
            return new ResultType(true, path);
        }
        if (visited.contains(cur)) {
            return new ResultType(false, 0.0);
        }
        visited.add(cur);
        for (Pair pair : map.get(cur)) {
            ResultType temp = dfs(map, visited, pair.node, target, path*pair.weight);
            if (temp.found) {
                return temp;
            }
        }
        return new ResultType(false, -1.0);
    }
}




//BFS
/*
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int length = queries.length;
        HashMap<String, HashMap<String, Double>> graph = makeGraph(equations, values);

        double[] ret = new double[length];
        for (int i = 0; i < length; i++) {
            String s = queries[i][0];
            String d = queries[i][1];
            if(s.equals(d) && graph.containsKey(s))
                ret[i] = 1.0;
            else{
                HashSet<String> visited = new HashSet<>();
                ret[i] = bfs(graph, s, d, visited);
            }

        }
        return ret;
    }

    private HashMap<String, HashMap<String, Double>> makeGraph(String[][] equations, double[] values) {
        int length = equations.length;
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < length; i++) {
            String s = equations[i][0], v = equations[i][1];
            double w = values[i];
            HashMap<String, Double> neighbors = graph.getOrDefault(s, new HashMap<>());
            neighbors.put(v, w);
            graph.put(s, neighbors);

            HashMap<String, Double> reverseNeighbors = graph.getOrDefault(v, new HashMap<>());
            reverseNeighbors.put(s, 1/w);
            graph.put(v, reverseNeighbors);
        }
        return graph;
    }

    private double bfs(HashMap<String, HashMap<String, Double>> graph, String s, String d, HashSet<String> visited) {
        HashMap<String, Double> neighbors = graph.getOrDefault(s, new HashMap<>());
        while (neighbors.size() > 0) {
            HashMap<String, Double> nextNeighbors = new HashMap<>();
            for (Map.Entry<String, Double> stringDoubleEntry : neighbors.entrySet()) {
                String k = stringDoubleEntry.getKey();
                Double v = stringDoubleEntry.getValue();
                if (k.equals(d)) {
                    return v;
                }
                HashMap<String, Double> tmp = graph.getOrDefault(k, new HashMap<>());
                for (Map.Entry<String, Double> doubleEntry : tmp.entrySet()) {
                    String tmpKey = doubleEntry.getKey();
                    if(visited.contains(tmpKey))
                        continue;
                    visited.add(tmpKey);
                    nextNeighbors.put(tmpKey, doubleEntry.getValue() * v);
                }
            }
            for (Map.Entry<String, Double> stringDoubleEntry : nextNeighbors.entrySet()) {
                neighbors.put(stringDoubleEntry.getKey(), stringDoubleEntry.getValue());
            }
            neighbors = nextNeighbors;
        }
        return -1.0;
    }
*/