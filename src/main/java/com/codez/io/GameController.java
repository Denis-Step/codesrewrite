package com.codez.io;

import com.codez.game.Game;

import java.util.Map;

public class GameController implements IOController {

    private final JedisClient jedis;

    public GameController() {
        jedis = new JedisClient();
    }

    public void save(Game game) {
        String gameID = game.ID;
        Map<String, String> words = game.getBoard();
        Map<String, String> playerState = game.getTurn();
        jedis.set(gameID + ":words", words);
        jedis.set(gameID + ":player", playerState);

    }
}
