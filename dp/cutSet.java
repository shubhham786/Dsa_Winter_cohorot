package dp;

import java.util.Arrays;
import java.util.List;

public class cutSet {
    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    static int matrixMultiplicationRec(int si, int ei, int[] arr, int[][] dp) {
        if (ei - si == 1)
            return 0;

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int minAns = (int) 1e9;

        for (int cut = si + 1; cut < ei; cut++) {
            int lans = matrixMultiplicationRec(si, cut, arr, dp);
            int rans = matrixMultiplicationRec(cut, ei, arr, dp);

            minAns = Math.min(minAns, lans + arr[si] * arr[cut] * arr[ei] + rans);
        }

        return dp[si][ei] = minAns;
    }

    static int matrixMultiplication(int arr[]) {
        // code here

        int n = arr.length;

        int[][] dp = new int[n][n];
        int ans= matrixMultiplicationRec(0, n - 1, arr, dp);
        display2D(dp);
        return ans;

    }

    public static int mcm_Dp(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        String[][] sdp = new String[n][n];

        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int minRes = (int) 1e9;
                String minStr = "";
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rightRes = dp[cut][ei];// mcm_memo(arr, cut, ei, dp);

                    if (leftRes + arr[si] * arr[cut] * arr[ei] + rightRes < minRes) {
                        minRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }

                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
            }

        }
        System.out.println(sdp[SI][EI]);
        display2D(dp);
        return dp[SI][EI];
    }

    //1547. Minimum Cost to Cut a Stick
    public static int minCost(int[] cuts, int si, int ei, int[][] dp) {
        if (ei == si + 1)
            return 0;

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int cost = (int) 1e9;

        for (int cut = si + 1; cut < ei; cut++) {
            int leftCost = minCost(cuts, si, cut, dp);
            int rightCost = minCost(cuts, cut, ei, dp);

            cost = Math.min(cost, leftCost + cuts[ei] - cuts[si] + rightCost);
        }

        return dp[si][ei] = cost;
    }

    public static int minCost(int n, int[] cuts) {
        int m = cuts.length;

        int[] cutsCopy = new int[m + 2];
        cutsCopy[0] = 0;
        cutsCopy[m + 1] = n;

        for (int i = 1; i <= m; i++)
            cutsCopy[i] = cuts[i - 1];


        Arrays.sort(cutsCopy);
        int[][] dp = new int[m + 2][m + 2];
        int ans= minCost(cutsCopy, 0, m + 1, dp);
        display2D(dp);
        return ans;
    }

    public int minCostDP(int n, int[] cuts) {
        int m = cuts.length;

        int[] cutsCopy = new int[m + 2];
        cutsCopy[0] = 0;
        cutsCopy[m + 1] = n;

        for (int i = 1; i <= m; i++)
            cutsCopy[i] = cuts[i - 1];

        Arrays.sort(cutsCopy);
        cuts=cutsCopy;
        int[][] dp = new int[m + 2][m + 2];

        for (int gap = 1; gap < m + 2; gap++) {
            for (int si = 0, ei = gap; ei < m + 2; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    continue;
                }

                int cost = (int) 1e9;

                for (int cut = si + 1; cut < ei; cut++) {
                    int leftCost = dp[si][cut];//minCost(cuts, si, cut, dp);
                    int rightCost = dp[cut][ei];//minCost(cuts, cut, ei, dp);

                    cost = Math.min(cost, leftCost + cuts[ei] - cuts[si] + rightCost);
                }

                dp[si][ei] = cost;
            }
        }

        return dp[0][m+1];
    }

    //leetcode 312
    public static int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (si > ei)
            return 0;

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int maxCost = 0;

        for (int cut = si; cut <= ei; cut++) {
            int leftCost = maxCoins(nums, si, cut - 1, dp);
            int rightCost = maxCoins(nums, cut + 1, ei, dp);

            int currentCost = 0;

            currentCost = nums[si - 1] * nums[cut] * nums[ei + 1];

            currentCost = currentCost + leftCost + rightCost;
            maxCost = Math.max(maxCost, currentCost);

        }

        return dp[si][ei] = maxCost;
    }

    public static int maxCoins(int[] nums) {
        int n = nums.length;

        int[] numsCopy = new int[n + 2];
        numsCopy[0] = 1;
        numsCopy[n + 1] = 1;

        for (int i = 1; i <= n; i++)
            numsCopy[i] = nums[i - 1];

        nums = numsCopy;
        int[][] dp = new int[n + 2][n + 2];
        int ans=maxCoins(nums, 1, n, dp);
         display2D(dp);
        return ans;
    }

    //
    public int maxCoinsDP(int[] nums) {
        int n = nums.length;

        // Add 1 to both ends of the array
        int[] numsCopy = new int[n + 2];
        numsCopy[0] = 1;
        numsCopy[n + 1] = 1;

        for (int i = 1; i <= n; i++) {
            numsCopy[i] = nums[i - 1];
        }

        nums = numsCopy;
        int[][] dp = new int[n + 2][n + 2];

        // Gap-based DP
        for (int gap = 1; gap <= n; gap++) { // Length of the range
            for (int si = 1, ei = gap; ei <= n; si++, ei++) { // si starts at 1, ei = si + gap - 1

                int maxCost = 0;
                for (int cut = si; cut <= ei; cut++) {
                    // Left and right costs
                    int leftCost = dp[si][cut - 1];
                    int rightCost = dp[cut + 1][ei];

                    // Current cost
                    int currentCost = nums[si - 1] * nums[cut] * nums[ei + 1];

                    // Total cost for this cut
                    int totalCost = currentCost + leftCost + rightCost;

                    // Update maximum cost
                    maxCost = Math.max(maxCost, totalCost);
                }

                dp[si][ei] = maxCost; // Store result for this range
            }
        }

        return dp[1][n]; // The result for the entire range
    }

    //https://www.naukri.com/code360/problems/boolean-evaluation_1214650?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
    public static class pairBoolen {
        long totalTrue = 0;
        long totalFalse = 0;

        pairBoolen(long totalTrue, long totalFalse) {
            this.totalFalse = totalFalse;
            this.totalTrue = totalTrue;
        }

        pairBoolen() {

        }
    }

    public static void evaluateTrue(pairBoolen left, pairBoolen right, pairBoolen res, char operator) {
        long mod =1000000007;
        long totalTF = ((left.totalTrue + left.totalFalse) * (right.totalTrue + right.totalFalse)) % mod;
        long totalTrue = 0, totalFalse = 0;
        if (operator == '|') {
            totalFalse = (left.totalFalse * right.totalFalse) % mod;
            totalTrue = (totalTF - totalFalse + mod) % mod;
        } else if (operator == '^') {
            totalTrue = (left.totalFalse * right.totalTrue) % mod + (left.totalTrue * right.totalFalse) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;

        } else if (operator == '&') {
            totalTrue = (left.totalTrue * right.totalTrue) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;
        }

        res.totalFalse = (res.totalFalse + totalFalse) % mod;
        res.totalTrue = (res.totalTrue + totalTrue) % mod;
    }

    public static pairBoolen countWays(String S, int si, int ei, pairBoolen[][] dp) {
        if (si == ei) {
            char ch = S.charAt(si);
            int t = ch == 'T' ? 1 : 0;
            int f = ch == 'F' ? 1 : 0;
            return dp[si][ei] = new pairBoolen(t, f);
        }
        if (dp[si][ei] != null) {
            return dp[si][ei];
        }

        pairBoolen res = new pairBoolen();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairBoolen lres = countWays(S, si, cut - 1, dp);
            pairBoolen rres = countWays(S, cut + 1, ei, dp);

            evaluateTrue(lres, rres, res, S.charAt(cut));
        }

        return dp[si][ei] = res;
    }
    public static int evaluateExp(String exp) {
        // Write your code here.
        int N=exp.length();
        pairBoolen[][] dp = new pairBoolen[N][N];
        return (int) countWays(exp, 0, N - 1, dp).totalTrue;
    }

    //leetcode 132
    public int minCut(String s, int si, int ei, int[][] dp, int[][] cutDp) {
        // Base case: no cut needed for an empty substring
        if (si >= ei) {
            return 0;
        }

        // If the substring s[si...ei] is already a palindrome, no cut is needed
        if (dp[si][ei] == 1) {
            return 0;
        }

        // If this subproblem is already solved, return the result from cutDp
        if (cutDp[si][ei] != -1) {
            return cutDp[si][ei];
        }

        // Initialize the minimum cuts to a large value
        int minCost = Integer.MAX_VALUE;

        // Try all possible cuts and recursively calculate the minimum cuts
        for (int cut = si; cut < ei; cut++) { // Now only valid cuts are considered
            int lans = minCut(s, si, cut, dp, cutDp); // Left substring up to cut
            int rans = minCut(s, cut + 1, ei, dp, cutDp); // Right substring after cut
            minCost = Math.min(minCost, lans + rans + 1);
        }
        // Memoize the result for this subproblem
        cutDp[si][ei] = minCost;
        return minCost;
    }

    public int minCut(String s) {
        int n = s.length();

        // dp[i][j] will be 1 if the substring s[i...j] is a palindrome
        int[][] dp = new int[n][n];

        // Fill dp table for palindrome substrings
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = 1; // Single character is always a palindrome
                } else if (gap == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 1 : 0; // Two characters
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) ? 1 : 0;
                }
            }
        }

        // cutDp[i][j] will store the minimum cuts for the substring s[i...j]
        int[][] cutDp = new int[n][n];

        // Initialize cutDp table with -1 indicating that the subproblem is not solved
        // yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cutDp[i][j] = -1;
            }
        }

        // Call the helper function to get the minimum cuts for the entire string
        return minCut(s, 0, n - 1, dp, cutDp);
    }

    //solution
    public int minCut(String s, int si, int[][] dp, int[] cutDp) {
        // Base case: no cut needed for an empty substring
        if (dp[si][s.length() - 1] == 1) {
            return 0;
        }

        // If this subproblem is already solved, return the result from cutDp
        if (cutDp[si] != -1) {
            return cutDp[si];
        }

        // Initialize the minimum cuts to a large value
        int minCost = Integer.MAX_VALUE;

        for (int cut = si; cut < s.length(); cut++) {
            if (dp[si][cut] == 1) {
                minCost = Math.min(minCost, minCut(s, cut + 1, dp, cutDp) + 1);
            }
        }
        // Memoize the result for this subproblem
        cutDp[si] = minCost;
        return minCost;
    }

    public int minCutCorrect(String s) {
        int n = s.length();

        // dp[i][j] will be 1 if the substring s[i...j] is a palindrome
        int[][] dp = new int[n][n];

        // Fill dp table for palindrome substrings
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = 1; // Single character is always a palindrome
                } else if (gap == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 1 : 0; // Two characters
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) ? 1 : 0;
                }
            }
        }

        int[] cutDp = new int[n];
        Arrays.fill(cutDp, -1);
        return minCut(s, 0, dp, cutDp);
    }

    //leetcode 1043
    public int parttition(int[] arr, int ind, int k, int[] dp) {
        if (ind == arr.length)
            return 0;

        if (dp[ind] != -1)
            return dp[ind];

        int maxAns = -(int) 1e9;

        int maxValue = -(int) 1e9;
        for (int i = ind; i < Math.min(ind + k, arr.length); i++) {
            maxValue = Math.max(maxValue, arr[i]);
            int ans = maxValue * (i - ind + 1);

            maxAns = Math.max(maxAns, ans + parttition(arr, i + 1, k, dp));
        }

        return dp[ind] = maxAns;
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return parttition(arr, 0, k, dp);
    }

    public int maxSumAfterPartitioningDp(int[] arr, int k) {

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

    //leetcode 813
    public double recursiveDp(int[] nums, int ind, int k, double[][] dp) {

        if (ind == nums.length)
            return 0;

        // we have patrition it into 3 and element in array is till left
        if (k == 0) {

            return -(int) 1e9;
        }

        if (dp[ind][k] != -1)
            return dp[ind][k];

        double sum = 0;
        double maxAverage = 0;

        for (int i = ind; i < nums.length; i++) {
            sum += nums[i];
            double avg = sum / (i - ind + 1);

            maxAverage = Math.max(maxAverage, recursiveDp(nums, i + 1, k - 1, dp) + avg);
        }

        return dp[ind][k] = maxAverage;
    }

    public double largestSumOfAverages(int[] nums, int k) {

        double[][] dp = new double[nums.length][k + 1];
        for (int i = 0; i < nums.length; i++)
            Arrays.fill(dp[i], -1);
        return recursiveDp(nums, 0, k, dp);
    }

    //leetcode 474
    public int find(String[] strs, int m, int n, int idx, int[][][] dp) {

        if (strs.length == idx)
            return 0;

        if (m == 0 && n == 0)
            return 0;

        if (dp[idx][m][n] != -1)
            return dp[idx][m][n];
        int maxAns = 0;

        int notTake = find(strs, m, n, idx + 1, dp);
        maxAns = Math.max(maxAns, notTake);

        String s = strs[idx];

        int countZero = 0;
        int countOne = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '0')
                countZero += 1;
            else
                countOne += 1;
        }

        if (m - countZero >= 0 && n - countOne >= 0)
            maxAns = Math.max(maxAns, find(strs, m - countZero, n - countOne, idx + 1, dp) + 1);

        return dp[idx][m][n] = maxAns;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int s = strs.length;
        int[][][] dp = new int[s + 1][m + 1][n + 1];
        for (int i = 0; i <= s; i++)
            for (int j = 0; j <= m; j++)
                Arrays.fill(dp[i][j], -1);

        int ans = find(strs, m, n, 0, dp);
        return ans;
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
    public static void main(String[] args) {
//        int[] arr = {1,3,4,5};
//        int n=7;
//
//        System.out.println(minCost(n,arr));

        int []arr={3,1,5,8};
        System.out.println(maxCoins(arr));


    }
}
