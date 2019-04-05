package com.wmh.queue;

import com.wmh.array.Array;

public class LoopQueue<E> implements Queue<E> {
	private E[] data;
	private int start;
	private int tail;
	private int size;

	public LoopQueue() {
		this(10);
	}

	public LoopQueue(int capacity) {
		data = (E[]) new Object[capacity + 1];
	}

	public int getCapacity() {
		return data.length - 1;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return start == tail;
	}

	@Override
	public void enqueue(E e) {
		if ((tail + 1) % data.length == start) {
			reSize(getCapacity() * 2);
		}
		data[tail] = e;
		tail = (tail + 1) % data.length;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		E e = data[start];
		data[start] = null;
		start = (start + 1) % data.length;
		size--;
		if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
			reSize(getCapacity() / 2);
		}

		return e;
	}

	@Override
	public E getFront() {
		if (isEmpty())
			throw new IllegalArgumentException("Queue is empty.");
		return data[start];
	}

	public void reSize(int newCapacity) {
		E[] newData = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++)
			newData[i] = data[(i + start) % data.length];

		data = newData;
		start = 0;
		tail = size;
	}

	@Override
	public String toString() {

		StringBuilder res = new StringBuilder();
		res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
		res.append("front [");
		for (int i = start; i != tail; i = (i + 1) % data.length) {
			res.append(data[i]);
			if ((i + 1) % data.length != tail)
				res.append(", ");
		}
		res.append("] tail");
		return res.toString();
	}

	public static void main(String[] args) {
		LoopQueue<Integer> queue = new LoopQueue<Integer>();
		queue.enqueue(8978);
		queue.enqueue(67);
		queue.enqueue(3324);
		queue.enqueue(324);
		queue.enqueue(2);

		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);

	}

}
