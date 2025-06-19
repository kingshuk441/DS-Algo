package hmapheap;

import java.util.*;

public class Questions {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : nums) {
            while (pq.size() > 0 && pq.peek() < e) pq.remove();
            pq.add(e);
        }
        return pq.peek();
    }

    public int longestSubarray(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        map.put(0, -1);
        int n = arr.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }

            map.put(sum, map.getOrDefault(sum, i));

        }

        return maxLen;
    }

    public int subarraySum(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        map.put(0, 1);
        int n = arr.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);

        }

        return count;
    }

    public int subarraysDivByK(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0, n = arr.length;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            int rem = (sum % k + k) % k;
            if (map.containsKey(rem)) count += map.get(rem);
            map.put(rem, map.getOrDefault(rem, 0) + 1);

        }
        return count;
    }

    public int findMaxLength(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, n = arr.length, sum = 0;
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            int ele = arr[i] == 0 ? -1 : 1;
            sum += ele;
            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            }
            map.put(sum, map.getOrDefault(sum, i));

        }
        return maxLen;
    }

    class LRUCache {
        private class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private HashMap<Integer, Node> map;
        private int maxCapacity;
        private int size;
        private Node head;
        private Node tail;


        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.maxCapacity = capacity;
            this.size = 0;
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }
            Node remNode = this.map.get(key);


            removeNode(remNode);
            addLast(remNode);

            return map.get(key).value;
        }

        private void removeNode(Node node) {
            if (this.head == null) return;
            if (this.head == this.tail) {
                this.head = this.tail = null;
                this.size--;
                return;
            }
            if (this.head == node) {
                this.head = this.head.next;
                this.size--;
                return;
            }
            if (this.tail == node) {
                this.tail = this.tail.prev;
                this.size--;
                return;
            }

            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            node.next = node.prev = null;
            this.size--;
        }


        private int removeFirst() {
            if (this.head == null) {
                return -1;

            }
            if (this.head == this.tail) {
                int key = this.head.key;
                this.head = this.tail = null;
                this.size--;
                return key;
            }
            Node next = this.head.next;
            int key = this.head.key;
            this.head.next = null;
            next.prev = null;
            this.head = next;
            this.size--;
            return key;

        }

        private Node addLast(Node n) {
            if (this.tail == null) {
                this.head = this.tail = n;
            } else {
                this.tail.next = n;
                n.prev = this.tail;
                this.tail = n;
            }
            this.size++;
            return this.tail;
        }

        //tail is latest,head is oldest
        public void put(int key, int value) {
            if (this.map.containsKey(key)) {
                this.get(key);
                // update key
                Node node = map.get(key);
                node.value = value;

            } else {
                if (size >= maxCapacity) {
                    int remKey = removeFirst();
                    this.map.remove(remKey);
                }
                Node n = new Node(key, value);
                Node addedNode = addLast(n);
                map.put(key, addedNode);

            }

        }
    }

    class FreqStack {
        private List<Stack<Integer>> list;
        private HashMap<Integer, Integer> map;
        private int maxFreq = 0;

        public FreqStack() {
            this.list = new ArrayList<>();
            list.add(new Stack<>());
            this.map = new HashMap<>();
        }

        public void push(int val) {
            if (list.size() - 1 == maxFreq) list.add(new Stack<>());
            map.put(val, map.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(map.get(val), maxFreq);
            list.get(map.get(val)).push(val);
        }

        public int pop() {
            Stack<Integer> st = list.get(maxFreq);
            int item = st.pop();
            map.put(item, map.get(item) - 1);
            if (map.get(item) == 0) map.remove(item);
            if (st.isEmpty()) maxFreq--;
            return item;
        }
    }

    class RandomizedSet {
        private List<Integer> list;
        private HashMap<Integer, Integer> map;
        private Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                map.put(val, list.size());
                list.add(val);
                return true;
            }
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int idx = map.get(val);
                int lidx = list.size() - 1;
                int v1 = list.get(idx);
                int v2 = list.get(lidx);
                list.set(idx, v2);
                list.set(lidx, v1);
                list.remove(list.size() - 1);
                map.put(v2,idx);
                map.remove(v1);

                return true;
            } else {
                return false;
            }

        }

        public int getRandom() {
            int idx = random.nextInt(list.size());
            return list.get(idx);
        }
    }

}
