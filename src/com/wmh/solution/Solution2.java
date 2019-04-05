package com.wmh.solution;

import java.util.LinkedHashMap;
import java.util.Set;



public class Solution2 {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	public ListNode deleteDuplicates(ListNode head) {
		if (head != null) {
			LinkedHashMap<Integer, Integer> map = new LinkedHashMap();
			ListNode node = head;
			while (node != null) {
				map.put(node.val, node.val);
				node = node.next;
			}
			ListNode head2 = new ListNode(map.get(head.val));
			ListNode node2 = head2;
			int i = 0;
			for (Integer key : map.keySet()) {
				if (i > 0) {
					ListNode n = new ListNode(key);
					node2.next = n;
					node2 = n;
				}
				i++;
			}
			return head2;
		}else{
			return head;
		}

	}

}