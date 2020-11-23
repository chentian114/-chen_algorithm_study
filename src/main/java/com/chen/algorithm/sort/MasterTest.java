package com.chen.algorithm.sort;

/**
 * @author: Chentian
 * @date: Created in 2020/11/12 6:14
 * @desc
 */
public class MasterTest {

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,2,3,1,0,9};
        System.out.println(findMax(arr,0,arr.length-1));
    }


    public static int findMax(int[] arr,int L, int R){
        if(L == R){
            return arr[L];
        }

        int mid = (L+R)/2;
        int leftMax = findMax(arr, L, mid);
        int rightMax = findMax(arr, mid+1 , R);
        return leftMax > rightMax ? leftMax : rightMax;
    }

}