package com.codez;
import com.codez.game.Game;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        JedisClient jeds = new JedisClient();

        HashMap<String, String> testWords = new HashMap<>();

        testWords.put("hello", "red");
        testWords.put("yo", "blue");
        testWords.put("gmorning", "black");

        jeds.set("testwords", testWords);

        System.out.println(jeds.getAll("testwords"));
        Game g = Game.createGame();
        System.out.println(g.getBoardState());

	// write your code here
    }
}
