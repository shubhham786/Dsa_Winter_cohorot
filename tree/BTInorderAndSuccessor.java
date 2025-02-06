package tree;

public class BTInorderAndSuccessor {

    static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }
    public static BinaryTreeNode<Integer> rightMostNode(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> curr) {

        while (root.right != null && root.right != curr) {
            root = root.right;
        }

        return root;
    }

    public static BinaryTreeNode<Integer> inorderSuccesor(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node) {
        // Write you code here.

        BinaryTreeNode<Integer> pred = null;
        BinaryTreeNode<Integer> succ = null;
        boolean found = false;

        while (root != null) {
            BinaryTreeNode<Integer> leftNode = root.left;
            if (leftNode == null) {
                if (found && succ == null) {
                    succ = root;
                    break;
                }

                if (root.data == node.data)
                    found = true;

                if (!found)
                    pred = root;

                root = root.right;
            } else {
                BinaryTreeNode<Integer> rightNode = rightMostNode(leftNode, root);

                if (rightNode.right == null) {
                    rightNode.right = root;
                    root = root.left;
                } else {
                    rightNode.right = null;
                    if (found && succ == null) {
                        succ = root;
                        break;
                    }

                    if (root.data == node.data)
                        found = true;

                    if (!found)
                        pred = root;

                    root = root.right;

                }

            }
        }

        return succ;
    }
}
