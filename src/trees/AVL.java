package trees;

import java.util.ArrayList;
import java.util.Arrays;

public class AVL {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int balancingFactor;
        int height;

        TreeNode(int x) {
            val = x;
        }
    }

    public void updateHeightAndBalancingFactor(TreeNode root) {
        int lh = -1, rh = -1;
        if (root.left != null) lh = root.left.height;
        if (root.right != null) rh = root.right.height;
        root.height = Math.max(rh, lh) + 1;
        root.balancingFactor = lh - rh;
    }

    public TreeNode leftRotate(TreeNode A) {
        TreeNode B = A.right;
        TreeNode Bleft = B.left;
        B.left = A;
        A.right = Bleft;
        updateHeightAndBalancingFactor(A);
        updateHeightAndBalancingFactor(B);
        return B;
    }

    public TreeNode rightRotate(TreeNode A) {
        TreeNode B = A.left;
        TreeNode Bright = B.right;
        B.right = A;
        A.left = Bright;
        updateHeightAndBalancingFactor(A);
        updateHeightAndBalancingFactor(B);
        return B;
    }

    public TreeNode balanceBST_AVL(TreeNode root) {
        updateHeightAndBalancingFactor(root);
        //rotate when skewed
        if (root.balancingFactor == 2) { //left-left , left-right
            if (root.left.balancingFactor == 1) { // left-left
                return rightRotate(root);
            } else { // left-right
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        } else if (root.balancingFactor == -2) { // right-right, right-left
            if (root.right.balancingFactor == -1) {
                return leftRotate(root);//right-right
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);//right-left
            }
        }
        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return balanceBST_AVL(root);
    }

    private TreeNode getRightMost(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) return null;
            else if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            } else {
                TreeNode rightMost = getRightMost(root.left);
                root.val = rightMost.val;
                root.left = deleteNode(root.left, rightMost.val);
            }
        }
        return balanceBST_AVL(root);
    }

    //===============================
    public TreeNode leftRotateAVL(TreeNode A) {
        TreeNode B = A.right;
        TreeNode Bleft = B.left;
        B.left = A;
        A.right = Bleft;
        B.left = balanceBST_AVL2(A);
        return balanceBST_AVL2(B);
    }

    public TreeNode rightRotateAVL(TreeNode A) {
        TreeNode B = A.left;
        TreeNode Bright = B.right;
        B.right = A;
        A.left = Bright;
        B.right = balanceBST_AVL2(A);
        return balanceBST_AVL2(B);
    }

    int height[];

    public TreeNode balanceBST(TreeNode root) {
        height = new int[(int) 1e6];
        Arrays.fill(height, -1);
        return constructAVL(root);
    }

    public int getBF(TreeNode root) {
        int lh = -1, rh = -1;
        if (root.left != null) lh = height[root.left.val];
        if (root.right != null) rh = height[root.right.val];

        return lh - rh;
    }

    public TreeNode balanceBST_AVL2(TreeNode root) {
        updateHeightArray(root);
        //rotate when skewed
        if (getBF(root) >= 2) { //left-left , left-right
            if (getBF(root.left) >= 1) { // left-left
                return rightRotateAVL(root);
            } else { // left-right
                root.left = leftRotateAVL(root.left);
                return rightRotateAVL(root);
            }
        } else if (getBF(root) <= -2) { // right-right, right-left
            if (getBF(root.right) <= -1) {
                return leftRotateAVL(root);//right-right
            } else {
                root.right = rightRotateAVL(root.right);
                return leftRotateAVL(root);//right-left
            }
        }
        return root;
    }

    private void updateHeightArray(TreeNode root) {
        int lh = -1, rh = -1;
        if (root.left != null) lh = height[root.left.val];
        if (root.right != null) rh = height[root.right.val];
        height[root.val] = Math.max(rh, lh) + 1;
    }

    private TreeNode constructAVL(TreeNode root) {
        if (root == null) return null;
        root.left = constructAVL(root.left);
        root.right = constructAVL(root.right);
        return balanceBST_AVL2(root);
    }
}
