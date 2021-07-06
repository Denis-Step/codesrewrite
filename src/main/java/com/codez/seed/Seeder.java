package com.codez.seed;

import com.codez.game.WordsState;
import java.util.stream.Stream;

public interface Seeder {

    // Returns all words in this source.

    public Stream<String> getAllWords();

    public WordsState createBoard();
}
