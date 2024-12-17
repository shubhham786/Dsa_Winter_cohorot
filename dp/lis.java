package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lis {

    //leetcode 300
    public int lisLR(int[] nums, int i, int[] dp) {
        if (dp[i] != 0)
            return dp[i];

        int maxLen = 1;

        for (int j = i - 1; j >= 0; j--) {

            if (nums[j] < nums[i]) {
                int len = lisLR(nums, j, dp);
                maxLen = Math.max(maxLen, 1 + len);
            }
        }

        return dp[i] = maxLen;
    }

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        int len = 0;

        for (int i = 0; i < n; i++) {

            len = Math.max(len, lisLR(nums, i, dp));
        }

        return len;
    }

    //Lis Left->right
    public int lengthOfLIS_LR(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        int len = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }

            }

            len = Math.max(len, dp[i]);
        }

        return len;
    }

    //lis Right to left

    public int lengthOfLIS_RL(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        int len = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }

            }

            len = Math.max(len, dp[i]);
        }

        return len;
    }

//https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=printing-longest-increasing-subsequence
public ArrayList<Integer> longestIncreasingSubsequence(int n, int arr[]) {
    // dp[i] stores the length of LIS ending at index i
    int[] dp = new int[n];
    // prev[i] stores the previous index in the LIS ending at index i
    int[] prev = new int[n];

    // Initialize dp array with 1 and prev array with -1
    Arrays.fill(dp, 1);
    Arrays.fill(prev, -1);

    // Track the maximum length and ending index of LIS
    int maxLen = 1;
    int maxIndex = 0;

    // Calculate dp values and track previous indices
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                prev[i] = j;

                // Update maxLen and maxIndex if we found a longer subsequence
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                    maxIndex = i;
                }
            }
        }
    }

    // Reconstruct the subsequence using prev array
    ArrayList<Integer> result = new ArrayList<>();
    while (maxIndex != -1) {
        result.add(0, arr[maxIndex]); // Add at beginning to maintain order
        maxIndex = prev[maxIndex];
    }

    return result;
}

    public static ArrayList<ArrayList<Integer>> findAllLIS(int n, int arr[]) {
        // Step 1: Find length of LIS using dp array
        int[] dp = new int[n];
        int maxLen = 1;

        // Fill dp array
        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }

        // Step 2: Find all sequences with maxLen
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        findSequences(arr, -1, 0, maxLen, new ArrayList<>(), result);
        return result;
    }

    private static void findSequences(int[] arr, int prevIndex, int currIndex,
                               int remainingLen, ArrayList<Integer> curr,
                               ArrayList<ArrayList<Integer>> result) {
        // If we found a valid sequence
        if(remainingLen == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        // If we reached end of array
        if(currIndex == arr.length) {
            return;
        }

        // For each number, we have two choices:
        // 1. Include current number if it's greater than previous
        if(prevIndex == -1 || arr[currIndex] > arr[prevIndex]) {
            curr.add(arr[currIndex]);
            findSequences(arr, currIndex, currIndex + 1, remainingLen - 1, curr, result);
            curr.remove(curr.size() - 1);
        }

        // 2. Skip current number
        findSequences(arr, prevIndex, currIndex + 1, remainingLen, curr, result);
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

    //leetcode 1048
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

    //https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int LongestBitonicSequence(int n, int[] nums) {
        // code here


        int[] dp = new int[n];

        int len = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }

            }


        }

        int[] dp1 = new int[n];

        len = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp1[i] = 1;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);

                }

            }


        }

        len=0;

        for(int i=0;i<n;i++)
        {
            if(dp[i]==1 || dp1[i]==1)
                continue;

            len=Math.max(len,dp[i]+dp1[i]-1);
        }

        return len;

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
    public static void main(String[] args) {
       int [] arr = {5, 8, 3, 7, 9, 1};
       int n=arr.length;

        ArrayList<ArrayList<Integer>>ans=findAllLIS(n,arr);
        System.out.println(ans);
    }

}
