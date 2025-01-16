package tree.practice;

public class BSTtoDLL {

    static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static TreeNode<Integer> rightMostNode(TreeNode<Integer> root, TreeNode<Integer> curr) {
        while (root.right != null && root.right != curr) {
            root = root.right;
        }
        return root;
    }

    public static TreeNode<Integer> bstToSortedDLL(TreeNode<Integer> root) {
        TreeNode<Integer> prev;
        TreeNode<Integer> head = new TreeNode<>(-1);

        prev = head;

        while (root != null) {
            TreeNode<Integer> leftNode = root.left;

            if (leftNode == null) {
                prev.right = root;
                root.left = prev;
                prev = root;
                root = root.right;
            } else {
                TreeNode<Integer> rightNode = rightMostNode(leftNode, root);

                if (rightNode.right == null) {
                    rightNode.right = root;
                    root = root.left;
                } else {
                    rightNode.right = null;
                    prev.right = root;
                    root.left = prev;
                    prev = root;
                    root = root.right;
                }
            }
        }

        TreeNode<Integer>head1=head.right;
        head1.left=null;
        head.right=null;

        return head1;

    }

    public static void main(String[] args) {
        // Construct the BST based on the given input
        TreeNode<Integer> root = new TreeNode<>(50);
        root.left = new TreeNode<>(30);
        root.right = new TreeNode<>(70);
        root.left.left = new TreeNode<>(20);
        root.left.right = new TreeNode<>(40);
        root.right.left = new TreeNode<>(60);
        root.right.right = new TreeNode<>(80);

        // Convert the BST to a Sorted Doubly Linked List
        TreeNode<Integer> head = bstToSortedDLL(root);

        // Debug and print the sorted DLL
        System.out.println("Sorted Doubly Linked List:");
        TreeNode<Integer> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.right;
        }
        System.out.println();

        // Optional: Print the DLL in reverse to ensure links are correct
        System.out.println("DLL in reverse:");
        TreeNode<Integer> tail = head;
        while (tail != null && tail.right != null) {
            tail = tail.right;
        }
        while (tail != null) {
            System.out.print(tail.data + " ");
            tail = tail.left;
        }
        System.out.println();
    }
}
