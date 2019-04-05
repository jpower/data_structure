package com.wmh.tree;

/// Leetcode 211. Add and Search Word - Data structure design
/// https://leetcode.com/problems/add-and-search-word-data-structure-design/description/

import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class WordDictionary {

	private class Node {

		public boolean isWord;
		public TreeMap<Character, Node> next;

		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}

		public Node() {
			this(false);
		}
	}

	private Node root;

	public WordDictionary() {
		root = new Node();
	}

	public void addWord(String word) {

		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (cur.next.get(c) == null)
				cur.next.put(c, new Node());
			cur = cur.next.get(c);
		}
		cur.isWord = true;
	}

	public boolean search(String word) {
		return match(root, word, 0);
	}

	private boolean match(Node node, String word, int index) {
		if (index == word.length())
			return node.isWord;
		char ch = word.charAt(index);

		if (ch == '.') {
			Set<Character> keySet = node.next.keySet();
			for (Character character : keySet) {
				if(match(node.next.get(character), word, index + 1))
					return true;
			}
			return false;
		} else {
			if (!node.next.containsKey(ch)) {
				return false;
			}
			return match(node.next.get(ch), word, index + 1);
		}

	}
}
