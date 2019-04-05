package com.wmh.array;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

class Solution {
	private class Array<E> {

		private E[] data;
		private int size;

		// 构造函数，传入数组的容量capacity构造Array
		public Array(int capacity) {
			data = (E[]) new Object[capacity];
			size = 0;
		}

		// 无参数的构造函数，默认数组的容量capacity=10
		public Array() {
			this(10);
		}

		// 获取数组的容量
		public int getCapacity() {
			return data.length;
		}

		// 获取数组中的元素个数
		public int getSize() {
			return size;
		}

		// 返回数组是否为空
		public boolean isEmpty() {
			return size == 0;
		}

		// 在index索引的位置插入一个新元素e
		public void add(int index, E e) {

			if (index < 0 || index > size)
				throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

			if (size == data.length)
				resize(2 * data.length);
			for (int i = size - 1; i >= index; i--)
				data[i + 1] = data[i];

			data[index] = e;

			size++;
		}

		// 向所有元素后添加一个新元素
		public void addLast(E e) {
			add(size, e);
		}

		// 在所有元素前添加一个新元素
		public void addFirst(E e) {
			add(0, e);
		}

		// 交换值
		public void swap(int index1, int index2) {
			E e = data[index1];
			data[index1] = data[index2];
			data[index2] = e;
		}

		// 获取index索引位置的元素
		public E get(int index) {
			if (index < 0 || index >= size)
				throw new IllegalArgumentException("Get failed. Index is illegal.");
			return data[index];
		}

		public E getLast() {
			return get(size - 1);
		}

		public E getFirst() {
			return get(0);
		}

		// 修改index索引位置的元素为e
		public void set(int index, E e) {
			if (index < 0 || index >= size)
				throw new IllegalArgumentException("Set failed. Index is illegal.");
			data[index] = e;
		}

		// 查找数组中是否有元素e
		public boolean contains(E e) {
			for (int i = 0; i < size; i++) {
				if (data[i].equals(e))
					return true;
			}
			return false;
		}

		// 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
		public int find(E e) {
			for (int i = 0; i < size; i++) {
				if (data[i].equals(e))
					return i;
			}
			return -1;
		}

		// 从数组中删除index位置的元素, 返回删除的元素
		public E remove(int index) {
			if (index < 0 || index >= size)
				throw new IllegalArgumentException("Remove failed. Index is illegal.");

			E ret = data[index];
			for (int i = index + 1; i < size; i++)
				data[i - 1] = data[i];
			size--;
			data[size] = null; // loitering objects != memory leak

			if (size == data.length / 4 && data.length / 2 != 0)
				resize(data.length / 2);
			return ret;
		}

		// 从数组中删除第一个元素, 返回删除的元素
		public E removeFirst() {
			return remove(0);
		}

		// 从数组中删除最后一个元素, 返回删除的元素
		public E removeLast() {
			return remove(size - 1);
		}

		// 从数组中删除元素e
		public void removeElement(E e) {
			int index = find(e);
			if (index != -1)
				remove(index);
		}

		@Override
		public String toString() {

			StringBuilder res = new StringBuilder();
			res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
			res.append('[');
			for (int i = 0; i < size; i++) {
				res.append(data[i]);
				if (i != size - 1)
					res.append(", ");
			}
			res.append(']');
			return res.toString();
		}

		// 将数组空间的容量变成newCapacity大小
		private void resize(int newCapacity) {

			E[] newData = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++)
				newData[i] = data[i];
			data = newData;
		}
	}

	private class MaxHeap<E extends Comparable> {
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
				if (j + 1 < array.getSize() && array.get(j).compareTo(array.get(j + 1)) < 0) {
					j++;
				}
				if (array.get(index).compareTo(array.get(j)) >= 0)
					break;

				array.swap(index, j);
				index = j;
			}

		}
	}

	private class Node implements Comparable<Node> {
		public int data;
		public int size;

		public Node(int data, int size) {
			super();
			this.data = data;
			this.size = size;
		}

		public Node() {
			super();
		}

		@Override
		public int compareTo(Node n) {
			if (this.size > n.size) {
				return -1;
			} else if (this.size < n.size) {
				return 1;
			} else {
				return 0;
			}

		}

	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		TreeMap<Integer, Integer> map = new TreeMap();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		MaxHeap<Node> heap = new MaxHeap<>();
		Set<Integer> set = map.keySet();
		for (Integer integer : set) {
			if (heap.getSize() >= k) {
				if (map.get(integer) > heap.findMax().size) {
					heap.extractMax();
					heap.add(new Node(integer, map.get(integer)));
				}
			} else {
				heap.add(new Node(integer, map.get(integer)));
			}
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		while (!heap.isEmpty())
			list.addFirst(heap.extractMax().data);

		return list;
	}

}