package Graph;

import java.util.Arrays;

public class Dsu {

    int findParent(int u, int [] par)
    {
         return par[u]==u?u:(par[u]=findParent(par[u],par));
    }

    void merge(int p1, int p2, int[] size, int[] par)
    {
        if(size[p1]>size[p2])
        {
            par[p2]=p1;
            size[p1]+=size[p2];
        }
        else
        {
            par[p1]=p2;
            size[p2]+=size[p1];
        }
    }
    //leetcode 684
    public int findPar(int u, int[] par) {
        return par[u] == u ? u : (par[u] = findPar(par[u], par));
    }

    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;

        int[] par = new int[n + 1];
        for (int i = 0; i <= n; i++)
            par[i] = i;

        int ans1 = 0, ans2 = 0;

        for (int[] a : edges) {
            int p1 = findPar(a[0], par);
            int p2 = findPar(a[1], par);

            if (p1 != p2) {
                par[p1] = p2;
            } else {
                ans1 = a[0];
                ans2 = a[1];
            }
        }

        return new int[] { ans1, ans2 };
    }

    //leetcode 1061


    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] par = new int[26];
        for (int i = 0; i < 26; i++) {
            par[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            int a = ch1 - 'a';
            int b = ch2 - 'a';
            int p1 = findPar(a, par);
            int p2 = findPar(b, par);

            if (p1 < p2) {
                par[p2] = p1;
            } else {
                par[p1] = p2;
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < baseStr.length(); i++) {
            char ch = (char) ('a' + findPar(baseStr.charAt(i) - 'a', par));
            ans.append(ch);
        }

        return ans.toString();
    }

    //leetcode 839


    public boolean isPermuation(String s1, String s2) {
        int count = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }

        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;

        int[] par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        int ans = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int p1 = findPar(i, par);
                int p2 = findPar(j, par);

                if (p1 != p2) {
                    if (isPermuation(strs[i], strs[j])) {
                        par[p2] = p1;
                        ans--;
                    }
                }

            }
        }

        return ans;
    }

    //leetcode 695
    public int maxAreaOfIsland(int[][] grid) {

        int ans = 0;

        int n = grid.length;
        int m = grid[0].length;

        int[] par = new int[n * m];
        int[] size = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            par[i] = i;
            size[i] = 1;
        }
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {

                    int p1 = findPar(i * m + j, par);
                    for (int k = 0; k < dir.length; k++) {
                        int newRow = i + dir[k][0];
                        int newCol = j + dir[k][1];
                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1) {
                            int p2 = findPar(newRow * m + newCol, par);

                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];

                            }
                        }
                    }
                    ans = Math.max(ans, size[p1]);
                }
            }
        }

        return ans;
    }

//https://www.naukri.com/code360/problems/number-of-islands-ii_1266048?leftPanelTabValue=SUBMISSION
/*
    public static int findPar(int u,int[] par)
    {
        return par[u]==u?u:(par[u]=findPar(par[u],par));
    }
    public static int[] numOfIslandsII(int n, int m, int[][] q) {
        // Write your code here.

        int[] par=new int[n*m];

        for(int i=0;i<n*m;i++)
        {
            par[i]=-1;
        }

        int num=q.length;

        int [] ans=new int[num];
        int count=0;
        int index=0;
        int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] q1:q)
        {
            int i=q1[0];
            int j=q1[1];


            if(par[i*m+j]!=-1)
            {
                ans[index++]=count;
                continue;
            }

            par[i*m+j]=i*m+j;
            count++;
            int p1=findPar(i*m+j,par);
            for(int k=0;k<dir.length;k++)
            {
                int row=i+dir[k][0];
                int col=j+dir[k][1];

                if (row >= 0 && row < n && col >= 0 && col < m && par[row * m + col] != -1 ) {

                    int p2=findPar(row*m+col,par);

                    if(p1!=p2)
                    {
                        par[p2]=p1;
                        count--;
                    }
                }
            }

            ans[index++]=count;
        }
        return ans;
    }
    */

    /*
    //https://www.naukri.com/code360/problems/water-supply-in-a-village_1380956?leftPanelTabValue=SUBMISSION
    static int findPar(int u, int [] par)
    {
        return par[u]==u?u:(par[u]=findPar(par[u],par));
    }
    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        // Write your code here



        int [][] pipe=new int[2*n][3];

        for(int i=0;i<k;i++)
        {
            pipe[i][0]=pipes[i][0];
            pipe[i][1]=pipes[i][1];
            pipe[i][2]=pipes[i][2];
        }

        for (int i = 0; i < n; i++) {
            pipe[k + i][0] = 0; // All well pipes connect to node 0
            pipe[k + i][1] = i + 1; // Each well connects to house i+1
            pipe[k + i][2] = wells[i]; // Cost of the well pipe
        }

        Arrays.sort(pipe,(a,b)->a[2]-b[2]);

        int [] par=new int[n+1];
        for(int i=0;i<=n;i++)
            par[i]=i;

        int cost=0;
        for(int[] edge:pipe)
        {
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];

            int p1=findPar(u,par);
            int p2=findPar(v,par);

            if(p1!=p2)
            {
                par[p1]=p2;
                cost+=w;
            }
        }


        return cost;

    }
*/


    //leetcode 1584
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;

        int[][] graph = new int[(n * (n - 1)) / 2][3];
        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int w = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                graph[index][0] = i;
                graph[index][1] = j;
                graph[index][2] = w;
                index++;
            }
        }

        Arrays.sort(graph, (a, b) -> a[2] - b[2]);

        int[] par = new int[n];

        for (int i = 0; i < n; i++)
            par[i] = i;

        int ans = 0;

        int noOfEdges = 0;

        for (int i = 0; i < graph.length; i++) {
            int u = graph[i][0];
            int v = graph[i][1];
            int w = graph[i][2];

            int p1 = findParent(u, par);
            int p2 = findParent(v, par);

            if (p1 != p2) {
                par[p1] = p2;
                ans += w;
                noOfEdges++;
            } else
                continue;

            if (noOfEdges == n - 1)
                break;
        }

        return ans;

    }

    //leetcode 990
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        int[] par = new int[26];

        for (int i = 0; i < 26; i++) {
            par[i] = i;
        }

        for (int i = 0; i < equations.length; i++) {
            int val1 = equations[i].charAt(0) - 'a';
            int val2 = equations[i].charAt(3) - 'a';

            int p1 = findParent(val1, par);
            int p2 = findParent(val2, par);

            if (equations[i].charAt(1) == '=') {
                par[p1] = p2;
            }
        }

        for (int i = 0; i < equations.length; i++) {
            if (equations[i].charAt(1) == '!') {
                int val1 = equations[i].charAt(0) - 'a';
                int val2 = equations[i].charAt(3) - 'a';

                int p1 = findParent(val1, par);
                int p2 = findParent(val2, par);

                if (p1 == p2) {
                    return false;
                }

            }
        }

        return true;

    }
}
