package com.wmh.map;

public class LinkedMap<K, V> implements Map<K, V> {
	private class Node {
		public K key;
		public V value;
		public Node next;

		public Node(K key, V value, LinkedMap<K, V>.Node next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String toString() {
			return key + ":" + value;
		}

	}

	private int size;
	private Node dummyHead;

	public LinkedMap() {
		dummyHead = new Node(null, null);
	}

	public Node getNode(K key) {
		Node node = dummyHead.next;
		while (node != null) {
			if (node.key.equals(key)) {
				return node;
			}
			node = node.next;
		}
		return null;

	}

	@Override
	public void add(K key, V value) {
		Node node = getNode(key);
		if (node == null) {
			dummyHead.next = new Node(key, value, dummyHead.next);
		} else {
			node.value = value;
		}
	}

	@Override
	public boolean contains(K key) {
		return getNode(key) != null;
	}

	@Override
	public V get(K key) {
		LinkedMap<K, V>.Node node = getNode(key);
		return node == null ? null : node.value;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(key);
		if (node == null)
			throw new IllegalArgumentException(key + " doesn't exist!");

		node.value = newValue;
	}

	@Override
	public V remove(K key) {
		Node node = dummyHead;
		while (node.next != null) {
			if (node.next.key.equals(key)) {
				Node delNode = node.next;
				node.next = delNode.next;
				delNode.next = null;
				size--;
				return node.value;
			}
			node = node.next;
		}

		return null;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
