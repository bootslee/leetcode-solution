package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.ListNode;

import java.util.Stack;

/**
 * Created By BootsLee on 2020/5/16
 **/
public class ReverseNodesInKGroup25 {
    /**
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 记录起点位置
        ListNode pre = dummy;
        // 记录一个翻转链表的结束位置
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            // 暂存一组待翻转链表的起点位置
            ListNode start = pre.next;
            // 暂存一组结束后的位置
            ListNode next = end.next;
            // 断开连接，防止翻转时异常
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    /**
     * 翻转一组链表，返回翻转后的头节点
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            // 记录下一个节点
            ListNode next = curr.next;
            // 翻转指向
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

}
