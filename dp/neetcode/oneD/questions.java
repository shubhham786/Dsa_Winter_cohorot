package dp.neetcode.oneD;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class questions {
    //1137. N-th Tribonacci Number
    public int tribonacci(int n) {

        if (n == 0 || n == 1)
            return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    //leetcode 5
    //gap stragety
    public String longestPalindrome(String s) {

        if (s.length() < 2)
            return s;

        int n = s.length();

        int[][] dp = new int[n][n];

        int si = 0;
        int maxLen = 0;

        String ans = "";

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) ? 2 : 0);
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] != 0) ? 2 + dp[i + 1][j - 1] : 0;
                }

                if (dp[i][j] > maxLen) {

                    si = i;
                    maxLen = dp[i][j];
                }

            }
        }

        // include strating index, exculde ending index
        // both are index in java
        return ans = s.substring(si, si + maxLen);

    }

    //leetcode 647
    public int countSubstrings(String s) {

        int count = 0;

        if (s.length() < 2)
            return 1;

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = 1;
                } else if (gap == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) ? 1 : 0);
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] != 0) ? 1 : 0;
                }

                count += dp[i][j];
            }
        }

        return count;
    }

    //leetcode 91
    public int decodings(String s, int idx, int[] dp) {

        if (idx == s.length())
            return dp[idx] = 1;

        if (dp[idx] != -1)
            return dp[idx];
        // two choices

        // single
        int count = 0;
        char ch = s.charAt(idx);

        if (ch == '0')
            return dp[idx] = 0;

        count += decodings(s, idx + 1, dp);

        // double
        if (idx + 1 < s.length()) {
            char ch1 = s.charAt(idx + 1);

            int z = (ch - '0') * 10 + (ch1 - '0');
            if (z <= 26)
                count += decodings(s, idx + 2, dp);

        }

        return dp[idx] = count;

    }

    public int numDecodings(String s) {

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        return decodings(s, 0, dp);
    }

    //leetcode 322
    // include exclude
    public int coinChange(int[] coins, int amount, int idx, int[][] dp) {
        if (idx == coins.length)
            return (int) 1e9;

        if (amount == 0)
            return dp[idx][amount] = 0;

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int ans = (int) 1e9;

        // not include
        ans = Math.min(ans, coinChange(coins, amount, idx + 1, dp));

        if (amount - coins[idx] >= 0)
            ans = Math.min(1 + coinChange(coins, amount - coins[idx], idx, dp), ans);

        return dp[idx][amount] = ans;

    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n][amount + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = coinChange(coins, amount, 0, dp);
        return ans == (int) 1e9 ? -1 : ans;
    }

    //leetcode 152
    public int maxProduct(int[] nums) {

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] < 0) {
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            minEndingHere = Math.min(nums[i], minEndingHere * nums[i]);
            maxEndingHere = Math.max(nums[i], maxEndingHere * nums[i]);

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    //leetcode 139
    HashMap<String, Integer> map;

    public boolean wordBreak(String s, int idx, int[] dp) {
        if (idx == s.length())
            return true;

        if (dp[idx] != -1)
            return dp[idx] == 1;

        StringBuilder sb = new StringBuilder();
        boolean res = false;
        for (int i = idx; i < s.length(); i++) {
            sb.append(s.charAt(i));
            String s1 = sb.toString();

            if (map.containsKey(s1)) {
                res = res || wordBreak(s, i + 1, dp);
            }
        }
        dp[idx] = (res == true) ? 1 : 0;
        return res;

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();

        for (String word : wordDict) {
            map.put(word, 1);
        }
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);

        return wordBreak(s, 0, dp);
    }

//leetcode 300
public int lengthOfLIS(int[] nums) {

    int n = nums.length;
    int[] dp = new int[n];
    int len = 1;
    for (int i = 0; i < nums.length; i++) {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

        }

        len = Math.max(len, dp[i]);
    }

    return len;
}

//leetcode 416
public boolean targetSum(int[] nums, int tar, int idx, Boolean[][] dp) {
    if (tar == 0)
        return dp[idx][tar] = true;

    if (idx == nums.length) {
        dp[idx][tar] = (tar == 0 ? true : false);
    }
    if (dp[idx][tar] != null)
        return dp[idx][tar];

    boolean res = false;

    res = res || targetSum(nums, tar, idx + 1, dp);

    if (tar - nums[idx] >= 0)
        res = res || targetSum(nums, tar - nums[idx], idx + 1, dp);

    return dp[idx][tar] = res;
}

    public boolean canPartition(int[] nums) {

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0)
            return false;

        int tar = sum / 2;
        int n = nums.length;
        Boolean[][] dp = new Boolean[n + 1][tar + 1];

        Boolean res = targetSum(nums, tar, 0, dp);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= tar; j++)
                System.out.print(dp[i][j] + " ");

            System.out.println();
        }

        return res;

    }

    //leetcode 120
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n - 1).size();

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = m - 1; i >= 0; i--) {
            dp[n - 1][i] = triangle.get(n - 1).get(i);
        }

        m--;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
            m--;
        }

        return dp[0][0];
    }

}
