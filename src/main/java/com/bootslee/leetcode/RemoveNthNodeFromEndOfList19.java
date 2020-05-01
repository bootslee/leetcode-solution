package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.ListNode;

public class RemoveNthNodeFromEndOfList19 {
    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node=head;
        int length=0;
        while (node!=null){
            length++;
            node=node.next;
        }
        if(n==length)return head.next;
        int nth=length-n;

        node=head;length=0;

        while (node!=null){
            length++;
            if(length==nth){
                node.next=node.next.next;
                break;
            }
            node=node.next;
        }
        return head;
    }
    //快慢指针
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode end=head;
        ListNode pre=head;
        int index=0;
        //保持 快慢指针 的间隔为 n+1
        // 为什么是n+1呢，因为当快指针到到尾部的时候，需要找到 待删除元素的前一个节点，
        while (end!=null&&index<n+1){
            index++;
            end=end.next;
        }
        //说明删除的是第一个元素
        if(index<n+1) return  head.next;
        while (end!=null){
            end=end.next;
            pre=pre.next;
        }
        pre.next=pre.next.next;
        return head;
    }
}
