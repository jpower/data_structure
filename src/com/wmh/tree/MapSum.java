package com.wmh.tree;

import java.util.TreeMap;

public class MapSum {
	private class Node {
		public int value;
		public TreeMap<Character, Node> next;

		public Node(int value) {
			this();
			this.value = value;

		}

		public Node() {
			next = new TreeMap();
		}

	}

	private Node root;

	public MapSum() {
		root = new Node();
	}

	public void insert(String key, int val) {
		Node node = root;
		for (int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			if (!node.next.containsKey(ch)) {
				node.next.put(ch, new Node());
			}
			node = node.next.get(ch);
		}
		node.value = val;
	}

	public int sum(String prefix) {
		Node node = root;
		
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if(node.next.containsKey(ch)) 
				node = node.next.get(ch);
			else {
				return 0;
			}
		}
		int sum = node.value;
		return sum(node,sum);
	}
	public int sum(Node node,int sum) {
		if(!node.next.isEmpty()){
			for(char ch:node.next.keySet()) {
				sum+=sum(node.next.get(ch),node.next.get(ch).value);
			}
		}
		return sum;
	}

}
