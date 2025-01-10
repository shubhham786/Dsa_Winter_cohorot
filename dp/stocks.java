package dp;

import java.util.*;

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

    //leetcode 983
    public int mincostTicket(int[] days, int[] costs, Boolean[] isTravelNeeded, int[] dp, int currDay) {
        if (currDay > dp.length - 1)
            return 0;

        if (!isTravelNeeded[currDay]) {

            return mincostTicket(days, costs, isTravelNeeded, dp, currDay + 1);
        }
        if (dp[currDay] != -1)
            return dp[currDay];

        int oneDayCost = costs[0] + mincostTicket(days, costs, isTravelNeeded, dp, currDay + 1);
        int sevenDayCost = costs[1] + mincostTicket(days, costs, isTravelNeeded, dp, currDay + 7);
        int thirtyDayCost = costs[2] + mincostTicket(days, costs, isTravelNeeded, dp, currDay + 30);

        return dp[currDay] = Math.min(oneDayCost, Math.min(sevenDayCost, thirtyDayCost));

    }

    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[days[days.length - 1] + 1];
        Arrays.fill(dp, -1);
        Boolean[] isTravelNeeded = new Boolean[days[days.length - 1] + 1];
        Arrays.fill(isTravelNeeded, false);
        for (int day : days) {
            isTravelNeeded[day] = true;
        }

        return mincostTicket(days, costs, isTravelNeeded, dp, days[0]);

    }

    //leetcode 343
    public int integerBreak(int n, int[] dp) {
        if (n == 2)
            return 1;

        if (dp[n] != 0)
            return dp[n];

        int maxAns = 0;
        for (int i = 1; i < n; i++) {
            maxAns = Math.max(maxAns, Math.max(i * (n - i), i * integerBreak(n - i, dp)));
        }

        return dp[n] = maxAns;
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];

        return integerBreak(n, dp);

    }

    //leetcode 673
    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        int[] count = new int[n];
        int maxLen = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }

                }

            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (dp[i] == maxLen) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    //leetcode 1035
    public int lcs(int[] nums1, int[] nums2, int idx1, int idx2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0)
            return 0;

        if (dp[idx1][idx2] != -1)
            return dp[idx1][idx2];
        int count = 0;

        if (nums1[idx1] == nums2[idx2]) {
            count = Math.max(count, lcs(nums1, nums2, idx1 - 1, idx2 - 1, dp) + 1);
        } else
            count = Math.max(count,
                    Math.max(lcs(nums1, nums2, idx1 - 1, idx2, dp), lcs(nums1, nums2, idx1, idx2 - 1, dp)));

        return dp[idx1][idx2] = count;

    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++)
            Arrays.fill(dp[i], -1);
        return lcs(nums1, nums2, nums1.length - 1, nums2.length - 1, dp);
    }

    //leetcode 2140
    public long mostPoints(int[][] questions, int idx, long[] dp) {
        if (questions.length <= idx) {
            return 0;
        }

        if (dp[idx] != -1)
            return dp[idx];
        // take

        long take = questions[idx][0] + mostPoints(questions, idx + questions[idx][1] + 1, dp);

        long notTake = 0 + mostPoints(questions, idx + 1, dp);

        return dp[idx] = Math.max(take, notTake);
    }

    public long mostPoints(int[][] questions) {

        int n = questions.length;

        long[] dp = new long[n];
        Arrays.fill(dp, -1);
        return mostPoints(questions, 0, dp);
    }

    //2466. Count Ways To Build Good Strings
    int mod = (int) 1e9 + 7;

    public int countGoodStrings(int current, int low, int high, int zero, int one, int[] dp) {
        if (current > high)
            return 0;

        if (dp[current] != -1)
            return dp[current];
        int count = (current >= low) ? 1 : 0;

        count = (count + countGoodStrings(current + zero, low, high, zero, one, dp)) % mod;
        count = (count + countGoodStrings(current + one, low, high, zero, one, dp)) % mod;

        return dp[current] = count;
    }

    public int countGoodStrings(int low, int high, int zero, int one) {

        int[] dp = new int[high + 1];
        Arrays.fill(dp, -1);
        return countGoodStrings(0, low, high, zero, one, dp);
    }
    //leetcode 1626
    public int lis(int[][] scoreAge) {
        int n = scoreAge.length;

        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < scoreAge.length; i++) {
            dp[i] = scoreAge[i][0];

            for (int j = i - 1; j >= 0; j--) {
                if (scoreAge[i][0] >= scoreAge[j][0]) {
                    dp[i] = Math.max(dp[i], scoreAge[i][0] + dp[j]);
                }

            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public int bestTeamScore(int[] scores, int[] ages) {

        int n = scores.length;

        int[][] scoreAge = new int[n][2];

        for (int i = 0; i < n; i++) {
            scoreAge[i][0] = scores[i];
            scoreAge[i][1] = ages[i];
        }

        // problem have been converted to lis
        Arrays.sort(scoreAge, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        return lis(scoreAge);

    }


    //1048. Longest String Chain
    public boolean isPredecessor(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        if (m - n != 1) {
            return false;
        }
        int i = 0;
        int j = 0;
        int diff = 0;

        while (i < n && j < m) {
            if (word1.charAt(i) != word2.charAt(j)) {
                diff++;
                j++;
            } else {
                i++;
                j++;
            }
        }

        if (j < m) {

            diff += m - j;

        }

        return diff == 1;
    }

    public int longestStrChain(String[] nums) {

        Arrays.sort(nums, (a, b) -> a.length() - b.length());
        int n = nums.length;

        int[] dp = new int[n];

        int len = 0;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if ((isPredecessor(nums[j], nums[i])) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;

                }

            }

            if (dp[i] > len) {
                len = dp[i];

            }

        }

        return len;
    }

    //leetcode 935
    HashMap<Integer, int[]> map;
    int mod1 = (int) 1e9 + 7;

    public int knight(int n, int curr, int[][] dp) {
        if (n == 1)
            return 1;

        if (curr == 5)
            return 0;

        if (dp[curr][n] != -1)
            return dp[curr][n];

        int[] arr = map.get(curr);
        int ans = 0;
        for (int i : arr) {
            ans = (ans + knight(n - 1, i, dp)) % mod1;
        }

        return dp[curr][n] = ans;

    }

    public int knightDialer(int n) {

        map = new HashMap<>();

        map.put(0, new int[] { 4, 6 });
        map.put(1, new int[] { 6, 8 });
        map.put(2, new int[] { 7, 9 });
        map.put(3, new int[] { 4, 8 });
        map.put(4, new int[] { 3, 0, 9 });
        // map.put(5, new int[1]);
        map.put(6, new int[] { 1, 0, 7 });
        map.put(7, new int[] { 2, 6 });
        map.put(8, new int[] { 1, 3 });
        map.put(9, new int[] { 2, 4 });

        int[][] dp = new int[10][n + 1];
        for (int i = 0; i < 10; i++)
            Arrays.fill(dp[i], -1);

        int ans = 0;

        for (int i = 0; i < 10; i++) {
            ans = (ans + knight(n, i, dp)) % mod1;

        }

        return ans;

    }

    //leetcode 1043
    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int ind = n - 1; ind >= 0; ind--) {

            int maxAns = -(int) 1e9;

            int maxValue = -(int) 1e9;
            for (int i = ind; i < Math.min(ind + k, arr.length); i++) {
                maxValue = Math.max(maxValue, arr[i]);
                int ans = maxValue * (i - ind + 1);

                maxAns = Math.max(maxAns, ans + dp[i + 1]);
            }
            dp[ind] = maxAns;
        }

        return dp[0];
    }

    //leetcode 368
    public List<Integer> largestDivisibleSubset(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;

        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        int len = 0;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if ((nums[i] % nums[j] == 0) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;

                }

            }

            if (dp[i] > len) {
                len = dp[i];
                maxIndex = i;
            }

        }

        ArrayList<Integer> result = new ArrayList<>();
        while (maxIndex != -1) {
            result.add(0, nums[maxIndex]); // Add at beginning to maintain order
            maxIndex = prev[maxIndex];
        }

        return result;
    }

}
