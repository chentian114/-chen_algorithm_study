package com.chen.algorithm.sort;

/**
 * @author: Chentian
 * @date: Created in 2020/11/27 6:25
 * @desc leetcode 164. 最大间距
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * https://leetcode-cn.com/problems/maximum-gap/
 * 利用桶排序思想
 */
public class MaximumGap {

    public static void main(String[] args) {
        int[] nums = {3,6,9,1};
        int gap = new MaximumGap().maximumGap(nums);
        System.out.println(gap);
    }

    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }

        //获取最大值和最小值
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length ; i++){
            minNum = nums[i] < minNum ? nums[i] : minNum;
            maxNum = nums[i] > maxNum ? nums[i] : maxNum;
        }
        if(maxNum == minNum){
            return 0;
        }

        int bucketSize = nums.length + 1;
        int[] maxBucket = new int[bucketSize];
        int[] minBucket = new int[bucketSize];
        boolean[] emptyBucket = new boolean[bucketSize];

        //将无序数组放入桶中
        for (int i = 0 ; i < nums.length ; i++){
            int index = getBucketIndex(minNum,maxNum,nums.length,nums[i]);

            minBucket[index] = emptyBucket[index] ? Math.min(nums[i], minBucket[index]) : nums[i];
            maxBucket[index] = emptyBucket[index] ? Math.max(nums[i], maxBucket[index]) : nums[i];
            emptyBucket[index] = true;
        }

        //获取相邻两数最大间距
        int maxGap = 0;
        int lastMax = maxBucket[0] ;
        for (int i = 1 ; i < bucketSize; i ++){
            if(!emptyBucket[i]){
                continue;
            }

            maxGap = Math.max(maxGap,minBucket[i] - lastMax);
            lastMax = maxBucket[i];
        }
        return maxGap;
    }

    private int getBucketIndex(long minNum, long maxNum, long length, long num) {
        //获取元素在存放桶的索引，使用long防止溢出
        return (int)((num - minNum) * length / (maxNum - minNum)) ;
    }
}
