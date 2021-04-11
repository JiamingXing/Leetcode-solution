package dfs;

import java.util.*;

//greedy做法 不需要backtracking

public class ReconstructLtinerary {
    List<String> res;
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }
        //to-do
        return res;
    }
    private boolean helper(Map<String, PriorityQueue<String>> graph, String cur, List<String> path, int n) {
        if (res.size() == n+1) {
            //found result...
            res = new ArrayList<>(path);
            return true;
        }
        path.add(cur);
        String last = "";
        if (!graph.containsKey(cur)) {
            return false;
        }
        for (int i = 0; i < graph.get(cur).size(); i++) {
            String next = graph.get(cur).poll();
            if (helper(graph, next, path, n)) {
                return true;
            }

        }
        return false;
    }
}

//别人的代码
/*
public class ReconstructLtinerary {
    public List<String> findItinerary(String[][] tickets) {
        List<String> ans = new ArrayList<String>();
        if(tickets == null || tickets.length == 0) return ans;
        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
        for(int i = 0; i < tickets.length; i++) {
            if(!ticketsMap.containsKey(tickets[i][0])) ticketsMap.put(tickets[i][0], new PriorityQueue<String>());
            ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
        }

        String curr = "JFK";
        Stack<String> drawBack = new Stack<String>();
        for(int i = 0; i < tickets.length; i++) {
            while(!ticketsMap.containsKey(curr) || ticketsMap.get(curr).isEmpty()) {
                drawBack.push(curr);
                curr = ans.remove(ans.size()-1);
            }
            ans.add(curr);
            curr = ticketsMap.get(curr).poll();
        }
        ans.add(curr);
        while(!drawBack.isEmpty()) ans.add(drawBack.pop());
        return ans;
    }
}
 */
