package com.codez.tst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codez.game.WordsState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordsStateTests {
    WordsState ws;

    private Map<String, String > sampleBoard() {
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

        return wordsMap;
    }

    @BeforeAll
    @Test
    void newWS() {
        Map<String, String> wordsMap = sampleBoard();
        ws = new WordsState(wordsMap);
    }

    @Test
    void getWordsMap() {
        assertEquals(ws.getWordsMap(), sampleBoard());
    }

    @Test
    void getWordsValue() {
        assertEquals(ws.getWordValue("a"), "red");
        assertEquals(ws.getWordValue("j"), "blue");
        assertEquals(ws.getWordValue("r"), "black");
        assertEquals(ws.getWordValue("s"), "neutral");
    }

    @Test
    void chooseWord() {
        WordsState newWS = ws.chooseWord("a", "red");
        assertEquals(newWS.getWordValue("a"), "red-red");

        newWS = newWS.chooseWord("b", "blue");
        assertEquals(newWS.getWordValue("b"), "red-blue");
        newWS = newWS.chooseWord("b", "red");
        assertEquals(newWS.getWordValue("b"), "red-blue");

    }
}
