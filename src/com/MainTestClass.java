package com;

import java.util.HashMap;
import java.util.Map;

public class MainTestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Map<String, String> keys = new HashMap<>();
		 keys.put("courseUrl_1", "s");
		 keys.put("courseUrl_2", "s2");
		 
		 System.out.println(keys.values().contains("courseUrl_"));

	}

}
