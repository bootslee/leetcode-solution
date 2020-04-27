package com.bootslee.leetcode;

import java.util.Stack;

public class AddTwoNumbers445 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1=new Stack<>();
        Stack<ListNode> stack2=new Stack<>();
        ListNode iterL1=l1;
        while (iterL1!=null){
            stack1.push(iterL1);
            iterL1=iterL1.next;
        }
        iterL1=l2;
        while (iterL1!=null){
            stack2.push(iterL1);
            iterL1=iterL1.next;
        }
        int add=0;
        ListNode header=null;
        while (!stack1.empty()&&!stack2.empty()){
            ListNode n1=stack1.pop();
            ListNode n2=stack2.pop();
            int sum=n1.val+n2.val+add;
            if(sum>9){
                add=1;
                sum%=10;
            }else {
                add=0;
            }
            ListNode node=new ListNode(sum);
            node.next=header;
            header=node;
        }
        while (!stack1.empty()){
            ListNode n1=stack1.pop();
            int sum=n1.val+add;
            if(sum>9){
                add=1;
                sum%=10;
            }else {
                add=0;
            }
            ListNode node=new ListNode(sum);
            node.next=header;
            header=node;
        }
        while (!stack2.empty()){
            ListNode n1=stack2.pop();
            int sum=n1.val+add;
            if(sum>9){
                add=1;
                sum%=10;
            }else {
                add=0;
            }
            ListNode node=new ListNode(sum);
            node.next=header;
            header=node;
        }
        if(add!=0){
            ListNode node=new ListNode(add);
            node.next=header;
            header=node;
        }
        return header;
    }
}
