package design;

//对于这种设计题 首先你要知道的是既然我要有一个expiring time那么我就要封装一个类给我们的V
//然后就是你要知道System.currentTimeMillis()这个method是返回一个long的当前时间

import java.util.*;

public class ExpiringMap<K, V> {
    Map<K, Data<V>> map = new HashMap<>();
    //用treemap 可以对key进行排序 这里我们按照时间进行排序 那我们先出来的一定是最recent的
    TreeMap<TimeKey<K>, Data<V>> treeMap = new TreeMap<>(new Comparator<TimeKey<K>>() {
        @Override
        public int compare(TimeKey<K> o1, TimeKey<K> o2) {
            return (int)(o2.startTime - o1.startTime);
        }
    });

    private class Data<V> {
        V val;
        long duration;
        long startTime;
        public Data(V val, long duration, long startTime) {
            this.val = val;
            this.duration = duration;
            this.startTime = startTime;
        }
    }

    private class TimeKey<K> {
        K val;
        long startTime;
        long duration;
        public TimeKey(K val, long startTime, long duration) {
            this.val = val;
            this.startTime = startTime;
            this.duration = duration;
        }
    }


    public void put(K key, V value, long duration) {
        map.put(key, new Data<>(value, duration, System.currentTimeMillis()));
    }

    public V get(K key) {
//        ArrayList<Integer> a = new ArrayList();
//        a.add(1);
//        a.add("1");
        if (!map.containsKey(key)) {
            return null;
        }
        //Data data = map.get(key);
        Data<V> data = map.get(key);
        if (data.startTime + data.duration < System.currentTimeMillis()) {
            map.remove(key);
            return null;
        } else {
            return data.val;
        }
    }

    public V getMostRecentValue(K key) {
        Data<V> res = null;
        Iterator<TimeKey<K>> iter = treeMap.keySet().iterator();
        while (iter.hasNext()) {
            TimeKey<K> cur = iter.next();
            if (cur.startTime + cur.duration >= System.currentTimeMillis()) {
                res = treeMap.get(cur);
                break;
            }
        }
        return res == null ? null : res.val;
    }
}
