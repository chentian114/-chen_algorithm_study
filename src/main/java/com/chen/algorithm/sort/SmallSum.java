package com.chen.algorithm.sort;

import java.util.Scanner;

/**
 * @author: Chentian
 * @date: Created in 2020/11/17 6:33
 * @desc 小和问题
 * 牛客网-在线编程-程序员代码面试指南-计算数组的小和
 * https://www.nowcoder.com/ta/programmer-code-interview-guide
 * 题目描述：
 * 数组小和的定义如下：
 * 例如，数组s = [1, 3, 5, 2, 4, 6]，在s[0]的左边小于或等于s[0]的数的和为0；在s[1]的左边小于或等于s[1]的数的和为1；在s[2]的左边小于或等于s[2]的数的和为1+3=4；在s[3]的左边小于或等于s[3]的数的和为1；
 * 在s[4]的左边小于或等于s[4]的数的和为1+3+2=6；在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15。所以s的小和为0+1+4+1+6+15=27
 * 给定一个数组s，实现函数返回s的小和
 * [要求]
 * 时间复杂度为O(nlogn)，空间复杂度为O(n)
 * 解题思路：
 *   利用归并排序，采用分治思想，等分为两部分，
 *   对左边部分进和排序并计算左边部分的小和，
 *   对右边部分进行排序并计算右边部分的小和，
 *   利用外排对两个有序数组进行合并；合并过程中，当左边数组的元素小于等于右边数组的元素时，累加小和 右边数组剩余元素数量 * 元素值 mergeSum += (arr[a] * (R-b+1))
 */
public class SmallSum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i <n; i++){
            arr[i] = sc.nextInt();
        }
        long smallSum = new SmallSum().smallSum(arr);
        System.out.println(smallSum);
    }

    public long smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0L;
        }
        return smallSum(arr, 0 , arr.length-1);
    }

    public long smallSum(int[] arr, int L , int R){
        if(L == R){
            return 0L ;
        }

        // int mid = ( L + R ) / 2; //防止值溢出
        int mid = L + ((R - L) >> 1);
        long leftSum = smallSum(arr , L , mid);
        long rightSum = smallSum(arr, mid+1 , R);
        long mergeSum = merge(arr, L , mid , R );

        return leftSum + rightSum + mergeSum;
    }

    public long merge(int[] arr, int L , int mid , int R){
        long mergeSum = 0 ;

        int[] newArr = new int[R - L +1];
        int a = L ;
        int b = mid + 1;
        int c = 0 ;
        while( a <= mid && b <= R){
            if(arr[a] <= arr[b]){
                mergeSum += (arr[a] * (R-b+1));
                newArr[c++] = arr[a++];
            }else{
                newArr[c++] = arr[b++];
            }
        }
        while(a <= mid){
            newArr[c++] = arr[a++];
        }
        while(b <= R){
            newArr[c++] = arr[b++];
        }

        for(int i = 0 ; i < newArr.length ; i++){
            arr[L+i] = newArr[i];
        }
        return mergeSum;
    }
}