package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Questions {
    public static boolean isDuplicate(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ')') st.push(ch);
            else {
                boolean flag = false;
                while (st.size() > 0 && st.peek() != '(') {
                    st.pop();
                    flag = true;
                }
                st.pop();
                if (!flag) return true;
            }
        }
        return false;
    }

    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') st.push(ch);
            else {
                if (st.size() == 0) return false;
                if (ch == ')' && st.peek() != '(') return false;
                if (ch == ']' && st.peek() != '[') return false;
                if (ch == '}' && st.peek() != '{') return false;
                st.pop();
            }
        }
        return st.size() == 0; // [(]
    }

    public ArrayList<Integer> nextLargerElement(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int ele = arr[i];
            while (st.size() > 0 && arr[st.peek()] < ele) {
                ans[st.pop()] = ele;
            }
            st.push(i);
        }
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            al.add(ans[i]);
        }
        return al;
    }

    public ArrayList<Integer> nextLargerElement2(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int ans[] = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int ele = arr[i];
            while (st.size() > 0 && st.peek() <= ele) {
                st.pop();
            }
            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }
            st.push(ele);
        }
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            al.add(ans[i]);
        }
        return al;
    }

    public static void main(String[] args) {
        System.out.println(isDuplicate("((a+b)+((c+d)))"));
    }
}
