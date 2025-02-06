package tree;

import java.util.ArrayList;
import java.util.List;

public class bstInorderAndPredceesor {


   static  class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }
        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    };
    public static TreeNode getMin(TreeNode root) {
        while (root != null && root.left != null)
            root = root.left;

        return root;
    }

    public static TreeNode getMax(TreeNode root) {
        while (root != null && root.right != null)
            root = root.right;

        return root;
    }

    public static List<Integer> predecessorSuccessor(TreeNode root, int key) {
        // Write your code here.

        TreeNode pred = null;
        TreeNode succ = null;

        TreeNode curr = root;

        while (curr != null) {
            if (curr.data == key) {
                if (curr.left != null) {
                    pred = getMin(curr.left);
                }

                if (curr.right != null) {
                    succ = getMax(curr.right);
                }

                break;
            } else if (curr.data > key) {
                succ = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }

        }

        List<Integer> ans = new ArrayList<>();

        if (pred != null) {
            ans.add(pred.data);
        } else {
            ans.add(-1);
        }

        if (succ != null) {
            ans.add(succ.data);
        } else {
            ans.add(-1);
        }

        return ans;

    }
}
