package com.codez.io;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.codez.game.Game;

public class DynamoController implements IOController {

    private DynamoDBMapper mapper;

    public DynamoController() {
        mapper = new DynamoMapper().getMapper();
    }

    public void save(Game g) {
        GameDynamoDAO mappedGame = new GameDynamoDAO(g);
        mapper.save(mappedGame);
    }

    public Game load(String ID) {
        return this.mapper.load(GameDynamoDAO.class, ID).getGame();
    }

    public boolean exists(String ID) {
        try {
            this.mapper.load(GameDynamoDAO.class, ID).getGame();
            return true;
        } catch (ResourceNotFoundException exception) {
            return false;
        }
    }

    public void delete(String ID) {

    }

    public void delete(Game g) {
        try {
            GameDynamoDAO mappedGame = new GameDynamoDAO(g);
            this.mapper.delete(mappedGame);
        } catch (ResourceNotFoundException exception) {
            // Swallow delete exceptions on games not found.
            return;
        }
    }
}
