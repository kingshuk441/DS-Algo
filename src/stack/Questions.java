package stack;

import java.util.*;

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

    public int[] leftSmaller(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int ans[] = new int[arr.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            while (st.size() > 0 && st.peek() >= ele) {
                st.pop();
            }
            if (st.size() != 0) ans[i] = st.peek();
            st.push(ele);
        }
        return ans;
    }

    public ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> al = new ArrayList<>();
        st.push(-1);
        for (int i = 0; i < n; i++) {
            int ele = arr[i];
            while (st.peek() != -1 && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            al.add(i - st.peek());
            st.push(i);
        }
        return al;
    }

    public static int getMaxArea(int arr[]) {
        int n = arr.length;
        int nsl[] = new int[n];
        int nsr[] = new int[n];

        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < n; i++) {
            int ele = arr[i];
            while (st.size() != 1 && arr[st.peek()] >= ele) {
                st.pop();
            }
            nsl[i] = st.peek();
            st.push(i);
        }
        st = new Stack<>();
        st.push(n);
        for (int i = n - 1; i >= 0; i--) {
            int ele = arr[i];
            while (st.size() != 1 && arr[st.peek()] >= ele) {
                st.pop();
            }
            nsr[i] = st.peek();
            st.push(i);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int area = arr[i] * (nsr[i] - nsl[i] - 1);
            max = Math.max(area, max);
        }
        return max;
    }

    public static int getMaxArea2(int arr[]) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int n = arr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int ele = arr[i];
            while (st.size() != 1 && arr[st.peek()] >= ele) {
                int idx = st.pop();
                int height = arr[idx];
                int width = i - st.peek() - 1;
                max = Math.max(height * width, max);
            }
            st.push(i);
        }
        while (st.size() != 1) {
            int idx = st.pop();
            int height = arr[idx];
            int width = n - st.peek() - 1;
            max = Math.max(height * width, max);
        }
        return max;
    }

    private int[] nextGreaterOnRight(int arr[]) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, n);
        for (int i = 0; i < n; i++) {
            int ele = arr[i];
            while (st.size() > 0 && arr[st.peek()] <= ele) {
                ans[st.pop()] = i;
            }

            st.push(i);
        }
        return ans;
    }

    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        if (n == 1) return new int[]{arr[0]};
        int ans[] = new int[n - k + 1];
        int ngr[] = nextGreaterOnRight(arr);

        int largestInWindowIdx = 0;
        for (int i = 0; i < n - k + 1; i++) {
            int windowLen = i + k - 1;
            if (i > largestInWindowIdx) largestInWindowIdx = i;

            while (largestInWindowIdx < n && ngr[largestInWindowIdx] <= windowLen) {
                largestInWindowIdx = ngr[largestInWindowIdx];
            }
            ans[i] = arr[largestInWindowIdx];
        }
        return ans;

    }

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        ArrayList<int[]> arr = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));


        for (int i = 0; i < n; i++) {
            int curr[] = intervals[i];
            if (arr.size() != 0) {
                int prev[] = arr.get(arr.size() - 1);
                if (prev[1] >= curr[0]) {
                    int f = prev[0];
                    int l = Math.max(prev[1], curr[1]);
                    arr.set(arr.size() - 1, new int[]{f, l});
                } else {
                    arr.add(curr);
                }
            } else {
                arr.add(curr);
            }
        }


        return arr.toArray(new int[arr.size()][]);
    }

    class MinStack {
        private Stack<Long> st = new Stack<>();
        private long min = Long.MAX_VALUE;

        public MinStack() {

        }

        public void push(int val) {
            if (st.size() == 0) {
                st.push((long) val);
                min = val;
            } else {
                if (val < min) {
                    st.push(2L * val - min);
                    min = val;
                } else {
                    st.push((long) val);
                }
            }
        }

        public void pop() {
            if (st.size() != 0) {
                if (st.peek() < min) {
                    min = 2 * min - st.pop();
                } else {
                    st.pop();
                }
            }
        }

        public int top() {
            if (st.size() != 0) {
                if (st.peek() < min) return (int) min;
                else return (int) (long) st.peek();
            }
            return 0;
        }

        public int getMin() {
            return (int) min;
        }
    }

    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int ngr[] = new int[n];
        Arrays.fill(ngr, -1);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!st.isEmpty() && arr[i % n] > arr[st.peek()]) {
                ngr[st.pop()] = arr[i % n];
            }
            if (i < n) st.push(i);
        }
        return ngr;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int idx = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            int ele = pushed[i];
            st.push(ele);
            while (st.size() > 0 && popped[idx] == st.peek()) {
                st.pop();
                idx++;
            }
        }
        return st.size() == 0;
    }

    public int minAddToMakeValid(String s) {
        int extraOpeningBrackets = 0, extraClosingBrackets = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') extraOpeningBrackets++;
            else {
                if (extraOpeningBrackets == 0) extraClosingBrackets++;
                else extraOpeningBrackets--;
            }
        }
        return extraOpeningBrackets + extraClosingBrackets;
    }

    public String removeOuterParentheses(String s) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (count > 0) {
                    sb.append('(');
                }
                count++;
            } else {
                if (count != 1) {
                    sb.append(')');
                }
                count--;
            }
        }
        return sb.toString();
    }

    public int minInsertions(String s) {
        int extraOpeningBrackets = 0, extraClosingBrackets = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                extraOpeningBrackets++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') i++;
                else extraClosingBrackets++;

                if (extraOpeningBrackets == 0) {
                    extraClosingBrackets++;
                } else {
                    extraOpeningBrackets--;
                }

            }
        }
        return extraClosingBrackets + extraOpeningBrackets * 2;
    }

    public boolean checkValidString(String s) {
        int maxOpeningBrac = 0, minOpeningBrac = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                maxOpeningBrac++;
                minOpeningBrac++;
            } else if (ch == ')') {
                minOpeningBrac--;
                maxOpeningBrac--;
            } else {
                maxOpeningBrac++;
                minOpeningBrac--;
            }
            if (maxOpeningBrac < 0) return false;
            if (minOpeningBrac < 0) minOpeningBrac = 0;
        }
        return minOpeningBrac == 0;
    }

    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.add(-1);
            } else {
                int sum = 0;
                while (st.size() > 0 && st.peek() != -1) {
                    sum += st.pop();
                }
                st.pop();
                st.add(sum == 0 ? 1 : 2 * sum);
            }
        }
        int ans = 0;
        while (st.size() > 0) {
            ans += st.pop();
        }
        return ans;
    }

    public boolean find132pattern(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int minSoFar[] = new int[n];
        minSoFar[0] = nums[0];
        for (int i = 1; i < n; i++) minSoFar[i] = Math.min(minSoFar[i - 1], nums[i]);
        st.add(nums[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            int ele = nums[i];
            int min = minSoFar[i];
            while (st.size() > 0 && st.peek() <= min) st.pop();
            if (st.size() > 0 && st.peek() < ele) return true;
            st.push(ele);
        }

        return false;
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int ele = asteroids[i];
            if (ele > 0) {
                st.push(ele);
            } else {
                boolean canPush = false;
                while (st.size() > 0 && st.peek() > 0) {
                    if (st.peek() < -ele) {
                        st.pop();
                        canPush = true;
                    } else if (st.peek() == -ele) {
                        st.pop();
                        canPush = false;
                        break;
                    } else {
                        canPush = false;
                        break;
                    }
                }
                if (canPush) st.push(ele);
            }

        }
        int ans[] = new int[st.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }
        return ans;
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (st.size() > 0 && st.peek() > ch && k > 0) {
                st.pop();
                k--;
            }
            st.push(ch);
        }

        while (k > 0 && st.size() > 0) {
            st.pop();
            k--;
        }
        if (st.size() == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (st.size() != 0) {
            sb.append(st.pop());
        }
        String ans = sb.reverse().toString();
        int idx = 0;
        while (idx != ans.length() && ans.charAt(idx) == '0') idx++;
        return idx == ans.length() ? "0" : ans.substring(idx, ans.length());
    }

    public int trap(int[] height) {
        int n = height.length;
        int leftMax[] = new int[n];
        int righMax[] = new int[n];
        for (int i = 0; i < n; i++) {
            leftMax[i] = Math.max(i > 0 ? leftMax[i - 1] : 0, height[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            righMax[i] = Math.max(i == n - 1 ? 0 : righMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int min = Math.min(righMax[i], leftMax[i]);
            ans += min - height[i];
        }
        return ans;
    }

    public int trap2(int[] height) {
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (st.size() > 0 && height[st.peek()] < height[i]) {
                int currentIdx = st.pop();
                if (st.size() == 0) break;
                int leftSideIdx = st.peek();
                int width = i - leftSideIdx - 1;
                int h = Math.min(height[leftSideIdx], height[i]) - height[currentIdx];
                ans += h * width;
            }
            st.push(i);
        }
        return ans;
    }


    class RecentCounter {

        LinkedList<Integer> que = new LinkedList<>();

        public RecentCounter() {

        }

        public int ping(int t) {
            while (que.size() > 0 && que.getFirst() < t - 3000) que.removeFirst();
            que.add(t);
            return que.size();
        }
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = nums.length;
        int j = 0;
        int ans[] = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (que.size() > 0 && que.getFirst() <= i - k) que.removeFirst();
            while (que.size() > 0 && nums[que.getLast()] < nums[i]) que.removeLast();
            que.add(i);
            ans[j++] = que.peek();

        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(isDuplicate("((a+b)+((c+d)))"));
    }
}
