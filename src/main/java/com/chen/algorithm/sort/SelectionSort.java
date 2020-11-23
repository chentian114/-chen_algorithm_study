package com.chen.algorithm.sort;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/11/10 5:19
 * @desc 选择排序
 * 实质：遍历未排序序列，选择出最小的元素排列成有序序列
 * 时间复杂度： O(n^2)
 * 算法稳定性： 不稳定的排序方法
 */
public class SelectionSort implements Sort{

    @Override
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        for (int i = 0 ; i < arr.length-1 ; i++){
            int minIndex = i;
            for (int j = i + 1 ; j < arr.length ; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr,i, minIndex);
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
        new SelectionSort().sort(arr);
        System.out.println(Arrays.toString(arr));

        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 10, 500000,
                (int[] data) -> new SelectionSort().sort(data));
    }
}
