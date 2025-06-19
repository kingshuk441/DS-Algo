package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Node {
    int data;
    List<Node> children;

    Node(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}

public class GT {


    public Node lineriseGT(Node root) {
        if (root.children.size() == 0) return root;
        lineriseGT(root.children.getLast());
        int size = root.children.size();
        for (int i = size - 2; i >= 0; i--) {
            Node child = root.children.get(i);
            lineriseGT(child);
            Node tail = getTail(child);
            tail.children.add(root.children.get(i + 1));
            root.children.removeLast();
        }

        return root;
    }

    public Node lineriseGT2(Node root) {
        if (root.children.isEmpty()) return root;
        Node lastChild = root.children.get(root.children.size() - 1);
        Node lastChildTail = lineriseGT2(lastChild);
        for (int i = root.children.size() - 2; i >= 0; i--) {
            Node child = root.children.get(i);
            Node childTail = lineriseGT2(child);
            childTail.children.add(lastChild);
            root.children.removeLast();
            lastChild = child;
        }
        return lastChildTail;
    }

    public Node getTail(Node root) {
        Node temp = root;
        while (temp.children.size() > 0) {
            temp = temp.children.get(0);
        }

        return temp;
    }


    public void displayTree(Node root) {
        System.out.print(root.data + " -> ");

        for (Node child : root.children) {
            System.out.print(child.data + " ,");
        }

        System.out.println(".");

        // asking the recursive function to print subTrees
        for (Node child : root.children) {
            displayTree(child);
        }
    }

    public Node constructGTStack(int arr[]) {
        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int ele = arr[i];
            if (ele == -1) {
                st.pop();
            } else {
                Node nn = new Node(ele);
                if (!st.isEmpty())
                    st.peek().children.add(nn);
                st.push(nn);
            }
        }
        System.out.println(st.size());
        return st.pop();
    }

    public static void main(String[] args) {
//        int[] treeData = {10, 20, 50, -1, -1, 30, 60, -1, 70, 110, -1, 120, -1, -1, 80, -1, -1, 40, 90, -1, 100};
        // int[] treeData = {10,20,50,-1,60,-1,-1,30,-1,40,80,-1,90,-1,100,-1,-1,-1};
//         int[] treeData = {10,20,50,140,-1,-1,-1,30,60,-1,70,110,-1,120,-1,-1,80,-1,-1,40,90,-1,100,130,-1,-1,-1,-1};
         int[] treeData = {10,20,80,-1,-1,30,50,-1,60,-1,-1,40,90,-1,100,120,-1,130,-1,-1,110,-1,-1,-1};

        GT gt = new GT();
        Node root = gt.constructGTStack(treeData);
        gt.displayTree(root);
        Node ModifiedRootTail = gt.lineriseGT(root);

        System.out.println("Printing Modified Tree ===================");
        gt.displayTree(root);
    }

}
