package com.wmh.linkedList;

public class LinkedList<E> {
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

	private Node dummyHead;
	private int size;

	public LinkedList() {
		dummyHead = new Node();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	public void add(int index, E e) {

		if (index < 0 || index > size)
			throw new IllegalArgumentException("Add failed. Illegal index.");

		Node prev = dummyHead;
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		prev.next = new Node(e, prev.next);
		size++;

	}

	// 在链表头添加新的元素e
	public void addFirst(E e) {
		add(0, e);
	}
	// 在链表末尾添加新的元素e
	public void addLast(E e) {
		add(size, e);
	}

	// 删除指定位置的元素 并返回值
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Remove failed. Index is illegal.");
		Node prev = dummyHead;
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		Node node = prev.next;
		prev.next = node.next;
		node.next = null;
		size--;
		return node.e;
	}

	// 从链表中删除第一个元素, 返回删除的元素
	public E removeFirst() {
		return remove(0);
	}

	// 从链表中删除最后一个元素, 返回删除的元素
	public E removeLast() {
		return remove(size - 1);
	}

	// 从链表中删除元素e
	public void removeElement(E e) {
		Node node = dummyHead;
		while (node.next != null) {
			if (node.next.e.equals(e)) {
				Node delNode = node.next;
				node.next = delNode.next;
				delNode.next = null;
				size--;
			} else {
				node = node.next;
			}
		}

	}

	public boolean contains(E e) {
		Node node = dummyHead;
		for (int i = 0; i < size; i++) {
			node = node.next;
			if (node.e.equals(e)) {
				return true;
			}
		}
		return false;
	}

	// 修改链表的第index(0-based)个位置的元素为e
	// 在链表中不是一个常用的操作，练习用：）
	public void set(int index, E e) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Set failed. Illegal index.");

		Node node = dummyHead.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		node.e = e;
	}

	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Get failed. Illegal index.");

		Node node = dummyHead.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.e;
	}

	// 获得链表的第一个元素
	public E getFirst() {
		return get(0);
	}

	// 获得链表的最后一个元素
	public E getLast() {
		return get(size - 1);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		Node cur = dummyHead.next;
		while (cur != null) {
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL");

		return res.toString();
	}

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.addLast(2);
		l.addLast(3);
		l.addLast(4);
		l.addLast(2);
		l.addLast(2);
		System.out.println(l);
		l.removeElement(2);
		System.out.println(l);
	}
}
