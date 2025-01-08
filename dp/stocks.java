package dp;

import java.util.Arrays;

public class stocks {

    //leetcode 121
    public int maxProfit1(int[] prices) {
        int n = prices.length;

        int profit = 0;
        int min1 = prices[0];

        for (int i = 1; i < n; i++) {
            profit = Math.max(profit, prices[i] - min1);
            min1 = Math.min(prices[i], min1);

        }

        return profit;
    }

    //leetcode 122
    // 1->buy 0->sell
    public int mProfit(int[] prices, int ind, int buy, int[][] dp) {

        if (ind == prices.length)
            return 0;

        if (dp[ind][buy] != -1)
            return dp[ind][buy];

        int profit = 0;

        if (buy == 1) {
            profit = Math.max(-prices[ind] + mProfit(prices, ind + 1, 0, dp), 0 + mProfit(prices, ind + 1, 1, dp));
        } else {
            profit = Math.max(prices[ind] + mProfit(prices, ind + 1, 1, dp), 0 + mProfit(prices, ind + 1, 0, dp));
        }

        return dp[ind][buy] = profit;
    }

    public int maxProfit2(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        return mProfit(prices, 0, 1, dp);
    }

    //leetcode 123
    public int mProfit(int[] prices, int ind, int buy, int numTrans, int[][][] dp) {

        if (ind == prices.length)
            return 0;

        if (numTrans == 4)
            return 0;

        if (dp[ind][buy][numTrans] != -1)
            return dp[ind][buy][numTrans];

        int profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[ind] + mProfit(prices, ind + 1, 0, numTrans + 1, dp),
                    0 + mProfit(prices, ind + 1, 1, numTrans, dp));
        } else {
            profit = Math.max(prices[ind] + mProfit(prices, ind + 1, 1, numTrans + 1, dp),
                    0 + mProfit(prices, ind + 1, 0, numTrans, dp));
        }

        return dp[ind][buy][numTrans] = profit;

    }

    public int maxProfit(int[] prices) {

        int n = prices.length;

        int[][][] dp = new int[n + 1][2][4];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i][0], -1);
            Arrays.fill(dp[i][1], -1);
        }
        return mProfit(prices, 0, 1, 0, dp);
    }

    //leetcode 188
    public int mProfit(int[] prices, int ind, int buy, int numTrans, int[][][] dp, int k) {

        if (ind == prices.length)
            return 0;

        if (numTrans == 2 * k)
            return 0;

        if (dp[ind][buy][numTrans] != -1)
            return dp[ind][buy][numTrans];

        int profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[ind] + mProfit(prices, ind + 1, 0, numTrans + 1, dp, k),
                    0 + mProfit(prices, ind + 1, 1, numTrans, dp, k));
        } else {
            profit = Math.max(prices[ind] + mProfit(prices, ind + 1, 1, numTrans + 1, dp, k),
                    0 + mProfit(prices, ind + 1, 0, numTrans, dp, k));
        }

        return dp[ind][buy][numTrans] = profit;

    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        int[][][] dp = new int[n + 1][2][2 * k];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i][0], -1);
            Arrays.fill(dp[i][1], -1);
        }
        return mProfit(prices, 0, 1, 0, dp, k);
    }

    //leetcode 309
    public int mProfit2(int[] prices, int ind, int buy, int[][] dp) {

        if (ind >= prices.length)
            return 0;

        if (dp[ind][buy] != -1)
            return dp[ind][buy];

        int profit = 0;

        if (buy == 1) {
            profit = Math.max(-prices[ind] + mProfit2(prices, ind + 1, 0, dp), 0 + mProfit2(prices, ind + 1, 1, dp));
        } else {
            profit = Math.max(prices[ind] + mProfit2(prices, ind + 2, 1, dp), 0 + mProfit2(prices, ind + 1, 0, dp));
        }

        return dp[ind][buy] = profit;
    }

    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        return mProfit2(prices, 0, 1, dp);
    }

    //leetcode 714
    public int mProfit(int[] prices, int ind, int buy, int[][] dp, int fee) {

        if (ind == prices.length)
            return 0;

        if (dp[ind][buy] != -1)
            return dp[ind][buy];

        int profit = 0;

        if (buy == 1) {
            profit = Math.max(-prices[ind] + mProfit(prices, ind + 1, 0, dp, fee),
                    0 + mProfit(prices, ind + 1, 1, dp, fee));
        } else {
            profit = Math.max(prices[ind] - fee + mProfit(prices, ind + 1, 1, dp, fee),
                    0 + mProfit(prices, ind + 1, 0, dp, fee));
        }

        return dp[ind][buy] = profit;
    }

    public int maxProfit4(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        return mProfit(prices, 0, 1, dp, fee);
    }

    //leetcode 740
    public int deleteAndEarn(int[] nums, int idx, int[] dp) {
        if (idx == nums.length)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];
        int take = 0;

        int i = idx + 1;
        while (i < nums.length) {
            if (nums[i] == nums[idx])
                take += nums[i];
            else if (nums[i] != nums[idx] + 1) {
                break;
            }

            i++;
        }

        take += nums[idx] + deleteAndEarn(nums, i, dp);

        int notTake = 0;

        i = idx + 1;

        while (i < nums.length && nums[i] == nums[idx]) {
            i++;
        }

        notTake = deleteAndEarn(nums, i, dp);

        return dp[idx] = Math.max(take, notTake);
    }

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return deleteAndEarn(nums, 0, dp);
    }

    //leetcode 377
    public int coinChange(int[] coins, int amount, int[] dp) {

        if (amount == 0)
            return 1;

        if (dp[amount] != -1)
            return dp[amount];

        int count = 0;

        for (int i = 0; i < coins.length; i++) {
            int ele = coins[i];

            if (amount - ele >= 0)
                count += coinChange(coins, amount - ele, dp);
        }

        return dp[amount] = count;

    }

    public int combinationSum4(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, -1);
        int res = coinChange(coins, amount, dp);
        return res;
    }
    /*
      public int coinChange(int[] coins, int amount, int idx, int[][] dp) {

        if (amount == 0)
            return 1;

        if (idx == coins.length) {
            return amount == 0 ? 1 : 0;
        }

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int count = 0;

        // exclude
        count += coinChange(coins, amount, idx + 1, dp);

        // include
        if (amount >= coins[idx])
            count += coinChange(coins, amount - coins[idx], 0, dp);

        return dp[idx][amount] = count;

    }

    public int combinationSum4(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        int res = coinChange(coins, amount, 0, dp);
        return res;
    }
     */

    //leetcode 279
    // idea is to solve n-square;

    public int minNumSquares(int n, int[] dp) {
        if (n == 0)
            return dp[n] = 0;

        if (dp[n] != -1)
            return dp[n];

        int ans = (int) 1e9;

        for (int i = 1; i * i <= n; i++) {
            ans = Math.min(ans, 1 + minNumSquares(n - i * i, dp));
        }

        return dp[n] = ans;
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return minNumSquares(n, dp);
    }

    //2369. Check if There is a Valid Partition For The Array
    public boolean check(int[] nums, int si, int ei) {
        int len = ei - si + 1;
        if (len == 2) {
            return nums[si] == nums[ei]; // Case: [x, x]
        }
        if (len == 3) {
            return (nums[si] == nums[si + 1] && nums[si + 1] == nums[ei]) // Case: [x, x, x]
                    || (nums[si + 1] == nums[si] + 1 && nums[ei] == nums[si] + 2); // Case: [x, x+1, x+2]
        }
        return false;
    }

    public boolean validPartition(int[] nums, int idx, int[] dp) {
        if (idx >= nums.length)
            return true;

        if (dp[idx] != -1)
            return dp[idx] == 1;

        boolean res = false;

        // Check partition of size 2
        if (idx + 1 < nums.length && check(nums, idx, idx + 1)) {
            res = res || validPartition(nums, idx + 2, dp);
        }

        // Check partition of size 3
        if (idx + 2 < nums.length && check(nums, idx, idx + 2)) {
            res = res || validPartition(nums, idx + 3, dp);
        }

        dp[idx] = (res == true) ? 1 : 0;
        return res;
    }

    public boolean validPartition(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return validPartition(nums, 0, dp);
    }
    //leetcode 1856
    public int[] nextSmallerOnright(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        LinkedList<Integer> st = new LinkedList<>();
        st.addLast(-1);

        for (int i = 0; i < n; i++) {
            while (st.getLast() != -1 && nums[st.getLast()] > nums[i]) {
                ans[st.removeLast()] = i;

            }

            st.addLast(i);
        }

        return ans;
    }

    public int[] nextSmallerOnLeft(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addLast(-1);

        for (int i = n - 1; i >= 0; i--) {
            while (st.getLast() != -1 && nums[st.getLast()] > nums[i]) {
                ans[st.removeLast()] = i;

            }

            st.addLast(i);
        }

        return ans;
    }

    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] ps = new long[n];
        ps[0] = nums[0];
        for (int i = 1; i < n; i++) {
            ps[i] = ps[i - 1] + nums[i];
        }

        int[] nsor = nextSmallerOnright(nums);
        int[] nsol = nextSmallerOnLeft(nums);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long leftSum = nsol[i] == -1 ? 0 : ps[nsol[i]];
            long rightSum = ps[nsor[i] - 1];
            ans = Math.max(ans, nums[i] * (rightSum - leftSum));
        }

        return (int) (ans % 1_000_000_007);

    }
}
