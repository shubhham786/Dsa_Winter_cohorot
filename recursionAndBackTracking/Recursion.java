package recursionAndBackTracking;

import java.sql.SQLOutput;
import java.util.*;

public class Recursion {

 //gfg
    //https://www.geeksforgeeks.org/problems/subset-sums2234/1

   static void subsetSums(ArrayList<Integer> arr, int index,int n, int currSum, ArrayList<Integer>finalAns)
    {

        if(index==n)
        {
            finalAns.add(currSum);
            return;
        }

        subsetSums(arr,index+1,n,currSum,finalAns);
        subsetSums(arr,index+1,n,currSum+arr.get(index),finalAns);
    }


    static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        // code here

        ArrayList<Integer> ans=new ArrayList<>();

        subsetSums(arr,0,n,0,ans);

        return ans;

    }

     //leetcode 90
    //https://leetcode.com/problems/subsets-ii/

     public static void subsetWithDup(int []nums, int index, int n, List<Integer>CurrAns,Set<List<Integer>> ans)
     {
         if(index==n)
         {
            ans.add(new ArrayList<>(CurrAns));
            return;
         }

          subsetWithDup(nums,index+1,n,CurrAns,ans);
           CurrAns.add(nums[index]);
           subsetWithDup(nums,index+1,n,CurrAns,ans);
          CurrAns.remove(CurrAns.size()-1);

     }

    /**
     *
     *  can we do it without using set,
     *  yes, as we have sorted the array
     *
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
         Set<List<Integer>> ans=new HashSet<>();

         List<List<Integer>> ans1=new ArrayList<>();
         ArrayList<Integer> currAns=new ArrayList<>();
                 subsetWithDup(nums,0,nums.length,currAns,ans);
         for(List<Integer>a:ans)
         {
             ans1.add(a);
         }
            return ans1;
    }


    /**
     * without set


    public static void subsetWithDup(int[] nums, int index, int n, List<Integer> CurrAns, List<List<Integer>> ans) {

        if (index == n) {
            ans.add(new ArrayList<>(CurrAns));
            return;
        }

        //include karte time karna hai include
        CurrAns.add(nums[index]);
        subsetWithDup(nums, index + 1, n, CurrAns, ans);
        CurrAns.remove(CurrAns.size() - 1);


        //exculde karte time check karo, kuki exlude kiye tu same element aa gaya tu tab dikkat hai
        while(index<n-1 && nums[index]==nums[index+1])
            index++;

        subsetWithDup(nums, index + 1, n, CurrAns, ans);

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        List<List<Integer>> ans1 = new ArrayList<>();
        ArrayList<Integer> currAns = new ArrayList<>();
        subsetWithDup(nums, 0, nums.length, currAns, ans);
        for (List<Integer> a : ans) {
            ans1.add(a);
        }
        return ans1;
    }
*/

    //permutation of string
      public  static int permutation(String str, String ans)
      {

          if(str.length()==0)
          {
              System.out.println(ans);
              return 1;
          }

          int count=0;

           for(int i=0;i<str.length();i++)
           {

               char ch=str.charAt(i);

               String ros=str.substring(0,i)+str.substring(i+1);

               count+=permutation(ros,ans+ch);
           }

           return count;
      }


      // top down

     //Uses less memory as results are added directly to a shared list.

    public  static int permutation(String str, String ans, ArrayList<String>finalAns)
    {

        if(str.length()==0)
        {
            finalAns.add(ans);
            return 1;
        }

        int count=0;

        for(int i=0;i<str.length();i++)
        {

            char ch=str.charAt(i);

            String ros=str.substring(0,i)+str.substring(i+1);

            count+=permutation(ros,ans+ch, finalAns);
        }

        return count;
    }


    // bootom up



    public  static ArrayList<String> permutation(String str)
    {

        if(str.length()==0)
        {

             ArrayList<String> ans=new ArrayList<>();
             ans.add("");
             return ans;


        }



         ArrayList<String>finalAns=new ArrayList<>();

        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);

            String ros=str.substring(0,i)+str.substring(i+1);
            ArrayList<String> ans=permutation(ros);

            for(String s:ans)
            {
                finalAns.add(ch+s);
            }
        }


        return finalAns;



    }


    //if {1,2,3} is given then
    // ArrayList is given then

    public static int permutaion(ArrayList<Integer>arr,int totalElement,int n,ArrayList<Integer>ans,ArrayList<ArrayList<Integer>>finalAns,boolean[] vis)
    {

        if(totalElement==n)
        {
            finalAns.add(new ArrayList<>(ans));

            return 1;
        }


        int count=0;
         for(int i=0;i<n;i++)
         {
             if(vis[i]==false) {
                 vis[i] = true;
                 int ele = arr.get(i);
                 ans.add(ele);
                 count += permutaion(arr, totalElement + 1, n, ans, finalAns, vis);
                 ans.remove(ans.size() - 1);
                 vis[i] = false;

             }
         }

         return count;
    }


    public static int permutation(
            ArrayList<Integer> arr,
            ArrayList<Integer> current,
            ArrayList<ArrayList<Integer>> result)
    {
        if (arr.isEmpty()) {
            result.add(new ArrayList<>(current));
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            // Choose the element at index i
            int chosen = arr.remove(i);
            current.add(chosen);

            // Recurse with the remaining elements
            count += permutation(arr, current, result);

            // Backtrack: restore the state
            current.remove(current.size() - 1);
            arr.add(i, chosen);
        }

        return count;
    }

    public static int permutaionUnique(int []arr,int totalElement,int n,List<Integer>ans,List<List<Integer>>finalAns,boolean[] vis)
    {

        if(totalElement==n)
        {
            finalAns.add(new ArrayList<>(ans));

            return 1;
        }


        Set<Integer> set=new HashSet<>();
        int count=0;
        for(int i=0;i<n;i++)
        {
            if(vis[i]==false && !set.contains(arr[i])) {
                set.add(arr[i]);
                vis[i] = true;
                int ele = arr[i];
                ans.add(ele);
                count += permutaionUnique(arr, totalElement + 1, n, ans, finalAns, vis);
                ans.remove(ans.size() - 1);
                vis[i] = false;

            }
        }

        return count;
    }

    public static int permutaionUnique2(int []arr,int totalElement,int n,List<Integer>ans,List<List<Integer>>finalAns,boolean[] vis)
    {

        if(totalElement==n)
        {
            finalAns.add(new ArrayList<>(ans));

            return 1;
        }


        int prev=-11;
        int count=0;
        for(int i=0;i<n;i++)
        {
            if(vis[i]==false && prev!=arr[i]) {

                vis[i] = true;
                int ele = arr[i];
                ans.add(ele);
                count += permutaionUnique2(arr, totalElement + 1, n, ans, finalAns, vis);
                ans.remove(ans.size() - 1);
                vis[i] = false;
                prev=arr[i];


            }


        }

        return count;
    }


    public static void main(String[] args) {


    }
}
