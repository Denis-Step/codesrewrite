package com.codez;
import com.codez.seed.TextFileSource;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        JedisClient jeds = new JedisClient();

        HashMap<String, String> testWords = new HashMap<String, String>();

        testWords.put("hello", "red");
        testWords.put("yo", "blue");
        testWords.put("gmorning", "black");

        jeds.set("testwords", testWords);

        System.out.println(jeds.getAll("testwords"));

        TextFileSource tfs = new TextFileSource("/Users/denisstepanenko/Documents/codezrewrite/src/main/java/com/codez/seed/5lenwords.txt");

        WordsState ws = tfs.createBoard();
        System.out.println(ws.getValues());

	// write your code here
    }
}
