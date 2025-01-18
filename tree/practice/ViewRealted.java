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

    //leetcode 337

    public int[] rob1(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }

        int[] left = rob1(root.left);
        int[] right = rob1(root.right);

        int[] ans = new int[2];
        ans[0] = left[1] + right[1] + root.val;
        ans[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return ans;
    }

    public int rob(TreeNode root) {
        if (root == null)
            return 0;

        int[] ans = rob1(root);

        return Math.max(ans[0], ans[1]);
    }

    //leetcode 1372
    // Global variable to store the maximum zigzag path length
    int maxAns = 0;

    // Helper function to calculate the longest zigzag path starting from the given node
// The function returns an array of two integers:
// arr[0] -> Longest zigzag path starting from the current node and going left
// arr[1] -> Longest zigzag path starting from the current node and going right
    public int[] longestZigZag_(TreeNode root) {
        // Base case: If the current node is null, return {-1, -1}
        // to indicate no zigzag path exists from this point
        if (root == null)
            return new int[] { -1, -1 };

        // Recursively calculate the zigzag path lengths for the left and right subtrees
        int[] left = longestZigZag_(root.left);
        int[] right = longestZigZag_(root.right);

        // Initialize an array to store the results for the current node
        int[] ans = new int[2];

        // Calculate the zigzag path length starting from the current node and going left
        // This is 1 + the zigzag path length from the left child when moving right
        ans[0] = 1 + left[1];

        // Calculate the zigzag path length starting from the current node and going right
        // This is 1 + the zigzag path length from the right child when moving left
        ans[1] = 1 + right[0];

        // Update the global maximum zigzag path length
        maxAns = Math.max(maxAns, Math.max(ans[0], ans[1]));

        // Return the results for the current node
        return ans;
    }

    // Main function to find the longest zigzag path in a binary tree
    public int longestZigZag(TreeNode root) {
        // Edge case: If the tree is empty, return 0
        if (root == null)
            return 0;

        // Call the helper function to compute the longest zigzag path
        longestZigZag_(root);

        // Return the global maximum zigzag path length
        return maxAns;
    }

    //leetcode 979
    int cost = 0;

    public int distributeCoins_(TreeNode root) {

        if (root == null)
            return 0;

        int left = distributeCoins_(root.left);
        int right = distributeCoins_(root.right);

        cost += Math.abs(left) + Math.abs(right);

        return left + right + root.val - 1;
    }

    public int distributeCoins(TreeNode root) {

        int z = distributeCoins_(root);

        return cost;
    }
}
