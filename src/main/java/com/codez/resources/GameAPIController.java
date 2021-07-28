package com.codez.resources;

import com.codez.game.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameAPIController {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String createGame() {
        Game g = Game.createGame();
        return g.ID;
    }

    public static String getGame(String ID) throws JsonProcessingException {
        Game g = Game.getGameById(ID);
        return mapper.writeValueAsString(g);
    }

}
