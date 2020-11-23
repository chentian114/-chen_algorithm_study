package com.chen.algorithm.sort;

import java.util.*;

/**
 * @author: Chentian
 * @date: Created in 2020/11/23 23:38
 * @desc 牛客网-题库-在线编程-程序员代码面试指南-CD80-随时找到数据流的中位数
 * https://www.nowcoder.com/ta/programmer-code-interview-guide
 * 有一个源源不断的吐出整数的数据流，假设你有足够的空间来保存吐出的数。
 * 请设计一个名叫MedianHolder的结构，MedianHolder可以随时取得之前吐出所有数的中位数。
 * [要求]
 * 1. 如果MedianHolder已经保存了吐出的N个数，那么将一个新数加入到MedianHolder的过程，
 * 其时间复杂度是O(logN)。
 * 2. 取得已经吐出的N个数整体的中位数的过程，时间复杂度为O(1)
 */
public class MedianHolder {

    //小根堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    //大根堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

    /**
     * 添加元素
     * @param num 新增元素
     */
    public void add(int num){
        if(maxHeap.isEmpty() && minHeap.isEmpty()){
            maxHeap.add(num);
            return;
        }

        if (num >= maxHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }

        if(maxHeap.size() > minHeap.size() && (maxHeap.size() - minHeap.size()) > 1){
            minHeap.add(maxHeap.poll());
        }

        if(minHeap.size() > maxHeap.size() && (minHeap.size() - maxHeap.size() > 1)){
            maxHeap.add(minHeap.poll());
        }
    }

    /**
     * 获取中位数
     * @return 中位数
     */
    public Double median(){
        int size1 = maxHeap.size();
        int size2 = minHeap.size();

        if( ( size1 + size2) % 2 == 0 ){
            return ( maxHeap.peek() + minHeap.peek() ) / 2.0;
        }

        return size1 > size2 ? (double)maxHeap.peek() : (double)minHeap.peek();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        MedianHolder medianHolder = new MedianHolder();
        for (int i = 0 ; i < times ; i++){
            int op = sc.nextInt();
            if(op == 1){
                medianHolder.add(sc.nextInt());
            }else{

                if(medianHolder.minHeap.isEmpty() && medianHolder.maxHeap.isEmpty()){
                    System.out.println(-1);
                }else {
                    System.out.printf("%.1f\n", medianHolder.median());
                }
            }
        }
    }
}
