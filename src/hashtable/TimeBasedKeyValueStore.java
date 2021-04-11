package hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//拿到这道题 想当然的第一思路 是用一个TreeMap 以timestamp作为key 还是要思路稍微灵活一点

public class TimeBasedKeyValueStore {

    Map<String, TreeMap<Integer, String>> map;


    public TimeBasedKeyValueStore() {
        this.map = new HashMap<>();
    }

    //store key,value along with timestamp
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            this.map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    //
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        Integer fKey = map.get(key).floorKey(timestamp);
        if (key == null) {
            return "";
        }
        return map.get(key).get(fKey);
    }
}
