package tree.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leetcode {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
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

    //leetcode 257

    //when using String builder in recursion usi setLength
    public void binaryTreePath(TreeNode root, StringBuilder currAns, List<String> ans) {
        if (root.left == null && root.right == null) {
            int len = currAns.length();
            currAns.append(root.val);
            ans.add(currAns.toString());
            currAns.setLength(len);
            return;
        }

        int len = currAns.length();
        currAns.append(root.val + "->");
        // currAns.append("->");

        if (root.left != null)
            binaryTreePath(root.left, currAns, ans);
        if (root.right != null)
            binaryTreePath(root.right, currAns, ans);

        currAns.setLength(len);

    }
/*
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        binaryTreePath(root, new StringBuilder(), ans);
        return ans;
    }

    public List<String> binaryTree(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        if (root.left == null && root.right == null) {
            List<String> ans = new ArrayList<>();
            String s = "" + root.val;
            ans.add(s);
            return ans;
        }

        List<String> ans = new ArrayList<>();
        List<String> left = binaryTree(root.left);
        List<String> right = binaryTree(root.right);

        for (String s : left) {
            String curr = root.val + "->" + s;
            ans.add(curr);
        }

        for (String s : right) {
            String curr = root.val + "->" + s;
            ans.add(curr);
        }

        return ans;

    }

    public List<String> binaryTreePaths(TreeNode root) {

        return binaryTree(root);
    }
    */


    //all single node parent

    public void exactlyOneChild(TreeNode root,ArrayList<Integer> ans) {
        if(root==null || (root.left == null && root.right == null)) {
            return;
        }


        //no returning from here
        if(root.left==null || root.right==null) {
            ans.add(root.val);
        }

        exactlyOneChild(root.left,ans);
        exactlyOneChild(root.right,ans);
    }

    //postorder
    public int exactlyOneChild(TreeNode root) {
        if(root==null || (root.left == null && root.right == null)) {
            return 0;
        }
        int leftSingleChildCount=exactlyOneChild(root.left);
        int rightSingleChildCount=exactlyOneChild(root.right);

        int ans=leftSingleChildCount+rightSingleChildCount;
        if(root.left==null || root.right==null) {
            ans++;
        }

        return ans;
    }

    public ArrayList<Integer> exactlyOneChildMain(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root,ans);
        return ans;
    }

    //leetcode 863
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

    public int distanceK(TreeNode root, TreeNode target, int k, List<Integer> finalAns) {
        if (root == null)
            return -1;

        if (root == target) {
            kDown(root, k, finalAns, null);
            return 1;
        }

        int leftDistance = distanceK(root.left, target, k, finalAns);
        if (leftDistance != -1) {
            kDown(root, k - leftDistance, finalAns, root.left);
            return leftDistance + 1;
        }

        int rightDistance = distanceK(root.right, target, k, finalAns);
        if (rightDistance != -1) {
            kDown(root, k - rightDistance, finalAns, root.right);
            return rightDistance + 1;
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        if (root == null || target == null)
            return new ArrayList<>();

        List<Integer> finalAns = new ArrayList<>();

        distanceK(root, target, k, finalAns);
        return finalAns;

    }

    //leetcode 2385
    int finalAns = -1;

    public int height(TreeNode root, TreeNode blocked) {
        if (root == null || root == blocked)
            return -1;

        int leftHeight = height(root.left, blocked);
        int rightHeight = height(root.right, blocked);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int amountOfTime1(TreeNode root, int start) {

        if (root == null)
            return -1;

        if (root.val == start) {
            int maxTime = height(root, null);
            finalAns = Math.max(maxTime, finalAns);
            return 1;
        }

        int leftSide = amountOfTime1(root.left, start);

        if (leftSide != -1) {
            int maxTime = height(root, root.left);
            finalAns = Math.max(maxTime + leftSide, finalAns);
            return leftSide + 1;
        }

        int rightSide = amountOfTime1(root.right, start);

        if (rightSide != -1) {
            int maxTime = height(root, root.right);
            finalAns = Math.max(maxTime + rightSide, finalAns);
            return rightSide + 1;
        }

        return -1;
    }

    public int amountOfTime(TreeNode root, int start) {

        amountOfTime1(root, start);
        return finalAns;
    }

    //leetcode 236
    public List<TreeNode> pathToNode(TreeNode root, TreeNode p) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root == p) {
            List<TreeNode> ans = new ArrayList<>();
            ans.add(p);
            return ans;
        }

        List<TreeNode> leftList = pathToNode(root.left, p);

        if (leftList.size() != 0) {
            leftList.add(root);
            return leftList;
        }

        List<TreeNode> rightList = pathToNode(root.right, p);

        if (rightList.size() != 0) {
            rightList.add(root);
            return rightList;
        }

        return new ArrayList<>();

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> list1 = pathToNode(root, p);
        List<TreeNode> list2 = pathToNode(root, q);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        TreeNode ans = root;

        while (i >= 0 && j >= 0) {
            if (list1.get(i) == list2.get(j)) {
                ans = list1.get(i);
                i--;
                j--;
            } else
                break;
        }

        return ans;
    }
/*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root==null)
            return null;


        if(root==p)
            return p;

        if(root==q)
            return q;


        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);


        if(left!=null && right!=null)
            return root;

        if(left!=null || right!=null)
        {
            if(left==null)
                return right;

            if(right==null)
                return left;
        }



        return null;

    }

    */

    //leetcode 701
    public TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) {
            TreeNode tree = new TreeNode(val);
            return tree;
        }

        if (root.val <= val) {
            root.right = insertIntoBST(root.right, val);
        } else
            root.left = insertIntoBST(root.left, val);

        return root;

    }


    //leetcode 450
    public TreeNode inorder(TreeNode root) {

        if (root == null)
            return null;

        if (root.left == null && root.right == null)
            return root;

        TreeNode ans = inorder(root.left);
        if (ans == null)
            return root;
        return ans;

    }

    // it is a leaf
    // one child
    // two child
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null)
            return root;

        if (root.val == key) {
            if (root.left == null && root.right == null)
                return null;

            if (root.left == null || root.right == null) {
                if (root.left == null)
                    return root.right;
                else
                    return root.left;
            } else {

                TreeNode succsoer = inorder(root.right);
                root.val = succsoer.val;

                root.right = deleteNode(root.right, succsoer.val);

                return root;

            }

        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else {
            root.right = deleteNode(root.right, key);
            return root;
        }
    }

    //leetcode 235
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {


        if(root==null)
            return root;


        // If both nodes are smaller than the root, LCA is in the left subtree
        if(root.val>p.val && root.val>q.val)
        {
            return lowestCommonAncestorBST(root.left,p,q);
        }


        // If both nodes are larger than the root, LCA is in the right subtree

        if(root.val<p.val && root.val<q.val)
            return lowestCommonAncestorBST(root.right,p,q);

        return root;
    }


    //morris inorder
    public TreeNode rightMostNode(TreeNode root, TreeNode curr) {



        while (root.right != null && root.right != curr) {
            root = root.right;
        }

        return root;
    }

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> ans = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            TreeNode leftNode = curr.left;

            if (leftNode == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightNode = rightMostNode(leftNode, curr);

                if (rightNode.right == null) {
                    rightNode.right = curr;
                    curr = curr.left;

                } else {
                    rightNode.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    //preorder
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> ans = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            TreeNode leftNode = curr.left;

            if (leftNode == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightNode = rightMostNode(leftNode, curr);

                if (rightNode.right == null) {
                    ans.add(curr.val);
                    rightNode.right = curr;
                    curr = curr.left;
                } else {
                    rightNode.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;

    }

    //leetcode 98
    public boolean isValidBST(TreeNode root) {

        long prev = (long) Integer.MIN_VALUE - 1;

        TreeNode curr = root;

        while (curr != null) {
            TreeNode leftNode = curr.left;

            if (leftNode == null) {
                if (curr.val <= prev)
                    return false;

                prev = curr.val;
                curr = curr.right;
            } else {
                TreeNode rightNode = rightMostNode(leftNode, curr);

                if (rightNode.right == null) {
                    rightNode.right = curr;
                    curr = curr.left;
                } else {
                    rightNode.right = null;
                    if (curr.val <= prev)
                        return false;

                    prev = curr.val;
                    curr = curr.right;
                }
            }
        }

        return true;
    }

    //leetcode 173
    class BSTIterator {

        LinkedList<TreeNode> stack;
        TreeNode root;

        public BSTIterator(TreeNode root) {
            this.root = root;
            stack = new LinkedList<>();
            initialize();
        }

        public int next() {

            TreeNode curr = stack.removeLast();
            int val = curr.val;

            curr = curr.right;

            while (curr != null) {
                stack.addLast(curr);
                curr = curr.left;
            }

            return val;
        }

        public boolean hasNext() {

            return stack.size() != 0;
        }

        private void initialize() {

            TreeNode curr = root;

            while (curr != null) {
                stack.addLast(curr);
                curr = curr.left;
            }
        }
    }

    /*
      //bst iterater using moris
      class BSTIterator {

    public TreeNode rightMostNode(TreeNode root, TreeNode curr) {

        while (root.right != null && root.right != curr) {
            root = root.right;
        }

        return root;
    }

    TreeNode root;

    public BSTIterator(TreeNode root) {

        this.root = root;

    }

    public int next() {

        int val = 0;

        while (root != null) {
            TreeNode leftNode = root.left;
            if (leftNode == null) {
                val = root.val;
                root = root.right;
                break;
            } else {
                TreeNode rightNode = rightMostNode(leftNode, root);

                if (rightNode.right == null) {
                    rightNode.right = root;
                    root = root.left;
                } else {
                    val = root.val;
                    rightNode.right = null;
                    root = root.right;
                    break;
                }
            }
        }

        return val;
    }

    public boolean hasNext() {

        return root != null;
    }

    private void intialize() {
        TreeNode curr = root;

    }
}
     */

    //leetcode 109
    public ListNode middleOfLinkedList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;

        }

        if (prev != null) {

            prev.next = null; // Split the list here
        }

        return slow;
    }



    public TreeNode sortedListToBST(ListNode head) {

        if (head == null)
            return null;

        if (head.next == null)
            return new TreeNode(head.val);



        // System.out.println(head.val);
        ListNode midNode = middleOfLinkedList(head);

        ListNode rightHead = midNode.next;
        ListNode leftNode = (midNode == head) ? null : head;
        TreeNode root = new TreeNode(midNode.val);

        root.left = sortedListToBST(leftNode);
        root.right = sortedListToBST(rightHead);

        return root;

    }

    //leetcode 1008
    int bstIndex = 0;

    public TreeNode bstFromPreorder(int[] preorder, int minElement, int maxElement) {
        if (bstIndex >= preorder.length || preorder[bstIndex] < minElement || preorder[bstIndex] > maxElement)
            return null;

        int val = preorder[bstIndex++];
        TreeNode root = new TreeNode(val);

        root.left = bstFromPreorder(preorder, minElement, val);
        root.right = bstFromPreorder(preorder, val, maxElement);

        return root;

    }

    public TreeNode bstFromPreorder(int[] preorder) {

        return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9);
    }


    //leetcode 449
    public class Codec {

        private TreeNode rightMostNode(TreeNode root, TreeNode curr) {

            while (root.right != null && root.right != curr) {
                root = root.right;
            }

            return root;
        }

        private int bstIndex = 0;

        private TreeNode bstFromPreorder(int[] preorder, int minElement, int maxElement) {
            if (bstIndex >= preorder.length || preorder[bstIndex] < minElement || preorder[bstIndex] > maxElement)
                return null;

            int val = preorder[bstIndex++];
            TreeNode root = new TreeNode(val);

            root.left = bstFromPreorder(preorder, minElement, val);
            root.right = bstFromPreorder(preorder, val, maxElement);

            return root;

        }

        private TreeNode bstFromPreorder(int[] preorder) {

            return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder ans = new StringBuilder();

            TreeNode curr = root;
            while (curr != null) {
                TreeNode leftNode = curr.left;

                if (leftNode == null) {
                    ans.append(curr.val).append(" ");
                    curr = curr.right;
                } else {
                    TreeNode rightNode = rightMostNode(leftNode, curr);
                    if (rightNode.right == null) {
                        rightNode.right = curr;
                        ans.append(curr.val).append(" ");
                        curr = curr.left;
                    } else {
                        rightNode.right = null;
                        curr = curr.right;
                    }
                }

            }
            return ans.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            if (data.length() == 0)
                return null;

            String[] ans = data.split(" ");
            int[] preorder = new int[ans.length];

            int index = 0;
            for (String a : ans) {
                preorder[index++] = Integer.parseInt(a);
            }
            return bstFromPreorder(preorder);
        }
    }

    //leetcode 331
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.isEmpty()) {
            return true;
        }

        String[] ans = preorder.split(",");

        int slot = 1;

        for (String s : ans) {
            slot--;

            if (slot < 0)
                return false;

            if (!s.equals("#"))
                slot += 2;

        }

        return slot == 0;
    }

    //leetcode 105
    public TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int si, int se) {
        if (si > se)
            return null;

        int idx = si;

        TreeNode root = new TreeNode(preorder[psi]);
        while (inorder[idx] != preorder[psi]) {
            idx++;
        }

        int count = idx - si;

        // psi+1, psi+count
        // si,idx-1

        // psicount+1,pei
        // idx+1,se

        root.left = buildTree(preorder, psi + 1, psi + count, inorder, si, idx - 1);
        root.right = buildTree(preorder, psi + count + 1, pei, inorder, idx + 1, se);

        return root;

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        int psi = 0;
        int pei = n - 1;

        int si = 0;
        int se = n - 1;

        return buildTree(preorder, psi, pei, inorder, si, se);
    }

    /*
    //leetcode 106
    public TreeNode buildTree(int[] inorder, int si, int se, int[] postorder, int psi, int pse) {
        if (si > se)
            return null;

        int idx = si;

        TreeNode root = new TreeNode(postorder[pse]);
        while (inorder[idx] != postorder[pse]) {
            idx++;
        }

        int count = idx - si;

        // left
        // si,idx-1
        // psi,psi+count-1

        // right
        // idx+1,ei
        // psi+count+1,pei-1

        root.left = buildTree(inorder, si, idx - 1, postorder, psi, psi + count - 1);
        root.right = buildTree(inorder, idx + 1, se, postorder, psi + count, pse - 1);

        return root;

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        int n = inorder.length;
        int si = 0;
        int se = n - 1;

        int psi = 0;
        int pse = n - 1;

        return buildTree(inorder, si, se, postorder, psi, pse);
    }
    */


    //leetcode 297
    public class Codec {

        StringBuilder sb;

        void serilaizeHelper(TreeNode root) {
            if (root == null) {
                sb.append("#").append(",");
                return;

            }

            sb.append(root.val).append(",");

            serilaizeHelper(root.left);
            serilaizeHelper(root.right);

        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            sb = new StringBuilder();

            serilaizeHelper(root);

            return sb.toString();
        }

        // Decodes your encoded data to tree.

        private TreeNode deserializeHelper(String[] arr, int[] index) {
            if (index[0] >= arr.length || arr[index[0]].equals("#")) {
                index[0]++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(arr[index[0]]));
            index[0]++;

            root.left = deserializeHelper(arr, index);
            root.right = deserializeHelper(arr, index);

            return root;
        }

        public TreeNode deserialize(String data) {

            String[] arr = data.split(",");

            return deserializeHelper(arr, new int[] { 0 });

        }
    }

    //leetcode 110
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        if (leftHeight == -1) return -1;

        int rightHeight = height(root.right);
        if (rightHeight == -1) return -1;

        // If difference in height is more than 1, return -1 to indicate unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // Return actual height
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
