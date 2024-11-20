package recursionAndBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class backtracking {

    //leetcode 1863
    public int subsetXORSum(int[] nums) {
        return subsetXorSum(nums, 0, 0, nums.length);
    }

    private int subsetXorSum(int[] nums, int xorSoFar, int index, int n) {
        if (index == n) {
            return xorSoFar;
        }

        // Include the current element in the XOR sum
        int include = subsetXorSum(nums, xorSoFar ^ nums[index], index + 1, n);

        // Exclude the current element from the XOR sum
        int exclude = subsetXorSum(nums, xorSoFar, index + 1, n);

        // Return the total XOR sum
        return include + exclude;
    }


    //leetcode 78
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
        subsetWithDup(nums, index + 1, n, CurrAns, ans);

    }


    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();


        List<Integer> currAns = new ArrayList<>();
        subsetWithDup(nums, 0, nums.length, currAns, ans);

        return ans;
    }

    //leetcode 39

    /*

       using normal for loop
     */
    public static int  combinationSum(int[] candidates, int target, int idx, List<Integer>currAns,List<List<Integer>>ans)
    {

        if(target==0)
        {
            ans.add(new ArrayList<>(currAns));
            return 1;
        }

        int count=0;
        for(int i=idx;i<candidates.length;i++)
        {
            int ele=candidates[i];

            if(target>=ele){
                currAns.add(ele);
                count+=combinationSum(candidates,target-ele, i,currAns,ans);
                currAns.remove(currAns.size()-1);

            }
        }


        return count;

    }

    /*
    // include exculde principle
    public static int combinationSum(int[] candidates, int target, int idx, List<Integer> currAns,
                                     List<List<Integer>> ans) {

        if (idx == candidates.length || target == 0) {
            if (target == 0) {
                ans.add(new ArrayList<>(currAns));
                return 1;
            }

            return 0;
        }

        int count = 0;

        // exculde current one
        count += combinationSum(candidates, target, idx + 1, currAns, ans);

        // include current one
        int ele = candidates[idx];
        if (target >= ele) {
            currAns.add(ele);
            count += combinationSum(candidates, target - ele, idx, currAns, ans);
            currAns.remove(currAns.size() - 1);
        }

        return count;

    }

    */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {


        List<Integer>currAns=new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();

        int count=combinationSum(candidates,target,0,currAns,ans);


        return ans;
    }

    //leetcode 77
    public int combine(int n, int k, int idx,List<Integer> currAns,List<List<Integer>>ans)
    {

        if(idx>n || k==0)
        {
            if(k==0)
            {
                ans.add(new ArrayList<>(currAns));
                return 1;
            }

            return 0;
        }

        int count=0;

        for(int i=idx;i<=n;i++)
        {

            if(k>0)
            {
                currAns.add(i);
                count+=combine(n,k-1,i+1,currAns,ans);
                currAns.remove(currAns.size()-1);
            }
        }

        return count;
    }

    /*

     public int combine(int n, int k, int idx,List<Integer> currAns,List<List<Integer>>ans)
    {

          if(idx>n || k==0)
          {
             if(k==0)
             {
                 ans.add(new ArrayList<>(currAns));
                 return 1;
             }

             return 0;
          }

             int count=0;





                     //include
                   currAns.add(idx);
                   count+=combine(n,k-1,idx+1,currAns,ans);
                   currAns.remove(currAns.size()-1);


               //exclude

               count+=combine(n,k,idx+1,currAns,ans);


          return count;
    }
*/


    public List<List<Integer>> combine(int n, int k) {
        List<Integer> currAns = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        int count=combine(n,k,1,currAns,ans);


        return ans;
    }

    //leetcode 46
    public static int permutaion(int []arr,int totalElement,int n,List<Integer>ans,List<List<Integer>>finalAns,boolean[] vis)
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
                int ele = arr[i];
                ans.add(ele);
                count += permutaion(arr, totalElement + 1, n, ans, finalAns, vis);
                ans.remove(ans.size() - 1);
                vis[i] = false;

            }
        }

        return count;
    }
    public List<List<Integer>> permute(int[] nums) {


        int n=nums.length;
        boolean [] vis=new boolean[n];

        List<Integer>ans=new ArrayList<>();
        List<List<Integer>>finalAns=new ArrayList<>();

        int count=permutaion(nums,0,n,ans,finalAns,vis);

        return finalAns;

    }
    //leetcode 90
    /*
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


    //leetcode 40
    /*
    public static int combinationSum(int[] candidates, int target, int idx, List<Integer> currAns,
                                     List<List<Integer>> ans) {

        if (idx == candidates.length || target == 0) {
            if (target == 0) {
                ans.add(new ArrayList<>(currAns));
                return 1;
            }

            return 0;
        }

        int count = 0;

        int prev = -1;
        for (int i = idx; i < candidates.length; i++) {
            int ele = candidates[i];

            if (target >= ele && prev != ele) {
                currAns.add(ele);
                count += combinationSum(candidates, target - ele, i + 1, currAns, ans);
                currAns.remove(currAns.size() - 1);
                prev = ele;
            }

        }

        return count;

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> currAns = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        int count = combinationSum(candidates, target, 0, currAns, ans);

        return ans;
    }

     */

    //leetcode 79
    public boolean exist(char[][] board, String word, int r, int c, int idx, boolean[][] vis, int[][] dir, int n,
                         int m) {
        if (idx == word.length()) {
            return true;
        }

        vis[r][c] = true;

        boolean res = false;

        for (int i = 0; i < dir.length; i++) {
            int nR = r + dir[i][0];
            int nC = c + dir[i][1];

            if (nR >= 0 && nC >= 0 && nR < n && nC < m && vis[nR][nC] == false && board[nR][nC] == word.charAt(idx)) {
                res = res || exist(board, word, nR, nC, idx + 1, vis, dir, n, m);
            }
        }

        vis[r][c] = false;

        return res;
    }

    public boolean exist(char[][] board, String word) {

        int n = board.length;
        int m = board[0].length;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        boolean[][] vis = new boolean[n][m];
        boolean res = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0) && vis[i][j] == false)
                    res = res || exist(board, word, i, j, 1, vis, dir, n, m);
            }
        }

        return res;

    }

    //leetcode 131
    public boolean isPalindrome(String input, int start, int end)
    {

        boolean isPalindrome = IntStream.range(0, (end - start + 1) / 2)
                .noneMatch(i -> input.charAt(start + i) != input.charAt(end - i));

        return isPalindrome;

    }


    public int partition(String input,int idx,List<String>currAns,List<List<String>>finalAns)
    {
        if(idx==input.length())
        {
            finalAns.add(new ArrayList<>(currAns));
            return 1;
        }


        int count=0;

        for(int i=idx;i<input.length();i++)
        {

            if(isPalindrome(input,idx,i)){

                currAns.add(input.substring(idx,i+1));
                count+=partition(input,i+1,currAns,finalAns);

                currAns.remove(currAns.size()-1);

            }
        }

        return count;
    }


    public List<List<String>> partition(String s) {
        List<String> currAns = new ArrayList<>();
        List<List<String>> finalAns = new ArrayList<>();

        int count=partition(s,0,currAns,finalAns);

        return finalAns;
    }
}
