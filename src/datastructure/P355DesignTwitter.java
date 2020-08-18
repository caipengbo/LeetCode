package datastructure;

import java.util.*;

// 思路：<userId, List<Integer> postList>   <userId, Set<Integer> followeeId> 

public class P355DesignTwitter {
    
    public static void main(String[] args) {

        Twitter twitter = new Twitter();
        twitter.postTweet(1, 1);
        List<Integer> res1 = twitter.getNewsFeed(1);
        System.out.println(res1);
    }
}

class Twitter {
    // 用户 id 和推文（单链表）的对应关系
    private Map<Integer, List<Tweet>> twitter;

    // 用户 id 和他关注的用户列表的对应关系
    private Map<Integer, Set<Integer>> followings;

    private static int timestamp = 0;

    private static PriorityQueue<Tweet> maxHeap;

    
    public Twitter() {
        followings = new HashMap<>();
        twitter = new HashMap<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> -o1.timestamp + o2.timestamp);
    }

    public void postTweet(int userId, int tweetId) {
        timestamp++;
        List<Tweet> tweets = twitter.getOrDefault(userId, new ArrayList<>());
        Tweet newTweet = new Tweet(tweetId, timestamp);
        tweets.add(newTweet);
        twitter.put(userId, tweets);
    }

    
    public List<Integer> getNewsFeed(int userId) {
        // 由于是全局使用的，使用之前需要清空
        maxHeap.clear();

        // 如果自己发了推文也要算上
        if (twitter.containsKey(userId)) {
            for (Tweet t : twitter.get(userId)) {
                maxHeap.offer(t);
            }
        }

        Set<Integer> followeeSet = followings.get(userId);
        List<Integer> res = new ArrayList<>(10);
        if (followeeSet != null) {
            for (Integer followeeId : followeeSet) {
                List<Tweet> tweetList = twitter.getOrDefault(followeeId, null);
                if (tweetList != null) {
                    for (Tweet t : tweetList) {
                        maxHeap.add(t);
                    }
                }
            }
        }
         
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet post = maxHeap.poll();
            res.add(post.id);
            count++;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        // 被关注人不能是自己
        if (followeeId == followerId) {
            return;
        }

        // 获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null) {
            Set<Integer> init = new HashSet<>();
            init.add(followeeId);
            followings.put(followerId, init);
        } else {
            if (followingList.contains(followeeId)) {
                return;
            }
            followingList.add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }

        // 获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);

        if (followingList == null) {
            return;
        }
        // 这里删除之前无需做判断，因为查找是否存在以后，就可以删除，反正删除之前都要查找
        followingList.remove(followeeId);
    }

    private class Tweet {
        private int id;
        private int timestamp;

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }
}
