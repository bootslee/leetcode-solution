package com.bootslee.leetcode;


import com.bootslee.leetcode.datastructure.MountainArray;

public class FindInMountainArray1095 {
    /**
     * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
     * 如果不存在这样的下标 index，就请返回 -1。
     * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
     * 首先，A.length >= 3
     * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
     * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
     * MountainArray.length() - 会返回该数组的长度
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int size = mountainArr.length();
        // 步骤 1：先找到山顶元素所在的索引
        int mountaintop = findMountaintop(mountainArr);
        // 步骤 2：在前有序且升序数组中找 target 所在的索引
        int res = findFromSortedArr(mountainArr, 0, mountaintop, target);
        if (res != -1) {
            return res;
        }
        // 步骤 3：如果步骤 2 找不到，就在后有序且降序数组中找 target 所在的索引
        return findFromInversedArr(mountainArr, mountaintop + 1, size - 1, target);

    }
    private int findFromInversedArr(MountainArray mountainArr, int left, int right, int target) {
        // 在后有序且降序数组中找 target 所在的索引
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 与 findFromSortedArr 方法不同的地方仅仅在于由原来的小于号改成大于好
            if (mountainArr.get(mid) > target) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(left) == target) {
            return left;
        }
        return -1;
    }


    private int findFromSortedArr(MountainArray mountainArr, int left, int right, int target) {
        // 在前有序且升序数组中找 target 所在的索引
        while (left < right) {
            int mid = left + ((right - left) >> 1);;
            if (mountainArr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(left) == target) {
            return left;
        }
        return -1;
    }

    private int findMountaintop(MountainArray mountainArr) {
        int left=0;int right=mountainArr.length()-1;
        // 返回山顶元素
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 取左中位数，因为进入循环，数组一定至少有 2 个元素
            // 因此，左中位数一定有右边元素，数组下标不会发生越界
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // 如果当前的数比右边的数小，它一定不是山顶
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 根据题意，山顶元素一定存在
        return left;
    }

    public static void main(String[] args) {
//        System.out.println(findInMountainArray(2,new MountainArray(new int[]{1,2,3,4,5,3,1})));
    }
}
