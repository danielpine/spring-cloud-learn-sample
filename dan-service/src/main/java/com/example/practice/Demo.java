package com.example.practice;

import java.util.Arrays;

public class Demo {
	public static void main(String[] args) {
		Arrays.asList("1", "2", "3").forEach(e -> System.out.println(e));
		Arrays.asList("1", "2", "3").forEach(System.out::println);
	}
}
