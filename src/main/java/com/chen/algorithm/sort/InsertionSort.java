package com.chen.algorithm.sort;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/11/11 7:41
 * @desc 插入排序
 * 实质：将一个记录插入到已经排好序的有序表中，从而得到一个新的、记录数增1的有序表；
 *     直到整个序列排为有序的过程。
 * 时间复杂度： O(n^2)
 * 空间复杂度： O(1)
 * 算法稳定性： 稳定的排序方法
 */
public class InsertionSort implements Sort{

    @Override
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }

        for (int i = 1 ; i < arr.length ; i++){
            for (int j = i ; j > 0 && arr[j] < arr[j-1]; j--){
                swap(arr,j-1, j);
            }
        }
        return;
    }

    private void swap(int[] arr, int i, int j) {
        if(i == j ){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {1,8,4,5,3,8,2,9,2};
        new InsertionSort().sort(arr);
        System.out.println(Arrays.toString(arr));

        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 10, 500000,
                (int[] data) -> new InsertionSort().sort(data));
    }
}
