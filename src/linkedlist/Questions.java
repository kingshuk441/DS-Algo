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

    ListNode th, tt, oh, ot;

    private void addFirst(ListNode head) {
        if (th == null) {
            th = tt = head;
        } else {
            head.next = th;
            th = head;
        }
    }

    private int sizeLL(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int size = sizeLL(head);
        while (size >= k) {
            int tempK = k;
            while (tempK-- > 0 && head != null) {
                ListNode next = head.next;
                head.next = null;
                addFirst(head);
                head = next;
            }
            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }
            tt = th = null;
            size -= k;
        }
        ot.next = head;
        return oh;
    }

    public Node copyRandomList(Node head) {

        //add nodes in b/w
        Node temp = head;
        while (temp != null) {
            Node next = temp.next;
            Node n = new Node(temp.data);
            temp.next = n;
            n.next = next;
            temp = next;
        }
        // point random pointer
        temp = head;
        while (temp != null) {
            Node next = temp.next.next;
            temp.next.random = temp.random == null ? null : temp.random.next;
            temp = next;
        }

        //remove added nodes
        Node dummy = new Node(-1);
        Node prev = dummy;
        while (head != null) {
            Node nextNode = head.next.next;
            prev.next = head.next;
            prev = head.next;
            head.next = nextNode;
            head = nextNode;
        }
        return dummy.next;

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head, curr = head.next;
        while (curr != null) {
            if (prev.val != curr.val) {
                prev = curr;
                curr = curr.next;
            } else {
                ListNode currNext = curr.next;
                prev.next = curr.next;
                curr = currNext;
            }
        }
        prev.next = null;
        return head;
    }


    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        prev.next = head;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (prev.next.val == curr.next.val) {
                while (curr != null && curr.val == prev.next.val) {
                    curr = curr.next;
                }
                prev.next = curr;
            } else {
                prev.next = curr;
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public boolean isCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
