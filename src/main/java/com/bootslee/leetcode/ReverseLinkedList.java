package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.ListNode;

import java.util.LinkedList;
import java.util.Stack;
public class ReverseLinkedList {
    /**
     * 反转一个单链表。
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack=new Stack<ListNode>();
        ListNode node=head;
        while (node!=null){
            stack.push(node);
            node=node.next;
        }
        ListNode header =new ListNode(0);
        node=header;
        while (!stack.isEmpty()){
            node.next=stack.pop();
            node = node.next;
        }
        node.next=null;
        return header.next;
    }
    public ListNode reverseList2(ListNode head) {
        ListNode node=head;
        ListNode pre=null;
        //修改node指针
        while (node!=null){
            ListNode tmp=node.next;
            node.next=pre;
            pre=node;
            node=tmp;
        }
        return pre;
    }

}
