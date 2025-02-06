package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Dfs {
    //leetcode 200
    public void dfs(int si, int ei, char[][] grid, int[][] dir) {
        grid[si][ei] = '2';

        for (int i = 0; i < dir.length; i++) {
            int row = si + dir[i][0];
            int col = ei + dir[i][1];

            if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == '1') {
                dfs(row, col, grid, dir);
            }
        }
    }

    public int numIslands(char[][] grid) {

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        int comp = 0;

        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    comp++;
                    dfs(i, j, grid, dir);
                }
            }
        }

        return comp;
    }

    //leetcode 695
    public int dfs(int si, int ei, int[][] grid, int[][] dir) {
        grid[si][ei] = -1;
        int size = 0;
        for (int i = 0; i < dir.length; i++) {
            int row = si + dir[i][0];
            int col = ei + dir[i][1];

            if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 1) {
                size += dfs(row, col, grid, dir);
            }
        }

        return size + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        int ans = 0;

        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {

                    ans = Math.max(ans, dfs(i, j, grid, dir));
                }
            }
        }

        return ans;
    }

    //leetcode 463
    public int islandPerimeter(int[][] grid) {

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                    continue;

                count += 4;

                for (int k = 0; k < dir.length; k++) {
                    int row = i + dir[k][0];
                    int col = j + dir[k][1];

                    if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 1) {
                        count -= 1;

                    }

                }
            }
        }

        return count;
    }


    //leetcode 130
    public void dfs1(int i, int j, char[][] grid, int[][] dir) {
        grid[i][j] = '$';

        for (int k = 0; k < dir.length; k++) {
            int row = i + dir[k][0];
            int col = j + dir[k][1];

            if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 'O') {
                dfs1(row, col, grid, dir);

            }

        }

    }

    public void solve(char[][] board) {
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && board[i][j] == 'O') {
                    dfs1(i, j, board, dir);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '$') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

    }

    //leetcode 994
    // mutli Point bfs
    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int fresh = 0;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    que.addLast(i * m + j);
                } else if (grid[i][j] == 1)
                    fresh++;

            }
        }

        if (fresh == 0 || que.size() == 0)
            return fresh == 0 ? 0 : -1;

        int level = 0;

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                int row = rvtx / m;
                int col = rvtx % m;
                for (int i = 0; i < 4; i++) {
                    int newRow = row + dir[i][0];
                    int newCol = col + dir[i][1];

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        que.addLast(newRow * m + newCol);
                        fresh--;

                        if (fresh == 0)
                            return level + 1;
                    }
                }

            }
            level++;
        }

        return -1;
    }

    //leetcode 542
    public int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    que.addLast(i * m + j);
                } else {
                    mat[i][j] = -1;
                }
            }
        }
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                int row = rvtx / m;
                int col = rvtx % m;
                for (int i = 0; i < 4; i++) {
                    int newRow = row + dir[i][0];
                    int newCol = col + dir[i][1];
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && mat[newRow][newCol] == -1) {
                        mat[newRow][newCol] = level + 1;
                        que.addLast(newRow * m + newCol);
                    }
                }

            }
            level++;
        }

        return mat;

    }


    //leetcode 1765
    public int[][] highestPeak(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    que.addLast(i * m + j);
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = -1;
                }
            }
        }
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                int row = rvtx / m;
                int col = rvtx % m;
                for (int i = 0; i < 4; i++) {
                    int newRow = row + dir[i][0];
                    int newCol = col + dir[i][1];
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && mat[newRow][newCol] == -1) {
                        mat[newRow][newCol] = level + 1;
                        que.addLast(newRow * m + newCol);
                    }
                }

            }
            level++;
        }

        return mat;

    }

    //leetcode 1020
    public int numEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (grid[i][j] == 1) {
                        que.addLast(i * m + j);
                        grid[i][j] = 0;
                    }
                }
            }

        }

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                int row = rvtx / m;
                int col = rvtx % m;
                for (int i = 0; i < 4; i++) {
                    int newRow = row + dir[i][0];
                    int newCol = col + dir[i][1];
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 0;
                        que.addLast(newRow * m + newCol);
                    }
                }

            }
            level++;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }

            }

        }

        return count;
    }


    //https://www.naukri.com/code360/problems/walls-and-gates_1092887?leftPanelTabValue=PROBLEM
    public static int[][] wallsAndGates(int[][] a, int n, int m) {
        // Write you code here

        LinkedList<Integer>que=new LinkedList<>();

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {


                if(a[i][j]==0)
                {
                    que.addLast(i*m+j);

                }

            }
        }

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int level=0;

        while(que.size()!=0)
        {
            int size=que.size();

            while(size-->0)
            {
                int rvtx = que.removeFirst();

                int row = rvtx / m;
                int col = rvtx % m;
                for (int i = 0; i < 4; i++) {
                    int newRow = row + dir[i][0];
                    int newCol = col + dir[i][1];
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && a[newRow][newCol] == 2147483647) {
                        a[newRow][newCol] = level+1;
                        que.addLast(newRow * m + newCol);
                    }
                }
            }

            level++;
        }

        return a;
    }

    //journey to moon
    public static int dfs(int src,List<List<Integer>>graph,boolean[] vis)
    {
        int size=1;
        vis[src]=true;

        for(int i:graph.get(src))
        {
            if(vis[i]==false)
            {
                size+=dfs(i,graph,vis);
            }
        }

        return size;
    }
    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here

        List<List<Integer>>graph=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            graph.add(new ArrayList<>());
        }

        for(List<Integer> a:astronaut)
        {
            graph.get(a.get(0)).add(a.get(1));
            graph.get(a.get(1)).add(a.get(0));
        }

        boolean[] vis=new boolean[n];
        Arrays.fill(vis,false);
        long sum = n;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int size = dfs(i, graph, vis);
                ans += (long) size * (sum - size); // Use long to prevent overflow
                sum -= size;
            }
        }

        return ans;

    }

    //leetcode 1905
    public boolean dfs(int i, int j, int[][] grid1, int[][] grid2, int[][] dir) {
        grid2[i][j] = 0;

        boolean res = true;
        int n = grid2.length;
        int m = grid2[0].length;

        for (int k = 0; k < dir.length; k++) {
            int row = i + dir[k][0];
            int col = j + dir[k][1];

            if (row >= 0 && row < n && col >= 0 && col < m && grid2[row][col] == 1) {
                res = dfs(row, col, grid1, grid2, dir) && res;
            }

        }

        return res && (grid1[i][j] == 1);

    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid2.length;
        int m = grid2[0].length;

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    boolean res = dfs(i, j, grid1, grid2, dir);
                    if (res)
                        ans++;
                }
            }
        }

        return ans;
    }




}
