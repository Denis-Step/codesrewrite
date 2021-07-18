package com.codez.game;

import com.codez.seed.Seeder;
import com.codez.seed.TextFileSource;

public class GameBuilder {
    public static Seeder seed;

    public GameBuilder (Seeder seed) {
        this.seed = seed;
    }

    public Game build() {
        WordsState ws = seed.createBoard();
        PlayerState ps = PlayerState.newGameState();
        return new Game(ws, ps);
    }
}
