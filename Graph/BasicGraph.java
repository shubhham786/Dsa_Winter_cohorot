package Graph;

import java.util.ArrayList;

public class BasicGraph {

    public static class Edge
    {
        int v,w;

         Edge(int v, int w)
         {
             this.v = v;
             this.w = w;
         }
    }

    //undirected graph
    public static void addEdge(ArrayList<Edge>[]graph, int u,int v,int w)
    {
         graph[u].add(new Edge(v,w));
         graph[v].add(new Edge(u,w));
    }

    //O(2E)==O(E)
    public static void display(ArrayList<Edge>[]graph,int totalVertex)
    {
        for(int i=0;i<totalVertex;i++)
        {
            System.out.print("i ----> ");
            for(Edge e:graph[i])
            {
                System.out.print("( "+e.v+","+e.w+") ");
            }
            System.out.println();
        }
    }

    public static int findEdge(ArrayList<Edge>[]graph,int u,int v)
    {
          for(int i=0;i<graph[u].size();i++)
          {
              Edge e=graph[u].get(i);
              if(e.v==v)
              {
                  return i;
              }
          }

          return -1;
    }

    public static void removeEdge(ArrayList<Edge>[]graph,int u,int v)
    {
        int idx=findEdge(graph,u,v);
         graph[u].remove(idx);

         int idx2=findEdge(graph,v,u);
         graph[v].remove(idx2);
    }

    public static boolean hasPath(ArrayList<Edge>[]graph, int src, int dest,boolean[]vis)
    {
        if(src==dest)
             return true;

        boolean res=false;
        vis[src]=true;
        for(Edge e:graph[src])
        {
            if(vis[e.v]==false)
            {
                res=res|| hasPath(graph,e.v,dest,vis);
            }
        }

        return res;
    }

    public static int allPath(ArrayList<Edge>[]graph, int src, int dest,boolean[]vis,String psf)
    {
         if(src==dest)
         {
             System.out.println(psf+dest);
             return 1;
         }

         int count=0;
         vis[src]=true;

         for(Edge e:graph[src])
         {

             if(vis[e.v]==false)
             {
                 count+=allPath(graph,e.v,dest,vis,psf+src);
             }
         }
         vis[src]=false;

         return count;
    }

    //heaviest path
    //longest path
    public static class pair{
        int heavyPath=0;
        String psf="";

        pair()
        {

        }

        pair(int heavyPath,String psf)
        {
            this.heavyPath=heavyPath;
            this.psf=psf;
        }
    }

    public static pair heaviestPath(ArrayList<Edge>[]graph, int src, int dest, boolean[] vis)
    {
         if(src==dest)
         {
             return new pair();
         }

         vis[src]=true;
         pair myAns=new pair(-1,"");

         for(Edge e:graph[src])
         {
             if(vis[e.v]==false)
             {
                 pair ans=heaviestPath(graph,e.v,dest,vis);

                 if(ans.heavyPath!=-1 && ans.heavyPath>myAns.heavyPath)
                 {
                     myAns.heavyPath=ans.heavyPath;
                     myAns.psf=src+ans.psf;
                 }
             }
         }

         vis[src]=false;
         return myAns;

    }

}
