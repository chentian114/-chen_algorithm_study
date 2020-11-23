package com.chen.algorithm.sort;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/11/18 4:59
 * @desc 数组分区问题
 * 给定一个数组arr， 和一个数num， 请把小于num的数放在数组的左边， 等于num的数放在数组的中间， 大于num的数放在数组的右边。
 * 要求：额外空间复杂度O(1)， 时间复杂度O(N)
 */
public class PartitionArray {

    public static void main(String[] args) {
        int[] arr = { 1 , 3 , 5 , 2 , 4 , 6, 0, 2, 3};
        int num = 3;
        new PartitionArray().partition(arr,num);
        System.out.println(Arrays.toString(arr));
    }

    public int[] partition(int[] arr,int num){
        if(arr == null || arr.length < 2){
            return arr;
        }

        int X = -1 ;
        for (int i = 0 ; i < arr.length ; i++){
            if(arr[i] <= num){
                X++;
                swap(arr,i,X);
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int a, int b) {
        if(a == b){
            return;
        }
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }
}
