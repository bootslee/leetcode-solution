package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.ListNode;

/**
 * Created By BootsLee on 2020/5/29
 **/
public class SwapNodesInPairs24 {
    /**
     * 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {

        ListNode node=head;
        if(head==null||head.next==null){
            return head;
        }
        ListNode first=new ListNode(0);
        first.next=node;
        ListNode pre=first;
        while (node!=null&&node.next!=null){
            pre.next=node.next;
            // 指针交换原则，先保存即将断开的指针
            ListNode temp=node.next.next;
            // 再将保存的指针的指向修改为正确反向
            node.next.next =node;
            // 再将另外的指针方向进行修改
            node.next = temp;
            // 移动指针
            pre=node;
            node=temp;
        }
        return first.next;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        new SwapNodesInPairs24().swapPairs(head);

    }
}
