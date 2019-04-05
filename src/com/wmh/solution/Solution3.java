package com.wmh.solution;



public class Solution3 {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode node = head;
		int i = 0;
		while (node != null) {
			i++;
			node = node.next;
		}
		int j = i - n + 1;
		ListNode node2 = head;
		if (j == 1) {
			ListNode next = node2.next;
			node2.next = null;
			node2 = next;
			return node2;
		}

		while (j-2>0) {
			node2 = node2.next;
			j--;
		}
		node2.next=node2.next.next;
		return head;


		/*ListNode node = head;
		int i = 0;
		while (node != null) {
			i++;
			node = node.next;
		}
		ListNode dummyHead = new ListNode(0);
		dummyHead.next=head;

		int k = i-n;
		ListNode node2 = dummyHead;
		while(k>0) {
			node2 = node2.next;
			k--;
		}
		node2.next=node2.next.next;
		return node2;*/

	}
}
