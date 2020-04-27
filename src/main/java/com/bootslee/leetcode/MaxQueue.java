package com.bootslee.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列的最大值
 */
public class MaxQueue {
    Queue<Integer> que;
    Deque<Integer> deq;

    public MaxQueue() {
        que = new LinkedList<>();  //队列：插入和删除
        deq = new LinkedList<>();  //双端队列：获取最大值
    }

    public int max_value() {
        return deq.size()>0?deq.peek():-1;  //双端队列的队首为que的最大值
    }

    public void push_back(int value) {
        que.offer(value);  //value入队
        while(deq.size()>0 && deq.peekLast()<value){
            deq.pollLast();  //将deq队尾小于value的元素删掉
        }
        deq.offerLast(value);  //将value放在deq队尾
    }

    public int pop_front() {
        int tmp = que.size()>0?que.poll():-1;  //获得队首元素
        if(deq.size()>0 && tmp==deq.peek()){
            deq.poll();  //如果出队的元素是当前最大值，将deq的队首出队
        }
        return tmp;
    }
}
