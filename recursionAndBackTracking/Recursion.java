package recursionAndBackTracking;

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
    public static void main(String[] args) {

   int [] nums={1,2};

      List<List<Integer>>ans=subsetsWithDup(nums);
      System.out.println(ans);

    }
}
