package com.codez.tst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codez.game.WordsState;
import com.codez.seed.TextFileSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@TODO: Mock Text File I/O

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeederTests {
    TextFileSource tfs;

    private Stream<String> wordStream() {
        String[] words = {"pasts",
                "kelpy",
                "bocce",
                "kicky",
                "taros",
                "lings",
                "dicky",
                "nerdy",
                "abend",
                "stela",
                "biggy",
                "laved",
                "baldy",
                "pubis",
                "gooks",
                "wonky",
                "stied",
                "hypos",
                " assed",
                "spumy",
                "osier",
                " roble",
                "rumba",
                "biffy",
                "pupal"};
        return Arrays.stream(words);
    }

    @Test
    @BeforeAll
    void seedStream(){
        tfs = new TextFileSource("/Users/denisstepanenko/Documents/codezrewrite/src/main/java/com/codez/seed/5lenwords.txt");

        String[] allWords = tfs.getAllWords().collect(Collectors.toList()).toArray(new String[1]);
        assertEquals(5757, allWords.length);
    }

    @Test
    void newBoard(){
        WordsState ws = tfs.createBoard();
    }
}
