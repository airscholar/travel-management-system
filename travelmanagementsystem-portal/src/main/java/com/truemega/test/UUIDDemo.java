package com.truemega.test;

import java.util.UUID;

public class UUIDDemo {
	public static void main(String[] args) {
		// creating UUID
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

		// checking the value of random UUID
		for (int i = 0; i < 10; i++)
			;
		// System.out.println("Random UUID value: " + uid.randomUUID());

		UUID idOne = UUID.randomUUID();
		UUID idTwo = UUID.randomUUID();
		System.out.println("UUID One: " + idOne.toString());
		System.out.println("UUID Two: " + idTwo);
	}
}
