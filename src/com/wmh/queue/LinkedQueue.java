package com.wmh.queue;

public class LinkedQueue<E> implements Queue<E> {
	private class Node {
		public E e;
		public Node next;

		public Node(E e, Node next) {
			super();
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			super();
			this.e = e;
		}

		public Node() {
			super();
		}

		@Override
		public String toString() {
			return e.toString();
		}

	}

	private Node head, tail;
	private int size;

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head.next == null;
	}

	@Override
	public void enqueue(E e) {
		if (tail == null) {
			tail = new Node(e);
			head = tail;
		} else {
			tail.next = new Node(e);
			tail = tail.next;
			size++;
		}

	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		Node node = head;
		head = head.next;
		node.next = null;
		if (head == null) {
			tail = head;
		}
		size--;
		return node.e;
	}

	@Override
	public E getFront() {
		if (isEmpty())
			throw new IllegalArgumentException("Queue is empty.");
		return head.e;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: front ");

		Node cur = head;
		while (cur != null) {
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL tail");
		return res.toString();
	}

}
