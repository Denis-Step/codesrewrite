package com.codez.io;
import java.util.Map;

import com.codez.game.Game;
import redis.clients.jedis.Jedis;

public class JedisClient implements IOController {

    // Guaranteed to work if no exceptions thrown.
    // AVOID SINGLETON PATTERN!!!
    // Percolate up RuntimeExceptions from base client.
    // @TODO: Make constructor a private method and refactor to turn strings into URI's.

    private final Jedis jedis;

    public JedisClient(){

        this.jedis = new Jedis("localhost", 6379);
        jedis.ping("Pong!");
    }

    public JedisClient(String host, int port){

        this.jedis = new Jedis(host, port);
        jedis.ping("Pong!");
    }

    public String get(String key){
        return this.jedis.get(key);
    }

    public String get(String key, String field) {
        return this.jedis.hget(key, field);
    }

    public Map<String, String> getAll(String key) {
        return this.jedis.hgetAll(key);
    }

    public void set (String key, String value) {
        this.jedis.set(key, value);
    }

    public void set (String key, Map<String, String> values) {
        this.jedis.hset(key, values);
    }

    public void save(Game game) {

    }

}
