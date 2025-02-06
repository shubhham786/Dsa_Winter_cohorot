package Graph;

import java.util.Arrays;

public class bellmanFord {
    //leetcode 787
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] prev = new int[n];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] curr = Arrays.copyOf(prev, n); // Create a fresh copy of prev

            for (int[] flight : flights) {
                if (prev[flight[0]] != (int) 1e9) {
                    curr[flight[1]] = Math.min(prev[flight[0]] + flight[2], curr[flight[1]]);
                }
            }

            prev = curr; // Update prev with the latest distances
        }

        return prev[dst] != (int) 1e9 ? prev[dst] : -1;
    }
}
