package com.chen.algorithm.sort;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author: Chentian
 * @date: Created in 2020/11/30 5:35
 * @desc 最大间距对数器
 */
public class MaximumGapValidator {
    public static void main(String[] args) {
        new MaximumGapValidator().validate(4,5,500000,
                (int[] data) -> new MaximumGap().maximumGap(data));

        new MaximumGapValidator().validate(4,5,500000,
                (int[] data) -> new MaximumGap2().maximumGap(data));
    }

    /**
     * 实现一个绝对正确但是可能复杂度不好的方法b
     */
    public static int comparator(int[] nums){
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }
    /**
     * 实现一个随机样本产生器
     * @param maxSize 数组最大容量
     * @param value 值域参照值
     * @return 随机无序数组
     */
    private int[] generateRandomArray(int maxSize, int value){
        return SortValidator.generateRandomArray(maxSize,value);
    }

    /**
     * 实现比对的方法
     * @param gap1 待比较的间距1
     * @param gap2 待比较的间距2
     * @return 两个间距是否相同
     */
    private boolean isEqual(int gap1, int gap2){
        return gap1 == gap2;
    }

    /**
     * 验证
     * @param maxSize 数组最大容量
     * @param value  值域参照值
     * @param testTime 测试次数
     * @param method 测试的方法
     * @return 验证结果
     */
    public boolean validate(int maxSize, int value, int testTime, Function<int[],Integer> method){
        if( testTime <= 0 ){
            testTime = 10000;
        }

        boolean result = true;

        for (int i = 0 ; i < testTime ; i++){
            int[] randomArr = generateRandomArray(maxSize,value);
            int[] arr1 = Arrays.copyOf(randomArr,randomArr.length);
            int[] arr2 = Arrays.copyOf(randomArr,randomArr.length);

            int gap1 = comparator(arr1);
            int gap2 = method.apply(arr2);

            if(!isEqual(gap1, gap2)){
                result = false;
                System.out.println("Error! test gap1 = "+gap1+" gap2 = "+gap2+" data:"+Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(result ? "Nice! Example:" : "");

        if(result) {
            int[] arr = generateRandomArray(maxSize, value);
            System.out.println("before data:"+Arrays.toString(arr));
            int gap = method.apply(arr);
            System.out.println("gap:"+gap);
        }
        return result;
    }
}
