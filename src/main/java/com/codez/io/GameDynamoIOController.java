package com.codez.io;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.codez.game.Game;

public class GameDynamoIOController implements IOController {

    private DynamoDBMapper mapper;

    public GameDynamoIOController() {
        mapper = new DynamoMapper().getMapper();
    }

    public void save(Game g) {
        GameDynamoDAO mappedGame = new GameDynamoDAO(g);
        mapper.save(mappedGame);
    }

    public Game getGame(String ID) {
        return this.mapper.load(GameDynamoDAO.class, ID).getGame();
    }
}
