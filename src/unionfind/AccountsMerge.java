package unionfind;

//这道题 用union-find，DFS，BFS都可以做 想想为什么卡住了 首先给的这个input很不熟悉

import java.util.*;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();
        //parent of each set as key to store
        Map<String, TreeSet<String>> disjointSets = new HashMap<>();
        //initialize
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                //initialize union find
                parent.put(account.get(i), account.get(i));
                //initial root
                owner.put(account.get(i), account.get(0));
            }
        }
        //union among every account
        for (List<String> account : accounts) {
            String p = find(account.get(1), parent);
            for (int i = 2; i < account.size(); i++) {
                //union
                parent.put(find(account.get(i), parent), p);
            }
        }
        //union all emails to disjoint sets
        for (List<String> account : accounts) {
            //String p = find(account.get(1), parent);
            for (int i = 1; i < account.size(); i++) {
                //disjointSets.getOrDefault(find(account.get(i), parent), new HashSet<>()).add(account.get(i));
                if (!disjointSets.containsKey(find(account.get(i), parent))) {
                    disjointSets.put(find(account.get(i), parent), new TreeSet<>());
                }
                disjointSets.get(find(account.get(i), parent)).add(account.get(i));
            }
        }
        //account merge
        List<List<String>> res = new ArrayList<>();
        for (String p : disjointSets.keySet()) {
            List<String> emails = new ArrayList<>(disjointSets.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> parent) {
        if (parent.get(s).equals(s)) {
            return s;
        }
        parent.put(s, find(parent.get(s), parent));
        return parent.get(s);
        //return parent.get(s) == s ? s : find(parent.get(s), parent);
    }
}
