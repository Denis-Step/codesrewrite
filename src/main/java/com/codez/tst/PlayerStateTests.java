package com.codez.tst;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codez.game.PlayerState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerStateTests {
    PlayerState ps;

    @Test
    @BeforeAll
    void newPS() {
        ps = PlayerState.newGameState();

        assertEquals(ps.getTeams(), PlayerState.DEFAULT_TEAMS);
        assertEquals(ps.getSpymasterTurn(), true);
        assertEquals(ps.getHint(), "");
        assertEquals(ps.getTeamTurn(), 0);
        assertEquals(ps.getRemainingGuesses(), 0);
    }

    @Test
    void changeTeamTurn() {
        PlayerState newPS = ps.changeTeamTurn();

        assertEquals(newPS.getTeamTurn(), 1);
        assertEquals(newPS.getSpymasterTurn(), true);
        assertEquals(newPS.getHint(), "");
        assertEquals(newPS.getRemainingGuesses(), 0);
    }

    @Test
    void makeSpymasterTurn() {
        PlayerState newPS = ps.makeSpymasterTurn("example", 3);

        assertEquals(newPS.getSpymasterTurn(), false);
        assertEquals(newPS.getTeamTurn(), 0);
        assertEquals(newPS.getHint(), "example");
        assertEquals(newPS.getRemainingGuesses(), 3);
    }

    @Test
    void makeChooserMove() {
        PlayerState newPS = ps.makeSpymasterTurn("example", 3);
        newPS = newPS.makeChooserMove();

        assertEquals(newPS.getRemainingGuesses(), 2);
        assertEquals(newPS.getHint(), "example");
        assertEquals(newPS.getTeamTurn(), 0);
        assertEquals(newPS.getSpymasterTurn(), false);
    }
}
