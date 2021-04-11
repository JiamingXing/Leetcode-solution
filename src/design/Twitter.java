package design;

//这道题很锻炼design的思维
//自己想的时候总在想 我们可不可以用O(1)的时间直接拿到recent 10 news....
//就是用一种数据结构存储每个user 10 recent news
//同时我们在follow和unfollow的时候 又对这个数据结构进行一定处理...然后就卡住了
//做算法很重要的一点就是永远不要一口吃成胖子 脑子中觉得有一个思路会很快很精妙 但是你实现不出来就从更慢更naive的思路着手

//分析这道题的时候有一些关键点都得分析到
//design的题分析的时候都要问一个问题，对于这个题 我们需要什么？
//在这个系统中，我们如何存储我们所有的user 想到map
//每个user 如何存储关注的所有user 想到set
//每个user 如何存储post过的所有twit（最关键的地方在这里）list? set?
//为了方便最快的得到最recent的10个twit，我们把twit设计成一个类似linkedlist的形式 存储的是twit的head
//有点类似smallest range 那道题 我们利用PQ实现排序的同时又实现保证PQ中有每个数组的元素
//这道题用PQ实现排序的同时，又能找到每个user的下一条twit的位置
//这一步是最精髓的一步 自己想可能只能想到用一个list存储所有的twit
//那么就会面对一个问题 因为每个user对应的floowee的数量是一个变量x 我们需要x个指针？在每次判断出一个最大的timestamp移动那个指针继续比较
//但是用PQ加LinkedNode的思路就完美解决 我每次都放x个点进行，然后每次poll出一个最大的加到res中，并且把poll出的node的next加到我们的pq中
//完美的解决这个问题，而且保证了只要10次poll肯定能拿到我们的结果

//一个额外的问题，这里对于一个新user 如果map找不到我们建一个新的user 考虑一下如果一个更健全的系统
//我们是不是应该有一个额外的method  createNewUser() 以及 在下面这些method找不到id的时候throw exception
import java.util.*;

public class Twitter {
    int timestamp = 0;
    Map<Integer, User> map;
    private class Tweet {
        int id;
        int time;
        Tweet next;
        public Tweet(int id) {
            this.id = id;
            this.time = timestamp++;
            this.next = null;
        }
    }

    private class User{
        int id;
        Set<Integer> followed;
        Tweet head;
        public User(int id) {
            this.id = id;
            this.followed = new HashSet<>();
        }

        public void follow(int id) {
            this.followed.add(id);
        }

        public void unfollow(int id) {
            this.followed.remove(id);
        }

        public void post(int id) {
            Tweet tweet = new Tweet(id);
            tweet.next = head;
            head = tweet;
        }
    }
    /** Initialize your data structure here. */
    public Twitter() {
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
        Set<Integer> users = map.get(userId).followed;
        PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
           @Override
           public int compare(Tweet t1, Tweet t2) {
               return t2.time - t1.time;
           }
        });
        if (map.get(userId).head != null) {
            pq.offer(map.get(userId).head);
        }
        for (int user : users) {
            if (map.get(user).head != null) {
                pq.offer(map.get(user).head);
            }
        }
        int n = 0;
        while (!pq.isEmpty() && n < 10) {
            Tweet t = pq.poll();
            res.add(t.id);
            if (t.next != null) {
                pq.offer(t.next);
            }
            n ++;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!map.containsKey(followerId)) {
            map.put(followeeId, new User(followerId));
        }
        if (!map.containsKey(followeeId)) {
            map.put(followeeId, new User(followeeId));
        }
        map.get(followerId).followed.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!map.containsKey(followerId) || !map.get(followerId).followed.contains(followeeId)) {
            return;
        }
        map.get(followerId).unfollow(followeeId);
    }
}
