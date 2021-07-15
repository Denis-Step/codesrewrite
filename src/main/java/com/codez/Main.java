package com.codez;
import com.codez.game.Game;
import com.codez.game.GameBuilder;
import com.codez.io.GameController;
import com.codez.io.JedisClient;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        JedisClient jeds = new JedisClient();

        GameBuilder gb = new GameBuilder();
        Game g = gb.build();
        GameController gc = new GameController();
        gc.save(g);

        System.out.println(g.ID);
        System.out.println(jeds.getAll(g.ID + ":words"));
        System.out.println(jeds.getAll(g.ID + ":player"));

	// write your code here
    }
}
