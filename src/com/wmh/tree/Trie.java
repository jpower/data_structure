package com.wmh.tree;

import java.util.Comparator;
import java.util.TreeMap;

public class Trie {
	private class Node {
		public boolean isWord;
		public TreeMap<Character, Node> next;

		public Node() {
			next = new TreeMap();
		}

		public Node(boolean isWord) {
			this();
			this.isWord = isWord;
		}
	}

	private Node root;

	public Trie() {
		root = new Node();
	}

	public void insert(String word) {
		Node node = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!node.next.containsKey(ch)) {
				node.next.put(ch, new Node());
			}
			node = node.next.get(ch);
		}
		node.isWord = true;
	}

	public boolean search(String word) {
		Node node = root;
		for(int i=0;i<word.length();i++) {
			char ch = word.charAt(i);
			if(!node.next.containsKey(ch)) {
				return false;
			}
			node = node.next.get(ch);
		}
		if(node.isWord) {
			return true;
		}
		return false;
	}

	public boolean startsWith(String prefix) {
		Node node = root;
		for(int i=0;i<prefix.length();i++) {
			char ch = prefix.charAt(i);
			if(!node.next.containsKey(ch)) {
				return false;
			}
			node = node.next.get(ch);
		}
			return true;
	}

}
