package com.chen.algorithm.sort;

/**
 * @author: Chentian
 * @date: Created in 2020/11/27 6:25
 * @desc 给定一个无序的
 * 数组，找出数组在排序之后，相邻元素之间最大的差值。
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

        int bucketSize = nums.length + 1;
        int[] maxBucket = new int[bucketSize];
        int[] minBucket = new int[bucketSize];
        int[] emptyBucket = new int[bucketSize];

        int minNum = getMinNum(nums);
        int maxNum = getMaxNum(nums);
        if(maxNum == minNum){
            return 0;
        }

        //将无序数组放入桶中
        for (int i = 0 ; i < nums.length ; i++){
            int index = getBucketIndex(minNum,maxNum,bucketSize,nums[i]);
            if(emptyBucket[index] == 0){
                emptyBucket[index] = 1;
                minBucket[index] = nums[i];
                maxBucket[index] = nums[i];
             }else {
                if(nums[i] < minBucket[index]){
                    minBucket[index] = nums[i];
                }
                if(nums[i] > maxBucket[index]){
                    maxBucket[index] = nums[i];
                }
            }
        }

        //获取相邻两数最大间距
        int maxGap = Integer.MIN_VALUE;
        for (int i = 0 ; i < bucketSize-1; i ++){
            if(emptyBucket[i] == 0){
                continue;
            }
            int next = i+1;
            for (; next < bucketSize; next++){
                if(emptyBucket[next] == 1){
                    break;
                }
            }

            int gap = minBucket[next] - maxBucket[i];
            if(gap > maxGap){
                maxGap = gap;
            }
        }
        return maxGap;
    }

    private int getBucketIndex(int minNum, int maxNum, int bucketSize, int num) {

        double valueSize = (maxNum - minNum ) / (bucketSize * 1.0);

        for (int i = 0 ; i < bucketSize ; i++){
            double left = minNum + (i * valueSize );
            double right = minNum + (i+1) * valueSize;
            if(num >= left && num <= right){
                return i;
            }
        }
        return bucketSize-1;
    }

    private int getMaxNum(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length ; i++){
            if(nums[i] > max){
                max = nums[i];
            }
        }
        return max;
    }

    private int getMinNum(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < nums.length ; i++){
            if(nums[i] < min){
                min = nums[i];
            }
        }
        return min;
    }
}
