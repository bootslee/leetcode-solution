package com.bootslee.leetcode;

public class ArrayReversePairs51 {
    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
     */
    // 用于统计逆序对的数量
    int res;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        res = 0;
        mergeSort(nums, 0, nums.length - 1);
        return res;
    }

    // 归并排序整体逻辑
    public void mergeSort(int[] nums, int left, int right) {
        // 如果两个指针相遇，则说明已经排好序
        if (left == right) {
            return;
        }

        int middle = left + ((right - left) >> 1);
        // 对数组的左半部分进行归并
        mergeSort(nums, left, middle);
        // 对数组的右半部分进行归并
        mergeSort(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    // 归并排序合并的过程
    public void merge(int[] nums, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        // pos1 指向左半部分数组中第一个元素
        int pos1 = left;
        // pos2 指向右半部分数组中第一个元素
        int pos2 = middle + 1;

        while (pos1 <= middle && pos2 <= right) {
            // 运算符 <= 是为了去除元素相等的情况
            // 例如在 [1, 3, 2, 3, 1] 中，排除 [1, 1] 和 [3, 3] 的情况
            if (nums[pos1] <= nums[pos2]) {
                // 将元素较小的放进 help 数组中
                help[i++] = nums[pos1++];
            } else if (nums[pos1] > nums[pos2]) {
                help[i++] = nums[pos2++];
                // 本题核心：由于 nums[pos1] > nums[pos2]，
                // 则从 nums[pos1] 到 nums[middle] 必定都是大于 nums[pos2] 的，
                // 因为两部分的子数组已经是各自有序的
                res += (middle - pos1 + 1);
            }
        }

        // 下面这两个 while 是当其中一个子数组中的指针如果已经遍历完了，
        // 那么另一个子数组肯定会有剩余元素，所以将剩余部分直接放到 help 中
        while (pos1 <= middle) {
            help[i++] = nums[pos1++];
        }
        while (pos2 <= right) {
            help[i++] = nums[pos2++];
        }
        // 将 help 中的元素拷贝到原数组
        for (int j = 0; j < help.length; j++) {
            nums[left + j] = help[j];
        }
    }
}
