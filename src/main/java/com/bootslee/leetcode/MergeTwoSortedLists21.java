package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.ListNode;

public class MergeTwoSortedLists21 {
    /**
     * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l2 == null && l1 != null) {
            return l1;
        }
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode tmpNode1 = l1;
        ListNode tmpNode2 = l2;
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
        return head.next;
    }
}
