package linkedlist;

public class Questions {
    int getKthFromLast(Node head, int k) {
        Node curr = head;
        Node prev = head;
        while (k-- > 0) {
            if (curr == null) return -1;
            curr = curr.next;
        }
        while (curr != null) {
            curr = curr.next;
            prev = prev.next;
        }
        return prev.data;
    }

    public ListNode middleNodeSecondMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode middleNodeFirstMid(ListNode head) {
        ListNode fast = head; // head.next
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) { // fast != null && fast.next != null
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode curr = head, prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNodeFirstMid(head);
        ListNode nHead = mid.next;
        mid.next = null;
        ListNode reversed = reverse(nHead);
        ListNode p1 = head, p2 = reversed;
        boolean flag = true;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) flag = false;
            p1 = p1.next;
            p2 = p2.next;
        }
        reversed = reverse(reversed);
        mid.next = reversed;
        return flag;
    }

    public void reorderList(ListNode head) {
        ListNode mid = middleNodeFirstMid(head);
        ListNode nHead = mid.next;
        ListNode p1 = head, p2 = reverse(nHead);
        while (p2 != null) {
            ListNode n1 = p1.next;
            ListNode n2 = p2.next;
            p1.next = p2;
            p2.next = n1;
            p1 = n1;
            p2 = n2;
        }
        p1.next = null;
    }

    public void reorderListRestore(ListNode head) {
        ListNode h1 = head, h2 = head.next;
        ListNode p1 = h1, p2 = h2;
        ListNode p1Tail = h1;
        while (p2 != null && p2.next != null) {
            ListNode n1 = p2.next;
            ListNode n2 = n1.next;
            p1.next = n1;
            p2.next = n2;
            p1 = n1;
            p2 = n2;
            p1Tail = p1;
        }
        p1Tail.next = reverse(h2);
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode evenHead = new ListNode(-1);
        ListNode oddHead = new ListNode(-1);
        ListNode evenPrev = evenHead;
        ListNode oddPrev = oddHead;
        int idx = 1;
        while (head != null) {
            if (idx % 2 == 0) {
                evenPrev.next = head;
                evenPrev = head;

            } else {
                oddPrev.next = head;
                oddPrev = head;
            }
            head = head.next;
            idx++;
        }
        oddPrev.next = null;
        evenPrev.next = null;
        ListNode odd = oddHead.next;
        oddPrev.next = evenHead.next;
        return odd;
    }
}
