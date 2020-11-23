package com.chen.algorithm.sort;

import java.util.Arrays;

import static com.chen.algorithm.sort.PartitionArray.swap;

/**
 * @author: Chentian
 * @date: Created in 2020/11/18 5:11
 * @desc 荷兰国旗问题
 * 给定一个数组arr， 和一个数num， 请把小于num的数放在数组的左边， 等于num的数放在数组的中间， 大于num的数放在数组的右边。
 * 要求：额外空间复杂度O(1)， 时间复杂度O(N)
 */
public class NetherlandsFlag {

    public static void main(String[] args) {
        int[] arr = { 1 , 3 , 5 , 2 , 4 , 6, 0, 2, 3};
        int num = 4;
        System.out.println(Arrays.toString(arr));
        int[] partition = new NetherlandsFlag().partition(arr, 0, arr.length-1, num);
        System.out.println("num="+num+" partition arr:"+Arrays.toString(arr)+" partition:"+Arrays.toString(partition));
    }

    /**
     * 指定数组区间内元素进行分区
     * @param arr   指定数组
     * @param left  指定数组分区的左边界
     * @param right 指定数组分区的右边界
     * @param num 指定比较的数
     * @return 返回等于区域的左边界和右边界
     */
    public int[] partition(int[] arr, int left, int right,int num){
        if(arr == null || left > right){
            return new int[]{-1,-1};
        }

        int L = left - 1 ;
        int R = right + 1;
        int i = left;
       while (i < R){
            if(arr[i] < num){
                L++;
                swap(arr,i,L);
                i++;
            }else if(arr[i] > num){
                R--;
                swap(arr,i,R);
            }else {
                i++;
            }
        }

        return new int[]{L+1,R-1};
    }

}
