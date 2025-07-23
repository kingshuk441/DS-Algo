package trees;

import com.sun.source.tree.Tree;

import java.util.*;

public class BT {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> ntrp(TreeNode root, TreeNode target) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root == target) {
            List<TreeNode> bAns = new ArrayList<>();
            bAns.add(root);
            return bAns;
        }
        List<TreeNode> leftPath = ntrp(root.left, target);
        if (!leftPath.isEmpty()) {
            leftPath.add(root);
            return leftPath;
        }
        List<TreeNode> rightPath = ntrp(root.right, target);
        if (!rightPath.isEmpty()) {
            rightPath.add(root);
            return rightPath;
        }
        return new ArrayList<>();

    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        distanceK(root, target, k, ans);
        return ans;
    }

    public List<Integer> distanceK_2(TreeNode root, TreeNode target, int k) {
        List<TreeNode> ntrp = ntrp(root, target);
        List<Integer> ans = new ArrayList<>();
        TreeNode blocker = null;
        for (int i = 0; i < ntrp.size(); i++) {
            kdown(ntrp.get(i), k - i, blocker, ans);
            blocker = ntrp.get(i);
        }
        return ans;
    }

    private int distanceK(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == null) return -1;
        if (root == target) {
            kdown(root, k, null, ans);
            return 0;
        }
        int lAns = distanceK(root.left, target, k, ans);
        if (lAns != -1) {
            kdown(root, k - lAns - 1, root.left, ans);
            return lAns + 1;
        }
        int rAns = distanceK(root.right, target, k, ans);
        if (rAns != -1) {
            kdown(root, k - rAns - 1, root.right, ans);
            return rAns + 1;
        }
        return -1;

    }

    private void kdown(TreeNode root, int k, TreeNode blockNode, List<Integer> ans) {
        if (root == null || root == blockNode || k < 0) return;
        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kdown(root.left, k - 1, blockNode, ans);
        kdown(root.right, k - 1, blockNode, ans);
    }

    TreeNode LCANode = null;

    private boolean LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean selfCheck = false;
        if (LCANode != null) return false;
        if (root == p || root == q) selfCheck = true;
        boolean left = LCA(root.left, p, q);
        if (selfCheck && left) {
            LCANode = root;
            return true;
        }
        boolean right = LCA(root.right, p, q);
        if (selfCheck && right) {
            LCANode = root;
            return true;
        }
        if (left && right) {
            LCANode = root;
            return true;
        }
        return left || right || selfCheck;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LCA(root, p, q);
        return LCANode;

    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) return null;
        return root;
    }

    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        return buildTreePreIn(preorder, inorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode buildTreePreIn(int[] preorder, int[] inorder, int psi, int pei, int isi, int iei) {
        if (psi > pei) return null;
        TreeNode root = new TreeNode(preorder[psi]);
        int idx = isi;
        while (idx <= iei && preorder[psi] != inorder[idx]) {
            idx++;
        }
        int totalElements = idx - isi;
        root.left = buildTreePreIn(preorder, inorder, psi + 1, psi + totalElements, isi, idx - 1);
        root.right = buildTreePreIn(preorder, inorder, psi + totalElements + 1, pei, idx + 1, iei);
        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructFromPrePost(preorder, postorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode constructFromPrePost(int[] preorder, int[] postorder, int psi, int pei, int posi, int poei) {
        if (psi > pei) return null;
        TreeNode root = new TreeNode(preorder[psi]);
        if (psi == pei)
            return root;

        int idx = posi;
        while (idx <= poei && postorder[idx] != preorder[psi + 1]) {
            idx++;
        }
        int totalElements = idx - posi + 1;
        root.left = constructFromPrePost(preorder, postorder, psi + 1, psi + totalElements, posi, idx);
        root.right = constructFromPrePost(preorder, postorder, psi + totalElements + 1, pei, idx + 1, poei - 1);
        return root;
    }


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return constructFromInPost(inorder, postorder, 0, inorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode constructFromInPost(int[] inorder, int[] postorder, int isi, int iei, int psi, int pei) {
        if (psi > pei) return null;
        TreeNode root = new TreeNode(postorder[pei]);
        int idx = isi;
        while (idx <= iei && postorder[pei] != inorder[idx]) {
            idx++;
        }
        int totalElements = idx - isi;
        root.left = constructFromInPost(inorder, postorder, isi, idx - 1, psi, psi + totalElements - 1);
        root.right = constructFromInPost(inorder, postorder, idx + 1, iei, psi + totalElements, pei - 1);
        return root;
    }

    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<Node> que = new ArrayDeque<>();
        que.add(root);
        while (que.size() > 0) {
            int size = que.size();
            ans.add(que.peek().data);
            while (size-- > 0) {
                Node child = que.remove();
                if (child.left != null) que.add(child.left);
                if (child.right != null) que.add(child.right);
            }
        }
        return ans;
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> que = new ArrayDeque<>();
        que.add(root);
        List<Integer> ans = new ArrayList<>();
        while (que.size() > 0) {
            int size = que.size();
            TreeNode firstNode = que.peek();
            ans.add(firstNode.val);
            while (size-- > 0) {
                TreeNode rem = que.remove();
                if (rem.right != null)
                    que.add(rem.right);
                if (rem.left != null)
                    que.add(rem.left);
            }
        }
        return ans;
    }

    // 0 -> min
    public int width(TreeNode root) {
        int minMax[] = new int[2];
        width(root, minMax, 0);
        return minMax[1] - minMax[0] + 1;
    }

    private void width(TreeNode root, int[] minMax, int verticalLevel) {
        if (root == null) return;
        minMax[0] = Math.min(minMax[0], verticalLevel);
        minMax[1] = Math.max(minMax[1], verticalLevel);
        width(root.left, minMax, verticalLevel - 1);
        width(root.right, minMax, verticalLevel + 1);
    }

    class VerticalPair {
        int hl;
        int vl;
        TreeNode node;

        public VerticalPair() {

        }

        public VerticalPair(TreeNode root, int hl, int vl) {
            this.vl = vl;
            this.hl = hl;
            this.node = root;
        }
    }

    public void verticalLevelOrder(TreeNode root) {
        Queue<VerticalPair> que = new ArrayDeque<>();

        List<List<Integer>> ans = new ArrayList<>();
        int minMax[] = new int[2];
        width(root, minMax, 0);
        int w = minMax[1] - minMax[0] + 1;
        int shift = -minMax[0];
        que.add(new VerticalPair(root, 0, shift));
        for (int i = 0; i < w; i++) ans.add(new ArrayList<>());

        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                VerticalPair pair = que.remove();
                TreeNode node = pair.node;
                int hl = pair.hl;
                int vl = pair.vl;
                ans.get(vl).add(node.val);
//                ans.get(vl+shift).add(node.val);
                if (node.left != null) que.add(new VerticalPair(node.left, hl + 1, vl - 1));
                if (node.right != null) que.add(new VerticalPair(node.right, hl + 1, vl + 1));
            }
        }
        System.out.println(ans);
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        Queue<VerticalPair> que = new ArrayDeque<>();

        ArrayList<Integer> ans = new ArrayList<>();
        int minMax[] = new int[2];
        width(root, minMax, 0);
        int shift = -minMax[0];
        int w = minMax[1] - minMax[0] + 1;
        que.add(new VerticalPair(root, 0, shift));
        for (int i = 0; i < w; i++) ans.add(0);


        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                VerticalPair pair = que.remove();
                TreeNode node = pair.node;
                int hl = pair.hl;
                int vl = pair.vl;
                ans.set(vl, node.val);
                if (node.left != null) que.add(new VerticalPair(node.left, hl + 1, vl - 1));
                if (node.right != null) que.add(new VerticalPair(node.right, hl + 1, vl + 1));
            }
        }
        return ans;

    }

    public ArrayList<Integer> verticalSum(TreeNode root) {
        Queue<VerticalPair> que = new ArrayDeque<>();

        ArrayList<Integer> ans = new ArrayList<>();
        int minMax[] = new int[2];
        width(root, minMax, 0);
        int shift = -minMax[0];
        int w = minMax[1] - minMax[0] + 1;
        que.add(new VerticalPair(root, 0, shift));
        for (int i = 0; i < w; i++) ans.add(0);


        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                VerticalPair pair = que.remove();
                TreeNode node = pair.node;
                int hl = pair.hl;
                int vl = pair.vl;
                ans.set(vl, ans.get(vl) + node.val);
                if (node.left != null) que.add(new VerticalPair(node.left, hl + 1, vl - 1));
                if (node.right != null) que.add(new VerticalPair(node.right, hl + 1, vl + 1));
            }
        }
        return ans;
    }

    ArrayList<Integer> topView(TreeNode root) {
        Queue<VerticalPair> que = new ArrayDeque<>();

        ArrayList<Integer> ans = new ArrayList<>();
        int minMax[] = new int[2];
        width(root, minMax, 0);
        int shift = -minMax[0];
        int w = minMax[1] - minMax[0] + 1;
        que.add(new VerticalPair(root, 0, shift));
        for (int i = 0; i < w; i++) ans.add(null);


        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                VerticalPair pair = que.remove();
                TreeNode node = pair.node;
                int hl = pair.hl;
                int vl = pair.vl;
                if (ans.get(vl) == null)
                    ans.set(vl, node.val);
                if (node.left != null) que.add(new VerticalPair(node.left, hl + 1, vl - 1));
                if (node.right != null) que.add(new VerticalPair(node.right, hl + 1, vl + 1));
            }
        }
        return ans;
    }

    int maxPathSumLeaf(TreeNode root) {
        int res[] = maxPathSumNodeToLeaf(root);
        if (res[1] == -(int) 1e8) return res[0];
        return res[1];
    }

    //{ntl,ltl}
    private int[] maxPathSumNodeToLeaf(TreeNode root) {
        if (root == null) {
            return new int[]{-(int) 1e8, -(int) 1e8};
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val, -(int) 1e8};
        }
        int[] leftRes = maxPathSumNodeToLeaf(root.left);
        int[] rightRes = maxPathSumNodeToLeaf(root.right);
        int myAns[] = new int[2];
        myAns[1] = Math.max(leftRes[1], rightRes[1]);
        if (root.left != null && root.right != null) {
            myAns[1] = Math.max(myAns[1], leftRes[0] + rightRes[0] + root.val);
        }
        myAns[0] = Math.max(leftRes[0], rightRes[0]) + root.val;
        return myAns;
    }


    public int maxPathSumNode(TreeNode root) {
        return maxPathSumNodeToNode(root)[1];
    }

    //{ntl,ntn}
    private int[] maxPathSumNodeToNode(TreeNode root) {
        if (root == null) {
            return new int[]{-(int) 1e8, -(int) 1e8};
        }
        int[] leftRes = maxPathSumNodeToNode(root.left);
        int[] rightRes = maxPathSumNodeToNode(root.right);
        int[] res = new int[2];
        int maxNodeToLeafSum = Math.max(root.val, Math.max(leftRes[0] + root.val, rightRes[0] + root.val));
        int maxNodeToNodeSum = Math.max(leftRes[0] + rightRes[0] + root.val, Math.max(maxNodeToLeafSum, Math.max(leftRes[1], rightRes[1])));
        res[0] = maxNodeToLeafSum;
        res[1] = maxNodeToNodeSum;
        return res;
    }

    class BalanceBstPair {
        int height;
        boolean isBalanced;

        BalanceBstPair() {
            this.height = -1;
            this.isBalanced = true;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return isBalanced_(root).isBalanced;
    }

    public BalanceBstPair isBalanced_(TreeNode root) {
        if (root == null)
            return new BalanceBstPair();
        BalanceBstPair left = isBalanced_(root.left);
        BalanceBstPair right = isBalanced_(root.right);
        BalanceBstPair myRes = new BalanceBstPair();
        myRes.isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        myRes.height = Math.max(left.height, right.height) + 1;
        return myRes;
    }

    public void morrisInorderTraversal(TreeNode root, List<Integer> ans) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) {
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    ans.add(curr.val);
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public void morrisPreorderTraversal(TreeNode root, List<Integer> ans) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) {
                    ans.add(curr.val);
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        morrisInorderTraversal(root, ans);
        return ans;
    }

    Node pred = null, succ = null;

    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> ans = new ArrayList<>();
        predSuccBT(root, key, ans);
        return ans;

    }

    private boolean predSuccBT(Node root, int key, ArrayList<Node> ans) {
        if (root == null) return false;
        if (predSuccBT(root.left, key, ans)) return true;
        if (root.data == key) {
            ans.add(pred);
        }
        if (pred != null && pred.data == key) {
            succ = root;
            ans.add(succ);
            return true;
        }
        pred = root;
        if (predSuccBT(root.right, key, ans)) return true;
        return false;
    }

    TreeNode bToDLL(TreeNode root) {
        TreeNode curr = root;
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                //print
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) {
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    //print
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;

                    curr = curr.right;
                }
            }
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        return head;

    }

    public void flatten(TreeNode root) {
        flatten_(root);
    }

    private TreeNode flatten_(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode leftTail = flatten_(root.left);
        TreeNode rightTail = flatten_(root.right);
        if (leftTail != null) {
            leftTail.right = root.right;
            TreeNode left = root.left;
            root.left = null;
            root.right = left;
        }
        return rightTail != null ? rightTail : leftTail;
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root == null) return "";
            serialize_(root, sb);
            return sb.toString();
        }

        private void serialize_(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("# ");
                return;
            }
            sb.append(root.val).append(" ");
            serialize_(root.left, sb);
            serialize_(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            System.out.println(data);
            if (data == null || data.isEmpty()) {
                return null;
            }
            String[] arr = data.split(" ");
            return deserialize(arr);
        }

        int idx = 0;

        private TreeNode deserialize(String[] arr) {
            if (arr[idx].equals("#")) {
                idx++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(arr[idx++]));
            root.left = deserialize(arr);
            root.right = deserialize(arr);
            return root;
        }
    }

    public Node connect(Node root) {
        if (root == null)
            return root;
        Node curr = root, first = root.left;
        while (first != null) {
            curr.left.next = curr.right;
            if (curr.next == null) {
                curr = first;
                first = curr.left;
            } else {
                curr.right.next = curr.next.left;
                curr = curr.next;
            }
        }
        return root;

    }

    public Node connect2(Node root) {
        Node curr = root, head = null, prev = null;
        while (curr != null) {
            while (curr != null) {
                if (curr.left != null) {
                    if (prev == null) {
                        head = curr.left;
                    } else {
                        prev.next = curr.left;
                    }
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev == null) {
                        head = curr.right;
                    } else {
                        prev.next = curr.right;
                    }
                    prev = curr.right;
                }
                curr = curr.next;
            }
            curr = head;
            head = prev = null;
        }
        return root;
    }

    int moves = 0;

    public int distributeCoins(TreeNode root) {
        distributeCoins_(root);
        return moves;
    }

    private int distributeCoins_(TreeNode root) {
        if (root == null) return 0;
        int left = distributeCoins_(root.left);
        int right = distributeCoins_(root.right);
        moves = moves + Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
    }

    int cameras = 0;

    public int minCameraCover(TreeNode root) {
        cameras = 0;
        int forRoot = minCameraCover_(root);
        if (forRoot == 1) {
            cameras++;
        }
        return cameras;
    }

    // -1-> not required , 0->i m camera, 1-> i m not covered
    private int minCameraCover_(TreeNode root) {
        if (root == null) return -1;
        int left = minCameraCover_(root.left);
        int right = minCameraCover_(root.right);
        if (left == 1 || right == 1) {
            cameras++;
            return 0;
        } else if (left == 0 || right == 0) {
            return -1;
        }
        return 1;
    }
}
