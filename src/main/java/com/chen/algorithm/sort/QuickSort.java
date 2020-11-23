package com.chen.algorithm.sort;

import static com.chen.algorithm.sort.PartitionArray.swap;

/**
 * @author: Chentian
 * @date: Created in 2020/11/19 5:24
 * @desc 随机快速排序
 * 实质：通过一趟排序将要排序的数据分割成独立的三部分，随机选择数组中的某一个值X，
 * 其中左部分的所有数据都比X都要小，中间部分所有数据都等于X，右边部分的所有数据都比X要大
 * 然后再按此方法对左、右两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 时间复杂度： O(n * logn)
 * 空间复杂度： O(logn)
 * 算法稳定性： 不稳定的排序方法
 */
public class QuickSort implements Sort{
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }

        quickSort(arr, 0 , arr.length-1);
    }

    private void quickSort(int[] arr, int L, int R) {
        if( L >= R ){
            return;
        }

        int[] mix = partition(arr,L,R);
        quickSort(arr,0,mix[0]-1);
        quickSort(arr,mix[1]+1,R);
    }

    private int[] partition(int[] arr, int l, int r) {
        int s = (int) (Math.random() * (r - l + 1)) + l;
        int num = arr[s];

        int L = l-1 ;
        int R = r+1;
        int i = l ;
        while (i<R){
            if(arr[i] < num){
                L++;
                swap(arr,i,L);
                i++;
            }
            else if(arr[i] > num){
                R--;
                swap(arr,i,R);
            }else {
                i++;
            }
        }

        return new int[]{L+1,R-1};
    }

    public static void main(String[] args) {

        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 10, 500000,
                (int[] data) -> new QuickSort().sort(data));
    }
}
