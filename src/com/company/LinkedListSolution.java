package com.company;

public class LinkedListSolution {
    class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
    public ListNode run () {

        ListNode t1 = new ListNode(1);
        t1.next = new ListNode(2);

        return quickSortHelper(t1,null);
    }
    public ListNode quickSort(ListNode head) {
        // write your solution here
        if(head == null) {
            return head;
        }
        return quickSortHelper(head,null);
    }

    private ListNode quickSortHelper (ListNode head,ListNode end) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = new ListNode(0);
        ListNode le = left;
        ListNode right = new ListNode(0);
        ListNode ri = right;
        ListNode cur = head;
        ListNode c = cur;
        head = head.next;
        cur.next = null;
        while (head != end) {
            if (head.value > cur.value) {
                ri.next = head;
                head = head.next;
                ri = ri.next;
                ri.next = null;
            } else if (head.value < cur.value) {
                le.next = head;
                head = head.next;
                le = le.next;
                le.next = null;
            } else {
                c.next = head;
                head = head.next;
                c = c.next;
                c.next = null;
            }
        }
        // le.next = cur;
        // c.next = right;
        // ri.next = end;
        left = quickSortHelper(left.next,null);

        right = quickSortHelper(right.next,null);
        le = left;
        if (left == null) {
            c.next = right;
            return cur;
        }
        while (le.next != null) {
            le = le.next;
        }
        le.next = cur;
        c.next = right;
        return left;
    }



}
