package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Bfs {

    public static class Edge
    {
        int v,w;

        Edge(int v, int w)
        {
            this.v = v;
            this.w = w;
        }
    }
    //remember two type of bfs


    //1. to detect cycle
    // use it to detect cycle
    public void bfsCycle(ArrayList<Edge>[]graph, int src,boolean[] visited)
    {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(src);


        boolean isCycle =false;
        int level=0;

        while(queue.size()!=0)
        {
            int size=queue.size();
            System.out.println("Level "+level);

             while(size-->0)
             {
                 int vtx=queue.removeFirst();

                 if(visited[vtx])
                 {
                     System.out.println("cycle detected");
                     isCycle=true;
                 }

                 System.out.println(vtx+",");

                 visited[vtx]=true;//marking true after removing from queue

                 for(Edge e:graph[vtx])
                 {
                     if(!visited[e.v])
                          queue.addLast(e.v);
                 }
             }
             level++;
            System.out.println();
        }

    }


    //2.optimized one
    //use it when need to find shortest path, it is most optimized one

    public void bfsShortest(ArrayList<Edge>[]graph, int src,boolean[] visited)
    {
         LinkedList<Integer>queue=new LinkedList<>();
          queue.add(src);
          visited[src]=true; // marking true before inserting
          int dest=6;
          int atLevel=-1;
          int level=0;

            while(queue.size()!=0)
            {
                int size=queue.size();

                  while(size-->0)
                  {
                      int vtx=queue.removeFirst();

                        for(Edge e:graph[vtx])
                        {
                            if(!visited[e.v])
                            {
                                visited[e.v]=true;//first marking true then pushing
                                queue.addLast(e.v);
                            }

                            if(atLevel==-1 && e.v==dest)
                                atLevel=level+1;
                        }
                  }

                  level++;
            }


    }


    //3. Bfs
    //when we need shortest path as well as path
    public void bfsPrintShortest(ArrayList<Edge>[]graph, int src,boolean[] visited)
    {
        LinkedList<Integer>queue=new LinkedList<>();
        queue.add(src);
        visited[src]=true; // marking true before inserting
        int dest=6;
        int atLevel=-1;
        int level=0;
        int[]par=new int[graph.length];
        Arrays.fill(par,-1);

        while(queue.size()!=0)
        {
            int size=queue.size();

            while(size-->0)
            {
                int vtx=queue.removeFirst();

                for(Edge e:graph[vtx])
                {
                    if(!visited[e.v])
                    {
                        visited[e.v]=true;//first marking true then pushing
                        queue.addLast(e.v);
                        par[e.v]=vtx;
                    }

                    if(atLevel==-1 && e.v==dest)
                        atLevel=level+1;
                }
            }

            level++;
        }
        System.out.println(dest+" Present at : "+atLevel);
        int idx=dest;
         while(idx!=-1)
         {
             System.out.print(idx+" --->");
             idx=par[idx];
         }


    }

     //leetcode question
    //1091
     public int shortestPathBinaryMatrix(int[][] grid) {
         int n = grid.length;
         int m = grid[0].length;

         if (grid[0][0] != 0 || grid[n - 1][m - 1] != 0)
             return -1;

         if (n == 1 && m == 1 && grid[n - 1][m - 1] == 0)
             return 1;

         int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
         LinkedList<Integer> queue = new LinkedList<Integer>();
         queue.addLast(0);
         grid[0][0] = 1;
         int atLevel = -1;
         int level = 1;
         while (queue.size() != 0) {
             int size = queue.size();

             while (size-- > 0) {
                 int vtx = queue.removeFirst();
                 int row = vtx / m;
                 int col = vtx % m;
                 for (int i = 0; i < dir.length; i++) {
                     int newRow = row + dir[i][0];
                     int newCol = col + dir[i][1];

                     if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m && grid[newRow][newCol] == 0) {
                         grid[newRow][newCol] = 1;
                         queue.addLast(newRow * m + newCol);
                     }

                     if (newRow == n - 1 && newCol == m - 1) {
                         // count++;
                         return level + 1;
                     }
                 }
             }
             level++;
         }

         return -1;

     }

     //leectode 785
     // cycle bfs
     public boolean bfs(int[][] graph, int src, int[] vis) {
         LinkedList<Integer> queue = new LinkedList<Integer>();
         queue.add(src);
         int color = 0;

         while (queue.size() != 0) {
             int size = queue.size();

             while (size-- > 0) {
                 int vtx = queue.removeFirst();

                 if (vis[vtx] != -1) {
                     if (color != vis[vtx])
                         return false;

                 }

                 vis[vtx] = color;
                 for (int i : graph[vtx]) {
                     if (vis[i] == -1) {
                         queue.addLast(i);
                     }
                 }

             }
             color = (color + 1) % 2;
         }

         return true;
     }

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int m = graph[0].length;

        int[] vis = new int[n];
        Arrays.fill(vis, -1);

        for (int i = 0; i < n; i++) {

            if (vis[i] == -1) {
                if (bfs(graph, i, vis) == false)
                    return false;
            }
        }

        return true;
    }


    //https://www.naukri.com/code360/problems/ninja-and-the-maze_1262274
    public static Boolean reachDestination(int[][] maze, int n, int m, int sr, int sc, int er, int ec) {
        // Write your code here
        LinkedList<Integer>que=new LinkedList<>();
        boolean[][] vis=new boolean[n][m];
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        que.add(sr*m+sc);
        vis[sr][sc]=true;

        while(que.size()!=0){
            int size=que.size();

            while(size-->0)
            {
                int idx=que.removeFirst();
                int i=idx/m;
                int j=idx%m;

                for(int[] d:dir)
                {
                    int r=i;
                    int c=j;

                    while(r>=0 && c>=0 && r<n && c<m && maze[r][c]==0)
                    {
                        r+=d[0];
                        c+=d[1];

                    }

                    r-=d[0];
                    c-=d[1];

                    if(vis[r][c])
                        continue;

                    vis[r][c]=true;
                    que.addLast(r*m+c);

                    if(r==er && c==ec)
                        return true;
                }
            }
        }

        return false;
    }
}
