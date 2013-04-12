package com.esofthead;

public class App {
	public static void main(String[] args) throws Exception {
		while (true) {
			Thread.sleep(2000);
			System.out.println(String.valueOf(System.currentTimeMillis()));
		}
	}
}
