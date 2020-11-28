package com.chen.algorithm.sort;

/**
 * @author: Chentian
 * @date: Created in 2020/11/27 6:25
 * @desc 给定一个无序的
 * 数组，找出数组在排序之后，相邻元素之间最大的差值。
 * https://leetcode-cn.com/problems/maximum-gap/
 * 使用基数排序
 */
public class MaximumGap2 {

    public static void main(String[] args) {
        int[] nums = {3,6,9,1};
        int gap = new MaximumGap2().maximumGap(nums);
        System.out.println(gap);
    }

    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }

        new LSDRadixSort().sort(nums);

        int maxGap = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length-1; i++){
            int gap = nums[i+1] - nums[i];
            if(gap > maxGap){
                maxGap = gap;
            }
        }
        return maxGap;
    }

}
