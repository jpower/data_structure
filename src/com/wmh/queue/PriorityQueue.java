package com.wmh.queue;

import java.util.Random;

import com.wmh.heap.MaxHeap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
	private MaxHeap<E> heap;

	public PriorityQueue() {
		heap = new MaxHeap<E>();
	}

	public PriorityQueue(int capacity) {
		heap = new MaxHeap(capacity);
	}

	@Override
	public int getSize() {
		return heap.getSize();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		heap.add(e);
	}

	@Override
	public E dequeue() {

		return heap.extractMax();
	}

	@Override
	public E getFront() {
		return heap.findMax();
	}

	public static void main(String[] args) {
		Queue<Integer> queue = new PriorityQueue<>(); 
		for(int i = 0; i<100;i++) {
			queue.enqueue(new Random().nextInt(100));
		}
		for(int i=0;i<100;i++) {
			System.out.println(queue.dequeue());
		}
		
	}

}
