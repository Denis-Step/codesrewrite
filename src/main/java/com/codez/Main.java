package com.codez;
import com.codez.game.Game;
import com.codez.io.GameController;
import com.codez.io.JedisClient;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        JedisClient jeds = new JedisClient();

        Game g = Game.createGame();
        GameController gc = new GameController();

        g = g.makeSpymasterTurn("rouge", 3);

        System.out.println(g.getTurn());

	// write your code here
    }
}
