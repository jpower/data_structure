package com.wmh.map;

public class BSTMap<K extends Comparable, V> implements Map<K, V> {
	private class Node {
		public K k;
		public V v;
		public Node left;
		public Node right;

		public Node(K k, V v) {
			super();
			this.k = k;
			this.v = v;
		}

		public String toString() {
			return k + ":" + v;
		}

	}

	private int size;
	private Node root;

	@Override
	public void add(K key, V value) {
		root = add(root, key, value);

	}

	public Node add(Node node, K key, V value) {
		if (node == null) {
			Node newNode = new Node(key, value);
			size++;
			return newNode;
		}
		if (key.compareTo(node.k) < 0) {
			node.left = add(node.left, key, value);
		} else if (key.compareTo(node.k) > 0) {
			node.right = add(node.right, key, value);
		} else {
			node.v = value;
		}
		return node;
	}

	@Override
	public boolean contains(K key) {
		if (size == 0) {
			return false;
		}
		return contains(root, key);
	}

	// 返回以node为根节点的二分搜索树中，key所在的节点
	private Node getNode(Node node, K key) {

		if (node == null)
			return null;

		if (key.equals(node.k))
			return node;
		else if (key.compareTo(node.k) < 0)
			return getNode(node.left, key);
		else // if(key.compareTo(node.key) > 0)
			return getNode(node.right, key);
	}

	public boolean contains(Node node, K key) {
		return getNode(root, key) != null;

	}

	@Override
	public V get(K key) {

		Node node = getNode(root, key);
		return node == null ? null : node.v;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if (node == null)
			throw new IllegalArgumentException(key + " doesn't exist!");

		node.v = newValue;
	}

	// 返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		if (node.left == null)
			return node;
		return minimum(node.left);
	}

	// 删除掉以node为根的二分搜索树中的最小节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {

		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			return rightNode;
		}

		node.left = removeMin(node.left);
		return node;
	}

	// 从二分搜索树中删除键为key的节点
	@Override
	public V remove(K key) {

		Node node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.v;
		}
		return null;
	}

	private Node remove(Node node, K key) {

		if (node == null)
			return null;

		if (key.compareTo(node.v) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else if (key.compareTo(node.v) > 0) {
			node.right = remove(node.right, key);
			return node;
		} else { // key.compareTo(node.key) == 0

			// 待删除节点左子树为空的情况
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}

			// 待删除节点右子树为空的情况
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}

			// 待删除节点左右子树均不为空的情况

			// 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
			// 用这个节点顶替待删除节点的位置
			Node successor = minimum(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;

			return successor;
		}
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
