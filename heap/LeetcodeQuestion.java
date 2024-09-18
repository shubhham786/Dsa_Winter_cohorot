package heap;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigInteger;
import java.util.*;


public class LeetcodeQuestion {

/*
   Easy level

 */

    //703. Kth Largest Element in a Stream

    class KthLargest {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int k;

        public KthLargest(int k, int[] nums) {
            Arrays.sort(nums);

            if (nums.length >= k) {
                for (int i = nums.length - k; i < nums.length; i++) {
                    pq.add(nums[i]);
                }
            } else {
                for (int i = 0; i < nums.length; i++) {
                    pq.add(nums[i]);
                }
            }
            this.k = k;
        }

        public int add(int val) {
            pq.add(val);
            while (pq.size() > k) {
                pq.remove();
            }

            return pq.peek();
        }
    }



    //1046. Last Stone Weight

    public int lastStoneWeight(int[] stones) {

     PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
     for (int stone : stones) {
         pq.add(stone);
     }

        while (pq.size() > 1) {
            int top1 = pq.poll();
            int top2 = pq.poll();

            if(top1 != top2){
                pq.add(top1-top2);
            }
        }

        return pq.peek();

    }

    /*

       Medium level Question

     */

    //973. K Closest Points to Origin
    public int[][] kClosest(int[][] points, int k) {

        // PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k,  (int[] a1, int[] a2) -> (int)((Math.pow(a1[0],2)+Math.pow(a1[1],2))-(Math.pow(a2[0],2)+Math.pow(a2[1],2))));
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a1, a2) -> (a1[0] * a1[0] + a1[1] * a1[1]) - (a2[0] * a2[0] + a2[1] * a2[1])
        );

        for (int i = 0; i < points.length; i++) {
            pq.add(points[i]);
        }

        int[][] res = new int[k][2];
        while (k-- > 0) {
            int[] temp = pq.poll();
            res[0] = temp;

        }

        return res;
    }


    //215. Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k) {

        //using max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }

        while (k-- > 1) {
            Integer temp = pq.poll();


        }

        return pq.peek();

    }


    //621. Task Scheduler

    //good question
    public int leastInterval(char[] tasks, int n) {


        int[] array=new int[26];
        int[] map=new int[26];

        for(char ch:tasks){
            array[ch-'A']++;
        }




   return -1;


    }


    //355. Design Twitter

    class Twitter1 {


        //everyone will always follow themSelf
        HashMap<Integer, Set<Integer>> userToFollowing;
        HashMap<Integer, Set<Integer>> userToTweet;
        int currentTweet=0;
        PriorityQueue<List<Integer>> pq ;

        public Twitter1() {
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

    //1481. Least Number of Unique Integers after K Removals

    public int findLeastNumOfUniqueInts(int[] arr, int k) {


         HashMap<Integer, Integer> map = new HashMap<>();
         for (int i = 0; i < arr.length; i++) {

             if(map.containsKey(arr[i]))
             {
                 map.put(arr[i],map.get(arr[i])+1);
             }
             else
             {
                 map.put(arr[i],1);
             }
         }

         PriorityQueue<int []> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);

         for(Integer key: map.keySet())
         {
             pq.add(new int[]{key,map.get(key)});
         }

          while(k>0)
          {
              int[] temp = pq.peek();

              if(k>=temp[1])
              {
                  k-=temp[1];
                  pq.poll();

              }
              else {
                  k=0;
              }

          }


   return pq.size();



    }

    //1642. Furthest Building You Can Reach
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->(a-b));
        //int count=0;
        for(int i=0;i<heights.length-1;i++)
        {
            if(heights[i+1]-heights[i]<=0){
                //count++;
                continue;
            }

            int jump=heights[i+1]-heights[i];

            pq.add(jump);

            if(pq.size()>ladders)
            {
                int bricksEmergency=pq.poll();
                bricks-=bricksEmergency;
                // pq.pop();
            }

            if(bricks<0)
                return i;
        }

        return  heights.length-1;

    }

    //2542. Maximum Subsequence Score

    public static long  maxScore(int[] nums1, int[] nums2, int k) {

        /*
          Time limit excceded

          List<List<Integer>>list=new ArrayList<>();

          for(int i=0;i<nums2.length;i++)
          {
           List<Integer>list1=new ArrayList<>();
              list1.add(nums2[i]);
              list1.add(nums1[i]);
              list.add(list1);
          }
          Collections.sort(list,(a,b)->a.get(0)-b.get(0));


          long maxScore=0;
        PriorityQueue<Integer>minpq=new PriorityQueue<>((a,b)->(a-b));
          for(int i=0;i<list.size()-k+1;i++)
          {
              int sumTillNow=list.get(i).get(1);
              int minVlaueToMulyiply=list.get(i).get(0);



                 for(int j=i+1;j<list.size();j++)
                 {
                     minpq.add(list.get(j).get(1));

                     if(minpq.size()>k-1)
                     {
                         minpq.poll();
                     }
                 }

                  while(!minpq.isEmpty())
                  {
                      sumTillNow+=minpq.poll();
                  }
                  maxScore=Math.max(maxScore,sumTillNow*minVlaueToMulyiply);
          }



            return maxScore;

     */

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < nums2.length; i++) {
            List<Integer> list1 = new ArrayList<>();
            list1.add(nums2[i]);
            list1.add(nums1[i]);
            list.add(list1);
        }
        Collections.sort(list, (a, b) -> b.get(0) - a.get(0));


        long maxScore = 0;
        PriorityQueue<Integer> minpq = new PriorityQueue<>();

        long sumTillNow = 0;

        //sliding window lage ge abb

        //first k windown phle bana le rahe hai
        for (int i = 0; i < k; i++) {
            int nums1Val = list.get(i).get(1);

            sumTillNow += nums1Val;
            minpq.add(nums1Val);

        }

        maxScore = Math.max(maxScore, sumTillNow * list.get(k - 1).get(0));

        for (int i = k; i < list.size(); i++) {
            sumTillNow -= minpq.poll();
            int nums1Val = list.get(i).get(1);
            sumTillNow += nums1Val;
            minpq.add(nums1Val);

            maxScore = Math.max(maxScore, sumTillNow * list.get(i).get(0));

        }
        return maxScore;
    }


    //1834. Single-Threaded CPU
    /*
        Good question as we are using two priority queue
        code for SJF in os schedluing algo


     */

    /*
      folow up- can be solved using one heap

        Store tasks with their index, so that we can retain the original order if needed
    int[][] indexedTasks = new int[tasks.length][3]; // {enqueueTime, processTime, index}
    for (int i = 0; i < tasks.length; i++) {
        indexedTasks[i][0] = tasks[i][0]; // Enqueue time
        indexedTasks[i][1] = tasks[i][1]; // Processing time
        indexedTasks[i][2] = i;           // Original index
    }

     */
    public static int[] getOrder(int[][] tasks) {

        // Priority queue for tasks sorted by enqueue time (min heap)
        // If enqueue times are equal, sort by index (for tie-breaking)
        PriorityQueue<int[]> minEnqpq = new PriorityQueue<>((a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));

        // Priority queue for tasks sorted by processing time (min heap)
        // If processing times are equal, sort by index (for tie-breaking)
        PriorityQueue<int[]> minPtqpq = new PriorityQueue<>((a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));

        int[] ans = new int[tasks.length]; // To store the order in which tasks are processed
        int index = 0; // Index for the answer array
        int minTime = 0; // Tracks the current CPU time

        // Add tasks to minEnqpq with {enqueue time, task index} for sorting
        for (int i = 0; i < tasks.length; i++) {
            minEnqpq.add(new int[]{tasks[i][0], i});
        }

        // Process tasks until both queues are empty
        while (minEnqpq.size() != 0) {

            // If no tasks are available to process but tasks remain in enqueue queue
            if (minPtqpq.size() == 0 && minEnqpq.peek()[0] > minTime) {
                // Move the time forward to the next available task's enqueue time
                minTime = minEnqpq.peek()[0];
            }

            // Add tasks to the processing queue if their enqueue time <= current time
            while (minEnqpq.size() != 0 && minEnqpq.peek()[0] <= minTime) {
                int taskIndex = minEnqpq.peek()[1]; // Get the index of the task
                minPtqpq.add(new int[]{tasks[taskIndex][1], taskIndex}); // Add {processing time, index}
                minEnqpq.poll(); // Remove from enqueue queue
            }

            // Process the next task from the processing queue
            if (minPtqpq.size() != 0) {
                // Update time by adding the processing time of the task
                minTime += minPtqpq.peek()[0];
                // Store the index of the processed task
                ans[index++] = minPtqpq.peek()[1];
                // Remove the processed task from the processing queue
                minPtqpq.poll();
            }
        }

        // Process remaining tasks in the processing queue (if any)
        while (minPtqpq.size() != 0) {
            // Store the index of the remaining tasks
            ans[index++] = minPtqpq.peek()[1];
            // Remove the processed task from the processing queue
            minPtqpq.poll();
        }

        return ans; // Return the order of task execution
    }



//1985. Find the Kth Largest Integer in the Array

    public String kthLargestNumber(String[] nums, int k) {

       //1. using BigInteger

        /*
            PriorityQueue<BigInteger>pq=new PriorityQueue<>((a,b)->(b.compareTo(a));

             for(String num:nums)
             {
                 pq.add(new BigInteger(num));
             }

               while(pq.size()!=0 && k-->1)
               {
                   pq.poll();
               }

               return pq.peek().toString();


         */

        // Method 2-using custom string PriorityQueue


        PriorityQueue<String>pq=new PriorityQueue<>((a,b)->(a.length()==b.length()?b.compareTo(a): b.length()-a.length()));
        /*
          PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (a.length() == b.length()) {
                return b.compareTo(a);
            } else {
                return Integer.compare(a.length(), b.length());
            }
        });
         */

        for(String num:nums)
        {
            pq.add(num);
        }

        while(pq.size()!=0 && k-->1)
        {
            pq.poll();
        }

        return pq.peek();

    }


//1405. Longest Happy String
    //BUg raise kiye hai iske liye

    public static  class Pair implements Comparable<Pair> {
        @Override
        public int compareTo(Pair o) {
            return o.val - this.val;
        }

        Character ch;
        Integer val;
        public Pair(Character ch, Integer val) {
            this.ch = ch;
            this.val = val;
        }

        public Pair()
        {

        }


    }

    public static String longestDiverseString(int a, int b, int c) {

        Pair a1=new Pair('a',a);
        Pair b1=new Pair('b',b);
        Pair c1=new Pair('c',c);

        PriorityQueue<Pair>pq=new PriorityQueue<>((Pair a2, Pair b2)->(a2.compareTo(b2)));

        pq.add(a1);
        pq.add(b1);
        pq.add(c1);
        StringBuilder sb = new StringBuilder();

        Pair prev=null;

        while(!pq.isEmpty())
        {
            Pair temp = pq.poll();

            if(temp.val>=2){
                temp.val-=2;
                sb.append(temp.ch);
                sb.append(temp.ch);
            }
            else{
                temp.val--;
                sb.append(temp.ch);
            }

            //map.put(temp.ch, temp.val);


            if(prev!=null && prev.val>0)
            {
                pq.add(prev);
            }
            prev=temp;

        }




        return sb.toString();
    }

//1094. Car Pooling
public boolean carPooling(int[][] trips, int capacity) {

  Arrays.sort(trips, (a,b)->a[1]==b[1]?a[2]-b[2]:a[1]-b[1]);

  PriorityQueue<int[]>minPq=new PriorityQueue<>((a,b)->a[1]-b[1]);


  int time=0;
  int seat=0;
    for(int i=0;i<trips.length;i++)
    {
        time=Math.max(time,trips[i][1]);
        seat+=trips[i][0];

            while(minPq.size()!=0 && minPq.peek()[1]<=time)
            {
                seat-=minPq.poll()[0];
            }


        if(seat>capacity)
            return false;

        minPq.add(new int[]{trips[i][0],trips[i][2]});

    }

     return true;
}


//1882. Process Tasks Using Servers
public static int[] assignTasks(int[] servers, int[] tasks) {


    // Priority Queue to store available servers: {weight, index}
    PriorityQueue<int[]> availableServers = new PriorityQueue<>((a, b) ->
            a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
    );
    // Priority Queue to store waiting servers: {freeTime, index}
    PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) ->
            a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
    );

    // Initialize available servers queue
    for (int i = 0; i < servers.length; i++) {
        availableServers.add(new int[]{servers[i], i});
    }

    int[] ans = new int[tasks.length];
    int currentTime = 0;

    for (int i = 0; i < tasks.length; i++) {
        currentTime = Math.max(currentTime, i); // Ensure time moves forward

        // Move servers from busy to available if their freeTime has passed
        while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
            int[] server = busyServers.poll();
            availableServers.add(new int[]{servers[server[1]], server[1]});
        }

        // If no server is available, fast forward time to when the next server is free
        if (availableServers.isEmpty()) {
            currentTime = busyServers.peek()[0];
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] server = busyServers.poll();
                availableServers.add(new int[]{servers[server[1]], server[1]});
            }
        }

        // Assign the task to the next available server
        int[] server = availableServers.poll();
        ans[i] = server[1]; // Record which server handled the task

        // Calculate when the server will be free and add it to busy servers
        int serverFreeTime = currentTime + tasks[i];
        busyServers.add(new int[]{serverFreeTime, server[1]});
    }

    return ans;



}

    //1675. Minimize Deviation in Array


    public static  int minimumDeviation(int[] nums) {



        // Max-heap to store the elements (reverse order)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int minNum = Integer.MAX_VALUE;
        int deviation = Integer.MAX_VALUE;

        // Preprocess the array
        for (int num : nums) {
            // If the number is odd, multiply by 2 to make it even
            if (num % 2 == 1) {
                num *= 2;
            }
            // Add to max-heap and update the minimum number
            pq.add(num);
            minNum = Math.min(minNum, num);
        }

        // Continue adjusting the numbers to minimize deviation
        while (true) {
            int maxNum = pq.poll();
            deviation = Math.min(deviation, maxNum - minNum);

            // If the maximum number is even, we can reduce it
            if (maxNum % 2 == 0) {
                maxNum /= 2;
                minNum = Math.min(minNum, maxNum);
                pq.add(maxNum);
            } else {
                // If it's odd, we cannot reduce it further, so break
                break;
            }
        }
        return deviation;
    }




  //767. Reorganize String

//    public static class Pair implements Comparable<Pair> {
//        @Override
//        public int compareTo(Pair o) {
//            return o.val - this.val;
//            //max-heap
//        }
//
//        Character ch;
//        Integer val;
//        public Pair(Character ch, Integer val) {
//            this.ch = ch;
//            this.val = val;
//        }
//
//        public Pair()
//        {
//
//        }
//
//
//    }
  public String reorganizeString(String s) {

         Map<Character, Integer> map = new HashMap<>();
         for(char c : s.toCharArray())
         {
             map.put(c, map.getOrDefault(c, 0) + 1);
         }

         PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> (a.compareTo(b)));
         for(char c : map.keySet())
         {
             pq.add(new Pair(c, map.get(c)));
         }

 for(Pair p : pq)
 {
     System.out.println(p.ch + " " + p.val);
 }
         StringBuilder sb = new StringBuilder();

          Pair prev=null;

         while(!pq.isEmpty())
         {
            Pair temp = pq.poll();
            sb.append(temp.ch);
            temp.val = map.getOrDefault(temp.ch, 0) - 1;
            map.put(temp.ch, temp.val);


             if(prev!=null && prev.val>0)
            {
                pq.add(prev);
            }
            prev=temp;

         }

         if(sb.length()!=s.length())
             return "";

          return sb.toString();
  }






  // 378. Kth Smallest Element in a Sorted Matrix

    /*  public int kthSmallest(int[][] matrix, int k) {

        int m=matrix[0].length;


        PriorityQueue<ArrayList<Integer>>pq=new PriorityQueue<>((a,b)->(a.get(0) - b.get(0)));

        for(int i=0;i<matrix.length;i++)
        {
            ArrayList<Integer> temp=new ArrayList<>();
            temp.add(matrix[i][0]);
            temp.add(i);
            temp.add(0);
            pq.add(temp);
        }

        while(k-->1)
        {
            ArrayList<Integer>top=pq.peek();
            pq.poll();

            int i=top.get(1);
            int j=top.get(2);
            j++;
            if(j<m)
            {
                ArrayList<Integer> temp=new ArrayList<>();
                temp.add(matrix[i][j]);
                temp.add(i);
                temp.add(j);
                pq.add(temp);
            }



        }


        return pq.peek().get(0);
        }
       */

    /*
Time Complexity Analysis:

1. Initial Heap Construction:
- We push the first element of each row (a total of n elements) into the min-heap.
- This step takes O(n log n) time, where n is the number of rows in the matrix.

2. Heap Operations:
- We perform k-1 operations where, in each operation, we pop the smallest element from the heap and push the next element from the same row if it exists.
- Each pop and push operation takes O(log n) time.
- Thus, the heap operations contribute O(k log n) time.

Overall Time Complexity:
- The total time complexity is O((n + k) log n).
- For large k (close to n^2), the time complexity can be approximated as O(k log n), but the exact complexity remains O((n + k) log n).

Sp


ace'
 ;'

 ;;Complexity:
- The space complexity is O(n) due to the storage of elements in the min-heap.
*/

    ;
    ;


        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;

            // Min-heap: Each element is an array [value, row, col]
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            // Add the first element of each row to the heap
            for (int i = 0; i < n; i++) {
                pq.offer(new int[]{matrix[i][0], i, 0});
            }

            // Extract min k-1 times
            while (k-- > 1) {
                int[] top = pq.poll();
                int row = top[1];
                int col = top[2];

                // If there is a next element in the same row, add it to the heap
                if (col + 1 < n) {
                    pq.offer(new int[]{matrix[row][col + 1], row, col + 1});
                }
            }

            // The root of the heap is the kth smallest element
            return pq.peek()[0];
        }





    //1673. Find the Most Competitive Subsequence
    public static int[] mostCompetitive(int[] nums, int k) {

        int n=nums.length;
        PriorityQueue<int []> pq=new PriorityQueue<>((a,b)-> a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);

        int[]ans=new int[k];
        int index=0;
        for(int i=0;i<n;i++)
        {
            pq.add(new int[]{nums[i],i});
            System.out.println(pq.peek()[0]+" "+pq.peek()[1]);
            if(i>=n-k)
            {
                int []top=pq.peek();
                ans[index]=top[0];
                index++;


                while(pq.size()>0 && pq.peek()[1]<=top[1]){
                  pq.poll();

                }
            }
        }
        return ans;
    }


    public int nthUglyNumber(int n) {

        PriorityQueue<Long>pq=new PriorityQueue<>();

        Set<Long>map=new HashSet<>();

        pq.add(1L);
        //notice here long not Long
        long ans=1L;


        while(n-->0)
        {
            ans=pq.poll();

            Long ans1=ans*2;
            if(!map.contains(ans1))
            {
                pq.add(ans1);
                map.add(ans1);
            }


            Long ans2=ans*3;

            // no need for this as( !map.conatins) see below
            if(!map.contains(ans2))
            {
                pq.add(ans2);
                map.add(ans2);
            }

            Long ans3=ans*5;
            if(pq.add(ans3))
            {

                map.add(ans3);
            }

        }


        return (int) ans;


    }



    /*
      Leetcode hard
     */

    //871. Minimum Number of Refueling Stops
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {

         int n=stations.length;

         //max heap
         PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->(b-a));
        int minReful=0;
        int currentDistance=startFuel;
           for(int i=0;i<n;i++)
           {


               while(pq.size()!=0 && currentDistance<stations[i][0])
               {
                    currentDistance+= pq.poll();
                    minReful++;
               }

                if(currentDistance<stations[i][0])
                    return -1;

               pq.add(stations[i][1]);
           }

        while(pq.size()!=0 && currentDistance<target)
        {
            currentDistance+= pq.poll();
            minReful++;
        }


        if(currentDistance<target)
            return -1;

        return minReful;
    }




     //23. Merge k Sorted Lists

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.add(lists[i]);
        }
        ListNode head = pq.poll();
        if (head == null)
            return head;

        if (head.next != null) {
            pq.add(head.next);
        }
        ListNode temp = head;
        while (pq.size() != 0) {
            ListNode temp1 = pq.poll();
            temp.next = temp1;
            temp = temp1;
            if (temp1.next != null)
                pq.add(temp1.next);

        }

        return head;
    }


    //239. Sliding Window Maximum
    public static int[] maxSlidingWindow(int[] nums, int k) {


        PriorityQueue<int []> pq=new PriorityQueue<>((a,b)->(b[0]-a[0]));

        int n=nums.length;
        int[] ans=new int[n-k+1];
        int index=0;

        for(int i=0;i<k;i++)
        {
            pq.add(new int[]{nums[i],i});
        }
        ans[index++]=pq.peek()[0];

        for(int i=k;i<n;i++)
        {
            pq.add(new int[]{nums[i],i});


               while(pq.size()>=k)
               {
                   int top[]=pq.peek();

                      if(top[1]>(i-k))
                      {
                          ans[index++]=pq.peek()[0];
                                break;
                             }
                      else
                          pq.poll();


               }


        }

     return ans;
    }

    /*

      same using dequeue

      here if use dequeue of elements itself then comparing whether the element is in desired window is tough
      can we use dequeue of indices
     */

    public static int[] maxSlidingWindow1(int[] nums, int k) {

        List<Integer> list=new LinkedList<>();

        int [] ans=new int[nums.length-k+1];
        for(int i=0;i<k;i++)
        {
            while(list.size()!=0 && nums[list.getLast()]<nums[i])
                list.removeLast();

            list.add(i);
        }
        int index=0;
        ans[index++]=nums[list.getFirst()];
        for(int i=k;i<nums.length;i++)
        {

               while(list.size()!=0 && list.getFirst()<(i-k+1))
                   list.removeFirst();

            while(list.size()!=0 && nums[list.getLast()]<=nums[i])
                list.removeLast();



            list.add(i);

            ans[index++]=nums[list.getFirst()];
        }

        return ans;

    }


//218. The Skyline Problem
   public static class pair implements Comparable<pair>{
    @Override
    public int compareTo(pair o) {

        if(this.x==o.x)
            return this.height-o.height;

        return this.x-o.x;
    }

    int x;
         int height;

    public pair(int x, int height) {
        this.x = x;
        this.height = height;
    }


}

    public static List<List<Integer>> getSkyline(int[][] buildings) {
         List<pair> building=new ArrayList<>();

          for(int i=0;i<buildings.length;i++)
          {

                  int sp=buildings[i][0];
                  int ep=buildings[i][1];

                  int height=buildings[i][2];

                  building.add(new pair(sp,-height));
                  building.add(new pair(ep,height));



          }

          Collections.sort(building);
  List<List<Integer>> res=new ArrayList<>();
          PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);

          int prevHt=0;
          pq.add(0);

          for(pair rem:building)
          {
              int ht= rem.height;
              int x=rem.x;

              if(ht<0)
              {
                  pq.add(-ht);
              }
              else{
                  pq.remove(ht);
              }

              //is change in height

              if(prevHt!= pq.peek())
              {
                  List<Integer> list=new ArrayList<>();
                   list.add(x);
                   list.add(pq.peek());
                         res.add(list);
                   prevHt=pq.peek();
              }
          }

          return res;
    }



    //leetcode 407
    //Trapping Rain Water II


    public int trapRainWater(int[][] heightMap) {

      PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(a[0]-b[0]));


      for(int i=0;i<heightMap.length;i++)
      {
          for(int j=0;j<heightMap[i].length;j++)
          {
              if(i==0 || j==0 || i==heightMap.length-1 || j==heightMap[i].length-1)
              {
                  pq.add(new int[]{heightMap[i][j],i,j});
                  heightMap[i][j]=-1;
              }
          }
      }

      int waterStored=0;
      int maxMinHeight=0;
   int [][]dir={{1,0},{0,1},{-1,0},{0,-1}};
        while(pq.size()!=0)
        {
            int []top=pq.poll();

            int ht=top[0];
             maxMinHeight=Math.max(maxMinHeight,ht);

             waterStored+=maxMinHeight-ht;
             int x=top[1];
             int y=top[2];

             for(int i=0;i<4;i++)
             {
                 int x1=x+dir[i][0];
                 int y1=y+dir[i][1];

                   if(x1>0 && y1>0 && x1<heightMap.length && y1<heightMap[x].length && heightMap[x1][y1]!=-1)
                   {
                       pq.add(new int[]{heightMap[x1][y1],x1,y1});
                       heightMap[x1][y1]=-1;
                   }
             }

        }

   return waterStored;

    }

//502. IPO
//public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        /*
   //2 priority queue se solve ho gaye ga

    //1. min priority queue me hum

    PriorityQueue<int[]> minPq=new PriorityQueue<>((a,b)->a[0]-b[0]);

    for(int i=0;i<profits.length;i++)
    {
        minPq.add(new int[]{capital[i],profits[i]});
    }

    //2. max priority queue

    PriorityQueue<Integer>maxPq=new PriorityQueue<>((a, b)->b-a);

    int maxCapital=0;
    while(k-->0)
    {
        while(minPq.size()!=0 && minPq.peek()[0]<=w)
        {
            maxPq.add(minPq.poll()[1]);
        }

        if(maxPq.size()!=0)
        {
            w+=maxPq.peek();
            maxCapital+=maxPq.poll();

        }
    }


        return  maxCapital;
*/


//}



    public static void main(String[] args) {
/*
        Twitter twitter = new Twitter();

        // Execute the sequence of operations provided in the input
        twitter.postTweet(1, 5);             // User 1 posts tweet with id 5
        twitter.follow(1, 2);                // User 1 follows user 2
        twitter.follow(2, 1);                // User 2 follows user 1

        System.out.println(twitter.getNewsFeed(2)); // User 2's news feed

        twitter.postTweet(2, 6);             // User 2 posts tweet with id 6
        System.out.println(twitter.getNewsFeed(1)); // User 1's news feed
        System.out.println(twitter.getNewsFeed(2)); // User 2's news feed

        twitter.unfollow(2, 1);              // User 2 unfollows user 1
        System.out.println(twitter.getNewsFeed(1)); // User 1's news feed
        System.out.println(twitter.getNewsFeed(2)); // User 2's news feed

        twitter.unfollow(1, 2);              // User 1 unfollows user 2
        System.out.println(twitter.getNewsFeed(1)); // User 1's news feed
        System.out.println(twitter.getNewsFeed(2)); // User 2's news feed
*/




       int[] servers = {10,63,95,16,85,57,83,95,6,29,71};
       int [] tasks = {70,31,83,15,32,67,98,65,56,48,38,90,5};

               int [] res=assignTasks(servers,tasks);

       for(int i=0;i<res.length;i++)
           System.out.println(res[i]);
      //  System.out.println(longestDiverseString(a,b,c));
    }
}
