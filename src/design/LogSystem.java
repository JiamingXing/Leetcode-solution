package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogSystem {
    private class Pair{
        String time;
        int id;
        public Pair(String time, int id) {
            this.time = time;
            this.id = id;
        }
    }
    List<Pair> logs;
    List<String> granular = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
    int[] index = {4,7,10,13,16,19};
    public LogSystem() {
        this.logs = new ArrayList<>();
    }

    public void put(int id, String timestamp) {
        logs.add(new Pair(timestamp, id));
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        int ind = index[granular.indexOf(gra)];
        for (Pair pair : logs) {
            if (pair.time.substring(0, ind).compareTo(s.substring(0, ind)) >= 0 &&
                    pair.time.substring(0, ind).compareTo(e.substring(0, ind)) <= 0) {
                res.add(pair.id);
            }
        }
        return res;
    }
}
