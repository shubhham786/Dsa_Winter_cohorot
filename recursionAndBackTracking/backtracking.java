package recursionAndBackTracking;

import java.util.*;
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

    //leetcode 93
    public int ipAddress(String s, int idx, String currAns, List<String> finalAns, int dots) {

        if (s.length() == idx || dots == 4) {

            if (s.length() == idx && dots == 4) {
                // removing last dot
                finalAns.add(currAns.substring(0, currAns.length() - 1));

                return 1;
            }

            return 0;
        }

        int count = 0;

        for (int len = 1; len <= 3; len++) {
            if (idx + len <= s.length()) {

                String part = s.substring(idx, idx + len);

                if (isValid(part))
                    count += ipAddress(s, idx + len, currAns + part + ".", finalAns, dots + 1);
            }

        }

        return count;
    }

    public boolean isValid(String s) {
        if (s.length() > 1 && s.charAt(0) == '0')
            return false;

        int num = Integer.parseInt(s);

        return num >= 0 && num <= 255;
    }

    //using StringBuilder
    public int  ipAddresses(String s, int idx, StringBuilder currAns, List<String> finalAns, int dots) {
        // Base case: if we've reached the end of the string or formed 4 segments
        if (idx == s.length() || dots == 4) {
            if (idx == s.length() && dots == 4) {
                // Remove the last dot and add to the final result
                finalAns.add(currAns.substring(0, currAns.length() - 1));
                return 1;
            }
            return 0;
        }

        int count=0;

        for (int len = 1; len <= 3; len++) {
            if (idx + len > s.length()) break; // Substring out of bounds

            String part = s.substring(idx, idx + len);

            // Check if the segment is valid
            if (isValid(part)) {
                int prevLength = currAns.length(); // Store current length

                // Add the current segment and a dot
                currAns.append(part).append('.');

                // Recursive call
                count+=ipAddresses(s, idx + len, currAns, finalAns, dots + 1);

                // Backtrack: remove the added segment and dot
                currAns.setLength(prevLength);
            }
        }

        return count;
    }
    public List<String> restoreIpAddresses(String s) {

        List<String> finalAns = new ArrayList<>();

        //using string
       // int count = ipAddress(s, 0, "", finalAns, 0);
        //using StringBuilder
        int count =  ipAddresses(s, 0,  new StringBuilder(), finalAns, 0);

        return finalAns;

    }

    //leetcode 17
    public int combination(String digits, int idx, StringBuilder currAns, List<String> finalAns, String[] map) {

        if (idx == digits.length()) {
            finalAns.add(currAns.toString());
            return 1;
        }

        int count = 0;
        int index = digits.charAt(idx) - '0';
        String currIndexString = map[index];

        for (int i = 0; i < currIndexString.length(); i++) {
            char ch = currIndexString.charAt(i);
            int prevLength=currAns.length();
            currAns.append(ch);
            count += combination(digits, idx + 1, currAns, finalAns, map);
            currAns.setLength(prevLength);

        }

        return count;
    }

    public List<String> letterCombinations(String digits) {

        List<String> finalAns = new ArrayList<>();
        if (digits.length() == 0)
            return finalAns;

        String[] map = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        int count = combination(digits, 0, new StringBuilder(), finalAns, map);

        return finalAns;

    }

    /*
    using string
        public int combination(String digits, int idx, String currAns, List<String> finalAns, String[] map) {

        if (idx == digits.length()) {
            finalAns.add(currAns);
            return 1;
        }

        int count = 0;
        int index = digits.charAt(idx) - '0';
        String currIndexString = map[index];

        for (int i = 0; i < currIndexString.length(); i++) {
            char ch = currIndexString.charAt(i);
            count += combination(digits, idx + 1, currAns + ch, finalAns, map);

        }

        return count;
    }

    public List<String> letterCombinations(String digits) {

        List<String> finalAns = new ArrayList<>();
        if (digits.length() == 0)
            return finalAns;

        String[] map = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        int count = combination(digits, 0, "", finalAns, map);

        return finalAns;

    }
     */


//leetcode 473
    //revisit during graph and dp
    public boolean makeSquare(int[] matchsticks, int idx, long[] len, long len1) {

        if (idx == matchsticks.length) {
            boolean res = true;
            for (int i = 0; i < 4; i++) {
                if (len[i] != len1)
                    res = false;
            }

            return res;

        }


        boolean res = false;

        for (int i = 0; i < 4; i++) {
            if (len[i] + matchsticks[idx] > len1) {
                continue;
            }
            len[i] += matchsticks[idx];
            res = res || makeSquare(matchsticks, idx + 1, len, len1);
            len[i] -= matchsticks[idx];

            // If the desired sum has already been achieved for this side (len[i] == 0 after
            // backtracking),
            // move forward in the loop to avoid checking equivalent empty sides.
            if (len[i] == 0) {
                break;
            }

        }

        return res;
    }

    public boolean makesquare(int[] matchsticks) {

        long[] len = new long[4];

        long sum = 0;

        for (int i = 0; i < matchsticks.length; i++)
            sum += matchsticks[i];

        if (sum % 4 != 0)
            return false;

        long len1 = sum / 4;

        return makeSquare(matchsticks, 0, len, len1);
    }

//leetcode 1849
    /*
    import java.math.BigInteger;

class Solution {

    public boolean isValid(String currStr, String prev) {

        BigInteger num = new BigInteger(currStr); // Parse to BigInteger
        BigInteger num1 = new BigInteger(prev);

        return num1.subtract(num).equals(BigInteger.ONE);

    }

    public boolean splitString(String s, int idx, String prev, int noSplit) {

        if (idx == s.length()) {

            // System.out.println("prev "+prev);
            if (prev.length() == idx)
                return false;

            return noSplit >= 1;
        }

        boolean res = false;
        for (int i = idx; i < s.length(); i++) {
            String currStr = s.substring(idx, i + 1);

            if (prev.equals("") || isValid(currStr, prev))
                res = res || splitString(s, i + 1, currStr, noSplit + 1);

        }

        return res;

    }

    public boolean splitString(String s) {

        return splitString(s, 0, "", 0);
    }
}
     */

    public boolean splitString(String s, int idx, ArrayList<Long> list) {

        if (idx == s.length()) {

            return list.size() >= 2;
        }

        boolean res = false;
        long num = 0;
        for (int i = idx; i < s.length(); i++) {

            num = num * 10 + (s.charAt(i) - '0');

            if (list.size() == 0 || list.get(list.size() - 1) - num == 1) {
                list.add(num);
                res = res || splitString(s, i + 1, list);

                list.remove(list.size() - 1);
            }

        }

        return res;

    }

    public boolean splitString(String s) {

        return splitString(s, 0, new ArrayList<Long>());
    }

    //1980. Find Unique Binary String
    private String generate(String curr, int n, Set<String> numsSet) {
        if (curr.length() == n) {
            if (!numsSet.contains(curr)) {
                return curr;
            }

            return "";
        }

        String addZero = generate(curr + "0", n, numsSet);
        if (addZero.length() > 0) {
            return addZero;
        }

        return generate(curr + "1", n, numsSet);
    }

    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> numsSet = new HashSet();
        for (String s : nums) {
            numsSet.add(s);
        }

        return generate("", n, numsSet);
    }

    //leetcode 1239
    public int maxLength(List<String> arr) {
        return backtrack(arr, 0, new HashSet<>());
    }

    // Helper function for backtracking
    private int backtrack(List<String> arr, int idx, Set<Character> used) {
        int maxLen = used.size(); // Current length is the size of the set

        for (int i = idx; i < arr.size(); i++) {
            String s = arr.get(i);
            if (canAdd(s, used)) {
                // Temporarily add this string's characters to the set
                for (char ch : s.toCharArray()) {
                    used.add(ch);
                }

                // Recur with the updated set
                maxLen = Math.max(maxLen, backtrack(arr, i + 1, used));

                // Backtrack: remove the string's characters
                for (char ch : s.toCharArray()) {
                    used.remove(ch);
                }
            }
        }

        return maxLen;
    }

    // Check if the string can be added without conflicts
    private boolean canAdd(String s, Set<Character> used) {
        Set<Character> temp = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (used.contains(ch) || !temp.add(ch)) {
                return false; // Conflict found
            }
        }
        return true;
    }

    //leetcode 698
    public boolean partition(int[] nums, int k, int idx, int sum, int csum, boolean[] vis) {

        if (k == 0)
            return true;

        boolean res = false;
        if (csum == sum)
            res = res || partition(nums, k - 1, 0, sum, 0, vis);

        for (int i = idx; i < nums.length; i++) {

            if (csum + nums[i] <= sum && vis[i] == false) {
                vis[i] = true;

                res = res || partition(nums, k, i + 1, sum, csum + nums[i], vis);
                vis[i] = false;

            }
        }

        return res;

    }

    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % k != 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        sum = sum / k;
        boolean[] vis = new boolean[nums.length];
        return partition(nums, k, 0, sum, 0, vis);
    }

    //leetcode 2597
    public int beautiful(int[] nums, int k, int idx, Map<Integer, Integer> map) {

        // System.out.println(s+" "+map);
        int count = 1;

        for (int i = idx; i < nums.length; i++) {
            int ele = nums[i];
            if (!map.containsKey(ele - k) && !map.containsKey(ele + k)) {
                map.put(ele, map.getOrDefault(ele, 0) + 1);
                count += beautiful(nums, k, i + 1, map);
                if (map.get(ele) == 1) {
                    map.remove(ele);
                } else {
                    map.put(ele, map.get(ele) - 1);
                }

            }
        }

        return count;

    }

    /*
    using exclude and include
     public int beautiful(int[] nums, int k, int idx, Map<Integer, Integer> map) {

        if (idx == nums.length)
            return 1;

        int count = 0;

        int ele = nums[idx];

        if (!map.containsKey(ele - k) && !map.containsKey(ele + k)) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
            count += beautiful(nums, k, idx + 1, map);
            if (map.get(ele) == 1) {
                map.remove(ele);
            } else {
                map.put(ele, map.get(ele) - 1);
            }
        }
        count += beautiful(nums, k, idx + 1, map);

        return count;

    }
     */
    public int beautifulSubsets(int[] nums, int k) {

         //using set will fail for [1,1,2,3]
        Arrays.sort(nums);


        Map<Integer, Integer> map = new HashMap<>();
        return beautiful(nums, k, 0, map) - 1;
    }

    //leetcode 51
    //Nqueen
    public boolean isSafeToPlaceQueen(List<String> currAns, int row, int col) {
        // same column me na ho kaui
        for (int i = 0; i < row; i++) {
            if (currAns.get(i).charAt(col) == 'Q') {
                return false; // Same column
            }

            //see this one
            if (Math.abs(i - row) == Math.abs(currAns.get(i).indexOf('Q') - col))
                return false;
        }

        return true;

    }

    public int solve(int n, int row, List<String> currAns, List<List<String>> ans) {
        if (row == n) {
            ans.add(new ArrayList<>(currAns));
            return 1;
        }
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (isSafeToPlaceQueen(currAns, row, i)) {
                String s = currAns.get(row); // Assuming currAns is a List<String>
                // String original = s;
                char[] charArray = s.toCharArray(); // Convert the string to a char array
                charArray[i] = 'Q'; // Modify the character at index i

                currAns.set(row, new String(charArray));

                count += solve(n, row + 1, currAns, ans);
                currAns.set(row, s);

            }
        }

        return count;

    }

    public List<List<String>> solveNQueens(int n) {

        List<String> currAns = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                sb.append(".");
            }

            currAns.add(sb.toString());
        }

        List<List<String>> ans = new ArrayList<>();

        int count = solve(n, 0, currAns, ans);

        return ans;
    }

    //leetcode 1255
    public int maxScoreWords(String[] words, int[] freq, int[] score, int idx) {
        if (idx == words.length) {
            return 0;
        }

        int exclude = maxScoreWords(words, freq, score, idx + 1);

        int include = 0;

        boolean isUsed = true;

        String word = words[idx];
        int sword = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (freq[ch - 'a'] == 0)
                isUsed = false;

            freq[ch - 'a']--;
            sword += score[ch - 'a'];
        }

        if (isUsed) {
            include = sword + maxScoreWords(words, freq, score, idx + 1);
        }

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            freq[ch - 'a']++;

        }

        return Math.max(include, exclude);

    }

    /*
    using for loop
    public int maxScoreWords(String[] words, int[] freq, int[] score, int idx) {
        if (idx == words.length) {
            return 0;
        }

        int maxScore = 0;
        for (int j = idx; j < words.length; j++) {

            String word = words[j];
            boolean isUsed = true;
            int wordScore = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (freq[ch - 'a'] == 0)
                    isUsed = false;

                freq[ch - 'a']--;
                wordScore += score[ch - 'a'];
            }

            int currentScore = 0;
            if (isUsed) {
                currentScore = wordScore + maxScoreWords(words, freq, score, j + 1);
            }

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                freq[ch - 'a']++;

            }

            maxScore = Math.max(maxScore, currentScore);
        }
        return maxScore;

    }
     */
    public int maxScoreWords(String[] words, char[] letters, int[] score) {

        int[] freq = new int[26];

        for (int i = 0; i < letters.length; i++) {
            char ch = letters[i];

            freq[ch - 'a']++;
        }
        return maxScoreWords(words, freq, score, 0);
    }

    //leetcode 140
    public int wordBreak(String s,List<String>finalAns,int idx,Set<String>set,StringBuilder currAns)
    {
        if(idx==s.length())
        {
            finalAns.add(currAns.toString().trim());
            return 1;
        }

        int count=0;

        StringBuilder sb=new StringBuilder();
        for(int i=idx;i<s.length();i++)
        {
            sb.append(s.charAt(i));

            String s1=sb.toString();

            if(set.contains(s1))
            {
                int prevLen=currAns.length();
                currAns.append(s1).append(" ");
                count+=wordBreak(s,finalAns,i+1,set,currAns);
                currAns.setLength(prevLen);

            }
        }

        return count;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {


        List<String>finalAns=new ArrayList<>();
        Set<String>set=new HashSet<>();

        for(String s1:wordDict)
            set.add(s1);


        wordBreak(s,finalAns,0,set,new StringBuilder());

        return finalAns;
    }
    /*
    using string
        public int wordBreak(String s,List<String>finalAns,int idx,Set<String>set,String currAns)
    {
          if(idx==s.length())
          {
              finalAns.add(currAns.trim());
              return 1;
          }

          int count=0;

          StringBuilder sb=new StringBuilder();
          for(int i=idx;i<s.length();i++)
          {
              sb.append(s.charAt(i));

              String s1=sb.toString();

              if(set.contains(s1))
              {

                  count+=wordBreak(s,finalAns,i+1,set,currAns+s1+" ");

              }
          }

          return count;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {


        List<String>finalAns=new ArrayList<>();
        Set<String>set=new HashSet<>();

        for(String s1:wordDict)
         set.add(s1);


         wordBreak(s,finalAns,0,set,"");

         return finalAns;
    }
     */
}
