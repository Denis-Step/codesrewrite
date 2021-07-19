package com.codez.io;
import java.util.Map;

import com.codez.game.Game;
import com.codez.game.PlayerState;
import com.codez.game.WordsState;
import io.lettuce.core.RedisException;
import redis.clients.jedis.Jedis;

public class RedisController implements IOController {

    // Guaranteed to work if no exceptions thrown.
    // AVOID SINGLETON PATTERN!!!
    // Percolate up RuntimeExceptions from base client.
    // @TODO: Make constructor a private method and refactor to turn strings into URI's.

    private final Jedis jedis;

    public RedisController(){

        this.jedis = new Jedis("localhost", 6379);
        jedis.ping("Pong!");
    }

    public RedisController(String host, int port){

        this.jedis = new Jedis(host, port);
        jedis.ping("Pong!");
    }

    private String get(String key){
        return this.jedis.get(key);
    }

    private String get(String key, String field) {
        return this.jedis.hget(key, field);
    }

    private Map<String, String> getAll(String key) {
        return this.jedis.hgetAll(key);
    }

    private void set (String key, String value) {
        this.jedis.set(key, value);
    }

    private void set (String key, Map<String, String> values) {
        this.jedis.hset(key, values);
    }

    public void save(Game game) {
        String ID = game.ID;
        Map<String, Map<String, String>> gm = game.toMap();

        set(ID + ":playerState", gm.get("playerState"));
        set(ID + ":wordsState", gm.get("wordsState"));

    }

    public boolean exists(String ID) {
        try {
            getAll(ID + ":playerState");
            return true;
        } catch (RedisException re) {
            return false;
        }
    }

    public Game getGame(String ID) {
        Map<String, String> wsMap = getAll(ID + ":wordsState");
        Map<String, String> psMap = getAll(ID + ":playerState");

        WordsState ws = new WordsState(wsMap);
        System.out.println(psMap.keySet());
        PlayerState ps = new PlayerState(
                    Integer.parseInt(psMap.get("teamTurn")),
                    Boolean.parseBoolean(psMap.get("spymasterTurn")),
                    psMap.get("hint"),
                    Integer.parseInt(psMap.get("remainingGuesses")),
                    psMap.get("teams").split(",")
                    );
        Game g = new Game(ws, ps);
        return g;
    }

}
