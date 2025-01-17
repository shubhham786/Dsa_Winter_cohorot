package tree.practice;

import java.util.*;

public class ViewRealted {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode 102
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        LinkedList<TreeNode> levelQueue = new LinkedList<>();

        if (root == null)
            return list;

        levelQueue.add(root);

        while (levelQueue.size() != 0) {
            int size = levelQueue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode top = levelQueue.removeFirst();

                level.add(top.val);

                if (top.left != null) {
                    levelQueue.add(top.left);
                }
                if (top.right != null) {
                    levelQueue.add(top.right);
                }
            }

            list.add(level);

        }

        return list;
    }

    //leetcoe 199
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        LinkedList<TreeNode> levelQueue = new LinkedList<>();

        if (root == null)
            return list;

        levelQueue.add(root);

        while (levelQueue.size() != 0) {
            int size = levelQueue.size();
            // List<Integer>level=new ArrayList<>();
            list.add(levelQueue.getFirst().val);
            while (size-- > 0) {
                TreeNode top = levelQueue.removeFirst();

                if (top.right != null) {
                    levelQueue.add(top.right);
                }

                if (top.left != null) {
                    levelQueue.add(top.left);
                }

            }

        }

        return list;
    }

    //vertical order
    public class verticalPair {

        TreeNode node = null;
        int h1 = 0;

        verticalPair(TreeNode node, int h) {
            this.node = node;
            this.h1 = h;
        }

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        LinkedList<verticalPair> que = new LinkedList<>();

        que.addLast(new verticalPair(root, 0));

        int minH = 0;
        int maxH = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                verticalPair front = que.removeFirst();
                // map.getOrDefault(front.h1, new ArrayList<>()).add(front.node.val);

                map.putIfAbsent(front.h1, new ArrayList<>());
                map.get(front.h1).add(front.node.val);
                minH = Math.min(minH, front.h1);
                maxH = Math.max(maxH, front.h1);

                if (front.node.left != null) {
                    que.addLast(new verticalPair(front.node.left, front.h1 - 1));
                }
                if (front.node.right != null) {
                    que.addLast(new verticalPair(front.node.right, front.h1 + 1));
                }

            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        System.out.println(map.get(minH));
        while (minH <= maxH) {
            ans.add(map.get(minH));
            minH++;
        }

        return ans;

    }
    /*
    class Tree
{
      Map<Integer,ArrayList<Integer>>map;
      int minH=0;
      public void diagonal(Node root,int dig)
      {
            if(root==null)
             return ;

             map.putIfAbsent(dig,new ArrayList<>());
             map.get(dig).add(root.data);
             minH=Math.min(minH,dig);
             diagonal(root.left,dig-1);
             diagonal(root.right,dig);

      }
     public ArrayList<Integer> diagonal(Node root)
      {
           //add your code here.
           map=new HashMap<>();
           ArrayList<Integer>ans=new ArrayList<Integer>();
           diagonal(root,0);
           int index=0;

            while(index>=minH)
            {
                for(int ele:map.get(index))
                {
                     ans.add(ele);
                }
                index--;
            }

           return ans;

      }
}
     */

    //bootom view
    //https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

    //leetcode 124
   // https://leetcode.com/problems/binary-tree-maximum-path-sum/submissions/1511931598/


    //leetcode 968
    // isCamera->1, i am covered=0,camera req=-1
    int camera = 0;

    public int minCameraCover1(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return -1;

        int left = minCameraCover1(root.left);
        int right = minCameraCover1(root.right);

        if (left == -1 || right == -1) {
            camera++;
            // i am camera;
            return 1;
        }

        if (left == 1 || right == 1)
            return 0;

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        int z = minCameraCover1(root);

        if (z == -1)
            camera++;

        return camera;
    }
}
