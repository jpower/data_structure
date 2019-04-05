package com.wmh.heap;

import com.wmh.array.Array;


public class MaxHeap<E extends Comparable> {
    private Array<E> array;
    public MaxHeap(int capacity) {
        array = new Array<E>(capacity);
    }

    public MaxHeap() {
        array = new Array<E>();
    }

    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        array.addLast(e);
        int index = array.find(e);
        siftUp(index);

    }

    // 上浮
    public void siftUp(int index) {

        while (index > 0 && array.get(index).compareTo(array.get(parent(index))) > 0) {
            array.swap(index, parent(index));
            index = parent(index);
        }
    }

    // 看堆中的最大元素
    public E findMax() {
        if (array.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return array.get(0);
    }

    // 取出最大元素
    public E extractMax() {
        E e = findMax();
        array.swap(0, array.getSize() - 1);
        array.removeLast();
        siftDown(0);
        return e;

    }

    // 下沉
    public void siftDown(int index) {
        while (leftChild(index) < array.getSize()) {
            int j = leftChild(index);
            j = j + 1 < array.getSize() && array.get(j).compareTo(array.get(j + 1)) < 0 ? j + 1 : j;
            if (array.get(index).compareTo(array.get(j)) >= 0) {
                break;
            }

            array.swap(index, j);
            index = j;
        }

    }
}
