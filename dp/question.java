package dp;

import java.util.*;

public class question {

    //leetcode 70
    public int bottomUp(int n, int[] dp) {


        for (int i = 0; i <= n; i++) {
            if (i == 0)
                dp[0] = 1;


            if (i - 1 >= 0)
                dp[i] += dp[i - 1];

            if (i - 2 >= 0)
                dp[i] += dp[i - 2];

        }

        return dp[n];
    }

    /*
     public int climb(int n, int[] dp) {
         if (n == 0)
             return dp[0] = 1;

         if (dp[n] != 0)
             return dp[n];

         int count = 0;

         if (n - 1 >= 0)
             count += climb(n - 1,dp);

         if (n - 2 >= 0)
             count += climb(n - 2,dp);

         return dp[n] = count;
     }
*/
    //https://www.geeksforgeeks.org/problems/geek-jump/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geek-jump
    public int climbStairs(int n) {

        int[] dp = new int[n + 1];

        return bottomUp(n, dp);
    }


    public int minimumEnergy(int arr[], int n, int idx, int[] dp) {

        if (idx == n - 1)
            return dp[idx] = 0;
        ;

        if (dp[idx] != 0)
            return dp[idx];


        int ans = Integer.MAX_VALUE;

        if (idx + 1 < n)
            ans = Math.min(ans, Math.abs(arr[idx] - arr[idx + 1]) + minimumEnergy(arr, n, idx + 1, dp));

        if (idx + 2 < n)
            ans = Math.min(ans, Math.abs(arr[idx] - arr[idx + 2]) + minimumEnergy(arr, n, idx + 2, dp));

        return dp[idx] = ans;

    }

    public int minimumEnergy_dp(int arr[], int n, int idx, int[] dp) {
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp[i] = 0;
                continue;
            }

            int ans = Integer.MAX_VALUE;

            if (i + 1 < n)
                ans = Math.min(ans, Math.abs(arr[i] - arr[i + 1]) + dp[i + 1]);//minimumEnergy(arr,n,idx+1,dp));

            if (i + 2 < n)
                ans = Math.min(ans, Math.abs(arr[i] - arr[i + 2]) + dp[i + 2]);//minimumEnergy(arr,n,idx+2,dp));

            dp[i] = ans;

        }

        return dp[0];

    }

    public int minimumEnergy(int arr[], int N) {
        //code here

        int[] dp = new int[N];
        return minimumEnergy(arr, N, 0, dp);
    }



    public int minimizeCost_dp(int k, int arr[], int idx, int n, int[] dp) {

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp[i] = 0;
                continue;
            }


            int ans = Integer.MAX_VALUE;

            for (int jump = 1; jump <= k; jump++) {
                if (i + jump < n)
                    ans = Math.min(ans, Math.abs(arr[i] - arr[i + jump]) + dp[i + jump]);
            }


            dp[i] = ans;
        }

        return dp[0];
    }

    public int minimizeCost(int k, int arr[], int idx, int n, int[] dp) {
        if (idx == n - 1)
            return dp[idx] = 0;


        if (dp[idx] != -1)
            return dp[idx];

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            if (idx + i < n)
                ans = Math.min(ans, Math.abs(arr[idx] - arr[idx + i]) + minimizeCost(k, arr, idx + i, n, dp));
        }


        return dp[idx] = ans;
    }

    public int minimizeCost(int k, int arr[]) {
        // code here
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return minimizeCost_dp(k, arr, 0, n, dp);

    }


    public int rob(int[] nums, int idx, int n, int[] dp) {

        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp[i] = nums[i];
                continue;
            }

            int ans = Integer.MIN_VALUE;
            ;
            // including cuurent
            if (i + 2 <= n)
                ans = Math.max(ans, nums[i] + dp[i + 2]);// rob(nums,idx+2,n);

            // excluding
            if (i + 1 <= n)
                ans = Math.max(ans, dp[i + 1]);// rob(nums,idx+1,n);

            dp[i] = ans;
        }

        return dp[0];

    }

    /*
     public int rob(int[] nums) {

        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < nums.length; i++) {
            int take = nums[i];
            if (i >= 2)
                take += prev2;

            int noTake = 0 + prev;

            int curr = Math.max(take, noTake);
            prev2 = prev;
            prev = curr;
        }

        return prev;

    }
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return rob(nums, 0, n, dp);
    }

    //https://www.geeksforgeeks.org/problems/geeks-training/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geeks-training
    public int maximumPoints(int arr[][], int currRow, int n, int prevCol, int[][] dp) {

        if (currRow == n)
            return 0;


        if (prevCol != -1 && dp[currRow][prevCol] != -1)
            return dp[currRow][prevCol];

        int maxPoints = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            if (prevCol == -1 || i != prevCol)
                maxPoints = Math.max(maxPoints, arr[currRow][i] + maximumPoints(arr, currRow + 1, n, i, dp));
        }

        if (prevCol != -1)
            dp[currRow][prevCol] = maxPoints;


        return maxPoints;


    }

    //tabulation
    /*
      can we further optimized as we just require 1d space of previous one
     */
    public int maximumPoints_dp(int arr[][], int n, int[][] dp) {

        for (int j = 0; j < 3; j++) {
            dp[n - 1][j] = arr[n - 1][j];
        }

        for (int j = n - 2; j >= 0; j--) {

            for (int col = 0; col < 3; col++) {
                int maxPoints = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    if (i != col)
                        maxPoints = Math.max(maxPoints, arr[j][col] + dp[j + 1][i]);// maximumPoints(arr,currRow+1,n,i,dp));
                }

                dp[j][col] = maxPoints;

            }

        }

        return Math.max(dp[0][0], Math.max(dp[0][1], dp[0][2]));
    }

    public int maximumPoints_dp(int arr[][], int n, int[] dp) {

        for (int j = 0; j < 3; j++) {
            dp[j] = arr[n - 1][j];
        }

        for (int j = n - 2; j >= 0; j--) {
            int[] temp = new int[3];
            for (int col = 0; col < 3; col++) {
                int maxPoints = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    if (i != col)
                        maxPoints = Math.max(maxPoints, arr[j][col] + dp[i]);// maximumPoints(arr,currRow+1,n,i,dp));
                }

                temp[col] = maxPoints;

            }
            dp = temp;
        }

        return Math.max(dp[0], Math.max(dp[1], dp[2]));
    }

    public int maximumPoints(int arr[][], int N) {
        // code here
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);


        return maximumPoints(arr, 0, N, -1, dp);

    }

    //https://leetcode.com/problems/unique-paths/description/
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

    //leetcode 64
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;


        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = dp[m - 1][i + 1] + grid[m - 1][i];
        }

        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dp[i + 1][n - 1] + grid[i][n - 1];
        }


        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }

        return dp[0][0];

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

    //leetcode 931
    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;


        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int point = matrix[i + 1][j];

                if (j + 1 < n)
                    point = Math.min(point, matrix[i + 1][j + 1]);

                if (j - 1 >= 0)
                    point = Math.min(point, matrix[i + 1][j - 1]);

                matrix[i][j] = point + matrix[i][j];
            }
        }


        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, matrix[0][i]);
        }

        return ans;
    }

    //leetcode 1463
    public int solve(int n, int m, int grid[][], int r1, int c1, int c2, int[][][] dp) {

        if (r1 < 0 || c1 < 0 || c2 < 0 || r1 >= n || c1 >= m || c2 >= m) {
            return -(int) 1e9;
        }

        if (r1 == n - 1) {
            if (c1 == c2)
                return grid[r1][c1];
            else
                return grid[r1][c1] + grid[r1][c2];
        }

        if (dp[r1][c1][c2] != -1)
            return dp[r1][c1][c2];

        int maxValue = Integer.MIN_VALUE;
        for (int dc1 = -1; dc1 <= 1; dc1++) {
            for (int dc2 = -1; dc2 <= 1; dc2++) {
                int value = 0;
                if (c1 == c2)
                    value = grid[r1][c1];
                else
                    value = grid[r1][c1] + grid[r1][c2];

                maxValue = Math.max(maxValue, value + solve(n, m, grid, r1 + 1, c1 + dc1, c2 + dc2, dp));

            }
        }

        return dp[r1][c1][c2] = maxValue;
    }

    public int solve(int n, int m, int grid[][]) {
        // Code here
        // 3d dp
        int[][][] dp = new int[n][m][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(n, m, grid, 0, 0, m - 1, dp);
    }

    public int cherryPickup(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        return solve(n, m, grid);
    }

    //leetcode  741
    /*
     4d dp
    public int solve(int n, int[][] grid, int r1, int c1, int r2, int c2, int[][][][] dp) {
        if (r1 >= n || r2 >= n || c1 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return -(int) 1e9;

        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }

        if (dp[r1][c1][r2][c2] != -1)
            return dp[r1][c1][r2][c2];

        int ans = 0;
        if (r1 == r2 && c1 == c2)
            ans += grid[r1][c1];
        else
            ans += grid[r1][c1] + grid[r2][c2];

        int maxAns = Integer.MIN_VALUE;

        maxAns = Math.max(maxAns, solve(n, grid, r1 + 1, c1, r2 + 1, c2, dp));
        maxAns = Math.max(maxAns, solve(n, grid, r1 + 1, c1, r2, c2 + 1, dp));
        maxAns = Math.max(maxAns, solve(n, grid, r1, c1 + 1, r2 + 1, c2, dp));
        maxAns = Math.max(maxAns, solve(n, grid, r1, c1 + 1, r2, c2 + 1, dp));

        return dp[r1][c1][r2][c2] = maxAns + ans;
    }

    public int cherryPickup(int[][] grid) {

        int n = grid.length;
        // int m=grid[0].length;
        int[][][][] dp = new int[n][n][n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++)
                    Arrays.fill(dp[i][j][k], -1);
            }
        }
        int ans = solve(n, grid, 0, 0, 0, 0, dp);

        return ans > 0 ? ans : 0;

    }

     */
    /*
    /**
 * Example: Grid State Optimization
 *
 * Consider a 3x3 grid:
 *
 * - 4D DP Representation:
 *   A state like dp[1][1][1][2] represents:
 *   - Person 1 at position (1, 1)
 *   - Person 2 at position (1, 2)
 *
 * - 3D DP Representation:
 *   The same state is represented as dp[1][1][2], where:
 *     r2 = r1 + c1 - c2
 *        = 1 + 1 - 2
 *        = 0
 *   - Person 2 is therefore at position (0, 2).
 *
 * - Optimization Impact:
 *   By deriving r2 from the other variables (r1, c1, c2), the DP state reduces
 *   from 4D to 3D. This saves memory and simplifies computation while retaining
 *   the correctness of the solution.
 */

 //leetcode 1643
 int comb(int n, int r) {
     long ans = 1; // Use long to handle larger intermediate values

     for (int i = 1; i <= r; i++) {
         ans = ans * (n - r + i) / i; // Multiply before dividing to avoid truncation
     }

     return (int) ans; // Convert back to int if necessary
 }

    public String kthSmallestPath(int[] destination, int k) {

        StringBuilder sb = new StringBuilder();
        int y = destination[0];
        int x = destination[1];

        int n = x + y;

        for (int i = 1; i <= n; i++) {

            if (x > 0) {

                int c = comb(x + y - 1, y);
                System.out.println(c);
                if (c >= k) {
                    sb.append('H');
                    x--;
                } else {
                    sb.append('V');
                    y--;
                    k -= c;
                }
            } else {
                sb.append('V');
                y--;
            }
        }

        return sb.toString();

    }

    //leetcode 174
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        // Calculate the health needed to survive the bottom-right cell
        dungeon[n - 1][m - 1] = Math.max(1, 1 - dungeon[n - 1][m - 1]);

        // Fill the last row: health needed to move right from each cell
        for (int i = m - 2; i >= 0; i--) {
            dungeon[n - 1][i] = Math.max(1, dungeon[n - 1][i + 1] - dungeon[n - 1][i]);
        }

        // Fill the last column: health needed to move down from each cell
        for (int i = n - 2; i >= 0; i--) {
            dungeon[i][m - 1] = Math.max(1, dungeon[i + 1][m - 1] - dungeon[i][m - 1]);
        }

        // Fill the rest of the grid from bottom-right to top-left
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                // Choose the minimum health needed between moving right and moving down
                int minHealth = Math.min(dungeon[i + 1][j], dungeon[i][j + 1]);
                // Ensure the health at the current cell is sufficient to survive
                dungeon[i][j] = Math.max(1, minHealth - dungeon[i][j]);
            }
        }
        // The top-left cell holds the minimum health needed to start
        return dungeon[0][0];
    }

    //leetcode 1301

    class Pair {
        int val; // Maximum score
        int num; // Number of paths

        Pair(int val, int num) {
            this.val = val;
            this.num = num;
        }
    }

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = board.get(i).toCharArray();
        }

        Pair[][] dp = new Pair[n][n];
        dp[n - 1][n - 1] = new Pair(0, 1); // Start from the bottom-right corner

        int[][] directions = { { -1, -1 }, { -1, 0 }, { 0, -1 } }; // Diagonal, up, left

        // Iterate from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'X')
                    continue; // Skip obstacles

                int maxVal = -1;
                int totalPaths = 0;

                for (int[] dir : directions) {
                    int ni = i - dir[0];
                    int nj = j - dir[1];

                    if (ni < n && nj < n && ni >= 0 && nj >= 0 && dp[ni][nj] != null) {
                        if (dp[ni][nj].val > maxVal) {
                            maxVal = dp[ni][nj].val;
                            totalPaths = dp[ni][nj].num;
                        } else if (dp[ni][nj].val == maxVal) {
                            totalPaths = (totalPaths + dp[ni][nj].num) % 1_000_000_007;
                        }
                    }
                }

                if (maxVal == -1)
                    continue; // No valid paths to this cell

                int cellValue = (grid[i][j] == 'S' || grid[i][j] == 'E') ? 0 : grid[i][j] - '0';
                dp[i][j] = new Pair(maxVal + cellValue, totalPaths);
            }
        }

        Pair result = dp[0][0];
        return result == null ? new int[] { 0, 0 } : new int[] { result.val, result.num };
    }

    //leetcode 1594
    public int maxProductPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int mod = (int) 1e9 + 7;

        // Use long to avoid overflow
        long[][] dpMax = new long[n][m];
        long[][] dpMin = new long[n][m];

        // Initialize the bottom-right corner
        dpMax[n - 1][m - 1] = grid[n - 1][m - 1];
        dpMin[n - 1][m - 1] = grid[n - 1][m - 1];

        // Fill the last row
        for (int j = m - 2; j >= 0; j--) {
            dpMax[n - 1][j] = dpMax[n - 1][j + 1] * grid[n - 1][j];
            dpMin[n - 1][j] = dpMax[n - 1][j]; // Only one path in the last row
        }

        // Fill the last column
        for (int i = n - 2; i >= 0; i--) {
            dpMax[i][m - 1] = dpMax[i + 1][m - 1] * grid[i][m - 1];
            dpMin[i][m - 1] = dpMax[i][m - 1]; // Only one path in the last column
        }

        // Fill the rest of the grid
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                long maxFromDown = dpMax[i + 1][j] * grid[i][j];
                long minFromDown = dpMin[i + 1][j] * grid[i][j];
                long maxFromRight = dpMax[i][j + 1] * grid[i][j];
                long minFromRight = dpMin[i][j + 1] * grid[i][j];

                dpMax[i][j] = Math.max(Math.max(maxFromDown, maxFromRight), Math.max(minFromDown, minFromRight));
                dpMin[i][j] = Math.min(Math.min(maxFromDown, maxFromRight), Math.min(minFromDown, minFromRight));
            }
        }

        // Return the result, modulo applied only for non-negative results
        return dpMax[0][0] >= 0 ? (int) (dpMax[0][0] % mod) : -1;
    }

    //https://www.naukri.com/code360/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0&leftPanelTabValue=PROBLEM
    public static int minimumDifference1(int[] nums) {
        int totSum = 0;

        // Calculate total sum of the array
        for (int num : nums) {
            totSum += num;
        }

        int n = nums.length;
        int tar = totSum / 2; // Target sum for one subset (to minimize difference)

        // DP table: dp[i][j] -> Can we form sum j using the first i elements
        boolean[][] dp = new boolean[n + 1][tar + 1];

        // Base case: sum 0 is always possible
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= tar; j++) {
                boolean res = false; // Initialize result for this cell

                // Exclude nums[i - 1]
                res = res || dp[i - 1][j];

                // Include nums[i - 1] if it's possible to include
                if (j >= nums[i - 1]) {
                    res = res || dp[i - 1][j - nums[i - 1]];
                }

                dp[i][j] = res; // Update DP table
            }
        }

        // Find the closest sum to tar
        int closestSum = 0;
        for (int j = tar; j >= 0; j--) {
            if (dp[n][j]) {
                closestSum = j;
                break;
            }
        }

        // Calculate the minimum difference
        return Math.abs(totSum - 2 * closestSum);
    }
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        return minimumDifference1(arr);
    }

    //leetcode 2035
    //if negative element but recursive solution only
    int ans = Integer.MAX_VALUE;

    public int minimumDifference(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        call(nums, 0, 0, sum, 0);
        return ans;
    }

    public void call(int[] nums, int sum, int i, int totalSum, int count) {
        int n = nums.length / 2;

        if (count == n) {
            int x = Math.abs(totalSum - (2 * sum));
            ans = Math.min(ans, x);
            return;
        }
        if (i >= nums.length) {
            return;
        }

        call(nums, sum + nums[i], i + 1, totalSum, count + 1);
        call(nums, sum, i + 1, totalSum, count);

    }

    //https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=perfect-sum-problem
    public int targetSum(int[] nums, int tar, int idx, int[][] dp) {
        // Check bounds first
        if (idx == nums.length) {
            return (tar == 0) ? 1 : 0;  // Valid combination found only if target is also 0
        }


        if (dp[idx][tar] != -1) {
            return dp[idx][tar];
        }

        int res = 0;
        // Include current number
        if (tar - nums[idx] >= 0) {
            res += targetSum(nums, tar - nums[idx], idx + 1, dp);
        }
        // Exclude current number
        res += targetSum(nums, tar, idx + 1, dp);

        return dp[idx][tar] = res;
    }

    public int perfectSum(int[] nums, int tar) {
        int n = nums.length;
        int[][] dp = new int[n + 1][tar + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return targetSum(nums, tar, 0, dp);
    }

    //https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=partitions-with-given-difference
    int countWaysTar(int[] arr,int idx,int tar,int[][] dp)
    {

        if(idx==arr.length)
            return tar==0?1:0;

        if(dp[idx][tar]!=-1)
            return dp[idx][tar];

        int count=0;

        count+=countWaysTar(arr,idx+1,tar,dp);

        if(tar>=arr[idx])
            count+=countWaysTar(arr,idx+1,tar-arr[idx],dp);


        return dp[idx][tar]=count;
    }
    int countPartitions(int[] arr, int d) {
        // code here

        int sum=0;
        int n=arr.length;
        for(int i=0;i<n;i++)
        {
            sum+=arr[i];
        }


        if((sum+d)%2!=0)
            return 0;

        int tar=(sum+d)/2;

        int[][] dp=new int[n+1][tar+1];

        for(int i=0;i<=n;i++)
        {
            Arrays.fill(dp[i],-1);
        }
        return countWaysTar(arr,0,tar,dp);

    }

    //494. Target Sum
    public int find(int[] nums, int target, int idx, Map<String, Integer> memo) {
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }

        String key = idx + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;

        // -ve
        count += find(nums, target + nums[idx], idx + 1, memo);

        // +ve
        count += find(nums, target - nums[idx], idx + 1, memo);

        memo.put(key, count);
        return count;
    }

    public int findTargetSumWays(int[] nums, int target) {
        return find(nums, target, 0, new HashMap<>());
    }

    //leetcode 322. Coin Change
    // using include exulde

    public int coinChange(int[] coins, int amount, int idx, int[][] dp) {

        if (amount == 0)
            return 0;

        if (idx == coins.length) {
            return amount == 0 ? 0 : (int) 1e9;
        }

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int count = (int) 1e9;

        // exclude
        count = Math.min(count, coinChange(coins, amount, idx + 1, dp));

        // include
        if (amount >= coins[idx])
            count = Math.min(count, coinChange(coins, amount - coins[idx], idx, dp) + 1);

        return dp[idx][amount] = count;

    }

    public int coinChange(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        int res = coinChange(coins, amount, 0, dp);
        return res == (int) 1e9 ? -1 : res;
    }

    //518. Coin Change II
    public int coinChange1(int[] coins, int amount, int idx, int[][] dp) {

        if (amount == 0)
            return 1;

        if (idx == coins.length) {
            return amount == 0 ? 1 : 0;
        }

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int count = 0;

        // exclude
        count += coinChange1(coins, amount, idx + 1, dp);

        // include
        if (amount >= coins[idx])
            count += coinChange1(coins, amount - coins[idx], idx, dp);

        return dp[idx][amount] = count;

    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        int res = coinChange1(coins, amount, 0, dp);
        return res;
    }

    //377. Combination Sum IV
    public int coinChange3(int[] coins, int amount, int idx, int[][] dp) {

        if (amount == 0)
            return 1;

        if (idx == coins.length) {
            return amount == 0 ? 1 : 0;
        }

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int count = 0;

        // exclude
        count += coinChange3(coins, amount, idx + 1, dp);

        // include
        if (amount >= coins[idx])
            count += coinChange3(coins, amount - coins[idx], 0, dp);

        return dp[idx][amount] = count;

    }

    public int combinationSum4(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        int res = coinChange3(coins, amount, 0, dp);
        return res;
    }

    /*
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
     */

    //https://www.geeksforgeeks.org/problems/rod-cutting0840/1
    public int cutRod(int[] price) {
        // code here

        int n=price.length;
        int cap=n;

        int [][] dp=new int[n+1][cap+1];


        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=cap;j++)
            {
                if(i==0 || j==0)
                {
                    dp[i][j]=0;
                    continue;
                }


                dp[i][j]=dp[i-1][j];

                if(j>=i)
                {
                    dp[i][j]=Math.max(dp[i][j],dp[i][j-i]+price[i-1]);
                }


            }
        }


        return dp[n][n];

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

    //https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=print-all-lcs-sequences
    //gave tle but good

    public List<String> all_longest_common_subsequences(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // Step 1: Fill the DP table
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 2: Backtrack to find all LCS
        Set<String> resultSet = new HashSet<>();
        backtrack(text1, text2, n, m, dp, new StringBuilder(), resultSet);

        List<String>ans= new ArrayList<>(resultSet);

        Collections.sort(ans);

        return ans;
    }

    private void backtrack(String text1, String text2, int i, int j, int[][] dp, StringBuilder current, Set<String> resultSet) {
        if (i == 0 || j == 0) {
            resultSet.add(current.reverse().toString());
            current.reverse(); // Reverse back to maintain original order
            return;
        }

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            current.append(text1.charAt(i - 1));
            backtrack(text1, text2, i - 1, j - 1, dp, current, resultSet);
            current.deleteCharAt(current.length() - 1);
        } else {
            if (dp[i - 1][j] == dp[i][j]) {
                backtrack(text1, text2, i - 1, j, dp, current, resultSet);
            }
            if (dp[i][j - 1] == dp[i][j]) {
                backtrack(text1, text2, i, j - 1, dp, current, resultSet);
            }
        }
    }

    //https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
    int maxSofar=0;

    public int lcs12(String s1, String s2, int i, int j, int [][]dp)
    {
        if(i==0 || j==0)
        {
            return dp[i][j]=0;
        }


        if(dp[i][j]!=-1)
            return dp[i][j];



        int s2_include=lcs12(s1,s2,i-1,j,dp);
        int s1_include=lcs12(s1,s2,i,j-1,dp);
        int s1_s2_include=lcs12(s1,s2,i-1,j-1,dp);

        if(s1.charAt(i-1)==s2.charAt(j-1))
        {
            maxSofar=Math.max(maxSofar,1+s1_s2_include);
            return dp[i][j]=1+s1_s2_include;
        }
        else
        {
            maxSofar=Math.max(maxSofar,Math.max(s2_include,s1_include));
            return dp[i][j]=0;
        }
    }
    public int longestCommonSubstr(String s1, String s2) {
        // code here
        int n=s1.length();
        int m=s2.length();

        int [][] dp=new int[n+1][m+1];

        for(int i=0;i<=n;i++)
            Arrays.fill(dp[i],-1);


        int ans=lcs12(s1,s2,n,m,dp);

        return maxSofar;
    }


    /*
        public int longestCommonSubstr(String s1, String s2) {
        // code here
         int n=s1.length();
         int m=s2.length();

          int maxSofar=0;
         int [][] dp=new int[n+1][m+1];

         for(int i=0;i<=n;i++)
         {
             for(int j=0;j<=m;j++)
             {
                 if(i==0 ||j==0)
                 {
                     dp[i][j]=0;
                     continue;
                 }

                 if(s1.charAt(i-1)==s2.charAt(j-1))
                 {
                     dp[i][j]=1+dp[i-1][j-1];

                 }
                 else
                   dp[i][j]=0;

                   maxSofar=Math.max(maxSofar,dp[i][j]);
             }
         }

         return maxSofar;
    }
     */

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
    //leetcode 583
    // can we solved using LCS
// ((n+m) -(2*lcs))
    public int minDeletion(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 0;
            else
                return dp[n][m] = (n >= m ? n : m);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int word1Included = 1 + minDeletion(word1, word2, n - 1, m, dp);
        int word2Included = 1 + minDeletion(word1, word2, n, m - 1, dp);
        int bothInclude = minDeletion(word1, word2, n - 1, m - 1, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1)) {
            return dp[n][m] = bothInclude;
        } else
            return dp[n][m] = Math.min(word1Included, word2Included);
    }

    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        return minDeletion(word1, word2, n, m, dp);
    }

    //leetcode 1092
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();

        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];
        // int dp[n+1][m+1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
            }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        StringBuilder s = new StringBuilder();

        int i = n;
        int j = m;
        while (i > 0 && j > 0) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                s.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {

                if (dp[i][j - 1] > dp[i - 1][j]) {
                    s.append(str2.charAt(j - 1));
                    j--;
                } else {
                    s.append(str1.charAt(i - 1));
                    i--;
                }
            }
        }

        while (i > 0) {
            s.append(str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            s.append(str2.charAt(j - 1));
            j--;
        }

        s.reverse();
        return s.toString();

    }

    //leetcode 115
    public int distinct(String s, String t, int n, int m, int[][] dp) {

        if (m == 0)
            return 1;

        if (n < m)
            return 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        int z = distinct(s, t, n - 1, m - 1, dp);
        int a = distinct(s, t, n - 1, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = z + a;
        else
            return dp[n][m] = a;
    }

    public int numDistinct(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        return distinct(word1, word2, n, m, dp);
    }

    //leetcode 72
    /*
    public int editDistance(String s, String t, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m : n);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = editDistance(s, t, n, m - 1, dp);
        int delete = editDistance(s, t, n - 1, m, dp);
        int replace = editDistance(s, t, n - 1, m - 1, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            return dp[n][m] = replace;
        } else {
            return dp[n][m] = 1 + Math.min(insert, Math.min(delete, replace));
        }

    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return editDistance(word1, word2, n, m, dp);
    }
    */
    //leetcode 44
    // 1->true,-1->not explored, 0->false;
    public int match(String s, String p, int n, int m, int[][] dp) {

        // base case
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return 1;

            if (m == 0 && n != 0)
                return 0;

            if (n == 0 && m > 0) {

                for (int i = 0; i < m; i++) {
                    if (p.charAt(i) != '*')
                        return 0;
                }

                return 1;
            }

        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch = s.charAt(n - 1);
        char ch1 = p.charAt(m - 1);

        if (ch == ch1 || ch1 == '?')
            return dp[n][m] = match(s, p, n - 1, m - 1, dp);

        if (ch1 == '*') {
            boolean res = false;
            res = res || (match(s, p, n, m - 1, dp) == 1);// considering * as empty
            res = res || (match(s, p, n - 1, m, dp) == 1);// considering * as one char

            return dp[n][m] = (res ? 1 : 0);
        }

        return dp[n][m] = 0;
    }

    public boolean isMatch(String s, String p) {
        int n = s.length();

        int m = p.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return match(s, p, n, m, dp) == 1;

    }
}

