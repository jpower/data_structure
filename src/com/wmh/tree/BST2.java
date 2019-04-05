package com.wmh.tree;

import java.util.Stack;

import com.wmh.queue.LinkedQueue;
import com.wmh.queue.Queue;

public class BST2<E extends Comparable> {
	private class Node {
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
		root = add(root, e);
	}

	public Node add(Node node, E e) {
		if (node == null) {
			size++;
			return new Node(e);
		}
		if (e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		} else if (e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}
		return node;
	}

	public boolean contains(E e) {

		return contains(root, e);
	}

	public boolean contains(Node node, E e) {
		if (node == null) {
			return false;
		}
		if (node.e.equals(e)) {
			return true;
		} else if (node.e.compareTo(e) < 0) {
			return contains(node.left, e);
		} else {
			return contains(node.right, e);
		}
	}

	// 二分搜索树的前序遍历
	public void preOrder() {
		preOrder(root);
	}

	// 二分搜索树的递归前序遍历
	public void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.e + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	// 二分搜索树的非递归前序遍历
	public void preOrderNR() {
		Stack<Node> stack = new Stack<Node>();
		stack.add(root);
		while (!stack.empty()) {

			Node node = stack.pop();
			System.out.print(node.e + " ");
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);

		}
	}

	// 二分搜索树的层序遍历
	public void levelOrder() {
		Queue<Node> queue = new LinkedQueue<Node>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node node = queue.dequeue();
			System.out.print(node.e + " ");
			if (node.left != null)
				queue.enqueue(node.left);
			if (node.right != null)
				queue.enqueue(node.right);
		}
	}

	// 寻找二分搜索树的最小元素
	public E minimum() {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty!");

		return minimum(root).e;
	}

	// 返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		if (node.left == null)
			return node;
		return minimum(node.left);
	}

	// 寻找二分搜索树的最大元素
	public E maximum() {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty");

		return maximum(root).e;
	}

	// 返回以node为根的二分搜索树的最大值所在的节点
	private Node maximum(Node node) {
		if (node.right == null)
			return node;

		return maximum(node.right);
	}

	// 从二分搜索树中删除最小值所在节点, 返回最小值
	public E removeMin() {
		E ret = minimum();
		removeMin(root);
		return ret;
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

	// 从二分搜索树中删除最大值所在节点
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}

	// 删除掉以node为根的二分搜索树中的最大节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node) {

		if (node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			size--;
			return leftNode;
		}

		node.right = removeMax(node.right);
		return node;
	}

	public void remove(E e) {
		root = remove(root,e);
	}

	public Node remove(Node node, E e) {
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		} else if (e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		} else {
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			Node newNode = new Node(minimum(node.right).e);
			size++;
			newNode.right = removeMin(node.right);
			size--;
			newNode.left = node.left;
			node.left = node.right = null;
			return newNode;
		}

	}

	// 二分搜索树的中序遍历
	public void inOrder() {
		inOrder(root);
	}

	// 中序遍历以node为根的二分搜索树, 递归算法
	private void inOrder(Node node) {
		if (node == null)
			return;

		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}

	// 二分搜索树的后序遍历
	public void postOrder() {
		postOrder(root);
	}

	// 后序遍历以node为根的二分搜索树, 递归算法
	private void postOrder(Node node) {
		if (node == null)
			return;

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}

	// 生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node, int depth, StringBuilder res) {

		if (node == null) {
			res.append(generateDepthString(depth) + "null\n");
			return;
		}

		res.append(generateDepthString(depth) + node.e + "\n");
		generateBSTString(node.left, depth + 1, res);
		generateBSTString(node.right, depth + 1, res);
	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++)
			res.append("--");
		return res.toString();
	}

	public static void main(String[] args) {
		System.out.println();
	}
}
