package topologicalsort;

//根据给的这些words 我们先建图
//分区间进行比较的
//我觉得这种题属于有思路 但是不好写。。。

//这里只有26个字母 你要表示一个图 就不用map了
//用一个26*26的arr 就可以表示图了
//而且这里其实基本思路也有问题 不需要去分层比较 你直接前后比较更直接

//你要handle重复的情况 以及你有没有想过如果某一个char 在长string的最后 判断不到

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        boolean[][] graph = new boolean[26][26];
        int[] inDegree = new int[26];
        Arrays.fill(inDegree, -1);
        //需要统计total number of chars
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                set.add(c);
                //存在的char 我们都入度initialize成0
                inDegree[c-'a'] = 0;
            }
        }
        buildGraph(words, graph, inDegree);
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                Q.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            sb.append((char)('a'+cur));
            for (int i = 0; i < 26; i++) {
                if (graph[cur][i]) {
                    graph[cur][i] = false;
                    inDegree[i] --;
                    if (inDegree[i] == 0) {
                        Q.add(i);
                    }
                }
            }
        }
        //
        return sb.length() == set.size() ? sb.toString() : "";
    }
    private void buildGraph(String[] words, boolean[][] graph, int[] inDgree) {
        int n = words.length;
        //每个word只和前面一个比较
        //并且得到first position with different char
        //n个word  得到n-1条 edge
        for (int i = 0; i < n-1; i++) {
            String s1 = words[i];
            String s2 = words[i+1];
            int j = 0;
            while (j < Math.min(s1.length(), s2.length())) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if (c1 != c2) {
                    //如果出现重复的edge 我们就跳过
                    if (!graph[c1-'a'][c2-'a']) {
                        graph[c1-'a'][c2-'a'] = true;
                        inDgree[c2-'a'] ++;
                    }
                    break;
                }
                j++;
            }
        }
    }
}
