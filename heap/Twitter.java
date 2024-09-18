package heap;

import java.util.*;

public class Twitter {


    //everyone will always follow themSelf
    HashMap<Integer, Set<Integer>> userToFollowing;
    HashMap<Integer, Set<Integer>> userToTweet;
    int currentTweet=0;
    PriorityQueue<List<Integer>> pq ;

    public Twitter() {
        userToFollowing = new HashMap<>();
        userToTweet = new HashMap<>();
        pq=new PriorityQueue<>((a,b)->b.get(0)-a.get(0));
    }

    public void postTweet(int userId, int tweetId) {

        //always follow themselves
        if(userToFollowing.containsKey(userId)) {
            userToFollowing.get(userId).add(userId);
        }
        else
        {
            userToFollowing.put(userId,new HashSet<>());
            userToFollowing.get(userId).add(userId);
        }


        if(!userToTweet.containsKey(userId)){

            userToTweet.put(userId, new HashSet<>());
            userToTweet.get(userId).add(tweetId);
        }
        else{
            userToTweet.get(userId).add(tweetId);
        }

        List<Integer> toBeAddedPQ =new ArrayList<>();
        toBeAddedPQ.add(currentTweet++);
        toBeAddedPQ.add(tweetId);
        pq.add(toBeAddedPQ);

    }

    public List<Integer> getNewsFeed(int userId) {

        List<Integer> newsFeed = new ArrayList<>();
        List<List<Integer>> toBeAddedPQ =new ArrayList<>();
        //int size=10;
        Set<Integer>userIds=userToFollowing.get(userId);
        // we want for a particular user the news feed to be of size<=10
        int size=1;
        while(pq.size()>0 && size<=10)
        {
            List<Integer> temp = pq.poll();
            int tweetId=temp.get(1);
            toBeAddedPQ.add(temp);

            for(Integer user:userIds)
            {
                if(userToTweet.containsKey(user)) {
                    if (userToTweet.get(user).contains(tweetId)) {
                        newsFeed.add(tweetId);
                        size++;
                    }
                }
            }


        }

        for(List<Integer> temp:toBeAddedPQ)
        {
            pq.add(temp);
        }

        return newsFeed;

    }

    public void follow(int followerId, int followeeId) {

        if(followerId!=followeeId){

            if(userToFollowing.containsKey(followerId)){
                userToFollowing.get(followerId).add(followeeId);
            }
            else{
                Set<Integer> list =new HashSet<>();
                list.add(followeeId);
                userToFollowing.put(followerId, list);
            }
        }

    }

    public void unfollow(int followerId, int followeeId) {

        if(followerId!=followeeId){
            Set<Integer> list = userToFollowing.get(followerId);

            list.remove(followeeId);

        }
    }
}

