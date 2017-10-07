package com.company;

import java.util.HashMap;
import java.util.Map;

//public class LRUCahe {
//    class DoubleLinkedNode {
//        DoubleLinkedNode prev,next;
//        int val;
//        int key;
//        public DoubleLinkedNode (int key, int val) {
//            this.key = key;
//            this.val = val;
//        }
//    }
//
//    private DoubleLinkedNode head,tail;
//    Map<Integer,DoubleLinkedNode> map;
//    private int cap,count;
//
//    public LRUCahe(int capacity) {
//        this.cap = capacity;
//        map = new HashMap<>();
//        this.count = 0;
//    }
//
//    public int get(int key) {
//        DoubleLinkedNode temp = map.get(key);
//        if (temp == null) return -1;
//        addToLast(temp);
//        return temp.val;
//    }
//
//    public void put(int key, int value) {
//        DoubleLinkedNode temp = map.get(key);
//        if (temp != null) {
//            temp.val = value;
//        } else {
//            temp = new DoubleLinkedNode(key,value);
//        }
//        addToLast(temp);
//    }
//
//    private void addToLast(DoubleLinkedNode node) {
//        if (head == null) {
//            head = node;
//            tail = node;
//            return;
//        }
//        if (node == head) {
//            head = node.next;
//            node.next.prev = null;
//            tail.next = node;
//            node.prev = tail;
//            tail = tail.next;
//        } else if (node == tail) {
//            return;
//        } else {
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
//            tail.next = node;
//            node.prev = tail;
//            tail = tail.next;
//        }
//        if (count == cap && head != null) {
//            map.remove(head.key);
//            DoubleLinkedNode temp = head;
//            head.next.prev = null;
//            head = head.next;
//            temp.next = null;
//        } else {
//            count++;
//        }
//    }
//}

class LRUCahe {
    class DoubleLinkNode {
        DoubleLinkNode prev,next;
        int val,key;

        public DoubleLinkNode (int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // unlink a node from DoubleLinkedList
    private void unlink(DoubleLinkNode node) {
        if (node.next != null && node.prev != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        } else if (node.next == null) {
            node.prev.next = null;
            node.prev = null;
        } else if (node.prev == null) {
            node.next.prev = null;
            node.next = null;
        }
    }

    // add a node to the head on DoubleLinkedList, inout node's prev and next should be null
    private void addtoHead(DoubleLinkNode node) {
        if (head.next == null) {
            head.next = node;
            tail.prev = node;
        } else {
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }
    }

    private DoubleLinkNode head,tail;
    private int capacity,count;
    Map<Integer,DoubleLinkNode> map;

    public LRUCahe(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        this.count = 0;

    }

    public int get(int key) {
        DoubleLinkNode temp = map.get(key);
        if (temp == null) {
            return -1;
        }
        unlink(temp);
        addtoHead(temp);
        return temp.val;
    }

    public void put(int key, int value) {
        DoubleLinkNode temp = map.get(key);
        if (temp == null) { //we need to insert new node into list, make sure # of nodes is in capacity
            temp = new DoubleLinkNode(key,value);
            map.put(key,temp);
            count++;
            if (count > capacity) {
                DoubleLinkNode headtoremove = head.next;
                unlink(headtoremove);
                map.remove(headtoremove.val);
                count--;
            }
        } else { //we need to update node val and place it back into head;
            temp.val = value;
            unlink(temp);
            addtoHead(temp);
        }
    }
}

