package com.chen.algorithm.sort;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/11/10 4:33
 * @desc 冒泡排序
 * 实质：把小的元素往前调或者把大的元素往后调
 * 时间复杂度：O(n^2)
 * 算法稳定性：稳定排序算法
 */
public class BubbleSort implements Sort{

    @Override
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int i = arr.length-1 ; i > 0 ; i--){
            boolean flag = true;
            for (int j = 0 ; j < i ; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                    flag = false;
                }
            }
            if(flag){
                return;
            }
        }
        return;
    }

    private void swap(int[] arr, int a, int b) {
        int cnt = arr[a];
        arr[a] = arr[b];
        arr[b] = cnt;
    }

    public static void main(String[] args) {
        int[] arr = {1,8,4,5,3,8,2,9,2};
        new BubbleSort().sort(arr);
        System.out.println(Arrays.toString(arr));

        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 10, 500000,
                (int[] data) -> new BubbleSort().sort(data));
    }

}
