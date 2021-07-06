package com.codez;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JedisClient jeds = new JedisClient();

        HashMap<String, String> testWords = new HashMap<String, String>();

        testWords.put("hello", "red");
        testWords.put("yo", "blue");
        testWords.put("gmorning", "black");

        jeds.set("testwords", testWords);

        System.out.println(jeds.getAll("testwords"));

        TextFileSource tfs = new TextFileSource("/Users/denisstepanenko/Documents/codezrewrite/src/main/java/com/codez/5lenwords.txt");

        System.out.println(tfs.getWords()[500]);

	// write your code here
    }
}
