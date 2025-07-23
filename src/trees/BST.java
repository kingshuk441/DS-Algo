package trees;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Stack;

public class BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildBST(int arr[], int si, int ei) {
        if (si > ei) return null;
        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = buildBST(arr, si, mid - 1);
        root.right = buildBST(arr, mid + 1, ei);

        return root;
    }

    public TreeNode find(TreeNode root, int tar) {
        while (root != null) {
            if (root.val == tar) return root;
            if (tar < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
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
        return root;
    }

    private TreeNode getRightMost(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public boolean findTarget(TreeNode curr, TreeNode root, int sum) {
        if (curr == null) return false;
        boolean res = false;
        res = res || findTarget(curr.left, root, sum);
        //inorder
        int remainingSum = sum - curr.val;
        TreeNode isFound = null;
        if (remainingSum > curr.val) {
            isFound = find(root, remainingSum);
        }
        if (isFound != null) return true;
        res = res || findTarget(curr.right, root, sum);
        return res;
    }

    public boolean findTarget(TreeNode root, int sum) {
        return findTarget(root, root, sum);
    }

    public TreeNode bstFromPreorder(int[] preorder, int lr, int rr, int idx[]) {
        int i = idx[0];
        if (i >= preorder.length) return null;
        int val = preorder[i];
        if (val > lr && val < rr) {
            TreeNode root = new TreeNode(val);
            idx[0]++;
            root.left = bstFromPreorder(preorder, lr, val, idx);
            root.right = bstFromPreorder(preorder, val, rr, idx);
            return root;
        } else {
            return null;
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, Integer.MIN_VALUE, Integer.MIN_VALUE, new int[]{0});
    }

    class BSTPair {
        long max;
        long min;
        boolean isBst;
        long sum;
        long maxSum;

        public BSTPair(long max, long min, boolean isBst) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        }

        public BSTPair(long max, long min, boolean isBst, long sum, long maxSum) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
            this.sum = sum;
            this.maxSum = maxSum;
        }

        public BSTPair() {

        }
    }


    public BSTPair isValidBSTBSTNode(TreeNode root) {
        if (root == null) {
            return new BSTPair(Long.MIN_VALUE, Long.MAX_VALUE, true);
        }
        BSTPair leftRes = isValidBSTBSTNode(root.left);
        BSTPair rightRes = isValidBSTBSTNode(root.right);
        BSTPair myRes = new BSTPair();
        if (!leftRes.isBst || !rightRes.isBst || leftRes.max >= root.val || root.val >= rightRes.min) {
            return new BSTPair(-1, -1, false);
        }
        return new BSTPair(Math.max(rightRes.max, root.val), Math.min(leftRes.min, root.val), true);

    }

    public boolean isValidBST(TreeNode root) {
        return isValidBSTBSTNode(root).isBst;
    }

    TreeNode prev;

    public boolean isValidBST_2(TreeNode root) {
        prev = null;
        return isValidBST_inorder(root);
    }

    private boolean isValidBST_inorder(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST_inorder(root.left);
        if (!left) return false;
        if (prev == null) {
            prev = root;
        } else if (prev.val >= root.val) {
            return false;
        }
        prev = root;
        boolean right = isValidBST_inorder(root.right);
        return right;
    }

    public int maxSumBST(TreeNode root) {
        return (int) maxSumBSTNode(root).maxSum;
    }

    private BSTPair maxSumBSTNode(TreeNode root) {
        if (root == null) {
            return new BSTPair(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0, 0);
        }
        BSTPair leftRes = maxSumBSTNode(root.left);
        BSTPair rightRes = maxSumBSTNode(root.right);
        BSTPair myRes = new BSTPair();
        if (leftRes.isBst && rightRes.isBst && leftRes.max < root.val && root.val < rightRes.min) {
            myRes.isBst = true;
            myRes.max = Math.max(rightRes.max, root.val);
            myRes.min = Math.min(leftRes.min, root.val);
            myRes.sum = leftRes.sum + rightRes.sum + root.val;
            myRes.maxSum = Math.max(leftRes.maxSum, Math.max(rightRes.maxSum, myRes.sum));
        } else {
            myRes.maxSum = Math.max(leftRes.maxSum, rightRes.maxSum);
        }
        return myRes;
    }

    TreeNode a, b;

    public void recoverTree(TreeNode root) {
        prev = a = b = null;
        recoverTree_(root);
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    private boolean recoverTree_(TreeNode root) {
        if (root == null) return false;
        if (recoverTree_(root.left)) return true;
        if (prev != null && prev.val > root.val) {
            b = root;
            if (a == null) {
                a = prev;
            }
        }
        prev = root;
        if (recoverTree_(root.right)) return true;

        return false;

    }

    class BSTIterator {
        private Stack<TreeNode> st;

        public BSTIterator(TreeNode root) {
            st = new Stack<>();

            addAllLeft(root);
        }

        private void addAllLeft(TreeNode root) {
            while (root != null) {
                st.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode node = st.pop();
            addAllLeft(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !st.isEmpty();
        }
    }

    private Node getRightMost(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    private Node getLeftMost(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // also ceil floor
    public ArrayList<Node> findPreSuc(Node root, int key) {
        Node pred = null, succ = null;
        ArrayList<Node> ans = new ArrayList<>();
        while (root != null) {
            if (key < root.data) {
                succ = root;
                root = root.left;
            } else if (key > root.data) {
                pred = root;
                root = root.right;
            } else {
                if (root.left != null) {
                    pred = getRightMost(root.left);
                }
                if (root.right != null) {
                    succ = getLeftMost(root.right);
                }
                break;
            }
        }
        ans.add(pred);
        ans.add(succ);

        return ans;

    }


}
