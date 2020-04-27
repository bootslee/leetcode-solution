package com.bootslee.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;


public class LFUCache {
    Map<Integer, Node> cache = new HashMap<>();
    //缓存 每个不同频率的节点列表，删除时直接删除最低频率的头节点。
    Map<Integer, LinkedHashSet<Node>> freqMap = new HashMap<>();
    int minFreq = 1; //维护最低频率
    int capacity; //容量大小
    int size = 0; //维护大小

    public LFUCache(int capacity) {
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        incFreq(node);
        return node.value;
    }

    private void incFreq(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> list = freqMap.get(freq);
        list.remove(node);
        if (freq == minFreq && list.size() == 0) {
            minFreq = freq + 1;
        }
        if (list.size() == 0) {
            freqMap.remove(freq);
        }
        node.freq++;
        //放到新频率列表里面，插入到尾部
        LinkedHashSet freqNodes = freqMap.getOrDefault(node.freq, new LinkedHashSet<>());
        freqNodes.add(node);
        freqMap.put(node.freq,freqNodes);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            incFreq(node);
        } else {
            if (size == capacity) {
                Node deadNode = getLeastFrequentlyUsedNode();
                cache.remove(deadNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            LinkedHashSet freqNodes = freqMap.getOrDefault(newNode.freq, new LinkedHashSet<>());
            freqNodes.add(newNode);
            freqMap.put(newNode.freq,freqNodes);
            minFreq=1;
            size++;
        }
    }

    private Node getLeastFrequentlyUsedNode() {
        LinkedHashSet<Node> nodes = freqMap.get(minFreq);
        Node lfuNode = nodes.iterator().next();//找到头节点删除
        nodes.remove(lfuNode);
        //维护一下这个频率缓存的大小
        if (nodes.size() == 0) {
            //删除后，这里不用担心下次下次找最小频率找不到，
            //因为很快就会维护新的最低频率
            freqMap.remove(minFreq);
        }
        return lfuNode;
    }

    private class Node {
        int key;
        int value;
        int freq = 1;//频率，
        public Node() {
        }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3));       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(1));       // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3));       // 返回 3
        System.out.println(cache.get(4));       // 返回 4

    }
}
