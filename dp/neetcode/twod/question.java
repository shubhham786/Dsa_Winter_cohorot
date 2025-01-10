package dp.neetcode.twod;

import java.util.Arrays;

public class question {

    //leetcode 62
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;

        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }

        return dp[m - 1][n - 1];

    }

    //leetcode 63
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If start or end point is blocked, return 0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];

        // Initialize the first row and column
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }

        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // Blocked cell
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    //leetcode 64
    public int minPathSum(int[][] grid) {

        int m=grid.length;
        int n=grid[0].length;


        int [][] dp=new int[m][n];

        dp[m-1][n-1]=grid[m-1][n-1];

        for(int i=n-2;i>=0;i--)
        {
            dp[m-1][i]=dp[m-1][i+1]+grid[m-1][i];
        }

        for(int i=m-2;i>=0;i--)
        {
            dp[i][n-1]=dp[i+1][n-1]+grid[i][n-1];
        }


        for(int i=m-2;i>=0;i--)
        {
            for(int j=n-2;j>=0;j--)
            {
                dp[i][j]=Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j];
            }
        }

        return dp[0][0];

    }

    //leetcode 1143
    public int lcs(String text1, String text2, int i, int j, int[][] dp) {
        if (i == 0 || j == 0)
            return dp[i][j] = 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (text1.charAt(i - 1) == text2.charAt(j - 1))
            return dp[i][j] = 1 + lcs(text1, text2, i - 1, j - 1, dp);
        else
            return dp[i][j] = Math.max(lcs(text1, text2, i - 1, j, dp), lcs(text1, text2, i, j - 1, dp));

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return lcs(text1, text2, n, m, dp);

    }


    //leetcode 516
    public int plaindrome(String s, int i, int j, int[][] dp) {

        if (i >= j) {
            if (i == j)
                return dp[i][j] = 1;
            else
                return 0;
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = 2 + plaindrome(s, i + 1, j - 1, dp);
        } else {
            return dp[i][j] = Math.max(plaindrome(s, i + 1, j, dp), plaindrome(s, i, j - 1, dp));
        }
    }

    public int longestPalindromeSubseq(String s) {

        int i = 0;
        int j = s.length() - 1;
        int[][] dp = new int[j + 1][j + 1];

        for (int k = 0; k <= j; k++)
            Arrays.fill(dp[k], -1);

        return plaindrome(s, i, j, dp);
    }

    //1049
    public int tarSum(int[] stones, int idx, int Sum, int[][] dp) {

        if(idx==stones.length)
            return 0;


        if(Sum==0)
            return 0;

        if(dp[idx][Sum]!=-1)
        {
            return dp[idx][Sum];
        }

        int take=0;

        if(Sum-stones[idx]>=0)
            take=stones[idx]+tarSum(stones,idx+1,Sum-stones[idx],dp);


        int notTake=tarSum(stones,idx+1,Sum,dp);



        return dp[idx][Sum]=Math.max(take,notTake);



    }

    public int lastStoneWeightII(int[] stones) {

        int wt = 0;

        for (int i : stones)
            wt += i;

        System.out.println(wt);
        int sum = wt / 2;
        int n = stones.length;
        int[][] dp = new int[n][sum + 1];
        for(int i=0;i<n;i++)
        {
            Arrays.fill(dp[i],-1);
        }

        int sum1=tarSum(stones,0,sum,dp);

        System.out.println(sum1);
        return wt-2*sum1;

    }
}
