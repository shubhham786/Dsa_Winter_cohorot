package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSorting {
    public class Edge
    {
        int v,w;

        Edge(int v, int w)
        {
            this.v = v;
            this.w = w;
        }
    }

    //1.Khan's algo
     void khansAlgo(int n, List<List<Edge>> graph) {

          int[] indegree = new int[n];
          for(List<Edge> edge : graph)
          {
               for(Edge e : edge)
               {
                   indegree[e.v]++;
               }
          }

          LinkedList<Integer> que = new LinkedList<>();
          List<Integer> ans=new ArrayList<>();

             for(int i=0;i<n;i++)
             {
                   if(indegree[i]==0)
                       que.addLast(i);
             }

                while(que.size()!=0)
                {
                     int size=que.size();
                       while(size-->0)
                       {
                            int vtx=que.removeFirst();
                            ans.add(vtx);

                             for(Edge e : graph.get(vtx))
                             {
                                   if(--indegree[e.v]==0)
                                        que.addLast(e.v);
                             }
                       }
                }

                if(ans.size()==n)
                {
                    System.out.println("Topological sort of "+n+" vertices");
                    System.out.println(ans);
                }
     }

     //leetcode 207
     public boolean canFinish(int n, int[][] pre) {
         List<List<Integer>> graph = new ArrayList<>();

         for (int i = 0; i < n; i++)
             graph.add(new ArrayList<>());

         int[] indegree = new int[n];
         for (int i = 0; i < pre.length; i++) {
             graph.get(pre[i][1]).add(pre[i][0]);
             indegree[pre[i][0]]++;
         }

         LinkedList<Integer> que = new LinkedList<>();
         int count = 0;

         for (int i = 0; i < n; i++) {
             if (indegree[i] == 0)
                 que.addLast(i);
         }

         while (que.size() != 0) {
             int size = que.size();
             while (size-- > 0) {
                 int vtx = que.removeFirst();
                 count++;

                 for (int i : graph.get(vtx)) {
                     if (--indegree[i] == 0)
                         que.addLast(i);
                 }
             }
         }

         return count == n;
     }
/*
   using dfs
    // -1->unvisired
    // 0->part of my currenty path
    // 2-> visited and over

    public boolean dfs(int src, List<List<Integer>> graph, int[] vis) {
        boolean res = true;
        vis[src] = 0;

        for (int i : graph.get(src)) {
            if (vis[i] == -1) {
                res = res && dfs(i, graph, vis);
            } else if (vis[i] == 0) {
                res = false;
                return res;
            }
        }

        vis[src] = 2;
        return res;
    }

    public boolean canFinish(int n, int[][] pre) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        for (int i = 0; i < pre.length; i++) {
            graph.get(pre[i][1]).add(pre[i][0]);

        }

        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res && dfs(i, graph, vis);
            }

        }
        return res;

    }
 */
     //leetcode 210
     public int[] findOrder(int n, int[][] pre) {
         List<List<Integer>> graph = new ArrayList<>();

         for (int i = 0; i < n; i++)
             graph.add(new ArrayList<>());

         int[] indegree = new int[n];
         for (int i = 0; i < pre.length; i++) {
             graph.get(pre[i][1]).add(pre[i][0]);
             indegree[pre[i][0]]++;
         }

         LinkedList<Integer> que = new LinkedList<>();
         int[] ans = new int[n];
         int count = 0;
         for (int i = 0; i < n; i++) {
             if (indegree[i] == 0)
                 que.addLast(i);
         }
         int index = 0;
         while (que.size() != 0) {
             int size = que.size();
             while (size-- > 0) {
                 int vtx = que.removeFirst();
                 count++;
                 ans[index++] = vtx;

                 for (int i : graph.get(vtx)) {
                     if (--indegree[i] == 0)
                         que.addLast(i);
                 }
             }
         }

         if (count == n)
             return ans;
         else
             return new int[0];
     }

     /*
      // -1->unvisired
    // 0->part of my currenty path
    // 2-> visited and over

    public boolean dfs(int src, List<List<Integer>> graph, int[] vis, LinkedList<Integer> stack) {
        boolean res = true;
        vis[src] = 0;

        for (int i : graph.get(src)) {
            if (vis[i] == -1) {
                res = res && dfs(i, graph, vis, stack);
            } else if (vis[i] == 0) {
                res = false;
                return res;
            }
        }

        vis[src] = 2;
        stack.addFirst(src);
        return res;
    }

    public int[] findOrder(int n, int[][] pre) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        for (int i = 0; i < pre.length; i++) {
            graph.get(pre[i][1]).add(pre[i][0]);

        }
        LinkedList<Integer> stack = new LinkedList<>();
        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res && dfs(i, graph, vis, stack);
            }

        }
        if (res == false)
            return new int[0];
        else {
            int[] ans = new int[n];
            int i = 0;
            while (stack.size() != 0) {
                int val = stack.removeFirst();
                ans[i++] = val;

            }
            return ans;
        }
    }
      */

    //leetcode 329
    // method-1 dfs +dp

    public int dfs(int row, int col, int[][] matrix, int[][] dp, int[][] dir, int n, int m) {

        if (dp[row][col] != -1)
            return dp[row][col];

        int ans = 1;
        for (int i = 0; i < dir.length; i++) {
            int newRow = row + dir[i][0];
            int newCol = col + dir[i][1];

            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && matrix[newRow][newCol] > matrix[row][col]) {
                ans = Math.max(dfs(newRow, newCol, matrix, dp, dir, n, m) + 1, ans);
            }
        }

        return dp[row][col] = ans;
    }

    public int longestIncreasingPath(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = 0;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) {
                    ans = Math.max(ans, dfs(i, j, matrix, dp, dir, n, m));
                }
            }
        }

        return ans;
    }
    /*

    // method-2
    // khan's algo

    public int longestIncreasingPath(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] indegree = new int[n][m];
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                indegree[i][j] = 0;

                for (int k = 0; k < dir.length; k++) {
                    int newRow = i + dir[k][0];
                    int newCol = j + dir[k][1];

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
                            && matrix[newRow][newCol] < matrix[i][j]) {
                        indegree[i][j]++;
                    }
                }

                if (indegree[i][j] == 0)
                    que.addLast(i * m + j);
            }
        }

        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();

                int row = vtx / m;
                int col = vtx % m;
                for (int i = 0; i < dir.length; i++) {
                    int newRow = row + dir[i][0];
                    int newCol = col + dir[i][1];

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
                            && matrix[newRow][newCol] > matrix[row][col]) {

                        if (--indegree[newRow][newCol] == 0) {
                            que.addLast(newRow * m + newCol);
                        }

                    }
                }
            }
            level++;
        }

        return level;

    }
     */

    //kosaraju algo
    //https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
    void dfs(int src,ArrayList<ArrayList<Integer>> adj,int[] vis,LinkedList<Integer> stack)
    {

        vis[src]=1;
        for(int i:adj.get(src))
        {
            if(vis[i]==-1)
                dfs(i,adj,vis,stack);
        }


        stack.addFirst(src);
    }


    public void reverseEdge(ArrayList<ArrayList<Integer>> adj,ArrayList<ArrayList<Integer>> rev, int n)
    {


        for(int i=0;i<n;i++)
        {
            for(int j=0;j<adj.get(i).size();j++)
            {
                rev.get(adj.get(i).get(j)).add(i);
            }
        }

    }

    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here

        LinkedList<Integer> stack=new LinkedList<>();

        int n=adj.size();
        int[] vis=new int[n];
        Arrays.fill(vis,-1);
        for(int i=0;i<n;i++)
        {
            if(vis[i]==-1)
                dfs(i,adj,vis,stack);
        }

        Arrays.fill(vis,-1);
        int count=0;
        ArrayList<ArrayList<Integer>>rev=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            rev.add(new ArrayList<>());
        }
        reverseEdge(adj,rev,n);
        LinkedList<Integer> stack1=new LinkedList<>();//dummy one
        while(stack.size()!=0)
        {
            int vtx=stack.removeFirst();
            if(vis[vtx]==-1)
            {
                count++;
                dfs(vtx,rev,vis,stack1);
            }
        }

        return count;
    }
}
