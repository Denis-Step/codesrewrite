
package com.codez.tst;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codez.game.Game;
import com.codez.game.GameBuilder;
import com.codez.game.PlayerState;
import com.codez.game.WordsState;
import com.codez.seed.TextFileSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class GameTests {
    private Game g;

    private WordsState sampleWS() {
        Map<String, String> wordsMap = new HashMap<>();
        wordsMap.put("a", "red");
        wordsMap.put("b", "red");
        wordsMap.put("c", "red");
        wordsMap.put("d", "red");
        wordsMap.put("e", "red");
        wordsMap.put("f", "red");
        wordsMap.put("g", "red");
        wordsMap.put("h", "red");
        wordsMap.put("i", "red");

        wordsMap.put("j", "blue");
        wordsMap.put("k", "blue");
        wordsMap.put("l", "blue");
        wordsMap.put("m", "blue");
        wordsMap.put("n", "blue");
        wordsMap.put("o", "blue");
        wordsMap.put("p", "blue");
        wordsMap.put("q", "blue");

        wordsMap.put("r", "black");

        wordsMap.put("s", "neutral");
        wordsMap.put("t", "neutral");
        wordsMap.put("u", "neutral");
        wordsMap.put("v", "neutral");
        wordsMap.put("w", "neutral");
        wordsMap.put("x", "neutral");
        wordsMap.put("y", "neutral");

        return new WordsState(wordsMap);
    }

    @Test
    @BeforeAll
    void setUpMocks(){
        TextFileSource mockedTFS = mock(TextFileSource.class);
        when(mockedTFS.createBoard()).thenReturn(sampleWS());

        Game.gb = new GameBuilder(mockedTFS);
        g = Game.createGame();

        assertEquals(0, g.getScore().get("red"));
        System.out.println(g.getScore());
    }

    @Test
    Game firstSpymasterMove() {
        Game ng = g.makeSpymasterTurn("example", 3);
        Map<String, String> turn = ng.getTurn();

        assertEquals("red", turn.get("teamTurn"));
        assertEquals("false", turn.get("spymasterTurn") );

        return ng;
    }

    @Test
    Game firstChooserMove() {
        Game ng = firstSpymasterMove().makeChooserMove("a");
        Map<String, String> turn = ng.getTurn();

        assertEquals(1, ng.getScore().get("red"));
        assertEquals(0, ng.getScore().get("blue"));
        assertEquals("2", turn.get("remainingGuesses"));

        return ng;
    }

    @Test
    Game nextWrongChooserMove() {
        Game ng = firstChooserMove().makeChooserMove("j");
        Map<String, String> turn = ng.getTurn();

        assertEquals(1, ng.getScore().get("blue"));
        assertEquals("0", turn.get("remainingGuesses"));
        assertEquals("blue", turn.get("teamTurn"));

        return ng;
    }



}
