package com.chen.algorithm.sort;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author: Chentian
 * @date: Created in 2020/11/17 20:27
 * @desc 小和问题对数器
 */
public class SmallSumValidator {
    public static void main(String[] args) {
        new SmallSumValidator().validate(4,5,500000,
                (int[] data) -> new SmallSum().smallSum(data));
    }

    /**
     * 实现一个绝对正确但是可能复杂度不好的方法b
     */
    public static long comparator(int[] arr){
        if(arr == null || arr.length < 2){
            return 0L;
        }
        long sum = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[j] <= arr[i]){
                    sum+=arr[j];
                }
            }
        }
        return sum;

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
     * @param sum1 待比较的小和1
     * @param sum2 待比较的小和2
     * @return 两个小和是否相同
     */
    private boolean isEqual(long sum1, long sum2){
        return sum1 == sum2;
    }

    /**
     * 验证
     * @param maxSize 数组最大容量
     * @param value  值域参照值
     * @param testTime 测试次数
     * @param method 测试的方法
     * @return 验证结果
     */
    public boolean validate(int maxSize, int value, int testTime, Function<int[],Long> method){
        if( testTime <= 0 ){
            testTime = 10000;
        }

        boolean result = true;

        for (int i = 0 ; i < testTime ; i++){
            int[] randomArr = generateRandomArray(maxSize,value);
            int[] arr1 = Arrays.copyOf(randomArr,randomArr.length);
            int[] arr2 = Arrays.copyOf(randomArr,randomArr.length);

            long sum1 = comparator(arr1);
            Long sum2 = method.apply(arr2);

            if(!isEqual(sum1, sum2)){
                result = false;
                System.out.println("Error! test sum1 = "+sum1+" sum2 = "+sum2+" data:"+Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(result ? "Nice! Example:" : "");

        if(result) {
            int[] arr = generateRandomArray(maxSize, value);
            System.out.println("before data:"+Arrays.toString(arr));
            long sum = method.apply(arr);
            System.out.println("small sum:"+sum);
        }
        return result;
    }

}
