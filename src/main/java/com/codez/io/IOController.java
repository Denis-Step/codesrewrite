package com.codez.io;

import com.codez.game.Game;

public interface IOController {

    public boolean exists(String Id);

    public void save(Game game);

    public Game getGame(String ID);

}
