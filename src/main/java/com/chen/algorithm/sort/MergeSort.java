package com.chen.algorithm.sort;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/11/17 4:59
 * @desc 归并排序
 * 实质：采用分治法，将已有序的子序列合并，得到完全有序的序列；
 * 时间复杂度： O(n * logn)
 * 空间复杂度： T(n)
 * 算法稳定性： 稳定的排序方法
 */
public class MergeSort implements Sort{

    @Override
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        mergeSort(arr, 0 , arr.length-1);
        return;
    }

    private int[] mergeSort(int[] arr, int L, int R){
        if(L == R){
            return arr;
        }

        int mid = L + ( (R-L) >> 1 );
        mergeSort(arr, L , mid);
        mergeSort(arr,mid+1 , R);
        merge(arr, L, mid, R);

        return arr;
    }

    private void merge(int[] arr, int L, int mid, int R) {
        int[] newArr = new int[R-L+1];
        int a = L ;
        int b = mid + 1 ;
        int c = 0 ;
        while ( a <= mid && b <= R ){
            if(arr[a] > arr[b]){
                newArr[c++] = arr[b++];
            }else {
                newArr[c++] = arr[a++];
            }
        }
        while ( a <= mid){
            newArr[c++] = arr[a++];
        }
        while ( b <= R ){
            newArr[c++] = arr[b++];
        }

        for (int i = 0; i < newArr.length ; i++){
            arr[L+i] = newArr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,8,4,5,3,8,2,9,2};
        new MergeSort().sort(arr);
        System.out.println(Arrays.toString(arr));

        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 10, 500000,
                (int[] data) -> new MergeSort().sort(data));
    }
}
