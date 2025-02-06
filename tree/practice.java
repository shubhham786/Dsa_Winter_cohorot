package tree;

import java.util.ArrayList;
import java.util.List;

public class practice {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //question root to given node path


    //way 1

    boolean nodeToRootPath(TreeNode root,int data, ArrayList<TreeNode> path) {

        if(root==null) return false;

        if(root.val==data){
            path.add(root);
            return true;
        }

        boolean res=nodeToRootPath(root.left,data,path) || nodeToRootPath(root.right,data,path);

        if(res)
            path.add(root);

        return res;
    }

    //way 2 using ArrayList
    ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data){

        if(root==null) return new ArrayList<>();

        if(root.val==data){
            ArrayList<TreeNode> path=new ArrayList<>();
            path.add(root);
            return path;
        }

        ArrayList<TreeNode> left=nodeToRootPath(root.left,data);
        if(left.size()!=0){
            left.add(root);
            return left;

        }
        ArrayList<TreeNode> right=nodeToRootPath(root.right,data);
        if(right.size()!=0){
            right.add(root);
            return right;
        }

        return new ArrayList<>();

    }

    //all node at distance k from a particular node
    public List<TreeNode> rootToNodePath(TreeNode root, TreeNode target) {
        if (root == null)
            return new ArrayList<>();

        if (root == target) {
            List<TreeNode> ans = new ArrayList<>();
            ans.add(root);
            return ans;
        }

        List<TreeNode> left = rootToNodePath(root.left, target);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        List<TreeNode> right = rootToNodePath(root.right, target);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();

    }

    public void kDown(TreeNode root, int k, List<Integer> ans, TreeNode blocked) {
        if (root == null || k < 0 || root == blocked) {

            return;
        }

        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kDown(root.left, k - 1, ans, blocked);
        kDown(root.right, k - 1, ans, blocked);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        if (root == null || target == null)
            return new ArrayList<>();

        List<TreeNode> ans = rootToNodePath(root, target);

        for (TreeNode s : ans)
            System.out.println(s.val);

        List<Integer> finalAns = new ArrayList<>();
        if (k == 0) {
            finalAns.add(target.val);
            return finalAns;
        }
        TreeNode blocked=null;
        for (int i = 0; i < ans.size(); i++) {

            kDown(ans.get(i),k-i,finalAns,blocked);
            blocked=ans.get(i);
        }

        return finalAns;
    }

}
