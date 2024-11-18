package recursionAndBackTracking;

import java.util.ArrayList;

public class recursionPractice {

    //maze Path

    /**
     *
     * one way without checking call recursion
     */
    public static ArrayList<String> getMazePath(int startX, int startY, int endX, int endY) {


        if(startX == endX && startY == endY) {
            ArrayList<String> path = new ArrayList<>();
            path.add("");
            return path;
        }
        else if(startX >endX  || startY > endY) {

            ArrayList<String> path = new ArrayList<>();
             return path;
        }

        ArrayList<String> pathH=getMazePath(startX,startY+1,endX,endY);
        ArrayList<String> pathV=getMazePath(startX+1,startY,endX,endY);

        ArrayList<String> finalAns=new ArrayList<>();

          for(String s:pathH){

               finalAns.add("H"+s);
          }

          for(String s:pathV){
              finalAns.add("V"+s);
          }

          return finalAns;
    }
    public static ArrayList<String> getMazePathChecking(int startX, int startY, int endX, int endY) {


        if(startX == endX && startY == endY) {
            ArrayList<String> path = new ArrayList<>();
            path.add("");
            return path;
        }
        ArrayList<String> pathH=new ArrayList<>();
         if(startY+1<=endY)
             pathH=getMazePathChecking(startX,startY+1,endX,endY);
        ArrayList<String> pathV=new ArrayList<>();
         if(startX+1<=endX)
           pathV=getMazePathChecking(startX+1,startY,endX,endY);

        ArrayList<String> finalAns=new ArrayList<>();

        for(String s:pathH){

            finalAns.add("H"+s);
        }

        for(String s:pathV){
            finalAns.add("V"+s);
        }

        return finalAns;
    }

    /*
      maze path with jump

     */
    public static ArrayList<String> getMazePathJump(int startX, int startY, int endX, int endY) {


        if(startX == endX && startY == endY) {
            ArrayList<String> path = new ArrayList<>();
            path.add("");
            return path;
        }


        ArrayList<String> path=new ArrayList<>();
        //Horizontal
        for(int ms=1; ms<=endX-startX; ms++) {

            ArrayList<String> pathH=getMazePathJump(startX+ms,startY,endX,endY);


            for(String s:pathH){

                path.add("H"+ ms+ s);
            }
        }

        //vertical
        for(int ms=1; ms<=endY-startY; ms++){

            ArrayList<String> pathV=getMazePathJump(startX,startY+ms,endX,endY);


            for(String s:pathV){

                path.add("V"+ ms+ s);
            }
        }

        //diagnol
        for(int ms=1; ms<=endY-startY && ms<=endX-startX; ms++) {

            ArrayList<String> pathD=getMazePathJump(startX+ms,startY+ms,endX,endY);


            for(String s:pathD){

                path.add("D"+ ms+ s);
            }


        }


        return path;

    }

    //https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    // we can use mat as vis array by placing -1;
/*
    public int findPath(int[][] mat, int si, int sj, int n , int m,String currAns,boolean [][] vis,ArrayList<String>ans,int [][] dir,String [] dirS)
    {

        if(si==n-1 && sj==m-1 && mat[si][sj]==1)
        {

            ans.add(currAns);
            return 1;
        }


        vis[si][sj]=true;
        int totalPath=0;
        for(int i=0;i<dir.length;i++)
        {
            int r=si+dir[i][0];
            int c=sj+dir[i][1];


            if(r>=0 && c>=0 && r<n && c<m && vis[r][c]==false && mat[r][c]==1)
            {
                totalPath+=findPath(mat,r,c,n,m,currAns+dirS[i],vis,ans,dir,dirS);
            }
        }


        vis[si][sj]=false;

        return totalPath;
    }

    public ArrayList<String> findPath(int[][] mat) {
        // Your code here

        int n = mat.length;
        int m = mat[0].length;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        String[] dirS = {"D", "R", "U", "L"};

        boolean[][] vis = new boolean[n][m];

        ArrayList<String> ans = new ArrayList<>();

        if (mat[0][0] == 0)
            return ans;

        findPath(mat, 0, 0, n, m, "", vis, ans, dir, dirS);

        return ans;
    }

    */

    public int findPath(int[][] mat, int si, int sj, int n, int m, String currAns, ArrayList<String> ans, int[][] dir,
                        String[] dirS) {

        if (si == n - 1 && sj == m - 1 && mat[si][sj] == 1) {

            ans.add(currAns);
            return 1;
        }

        mat[si][sj] = -1;
        int totalPath = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = si + dir[i][0];
            int c = sj + dir[i][1];

            if (r >= 0 && c >= 0 && r < n && c < m && mat[r][c] == 1) {
                totalPath += findPath(mat, r, c, n, m, currAns + dirS[i], ans, dir, dirS);
            }
        }

        mat[si][sj] = 1;

        return totalPath;
    }

    public ArrayList<String> findPath(int[][] mat) {
        // Your code here

        int n = mat.length;
        int m = mat[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        String[] dirS = { "D", "R", "U", "L" };

        ArrayList<String> ans = new ArrayList<>();

        if (mat[0][0] == 0)
            return ans;

        findPath(mat, 0, 0, n, m, "", ans, dir, dirS);

        return ans;

    }


    //https://www.geeksforgeeks.org/problems/special-matrix4201/1

    public int solve(int s, int e, int n, int m, int[][] arr, int[][] ansArr, int mod) {
        if (s == n && e == m) {
            return ansArr[s][e] = 1;
        }

        if (ansArr[s][e] != -1)
            return ansArr[s][e];

        int count = 0;

        if (s + 1 <= n && arr[s + 1][e] == 0) {
            count = (count + solve(s + 1, e, n, m, arr, ansArr, mod)) % mod;
        }

        if (e + 1 <= m && arr[s][e + 1] == 0) {
            count = (count + solve(s, e + 1, n, m, arr, ansArr, mod)) % mod;
        }

        return ansArr[s][e] = count % mod;
    }

    public int FindWays(int n, int m, int[][] blocked_cells) {
        // Code here
        int[][] arr = new int[n + 1][m + 1];

        int[][] ansArr = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            //Arrays.fill(ansArr[i],-1);
            for (int j = 0; j <= m; j++) {
                ansArr[i][j] = -1;
            }
        }

        for (int i = 0; i < blocked_cells.length; i++) {

            arr[blocked_cells[i][0]][blocked_cells[i][1]] = -1;

        }

        int mod = (int) 1e9 + 7;

        if (arr[n][m] == -1 || arr[1][1] == -1)
            return 0;

        return solve(1, 1, n, m, arr, ansArr, mod);

    }
        public static void main(String[] args) {

          ArrayList<String> mazePath = getMazePathJump(0,0,2,2);
          System.out.println(mazePath);
    }
}
