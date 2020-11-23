package com.chen.algorithm.sort;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author: Chentian
 * @date: Created in 2020/11/12 5:05
 * @desc 排序算法对数器：
 * 1. 有一个你想要测的方法a；
 * 2. 实现一个绝对正确但是复杂度不好的方法b；
 * 3. 实现一个随机样本产生器；
 * 4. 实现比对的方法；
 * 5. 把方法a和方法b比对很多次来验证方法a是否正确。
 * 6. 如果有一个样本使得比对出错， 打印样本分析是哪个方法出错；
 * 7. 当样本数量很多时比对测试依然正确，可以确定方法a已经正确。
 */
public class SortValidator {

    /**
     * 实现一个绝对正确但是可能复杂度不好的方法b
     */
    private void comparator(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        Arrays.sort(arr);
    }

    /**
     * 实现一个随机样本产生器
     * @param maxSize 数组最大容量
     * @param value 值域参照值
     * @return 随机无序数组
     */
    public static int[] generateRandomArray(int maxSize, int value){
        if(maxSize <= 0){
            return new int[0];
        }
        //生成长度随机的数组
        int randomSize = (int)((maxSize + 1) * Math.random());
        int[] arr = new int[randomSize];
        for (int i = 0 ; i < randomSize; i++){
            int randomValue = (int) ((value + 1) * Math.random()) - (int)(value * Math.random());
            arr[i] = randomValue;
        }
        return arr;
    }

    /**
     * 实现比对的方法
     * @param arr1 待比较的数组1
     * @param arr2 待比较的数组2
     * @return 两个数组是否相同
     */
    private boolean isEqual(int[] arr1, int[] arr2){
        if(arr1 == null && arr2 == null){
            return true;
        }
        if(arr1 == null || arr2 == null){
            return false;
        }
        if( arr1.length != arr2.length){
            return false;
        }

        for (int i = 0 ; i < arr1.length ; i ++){
            if(arr1[i] != arr2[i]){
                return false;
            }
        }

        return true;
    }

    /**
     * 验证
     * @param maxSize 数组最大容量
     * @param value  值域参照值
     * @param testTime 测试次数
     * @param method 测试的方法
     * @return 验证结果
     */
    public boolean validate(int maxSize, int value, int testTime, Consumer<int[]> method){
        if( testTime <= 0 ){
            testTime = 10000;
        }

        boolean result = true;

        for (int i = 0 ; i < testTime ; i++){
            int[] randomArr = generateRandomArray(maxSize,value);
            int[] arr1 = Arrays.copyOf(randomArr,randomArr.length);
            int[] arr2 = Arrays.copyOf(randomArr,randomArr.length);

            comparator(arr1);
            try {
                method.accept(arr2);
            }catch (Exception e){
                System.out.println("arr:"+Arrays.toString(randomArr));
                e.printStackTrace();
            }
            if(!isEqual(arr1,arr2)){
                result = false;
                System.out.println("Error! test data:"+Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(result ? "Nice! Example:" : "");

        if(result) {
            int[] arr = generateRandomArray(maxSize, value);
            System.out.println("before data:"+Arrays.toString(arr));
            method.accept(arr);
            System.out.println("end data:"+Arrays.toString(arr));
        }
        return result;
    }


}
