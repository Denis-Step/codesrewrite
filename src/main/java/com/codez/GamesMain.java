package com.codez;
import com.codez.game.Game;

public class GamesMain {

    public static void main(String[] args) {
        Game g = Game.createGame();
        g = g.makeSpymasterTurn("rouge", 3);
        g.save();
        //Game ng = Game.getGameById(g.ID);

        //System.out.println(ng.getTurn());

	// write your code here
    }
}
