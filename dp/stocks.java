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
}
