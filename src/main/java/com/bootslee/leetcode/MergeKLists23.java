package com.bootslee.leetcode;

public class MergeKLists23 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int left = 0;
        int right = 0;
        int count = 0;
        int size=lists.length;
        do {
            count = 0;
            left = 0;
            right = size-1;
            while (left < right) {
                lists[count++] = mergeTwoNode(lists[left++], lists[right--]);
            }
            size = (left == right)? left+1:left;
        } while (size != 1);
        return lists[0];
    }
    private ListNode mergeTwoNode(ListNode node1, ListNode node2) {
        if (node1 == null && node2 != null) {
            return node2;
        }
        if (node2 == null && node1 != null) {
            return node1;
        }
        if (node1 == null && node2 == null) {
            return null;
        }
        ListNode tmpNode1 = node1;
        ListNode tmpNode2 = node2;
        ListNode head = new ListNode(-1);
        ListNode preNode = head;
        while (tmpNode1 != null || tmpNode2 != null) {
            if (tmpNode1 == null) {
                preNode.next = tmpNode2;
                preNode = preNode.next;
                tmpNode2 = tmpNode2.next;
                continue;
            }
            if (tmpNode2 == null) {
                preNode.next = tmpNode1;
                preNode = preNode.next;
                tmpNode1 = tmpNode1.next;
                continue;
            }
            if (tmpNode1.val < tmpNode2.val) {
                preNode.next = tmpNode1;
                preNode = preNode.next;
                tmpNode1 = tmpNode1.next;
            } else {
                preNode.next = tmpNode2;
                preNode = preNode.next;
                tmpNode2 = tmpNode2.next;
            }
        }
        Integer.valueOf("".trim());
        return head.next;
    }
}
