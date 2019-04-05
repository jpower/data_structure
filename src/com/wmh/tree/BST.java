package com.wmh.tree;

public class BST {
	private class Node<E extends Comparable> {
		public E e;
		public Node left;
		public Node right;

		public Node(E e) {
			super();
			this.e = e;
		}

		public String toString() {
			return e.toString();
		}

		private int size;
		private Node root;

		public int getSize() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void add(E e) {
			if (root == null) {
				root = new Node(e);
				size++;
			} else {
				add(root, e);
			}
		}

		public void add(Node node,E e) {
			if(e.compareTo(node.e)==0) {
				return;
			}
			if (e.compareTo(node.e) < 0 && node.left == null) {
				node.left = new Node(e);
				size++;

			}else if (e.compareTo(node.e) > 0 && node.right == null) {
				node.right = new Node(e);
				size++;
			}
			if(e.compareTo(node.e) < 0) {
				add(node.left,e);
			}else {
				add(node.right,e);
			}
		}

	}
}
