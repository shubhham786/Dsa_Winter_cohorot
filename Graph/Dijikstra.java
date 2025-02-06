package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijikstra {
    static class iPair {
        int first, second;
        iPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        // Write your code here

        int n=adj.size();
        PriorityQueue<iPair> queue=new PriorityQueue<>((a, b)->a.second-b.second);
        queue.add(new iPair(src,0));
        int[] ans=new int[n];
        int[] vis=new int[n];
        Arrays.fill(vis,-1);

        while(queue.size()!=0)
        {
            int size=queue.size();

            while(size-->0)
            {
                iPair  vtx=queue.remove();

                if(vis[vtx.first]==0)
                {
                    continue;
                }

                vis[vtx.first]=0;
                ans[vtx.first]=vtx.second;

                for(iPair v:adj.get(vtx.first))
                {
                    if(vis[v.first]==-1)
                    {
                        queue.add(new iPair(v.first,v.second+vtx.second));
                    }
                }
            }
        }


        ArrayList<Integer> ans1=new ArrayList<>();


        for(int ele:ans)
        {
            ans1.add(ele);
        }


        return ans1;
    }

    //leetcode 1976
    public int countPaths(int n, int[][] roads) {
        List<List<iPair>> graph = new ArrayList<>();

        int mod = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] vtx : roads) {
            graph.get(vtx[0]).add(new iPair(vtx[1], vtx[2]));
            graph.get(vtx[1]).add(new iPair(vtx[0], vtx[2]));
        }

        // 2nd dijistra
        long[] dist = new long[n];
        int[] ways = new int[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<iPair> queue = new PriorityQueue<>((a, b) -> a.second - b.second);
        queue.add(new iPair(0, 0));

        while (queue.size() != 0) {
            int size = queue.size();
            while (size-- > 0) {
                iPair current = queue.remove();
                int u = current.first;
                long d = current.second;

                if (d > dist[u])
                    continue;

                for (iPair neighbor : graph.get(u)) {
                    int v = neighbor.first;
                    long newDist = d + neighbor.second;

                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        ways[v] = ways[u];
                        queue.add(new iPair(v, (int) newDist));
                    } else if (newDist == dist[v]) {
                        ways[v] = (ways[v] + ways[u]) % mod;
                    }
                }
            }
        }

        return ways[n - 1];

    }

    //https://www.naukri.com/code360/problems/maze-runner_3130881?leftPanelTabValue=SUBMISSION
    //2nd dijikstra
    public static int mazeRunner(int n, int m, int maze[][], int start[], int destination[]) {
        // Write your code here.

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int sr=start[0];
        int sc=start[1];
        int er=destination[0];
        int ec=destination[1];

        int[][] dist=new int[n][m];
        for(int i=0;i<n;i++)
            Arrays.fill(dist[i],(int)1e9);

        PriorityQueue<int[]>que=new PriorityQueue<>((a,b)->a[2]-b[2]);
        que.add(new int[]{sr,sc,0});
        dist[sr][sc]=0;

        while(que.size()!=0)
        {
            int size=que.size();
            while(size-->0)
            {
                int[] vtx=que.remove();
                int r=vtx[0];
                int c=vtx[1];
                int d1=vtx[2];

                if(r==er && c==ec)
                    return d1;

                for(int[] d:dir)
                {
                    int i=r;
                    int j=c;
                    int steps=d1;

                    while(i>=0 && j>=0 && i<n && j<m && maze[i][j]==0)
                    {
                        i+=d[0];
                        j+=d[1];
                        steps++;
                    }

                    i-=d[0];
                    j-=d[1];
                    steps--;

                    if(steps>=dist[i][j])
                        continue;

                    que.add(new int[]{i,j,steps});
                    dist[i][j]=steps;
                }
            }
        }

        return -1;
    }
}
