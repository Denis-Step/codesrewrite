package com.codez;

public class Main {

    public static void main(String[] args) {

        System.out.println("Working");

        JedisClient jeds = new JedisClient();

        jeds.set("test", "hot");
        String test = jeds.get("test");
        System.out.println(test);
	// write your code here
    }
}
