package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.ListNode;

public class AddTwoNumbers2 {


    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add=0;
        ListNode header=new ListNode(0);
        ListNode next=header;
        ListNode n1=l1;ListNode n2=l2;
        while (n1!=null&&n2!=null){
            int sum=n1.val+n2.val+add;
            if(sum>9){
                add=1;
                sum%=10;
            }else {
                add=0;
            }
            ListNode node=new ListNode(sum);
            next.next=node;
            next=node;
            n1=n1.next;n2=n2.next;
        }
        while (n1!=null){
            int sum=n1.val+add;
            if(sum>9){
                add=1;
                sum%=10;
            }else {
                add=0;
            }
            ListNode node=new ListNode(sum);
            next.next=node;
            next=node;
            n1=n1.next;
        }
        while (n2!=null){
            int sum=n2.val+add;
            if(sum>9){
                add=1;
                sum%=10;
            }else {
                add=0;
            }
            ListNode node=new ListNode(sum);
            next.next=node;
            next=node;
            n2=n2.next;
        }
        if(add!=0){
            ListNode node=new ListNode(add);
            next.next=node;
            next=node;
        }
        return header.next;
    }
}
