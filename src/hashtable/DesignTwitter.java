package hashtable;

import java.util.*;

//对于这种数据结构设计题 难在需要一些就经验判断可能需要哪些数据结构 因为你可以使用任意的数据结构
//让你method最快 自由度很高
//简单在不需要很难得算法
//同时要细心考虑一些corner case， null pointer的问题

public class DesignTwitter {

    private int curTime = 0;
    private Map<Integer, User> map;

    public class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id) {
            this.id = id;
            this.time = curTime++;
            this.next = null;
        }
    }

    public class User {
        int id;
        Set<Integer> followed;
        Tweet head;

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            head = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            followed.remove(id);
        }

        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = this.head;
            this.head = t;
        }
    }

    /** Initialize your data structure here. */
    public DesignTwitter() {
        this.map = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        map.get(userId).post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!map.containsKey(userId)) {
            return res;
        }
        PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o1.time - o2.time;
            }
        });
        pq.offer(map.get(userId).head);
        for (int temp : map.get(userId).followed) {
            Tweet t = map.get(temp).head;
            if (t != null) {
                pq.offer(t);
            }
        }
        int count = 10;
        while (pq.isEmpty() && count > 10) {
            Tweet cur = pq.poll();
            res.add(cur.id);
            if (cur.next != null) {
                pq.offer(cur.next);
            }
            count --;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!map.containsKey(followerId)) {
            map.put(followerId, new User(followerId));
        }
        if (!map.containsKey(followeeId)) {
            map.put(followeeId, new User(followeeId));
        }
        map.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!map.containsKey(followerId) || followerId==followeeId) {
            return;
        }
        map.get(followerId).unfollow(followeeId);
    }
}
