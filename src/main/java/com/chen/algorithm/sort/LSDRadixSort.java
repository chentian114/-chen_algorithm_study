package com.chen.algorithm.sort;

import java.util.LinkedList;

/**
 * @author: Chentian
 * @date: Created in 2020/11/27 7:12
 * @desc 最低位优先 基数排序
 */
public class LSDRadixSort{

    private LinkedList<Integer>[] buckets = new LinkedList[10];

    public LSDRadixSort(){
        for (int i = 0 ; i < buckets.length ; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public void sort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }

        int min = getMin(arr);
        //处理负数
        if(min < 0){
            for (int i = 0 ; i < arr.length ; i++){
                arr[i] = arr[i] - min;
            }
        }
        int max = getMax(arr);
        int maxDigital = getDigitalLen(max);

        for (int d = 1 ; d <= maxDigital ; d++){
            //将数组放入桶中
            for (int i = 0 ; i < arr.length ; i++){
                int digital = digital(arr[i],d);
                buckets[digital].add(arr[i]);
            }

            //从桶中取出放回数组
            int index = 0 ;
            for (int i = 0 ; i < buckets.length ; i++){
                while (!buckets[i].isEmpty()){
                    arr[index++] = buckets[i].poll();
                }
            }
        }

        //处理负数
        if(min < 0){
            for (int i = 0 ; i < arr.length ; i++){
                arr[i] = arr[i] + min;
            }
        }
    }

    private int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < arr.length ; i ++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }

    private int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < arr.length ; i++){
            if(min > arr[i]){
                min = arr[i];
            }
        }
        return min;
    }

    private int getDigitalLen(int num){
        int digital = 1;
        while (num / 10 != 0){
            num = num / 10 ;
            digital++;
        }
        return digital;
    }

    /**
     * 获取指定位数的数
     */
    private int digital(int num, int digital){
        int tmp = (int)Math.pow(10,digital-1);
        num = num / tmp;
        return num % 10;
    }

    public static void main(String[] args) {
        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 15670, 500000,
                (int[] data) -> new LSDRadixSort().sort(data));
    }
}
