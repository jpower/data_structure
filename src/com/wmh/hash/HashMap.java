package com.wmh.hash;

import java.util.TreeMap;

public class HashMap<K,V> {
	public static void main(String[] args) {
		Integer i01 = 59;
		int i02 = 59;
		
		@SuppressWarnings("deprecation")
		Integer i04 = new Integer(59);
		Integer i05 = new Integer(59);
		Integer i03 =Integer.valueOf(59);
		System.out.println(i03==i02);
	}
}
