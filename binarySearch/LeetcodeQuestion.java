package binarySearch;

import java.util.*;

public class LeetcodeQuestion {

    /*

      EASY
     */

    //704. Binary Search

    public int search(int[] nums, int target) {


         int si=0;
         int ei=nums.length-1;
         int ans=-1;
         while(ei>si){


               int mid=(ei+si)/2;

               if(nums[mid]==target){
                   ans=mid;
                   return ans;
               }
               else if(nums[mid]>target){
                   ei=mid-1;
               }
               else
                   si=mid+1;
         }

         return ans;
    }

    //35. Search Insert Position
  public  boolean check(int []nums, int x,int target)
  {
       return nums[x]>=target;
  }
    public int searchInsert(int[] nums, int target) {

        int si=0;
        int ei=nums.length-1;
          int ans=nums.length;// what if all element are larger than target


          while(si<=ei){

              int mid=(ei+si)/2;

              if(check(nums,mid,target))
              {
                  ans=mid;
                  ei=mid-1;

              }
              else {
                  si=mid+1;
              }
          }
        return ans;

    }


    //leetcode 441
    public static long sumTillI(long i) {
        return (i * (i + 1)) / 2;
    }

    public static int arrangeCoins(int n) {

        /*
         *
         * using math
         * int i=0;
         *
         * while(n>0 && n>i)
         * {
         * i++;
         * n=n-i;
         *
         * }
         *
         * return i;
         * }
         *
         *
         */

        // using binary search

        long si = 1;
        long ei = n;

        long ans = -1;

        while (si <= ei) {
            long mid = si+(ei-si)/2;

            if (sumTillI(mid) <= n) {
                ans = mid;
                si=mid+1;
            } else
                ei=mid-1;
        }

        return (int)ans;
    }


    //977. Squares of a Sorted Array
    public int[] sortedSquares(int[] nums) {

        // we can also solve it using two pointer

        int si = 0;
        int ei = nums.length - 1;
        //if none of the element is postivie that is 1
        int ans = nums.length;

        while (si <= ei) {
            int mid = (ei + si) / 2;

            if (nums[mid] >= 0) {
                ans = mid;
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        int[] finalAns = new int[nums.length];
        int index = 0;
        int j = ans - 1;
        int k = ans;

        while (j >= 0 && k <= nums.length-1) {
            if (nums[j] * nums[j] > nums[k] * nums[k]) {
                finalAns[index++] = nums[k]*nums[k];
                k = k + 1;
            } else {
                finalAns[index++] = nums[j]*nums[j];
                j--;
            }

        }

        while (j >= 0) {
            finalAns[index++] = nums[j] * nums[j];
            j--;
        }

        while (k <= nums.length-1) {
            finalAns[index++] = nums[k] * nums[k];
            k++;
        }
        return finalAns;
    }


    //leetcode 367

    public boolean isPerfectSquare(int num) {

        long si = 1;
        long ei = Integer.MAX_VALUE;

        long ans = -1;

        while (si <= ei) {
            long mid = si + (ei - si) / 2;

            if (mid * mid == num) {
                ans = mid;
                return true;
            } else if (mid * mid > num) {
                ei = mid - 1;

            } else
                si = mid + 1;
        }

        return ans == -1 ? false : true;
    }

    //leetcode 69
    public int mySqrt(int x) {
        long si = 1;
        long ei = Integer.MAX_VALUE;

        long ans = 0;

        while (si <= ei) {
            long mid = si + (ei - si) / 2;

            if (mid * mid <=x) {
                ans = mid;
                si=mid+1;
            }
            else
                ei=mid-1;
        }

        return (int)ans;
    }


    //540. Single Element in a Sorted Array
    boolean check(int[] nums, int mid)
    {
        if(mid%2==0)
        {

            // if single lement that is the answer
            if(mid+1>=nums.length)
                return true;


            //false ->go left
            //true -->go right

            return nums[mid]!=nums[mid+1];
        }
        else
        {

            if(mid-1<0)
                return true;
            //false -->go right
            //true -->go left
            return nums[mid]!=nums[mid-1];
        }
    }
    public int singleNonDuplicate(int[] nums) {

        if(nums.length==1)
            return nums[0];

        int si=0;
        int ei=nums.length-1;

        int ans=-1;

        while(si<=ei)
        {

            int mid=(si+ei)/2;

            if(check(nums,mid))
            {
                ans=nums[mid];
                ei=mid-1;
            }
            else
            {
                si=mid+1;
            }
        }

        return ans;
    }


    //1011. Capacity To Ship Packages Within D Days
 public static boolean canBeShippedWithinDays(int[] nums, int mid, int days) {

        int d=days;
        int countTillNow=0;

        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]+countTillNow>mid)
            {
                countTillNow=0;
                d--;
                if(d<0) return false;
            }

                countTillNow+=nums[i];

        }


      return d>0?true:false;

  }

    public static int shipWithinDays(int[] weights, int days) {


        //si->min of all
        //ei-->sum of all

        int si=0;
        int ei=0;

        for(int i=0;i<weights.length;i++)
        {
            si=Math.max(si,weights[i]);
            ei+=weights[i];
        }

        int ans=-1;

          while(si<=ei)
          {
              int mid=(si+ei)/2;

              if(canBeShippedWithinDays(weights,mid,days))
              {
                  ans=mid;
                  ei=mid-1;
              }
              else
              {
                  si=mid+1;
              }
          }

          return ans;
    }

    //162. Find Peak Element
    public boolean checkIfPeak(int[] nums, int index) {
        if (index + 1 == nums.length) {
            return nums[index] > nums[index - 1];
        }

        if (index == 0) {
            return nums[index] > nums[index + 1];
        }

        return nums[index] > nums[index - 1] && nums[index] > nums[index + 1];
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;

        int si = 0;
        int ei = nums.length - 1;
        int ans = -1;

        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (checkIfPeak(nums, mid)) {
                ans = mid;
                return ans;
            }
            // If the element to the right is greater, search right side
            if (mid + 1 < nums.length && nums[mid] < nums[mid + 1]) {
                si = mid + 1;
            } else {
                // Otherwise, search left side
                ei = mid - 1;
            }

        }

        return ans;
    }


    //2300. Successful Pairs of Spells and Potions

    public int[] successfulPairs(int[] spells, int[] potions, long success) {


           int n=spells.length;
           int m=potions.length;


           // will not work with primitive
            //Arrays.sort(potions,(a,b)->b-a);

          Arrays.sort(potions);
            int [] ans=new int[n];


             for(int i=0;i<n;i++) {
                 long currentMultiply = spells[i];
                 int si = 0;
                 int ei = m - 1;

                 int ind = -1;

                 while (si <= ei) {
                     int mid = (si + ei) / 2;

                     if (currentMultiply * potions[mid] >= success) {
                         ind = mid;
                         ei = mid - 1;
                     } else {
                         si = mid + 1;
                     }
                 }
                  if(ind==-1)
                      ans[i]=0;
                  else{
                      ans[i]=m-ind;
                  }
             }
            return ans;


    }

    //leetcode 875
    public boolean canEatMidBanananInH(int[] piles,int h,int mid)
    {

        int currentTimeTaken=0;


        for(int i=0;i<piles.length;i++)
        {
            currentTimeTaken+=piles[i]/mid;
            if(piles[i]%mid!=0)
            {
                currentTimeTaken++;

            }

            if(currentTimeTaken>h)
                return false;
        }

        return  currentTimeTaken<=h;
    }

    public int minEatingSpeed(int[] piles, int h) {

        int si=1;
        int ei=0;

        for(int i=0;i< piles.length;i++)
        {

            ei=Math.max(ei,piles[i]);
        }

        int ans=-1;

        while(si<=ei)
        {
            int mid=(si+ei)/2;


            if(canEatMidBanananInH(piles,h,mid))
            {
                ans=mid;
                ei=mid-1;

            }
            else
                si=mid+1;
        }

        return ans;
    }

    //34. Find First and Last Position of Element in Sorted Array

    public static  int[] searchRange(int[] nums, int target) {

        int[] ans1 = new int[2];

        int si = 0;
        int ei = nums.length - 1;

        int ans = nums.length;

        while (si <= ei) {
            int mid = (ei + si) / 2;

            if (nums[mid] >= target) {
                ans = mid;
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }

        if (ans == nums.length || nums[ans] > target) {
            ans1[0] = -1;
            ans1[1] = -1;
            return ans1;
        } else {
            ans1[0] = ans;
            si = 0;
            ei = nums.length - 1;
            ans = nums.length;

            while (si <= ei) {
                int mid = (ei + si) / 2;

                if (nums[mid] > target) {
                    ans = mid;
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            }
            if (ans == nums.length && nums[ans - 1] != target) {
                // ans1[0]=-1;
                ans1[1] = -1;
                return ans1;
            } else {
                ans1[1] = ans - 1;
                return ans1;

            }

        }
    }


    //1898. Maximum Number of Removable Characters


    public static boolean isSubSequence(String s, String p,int [] removal) {

        int j = 0; // Pointer for p
        for (int i = 0; i < s.length(); i++) {
            if (removal[i] == -1) continue; // Ignore removed characters
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == p.length()) {
                return true;
            }
        }
        return j == p.length();
    }
    public static int maximumRemovals(String s, String p, int[] removable) {



       int si=0;
       int ei=removable.length-1;
       int ans=0;

       while(si<=ei)
       {
           int mid = (ei + si) / 2;
           int []removal=new int[s.length()];
           for(int i=0;i<=mid;i++) {
               removal[removable[i]] = -1;
           }

             if(isSubSequence(s,p,removal))
             {
                 ans=mid+1;
                si=mid+1;
             }
             else {
                 ei=mid-1;
             }
       }





   return ans;

    }

    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {


        int si=0;
        int n=nums.length;
        int ei=nums.length-1;

        int ans=-1;

        while(si<=ei)
        {
            int mid=(si+ei)/2;


            //check function dekihye
            if(nums[mid]<=nums[n-1])
            {
                ans=mid;
                ei=mid-1;

            }
            else
            {
                si=mid+1;
            }

        }

        return nums[ans];

    }

    //33. Search in Rotated Sorted Array

    public int search(int[] nums, int target,int si, int ei)
    {

        int ans=-1;

        while(si<=ei)
        {
            int mid=(si+ei)/2;


            if(nums[mid]==target)
            {
                ans=mid;
                return ans;

            }
            else if(nums[mid]>target)
            {
                ei=mid-1;
            }
            else
            {
                si=mid+1;
            }
        }

        return ans;
    }
    public int search1(int[] nums, int target) {


        int si=0;
        int n=nums.length;
        int ei=nums.length-1;

        int ans=-1;

        while(si<=ei)
        {
            int mid=(si+ei)/2;

            if(nums[mid]<=nums[n-1])
            {
                ans=mid;
                ei=mid-1;

            }
            else
            {
                si=mid+1;
            }

        }

        int ans1=search(nums,target,0,ans-1);

        if(ans1==-1)
        {

            return search(nums,target,ans,n-1);
        }

        return ans1;


    }


    //https://www.interviewbit.com/problems/count-element-occurence/
    public static int findCount(List<Integer> A, int B) {

        int si=0;
        int ei=A.size()-1;

         int staringAns=-1;
         int endingAns=-1;

           while(si<=ei)
           {
               int mid = (ei + si) / 2;

               if(A.get(mid)==B)
               {


                       staringAns=mid;
                       ei=mid-1;


               }
               else if(A.get(mid)>B)
               {
                   ei=mid-1;
               }
               else{
                   si=mid+1;
               }
           }

           si=0;
           ei=A.size()-1;

        while(si<=ei)
        {
            int mid = (ei + si) / 2;

            if(A.get(mid)==B)
            {
                endingAns=mid;
                 si=mid+1;


            }
            else if(A.get(mid)>B)
            {
                ei=mid-1;
            }
            else{
                si=mid+1;
            }
        }


        //edge case if both not present then
        if(endingAns==-1)
            return 0;

    return endingAns-staringAns+1;



    }


    //https://www.interviewbit.com/problems/search-in-bitonic-array/
    public  static int binarySearch2(ArrayList<Integer>A,int target,int si,int ei) {


        int ans=-1;

        while(si<=ei)
        {
            int mid = (ei + si) / 2;


            if(A.get(mid)==target)
            {
                ans=mid;
                return mid;
            }
            else if(A.get(mid)>target)
            {
                si=mid+1;
            }
            else{

                ei=mid-1;
            }
        }

        return ans;
    }


    public  static int binarySearch1(ArrayList<Integer>A,int target,int si,int ei) {


         int ans=-1;

           while(si<=ei)
           {
               int mid = (ei + si) / 2;


               if(A.get(mid)==target)
               {
                   ans=mid;
                   return mid;
               }
               else if(A.get(mid)>target)
               {
                   ei=mid-1;
               }
               else{
                   si=mid+1;
               }
           }

           return ans;
    }
    public static int solve(ArrayList<Integer> A, int B) {

        int si=0;
        int n=A.size();
        int ei=A.size()-1;
        int ans=-1;
        while(si<=ei)
        {
            int mid=(si+ei)/2;

            if(mid>0 && mid<n-1 && A.get(mid)>A.get(mid-1) && A.get(mid)>A.get(mid+1))
            {
                ans=mid;
                break;
            }
            else if(mid>0 && A.get(mid)>A.get(mid-1))
            {
                si=mid+1;
            }
            else if(mid<n-1 && A.get(mid)>A.get(mid+1))
            {
                ei=mid-1;
            }

        }

        int ans1=binarySearch1(A,B,0,ans-1);

        if(ans1==-1)
        {
            return binarySearch2(A,B,ans,n-1);
        }

        return ans1;

    }

//https://www.interviewbit.com/problems/capacity-to-ship-packages-within-b-days/
    public static boolean check2(ArrayList<Integer> A, int B,int mid)
    {
        int count=0;
        int sum=0;
        for(int i:A)
        {
            sum+=i;

            if(sum>mid)
            {
                count++;
                sum=i;

            }

            if(count>B)
                return false;
        }
        //what if sum is still graeter than 0;
        if(sum>0)
            count++;

        return count<=B;
    }
    public  static int solve1(ArrayList<Integer> A, int B) {

        int si=Integer.MIN_VALUE;
        int ei=0;

        for(int i:A)
        {
            si=Math.max(si,i);

            ei+=i;


        }


        int ans=-1;

        while(si<=ei)
        {

            int mid=(si+ei)/2;

            if(check2(A,B,mid))
            {
                ans=mid;

                ei=mid-1;
            }
            else
            {
                si=mid+1;
            }
        }

        return ans;
    }

//https://www.interviewbit.com/problems/painters-partition-problem/
    public boolean check1(ArrayList<Integer> C,int A,int mid)
    {
        int count=0;
        int sum=0;
        for(int i:C)
        {
            sum+=i;

            if(sum>mid)
            {
                count++;
                sum=i;
            }

            if(count>A)
                return false;
        }

        if(sum>0)
            count++;


        return count<=A;

    }
    public int paint(int A, int B, ArrayList<Integer> C) {


        int si=Integer.MIN_VALUE;
        int ei=0;

        for(int i:C)
        {
            si=Math.max(si,i);
            ei+=i;
        }
        int ans=-1;
        while(si<=ei)
        {

            int mid=(si+ei)/2;

            if(check1(C,A,mid))
            {
                ans=mid;
                ei=mid-1;
            }
            else
            {
                si=mid+1;
            }
        }

        long ans1=(long)ans *B;

        ans1=ans1%10000003;

        return (int)ans1;


    }


    //https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

    public boolean check(int[] bloomDay, int m, int k,int mid)
    {
        int count=0;
        int sum=0;
        for(int i=0;i<bloomDay.length;i++)
        {
            if(bloomDay[i]<=mid)
            {

                sum++;


                if(sum==k)
                {
                    count++;
                    sum=0;
                }
            }
            else
            {
                sum=0;

            }


        }

        return count>=m;
    }
    public int minDays(int[] bloomDay, int m, int k) {

        int n=bloomDay.length;
        if(m*k>n)
            return -1;

        int si=1;
        int ei=Integer.MIN_VALUE;

        for(int i=0;i<bloomDay.length;i++)
        {
            ei=Math.max(ei,bloomDay[i]);
        }


        int ans=-1;
        while(si<=ei)
        {

            int mid=(si+ei)/2;

            if(check(bloomDay,m,k,mid))
            {
                ans=mid;
                ei=mid-1;
            }
            else
                si=mid+1;
        }

        return ans;


    }

    //https://www.interviewbit.com/problems/red-zone/
//    public static boolean check(ArrayList<Double>ansList,int B,int mid)
//    {
//
//        int count=0;
//
//
//
//        for(double i:ansList)
//        {
//            if(i<=2*mid)
//            {
//                count+=2;
//            }
//        }
//
//        return count>=B;
//    }
//    public static int solve4(ArrayList<ArrayList<Integer>> A, int B) {
//
//
//        if(B>A.size())
//            return -1;
//
//
//        int si=0;
//
//        int ei=10;
//
//        ArrayList<Double>ansList=new ArrayList<>();
//        for(int i=0;i<A.size();i++)
//        {
//            for(int j=i+1;j<A.size();j++)
//            {
//                int x1 = A.get(j).get(0) - A.get(i).get(0);
//                int y1 = A.get(j).get(1) - A.get(i).get(1);
//
//
//                double d=Math.sqrt((double)x1*x1 +(double)y1*y1);
//
//                ansList.add(d);
//            }
//
//        }
//        int ans=-1;
//
//        while(si<=ei)
//        {
//
//            int mid=(si+ei)/2;
//
//
//            if(check(ansList,B,mid))
//            {
//                ans=mid;
//                ei=mid-1;
//            }
//            else
//            {
//                si=mid+1;
//            }
//        }
//
//
//        return ans;
//
//    }

    public boolean check(ArrayList<ArrayList<Integer>> A,int B,int mid)
    {

        int count=0;


        HashSet<Integer>set=new HashSet<>();
        for(int i=0;i<A.size();i++)
        {
            for(int j=i+1;j<A.size();j++)
            {
                int x1 = A.get(j).get(0) - A.get(i).get(0);
                int y1 = A.get(j).get(1) - A.get(i).get(1);


                double d=Math.sqrt((double)x1*x1 +(double)y1*y1);

                if(d<=2*mid )
                {
                    if(!set.contains(i)) {
                        count++;
                        set.add(i);
                    }

                    if(!set.contains(j)) {
                        set.add(j);
                        count++;
                    }
                }
            }

        }

        return count>=B;
    }
    public int solve4(ArrayList<ArrayList<Integer>> A, int B) {

        if(B>A.size())
            return -1;


        int si=0;

        int ei=(int)1e9;

        // ArrayList<Double>ansList=new ArrayList<>();
        // for(int i=0;i<A.size();i++)
        //    {
        //        for(int j=i+1;j<A.size();j++)
        //        {
        //            int x1 = A.get(j).get(0) - A.get(i).get(0);
        //             int y1 = A.get(j).get(1) - A.get(i).get(1);


        //             double d=Math.sqrt((double)x1*x1 +(double)y1*y1);

        //             ansList.add(d);
        //        }

        //    }
        int ans=-1;

        while(si<=ei)
        {

            int mid=(si+ei)/2;


            if(check(A,B,mid))
            {
                ans=mid;
                ei=mid-1;
            }
            else
            {
                si=mid+1;
            }
        }


        return ans;

    }

    //https://www.interviewbit.com/problems/implement-power-function/
    private int binaryExp(int x, int n, int d) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        int res = binaryExp(x, n / 2, d);
        long res1 = ((long) res * res) % d; // Remove extra '+ d'

        if (n % 2 == 1) {
            res1 = (res1 * x) % d;
        }

        // Handle negative result
        if (res1 < 0) {
            res1 = (res1 + d) % d;
        }

        return (int) res1;
    }

    public int pow(int x, int n, int d) {
        return binaryExp(x % d, n, d);  // Ensure x is modulo d first to avoid overflow
    }
    public static void main(String[] args) {


//        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
//
//        // First inner list [2, 3]
//        ArrayList<Integer> row1 = new ArrayList<>();
//        row1.add(2);
//        row1.add(3);
//        A.add(row1);
//
//        // Second inner list [9, 4]
//        ArrayList<Integer> row2 = new ArrayList<>();
//        row2.add(9);
//        row2.add(4);
//        A.add(row2);
//
//        // Third inner list [10, 3]
//        ArrayList<Integer> row3 = new ArrayList<>();
//        row3.add(10);
//        row3.add(3);
//        A.add(row3);
//
//
//
//
//        // Assigning the value to B
//        int B = 2;
//
//
//        System.out.println(solve4(A,B));
    }
    }
